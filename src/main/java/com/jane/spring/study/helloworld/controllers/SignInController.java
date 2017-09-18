package com.jane.spring.study.helloworld.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Year;

/**
 * Sign in controller.
 */
@Controller
@RequestMapping("/sign-in")
public class SignInController {

    @GetMapping
    public String get(Model model) {
        addCommonDataToModel(model, "", "");
        return "sign-in";
    }

    @PostMapping
    public String post(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            Model model
    ) {
        if ("root".equals(username) && "password123".equals(password)) {
            return "redirect:/user/" + username;
        }
        addCommonDataToModel(model, username, password);
        model.addAttribute("error", "invalidUsernameOrPassword");
        return "sign-in";
    }

    private void addCommonDataToModel(Model model, String username, String password) {
        model.addAttribute("currentYear", Year.now());
        model.addAttribute("username", username);
        model.addAttribute("password", password);
    }
}
