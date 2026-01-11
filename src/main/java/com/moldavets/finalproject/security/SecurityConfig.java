package com.moldavets.finalproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").permitAll()

                        .requestMatchers("/employees/").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/employees/save").hasRole("MANAGER")
                        .requestMatchers("/employees/update").hasRole("MANAGER")
                        .requestMatchers("/employees/updateForm").hasRole("MANAGER")
                        .requestMatchers("/employees/delete").hasRole("ADMIN")

                        .requestMatchers("/departments").hasRole("MANAGER")
                        .requestMatchers("/departments/save").hasRole("MANAGER")
                        .requestMatchers("/departments/update").hasRole("MANAGER")
                        .requestMatchers("/departments/updateForm").hasRole("MANAGER")
                        .requestMatchers("/departments/delete").hasRole("ADMIN")

                        .requestMatchers("/datestamps").hasRole("MANAGER")
                        .requestMatchers("/datestamps/save").hasRole("MANAGER")
                        .requestMatchers("/datestamps/update").hasRole("MANAGER")
                        .requestMatchers("/datestamps/updateForm").hasRole("MANAGER")
                        .requestMatchers("/datestamps/delete").hasRole("ADMIN")

                        .requestMatchers("/salaries/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
        ).formLogin(form ->
                form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticate")
                        .permitAll()
        ).logout(logout -> logout.permitAll().logoutSuccessUrl("/login?logout")
        ).exceptionHandling(
                configurer ->
                        configurer.accessDeniedPage("/access-denied")
        );
        return http.build();
    }
}
