package com.jane.spring.study.helloworld.controllers.admin.user;

import com.jane.spring.study.helloworld.entities.User;
import com.jane.spring.study.helloworld.services.CountryService;
import com.jane.spring.study.helloworld.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Optional;

/**
 * Created by jane on 7/9/17.
 */
@Controller
@RequestMapping("/admin/users/{id}/edit-user")
public class EditUserController {

    private final UserService userService;
    private final CountryService countryService;

    @Autowired
    public EditUserController(UserService userService, CountryService countryService) {
        this.userService = userService;
        this.countryService = countryService;
    }

    @GetMapping
    public String get(Model model, @PathVariable(value = "id") Long id) {
        Optional<User> user = userService.getById(id);
        if (!user.isPresent()) {
            return "redirect:/admin/users";
        }
        User u = user.get();
        addCommonDataToModel(model, u.getId(), u.getFirstName(), u.getLastName(),
                u.getUsername(), u.getEmail(),
                u.getCountry(),
                u.getBirthDay().getYear(),
                u.getBirthDay().getDayOfMonth(),
                u.getBirthDay().getMonthValue());

        return "admin/edit-user";
    }

    @PostMapping
    public String post(Model model,
                       @RequestParam(value = "username", defaultValue = "", required = false) String username,
                       @RequestParam(value = "firstName", defaultValue = "", required = false) String firstName,
                       @RequestParam(value = "lastName", defaultValue = "", required = false) String lastName,
                       @PathVariable(value = "id") Long id,
                       @RequestParam(value = "year", defaultValue = "", required = false) int year,
                       @RequestParam(value = "month", defaultValue = "", required = false) int month,
                       @RequestParam(value = "day", defaultValue = "", required = false) int day,
                       @RequestParam(value = "country", defaultValue = "", required = false) String country,
                       @RequestParam(value = "email", defaultValue = "", required = false) String email) {
        Optional<User> user = userService.getById(id);
        if (!user.isPresent()) {
            return "redirect:/admin/users";
        }
        User userToEdit = user.get();
        LocalDate birthday;
        try {

            birthday = LocalDate.of(year, month, day);

            userToEdit.setFirstName(firstName);
            userToEdit.setLastName(lastName);
            userToEdit.setUsername(username);
            userToEdit.setEmail(email);
            userToEdit.setCountry(country);
            userToEdit.setBirthDay(birthday);
            userToEdit.setCountry(country);

            userService.update(userToEdit);
            return "redirect:/admin/users";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        addCommonDataToModel(model, user.get().getId(), user.get().getFirstName(), user.get().getLastName(),
                user.get().getUsername(), user.get().getEmail(), user.get().getCountry(), year, day, month);
        return "admin/edit-user";
    }

    @PostMapping(params = "cancel")
    public String cancelEdit() {
        return "redirect:/admin/users";
    }

    private void addCommonDataToModel(Model model, Long id, String firstName, String lastName, String username, String email,
                                      String country, int year, int day, int month) {
        model.addAttribute("currentYear", Year.now());
        model.addAttribute("firstName", firstName);
        model.addAttribute("id", id);
        model.addAttribute("lastName", lastName);
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("country", country);
        model.addAttribute("countries", countryService.list());
        if (day > 0) {
            model.addAttribute("day", day);
        }
        if (year > 0) {
            model.addAttribute("year", year);
        }

        model.addAttribute("month", month);
    }

}
