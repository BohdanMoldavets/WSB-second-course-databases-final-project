package com.moldavets.finalproject.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("access-denied")
    public String getAccessDeniedPage() {
        return "access-denied";
    }

}
