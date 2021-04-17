package com.github.PopovDmitry.nstu.webcw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InformationController {

    private final Logger logger = LoggerFactory.getLogger(InformationController.class);

    @GetMapping("/contacts")
    public String getContacts() {
        logger.info("getContacts");
        return "views/contacts";
    }

    @GetMapping("/help/bb_codes")
    public String getBBHelp() {
        logger.info("getBBHelp");
        return "views/bbCodesHelp";
    }
}
