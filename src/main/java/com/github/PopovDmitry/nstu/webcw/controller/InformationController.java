package com.github.PopovDmitry.nstu.webcw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformationController {

    @GetMapping("/contacts")
    public String getContacts() {
        return "views/contacts";
    }
}
