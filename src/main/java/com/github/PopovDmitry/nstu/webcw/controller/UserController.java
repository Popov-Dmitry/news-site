package com.github.PopovDmitry.nstu.webcw.controller;

import com.github.PopovDmitry.nstu.webcw.model.User;
import com.github.PopovDmitry.nstu.webcw.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("/{id}/edit")
    public String getEditForm(@PathVariable long id, Model model) {
        logger.info("getEditForm for user with id {}", id);
        if (userService.getUser(id).isEmpty()) {
            logger.info("User with id {} is not found", id);
        }

        model.addAttribute("user", userService.getUser(id).get());
        return "views/editUser";
    }

    @PatchMapping("/{id}")
    public String editUser(@PathVariable long id, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        logger.info("editUser with id {}", id);
        if (bindingResult.hasErrors()) {
            logger.info("invalid user");
            return "";
        }

        userService.saveUser(user);
        return "redirect:/news";
    }
}
