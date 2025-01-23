package com.moldavets.finalproject.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(value = "continue", required = false) String paramContinue) {
        if(paramContinue != null) {
            return "redirect:/employees/";
        }
        return "login";
    }

    @GetMapping("access-denied")
    public String getAccessDeniedPage() {
        return "access-denied";
    }

}
