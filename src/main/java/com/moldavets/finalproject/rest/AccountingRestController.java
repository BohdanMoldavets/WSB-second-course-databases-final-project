package com.moldavets.finalproject.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountingRestController {

    @GetMapping("/")
    public String landingPage() {
        return "redirect:/employees/";
    }

}
