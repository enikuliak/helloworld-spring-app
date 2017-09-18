package com.jane.spring.study.helloworld.services;

import com.jane.spring.study.helloworld.dao.CountryDao;
import com.jane.spring.study.helloworld.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by jane on 6/21/17.
 */
@Service
@Scope(value = "singleton")
public class CountryService {

    private static final Pattern ISO_CODE_PATTERN = Pattern.compile("^[A-Z]{2}$");

    private final CountryDao dao;

    @Autowired
    public CountryService(CountryDao dao) {
        this.dao = dao;

        this.dao.create(new Country("US", "United States"));
        this.dao.create(new Country("BY", "Belarus"));
        this.dao.create(new Country("GB", "United Kingdom"));
        this.dao.create(new Country("FR", "France"));
    }

    public Optional<Country> get(String iso) {
        return Optional.ofNullable(dao.get(iso));
    }

    public List<Country> list() {
        return dao.list();
    }

    public Country create(String iso, String name) {
        if (iso == null || !isValidIsoCode(iso) ||
                name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("ISO and name are expected.");
        }
        return this.dao.create(new Country(iso.toUpperCase(), name));
    }

    public Country update(Country country) {
        if (country.getName() == null || country.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name is expected");
        }
        if (country.getIso().isEmpty()) {
            throw new IllegalArgumentException("ISO is expected");
        }
        return this.dao.update(country);
    }

    public boolean remove(Country country) {
        return this.dao.remove(country);
    }

    public boolean isValidIsoCode(String isoCode) {
        return ISO_CODE_PATTERN.matcher(isoCode).matches();
    }


}
