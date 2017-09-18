package com.jane.spring.study.helloworld.controllers.admin.user;

import com.jane.spring.study.helloworld.entities.User;
import com.jane.spring.study.helloworld.pagination.PaginationResult;
import com.jane.spring.study.helloworld.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * User controller
 */
@Scope(value = "singleton")
@Controller
@RequestMapping("/admin/users")
public class UsersController {

    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final int USERS_PER_PAGE = 20;


    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String get(Model model,
                      @RequestParam(required = false) String query,
                      @RequestParam(required = false) Integer page) {

        page = page == null || page <= 0 ? 1 : page;
        String nonNullQuery = query == null ? "" : query;

        PaginationResult<User> users = userService.search(
                nonNullQuery,
                (page - 1) * USERS_PER_PAGE,
                USERS_PER_PAGE
        );

        model.addAttribute("users", users.getEntities());
        model.addAttribute("currentPage", page);
        model.addAttribute("pagination", users);
        model.addAttribute("query", nonNullQuery);
        model.addAttribute("currentYear", Year.now());
        model.addAttribute("dateTimeFormatter", DATE_TIME_FORMATTER);

        return "admin/users";
    }

    @PostMapping
    public String remove(@RequestParam("user") Long id) {
        Optional<User> user = userService.getById(id);
        if (!user.isPresent()) {
            return "redirect:/admin/users";
        }
        userService.remove(user.get());
        return "redirect:/admin/users";
    }
}
