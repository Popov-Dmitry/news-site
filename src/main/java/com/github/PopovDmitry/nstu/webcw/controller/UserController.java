package com.github.PopovDmitry.nstu.webcw.controller;

import com.github.PopovDmitry.nstu.webcw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/{id}")
    public String getUser(@PathVariable long id, Model model) {
        logger.info("getUser with id {}", id);
        if(userService.getUser(id).isEmpty()) {
            logger.info("getUser with id {} is not found", id);
            return "redirect:/news";
        }

        model.addAttribute("title", "Пользователь " + userService.getUser(id).get().getFullName());
        model.addAttribute("user", userService.getUser(id).get());
        return "views/showUser";
    }
}
