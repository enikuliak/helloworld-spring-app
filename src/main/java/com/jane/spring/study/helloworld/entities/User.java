package com.jane.spring.study.helloworld.entities;


import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * User entity.
 */
public class User {

    /**
     * Primary key.
     */
    private Long id;

    private String firstName;
    private String lastName;

    private String username;

    private Gender gender;
    private String email;
    private String country;
    private String phone;

    private String password;

    private LocalDate birthDay;

    private ZonedDateTime registrationDateTime;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthDay(int year, int month, int day) {
        this.setBirthDay(LocalDate.of(year, month, day));

    }

    public ZonedDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(ZonedDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    public User copy() {
        User copy = new User();
        copy.setId(getId());
        copy.setEmail(getEmail());
        copy.setFirstName(getFirstName());
        copy.setLastName(getLastName());
        copy.setUsername(getUsername());
        copy.setBirthDay(getBirthDay());
        copy.setGender(getGender());
        copy.setPhone(getPhone());
        copy.setPassword(getPassword());
        copy.setCountry(getCountry());
        copy.setRegistrationDateTime(getRegistrationDateTime());
        return copy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
