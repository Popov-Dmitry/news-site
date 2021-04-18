package com.github.PopovDmitry.nstu.webcw.controller;

import com.github.PopovDmitry.nstu.webcw.model.Role;
import com.github.PopovDmitry.nstu.webcw.model.Status;
import com.github.PopovDmitry.nstu.webcw.model.User;
import com.github.PopovDmitry.nstu.webcw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(UserService userService) { this.userService = userService; }

    @GetMapping("/auth")
    public String login() {
        logger.info("login");
        return "views/signIn";
    }

    @GetMapping("/auth/reg")
    public String getRegPage(Model model) {
        logger.info("getRegPage");
        model.addAttribute("user", new User());
        return "views/signUp";
    }

    @PostMapping("/auth/reg")
    public String reg(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        logger.info("reg");
        if(bindingResult.hasErrors()) {
            logger.info("Invalid user");
            return "views/signUp";
        }

        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userService.saveUser(user);
        return "redirect:/auth";
    }
}
