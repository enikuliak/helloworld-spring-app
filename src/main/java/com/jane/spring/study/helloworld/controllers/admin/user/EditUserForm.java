package com.jane.spring.study.helloworld.controllers.admin.user;

import com.jane.spring.study.helloworld.entities.User;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Edit user form. Data Transfer Object (DTO) between service layer and web layer: contains user data only visible to
 * edit user controller.
 */
public class EditUserForm {

    @NotNull
    @Min(1)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    @Min(1850)
    @Max(9999)
    private int year;

    @Min(1)
    @Max(12)
    private int month;

    @Min(1)
    @Max(31)
    private int day;

    @NotNull
    @Size(min = 2, max = 2)
    private String country;

    @NotNull
    @Size(min = 3, max = 50)
    @Email
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Creates user form buildNewFormFromUser given user.
     *
     * @param user User to create form buildNewFormFromUser.
     * @return Edit user form.
     */
    public static EditUserForm buildNewFormFromUser(User user) {
        EditUserForm form = new EditUserForm();
        form.setId(user.getId());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setUsername(user.getUsername());
        form.setEmail(user.getEmail());
        form.setCountry(user.getCountry());
        form.setYear(user.getBirthDay().getYear());
        form.setDay(user.getBirthDay().getDayOfMonth());
        form.setMonth(user.getBirthDay().getMonthValue());
        return form;
    }

    /**
     * Copies current form data to user.
     *
     * @param user User to copy data to.
     * @return User given as an argument to this method.
     */
    public User copyDataTo(User user) {
        // Note: there is no sense to copy id (also, for security reasons).
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setUsername(getUsername());
        user.setEmail(getEmail());
        user.setCountry(getCountry());
        user.setBirthDay(LocalDate.of(getYear(), getMonth(), getDay()));
        user.setCountry(getCountry());
        return user;
    }
}
