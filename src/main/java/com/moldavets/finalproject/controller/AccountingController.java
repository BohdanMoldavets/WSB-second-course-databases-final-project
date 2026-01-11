package com.moldavets.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountingController {

    @GetMapping("/")
    public String landingPage() {
        return "redirect:/employees/";
    }
}
