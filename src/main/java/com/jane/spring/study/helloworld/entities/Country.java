package com.jane.spring.study.helloworld.entities;

/**
 * Created by jane on 6/15/17.
 */
public class Country {

    private String iso;

    private String name;

    public Country() {
    }

    public Country(String iso, String name) {
        this.iso = iso;
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country copy() {
        Country countryCopy = new Country();
        countryCopy.setName(getName());
        countryCopy.setIso(getIso());
        return countryCopy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Country country = (Country) o;
        return iso == country.iso;
    }

    @Override
    public int hashCode() {
        return 13 * iso.hashCode();
    }
}
