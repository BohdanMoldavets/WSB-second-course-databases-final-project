package com.moldavets.finalproject.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountingRestController {

    @GetMapping("/app")
    public String app() {
        return "index";
    }

}
