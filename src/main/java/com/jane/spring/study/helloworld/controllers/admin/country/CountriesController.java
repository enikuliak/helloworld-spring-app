package com.jane.spring.study.helloworld.controllers.admin.country;

import com.jane.spring.study.helloworld.entities.Country;
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
import java.util.Optional;

/**
 * Countries controller.
 */
@Scope(value = "singleton")
@Controller
@RequestMapping("/admin/countries")
public class CountriesController {

    private final CountryService countryService;

    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String get(Model model) {
        addCommonData(model);
        return "admin/countries";
    }

    @PostMapping
    public String remove(@RequestParam("country") String iso) {
        Optional<Country> country = countryService.get(iso);
        if (!country.isPresent()) {
            return "redirect:/admin/countries";
        }
        countryService.remove(country.get());
        return "redirect:/admin/countries";
    }

    private void addCommonData(Model model) {
        model.addAttribute("countries", countryService.list());
        model.addAttribute("currentYear", Year.now());
    }
}
