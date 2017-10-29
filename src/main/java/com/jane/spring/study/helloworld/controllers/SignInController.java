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

    // TODO(enikuliak): Remove dummy constants after introduction of authentication.
    private static final String DUMMY_USERNAME = "root";
    private static final String DUMMY_PASSWORD = "password123";

    /**
     * Handles GET request returning HTML page with sign in form.
     *
     * @param model Model for the template.
     * @return Template name to render.
     */
    @GetMapping
    public String get(Model model) {
        addCommonDataToModel(model, "", "");
        return "sign-in";
    }

    /**
     * Handles POST request accepting username and password for user authentication.
     *
     * @param username Username entered by a user.
     * @param password Password entered by a user.
     * @param model Model for the tempate.
     * @return Template name to render or redirect action in case of successful authentication.
     */
    @PostMapping
    public String post(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password", required = false) String password,
            Model model
    ) {
        // TODO(enikuliak): Read user and credentials from datastore.
        if (DUMMY_USERNAME.equals(username) && DUMMY_PASSWORD.equals(password)) {
            // TODO(enikuliak): create encrypted authenticated cookie.
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
