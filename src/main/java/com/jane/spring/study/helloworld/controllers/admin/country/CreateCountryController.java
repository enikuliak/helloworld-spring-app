package com.jane.spring.study.helloworld.controllers.admin.country;

import com.jane.spring.study.helloworld.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Year;

/**
 * Create country controller.
 */
@Scope(value = "singleton")
@Controller
@RequestMapping("/admin/create-country")
public class CreateCountryController {

    private final CountryService countryService;

    @Autowired
    public CreateCountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String get(Model model) {
        addCommonDataModel(model, "", "");
        return "admin/create-country";
    }

    @PostMapping
    public String post(
            @RequestParam(value = "iso", defaultValue = "", required = false) String iso,
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            Model model) {
        iso = iso.toUpperCase();
        addCommonDataModel(model, iso, name);
        try {
            countryService.create(iso, name);
            return "redirect:/admin/countries";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "admin/create-country";
    }

    @PostMapping(params = "cancel")
    public String cancelCreateCountry(){
        return "redirect:/admin/countries";
    }



    private void addCommonDataModel(Model model, String iso, String name) {
        model.addAttribute("iso", iso);
        model.addAttribute("name", name);
        model.addAttribute("currentYear", Year.now());
    }
}
