package com.jane.spring.study.helloworld.controllers;

import com.jane.spring.study.helloworld.entities.Gender;
import com.jane.spring.study.helloworld.entities.User;
import com.jane.spring.study.helloworld.services.CountryService;
import com.jane.spring.study.helloworld.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.Year;

/**
 * Sign up controller.
 */
@Scope(value = "singleton")
@Controller
@RequestMapping("/sign-up")
public class SignUpController {

    private final UserService userService;
    private final CountryService countryService;

    @Autowired
    public SignUpController(UserService userService, CountryService countryService) {
        this.userService = userService;
        this.countryService = countryService;
    }

    @GetMapping
    public String get(Model model) {
        addCommonDataToModel(model, "", "", "", "", "", "", "", 0, 0, 0);
        return "sign-up";
    }

    @PostMapping
    public String post(Model model,
                       @RequestParam(required = false) String firstName,
                       @RequestParam(required = false) String lastName,
                       @RequestParam(required = false) String username,
                       @RequestParam(required = false) String gender,
                       @RequestParam(required = false) String email,
                       @RequestParam(required = false) String country,
                       @RequestParam(required = false) String phone,
                       @RequestParam(required = false, defaultValue = "0") int year,
                       @RequestParam(required = false, defaultValue = "0") int day,
                       @RequestParam(required = false, defaultValue = "0") int month,
                       @RequestParam(required = false) String password,
                       @RequestParam(required = false) String repeatPassword) {
        addCommonDataToModel(model, firstName, lastName, username, gender, email, country, phone, year, day, month);
        LocalDate birthdate;
        try {
            birthdate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sign-up";
        }
        Gender genderEnum;
        try {
            genderEnum = Gender.valueOf(gender);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "sign-up";
        }
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setBirthDay(birthdate);
        user.setGender(genderEnum);
        user.setPhone(phone);
        user.setCountry(country);
        user.setPassword(password);
        try {
            userService.signUp(user, repeatPassword);
            return "redirect:/sign-in?success=yes";
        } catch (ValidationException ve) {
            model.addAttribute("error", ve.getMessage());
        }
        return "sign-up";
    }

    private void addCommonDataToModel(
            Model model,
            String firstName,
            String lastName,
            String username,
            String gender,
            String email,
            String country,
            String phone,
            int year,
            int day,
            int month) {
        model.addAttribute("username", username);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        model.addAttribute("gender", gender);
        model.addAttribute("country", country);
        model.addAttribute("countries", countryService.list());

        if (day > 0) {
            model.addAttribute("day", day);
        }
        model.addAttribute("month", month);
        if (year > 0) {
            model.addAttribute("year", year);
        }

        model.addAttribute("currentYear", Year.now());
    }
}
