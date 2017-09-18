package com.jane.spring.study.helloworld.dao;

import com.jane.spring.study.helloworld.entities.Country;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@Component
@Scope(value = "singleton")
public class CountryDao {

    private ConcurrentMap<String, Country> countries;

    public CountryDao() {
        countries = new ConcurrentHashMap<>();
    }

    public Country get(String iso) {
        Country existingCountry = countries.get(iso);
        return existingCountry != null ? existingCountry.copy() : null;
    }

    public Country create(Country country) {
        validate(country);
        if (countries.containsKey(country.getIso())) {
            throw new IllegalArgumentException("Country exists: " + country.getIso());
        }
        Country copy = country.copy();
        countries.put(copy.getIso(), copy);
        return country;
    }

    public Country update(Country country) {
        validate(country);
        String existingCountryIso = country.getIso();
        if (!countries.containsKey(existingCountryIso)) {
            throw new IllegalArgumentException("Unable to update non-existent country");
        }
        countries.put(existingCountryIso, country.copy());
        return country;
    }

    public boolean remove(Country country) {
        return countries.remove(country.getIso()) == null;
    }

    public List<Country> list() {
        List<Country> list = new ArrayList<>(countries.size());

        for (Map.Entry<String, Country> entry : countries.entrySet()) {
            list.add(entry.getValue().copy());
        }

        Comparator<Country> comparator =
                (country1, country2) -> country1.getName().compareTo(country2.getName());

        Collections.sort(list, comparator);
        return list;
    }

    private void validate(Country country) {
        if (country.getIso() == null || country.getName() == null) {
            throw new IllegalArgumentException("Unable to create country with null fields.");
        }
    }

}



