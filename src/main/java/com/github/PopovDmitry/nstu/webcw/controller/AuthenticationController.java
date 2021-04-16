package com.github.PopovDmitry.nstu.webcw.controller;

import com.github.PopovDmitry.nstu.webcw.dto.AuthenticationRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String login(Model model) {
//        model.addAttribute("request", new AuthenticationRequestDTO());
        return "views/signIn";
    }
}
