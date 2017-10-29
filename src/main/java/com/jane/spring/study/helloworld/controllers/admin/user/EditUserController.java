package com.jane.spring.study.helloworld.controllers.admin.user;

import com.jane.spring.study.helloworld.entities.User;
import com.jane.spring.study.helloworld.services.CountryService;
import com.jane.spring.study.helloworld.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.Year;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Edit user controller.
 */
@Controller
@RequestMapping("/admin/users/{id}/edit-user")
public class EditUserController {

    private final UserService userService;
    private final CountryService countryService;

    /**
     * Constructs edit user controller.
     *
     * @param userService    User service.
     * @param countryService Country service.
     */
    @Autowired
    public EditUserController(UserService userService, CountryService countryService) {
        this.userService = userService;
        this.countryService = countryService;
    }

    /**
     * Renders user edit form or redirects to the list of users controller in case if user was not found.
     *
     * @param model Model for the form.
     * @param id    User id.
     * @return Template for the edit user or redirect to the list of users controller in case if user was not found.
     */
    @GetMapping
    public String get(Model model, @PathVariable(value = "id") Long id) {
        Optional<User> userOptional = userService.getById(id);
        // In case of absent user - redirect to users page.
        if (!userOptional.isPresent()) {
            return "redirect:/admin/users";
        }
        User user = userOptional.get();

        addCommonDataToModel(model, EditUserForm.buildNewFormFromUser(user));

        // Render the form.
        return "admin/edit-user";
    }

    /**
     * Tries to edit existing user with values from the edit user form {@link EditUserForm}. Redirects
     * to the list of user in case of success or re-renders the form with error in the model.
     *
     * @param editUserForm  Edit user form.
     * @param bindingResult Binding result with detailed error information.
     * @param model         Model to populate.
     * @return Redirect string to the list of users or template to re-render.
     */
    @PostMapping
    public String post(@Valid EditUserForm editUserForm, BindingResult bindingResult, Model model) {
        // In case of errors re-display the form.
        if (bindingResult.hasErrors()) {
            addCommonDataToModel(model, editUserForm);
            // Collect all errors in string to display on the top of the form.
            model.addAttribute("error",
                    bindingResult.getFieldErrors()
                            .stream()
                            .map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
                            .collect(Collectors.toList())
                            .toString());
            // Collect a map of errors: fieldName -> fieldError.
            model.addAttribute("errorMap",
                    bindingResult.getFieldErrors()
                            .stream()
                            .collect(Collectors.toMap(fieldError -> fieldError.getField(), fieldError -> fieldError)));
            return "admin/edit-user";
        }

        Optional<User> userOptional = userService.getById(editUserForm.getId());
        // In case of absent user - redirect to users page.
        if (!userOptional.isPresent()) {
            return "redirect:/admin/users";
        }
        User user = userOptional.get();
        // Try to update the user in storage and redirect to the list of the users.
        try {
            userService.update(editUserForm.copyDataTo(user));
            return "redirect:/admin/users";
        } catch (Exception e) {
            // Display the error.
            model.addAttribute("error", e.getMessage());
            addCommonDataToModel(model, editUserForm);
        }
        // Re-render the form.
        return "admin/edit-user";
    }

    /**
     * Cancels form submission and redirects back to the list of users.
     *
     * @return Redirect string to the list of users.
     */
    @PostMapping(params = "cancel")
    public String cancelEdit() {
        return "redirect:/admin/users";
    }

    /**
     * Adds given user and common data to the controller model.
     *
     * @param model Model to add common data to.
     * @param user  User form to add to model.
     */
    private void addCommonDataToModel(Model model, EditUserForm user) {
        model.addAttribute("user", user);
        model.addAttribute("currentYear", Year.now());
        model.addAttribute("countries", countryService.list());
        model.addAttribute("errorMap", Collections.emptyMap());
    }
}
