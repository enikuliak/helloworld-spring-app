package com.jane.spring.study.helloworld.controllers.admin.country;

import com.jane.spring.study.helloworld.entities.Country;
import com.jane.spring.study.helloworld.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Year;
import java.util.Optional;

/**
 * Created by jane on 6/24/17.
 */
@Controller
@RequestMapping("/admin/countries/{iso}/edit-country")
public class EditCountryController {

    private final CountryService countryService;

    @Autowired
    public EditCountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String get(Model model, @PathVariable(value = "iso") String iso) {
        Optional<Country> country = countryService.get(iso);
        if (!country.isPresent()) {
            return "redirect:/admin/countries";
        }
        addCommonDataToModel(model, country.get().getIso(), country.get().getName());
        return "admin/edit-country";
    }

    @PostMapping
    public String post(Model model,
                       @PathVariable(value = "iso") String iso,
                       @RequestParam(value = "name", defaultValue = "", required = false) String name) {
        Optional<Country> country = countryService.get(iso);
        if (!country.isPresent()) {
            return "redirect:/admin/countries";
        }
        Country countryToEdit = country.get();
        try {
            countryToEdit.setName(name);
            countryService.update(countryToEdit);
            return "redirect:/admin/countries";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        addCommonDataToModel(model, country.get().getIso(), country.get().getName());
        return "admin/edit-country";
    }

    @PostMapping(params = "cancel")
    public String cancelEdit() {
        return "redirect:/admin/countries";
    }

    private void addCommonDataToModel(Model model, String iso, String name) {
        model.addAttribute("currentYear", Year.now());
        model.addAttribute("name", name);
        model.addAttribute("iso", iso);
    }
}
