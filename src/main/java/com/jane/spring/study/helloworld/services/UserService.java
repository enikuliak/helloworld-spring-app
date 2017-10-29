package com.jane.spring.study.helloworld.services;

import com.jane.spring.study.helloworld.dao.UserDao;
import com.jane.spring.study.helloworld.entities.User;
import com.jane.spring.study.helloworld.pagination.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Business logic for {@link User} entity.
 */
@Service
@Scope(value = "singleton")
public class UserService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$");

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-z]+[a-z0-9._-]*[a-z0-9]+$");

    private final UserDao dao;

    @Autowired
    public UserService(UserDao dao) {
        this.dao = dao;

        for (int i = 0; i < 176; i++) {
            User user = new User();
            user.setFirstName("Jane");
            user.setLastName("Nik");
            user.setUsername("enikuliak" + i);
            user.setEmail("enikuliak@gmail.com" + i);
            user.setBirthDay(LocalDate.of(1983, 6, 26));
            user.setRegistrationDateTime(ZonedDateTime.now());
            this.dao.create(user);

            user = new User();
            user.setFirstName("Alexis");
            user.setLastName("Nik");
            user.setUsername("anikuliak" + i);
            user.setEmail("nobullet@gmail.com" + i);
            user.setBirthDay(LocalDate.of(1982, 9, 2));
            user.setRegistrationDateTime(ZonedDateTime.now());
            this.dao.create(user);

            user = new User();
            user.setFirstName("Dasha");
            user.setLastName("Nik");
            user.setUsername("dnikuliak" + i);
            user.setEmail("dnikuliak@gmail.com" + i);
            user.setBirthDay(LocalDate.of(2009, 2, 25));
            user.setRegistrationDateTime(ZonedDateTime.now());
            this.dao.create(user);
        }
    }

    public User update(User userToUpdate) throws ValidationException {
        validateUser(userToUpdate);

        User userInDatabase = this.getById(userToUpdate.getId())
                .orElseThrow(() ->
                        new ValidationException(String.format("User id:%d was not found ", userToUpdate.getId())));

        if (!userInDatabase.getUsername().equals(userToUpdate.getUsername())) {
            // Username was changed: lookup by new username.
            if (getByUsername(userToUpdate.getUsername()).isPresent()) {
                throw new ValidationException(
                        String.format("User with username %s exists.", userToUpdate.getUsername()));
            }

        }

        if (!userInDatabase.getEmail().equals(userToUpdate.getEmail())) {
            // Email was changed: lookup by new email.
            if (getByEmail(userToUpdate.getEmail()).isPresent()) {
                throw new ValidationException(
                        String.format("User with email %s exists.", userToUpdate.getEmail()));
            }
        }

        return this.dao.update(userToUpdate);
    }

    public boolean emailExist(final String email) {
        return getByUsername(email).isPresent();
    }

    public boolean userNameExist(final String username) {
        return getByUsername(username).isPresent();
    }

    public PaginationResult<User> search(String search, int offset, int perPage) {
        List<User> usersOnPage = dao.search(search, null, offset, perPage);
        long allUsers = dao.count(search);

        long currentPage = offset / perPage + 1;
        long totalPages = allUsers / perPage;
        if (allUsers % perPage > 0) {
            totalPages += 1;
        }
        return new PaginationResult<>(usersOnPage, currentPage, totalPages);
    }

    /**
     * Returns a user by email or username and password.
     *
     * @param emailOrUsername Email or username.
     * @param password        Password.
     * @return Optional if there is a user that matches given arguments.
     */
    public Optional<User> getBy(String emailOrUsername, String password) {
        Optional<User> result;
        if (emailOrUsername.indexOf('@') >= 0) {
            result = getByEmail(emailOrUsername);
        } else {
            result = getByUsername(emailOrUsername);
        }
        if (result.isPresent() && result.get().getPassword().equals(password)) {
            return result;
        }
        return Optional.empty();
    }

    public Optional<User> getById(Long id) {
        return Optional.ofNullable(dao.get(id));
    }

    public Optional<User> getByUsername(String username) {
        return dao.getByUsername(username.trim().toLowerCase());
    }

    public Optional<User> getByEmail(String email) {
        return dao.getByEmail(email.trim().toLowerCase());
    }

    public boolean remove(User user) {
        return this.dao.remove(user.getId());
    }

    /**
     * Registers user in database.
     *
     * @param user User to register.
     * @return Created user.
     * @throws ValidationException Validation exception in case of invalid data.
     */
    public User signUp(User user, String repeatedPassword) throws ValidationException {
        validateUser(user);
        if (user.getId() != null) {
            throw new ValidationException(String.format("Given user id=%d exists.", user.getId()));
        }
        if (user.getPassword() == null || user.getGender() == null) {
            throw new ValidationException("Password and gender are expected.");
        }
        if (user.getPassword().isEmpty() || !user.getPassword().equals(repeatedPassword)) {
            throw new ValidationException("Passwords don't match.");
        }
        if (getByUsername(user.getUsername()).isPresent()) {
            throw new ValidationException(String.format("User with username %s exists", user.getUsername()));
        }
        if (getByEmail(user.getEmail()).isPresent()) {
            throw new ValidationException(String.format("User with email %s exists", user.getEmail()));
        }
        return dao.create(user);
    }

    public void validateUser(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("Null is not expected.");
        }
        if (user.getEmail() == null || user.getUsername() == null) {
            throw new ValidationException("Email and username are expected.");
        }
        if (user.getFirstName() == null || user.getLastName() == null || user.getBirthDay() == null) {
            throw new ValidationException("Name and birthday are expected.");
        }
        if (!user.getBirthDay().isBefore(LocalDate.now().minus(18, ChronoUnit.YEARS))) {
            throw new ValidationException("18+ years old users are expected.");
        }
        user.setEmail(user.getEmail().trim().toLowerCase());
        user.setUsername(user.getUsername().trim().toLowerCase());
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new ValidationException("Email has invalid format.");
        }
        if (!USERNAME_PATTERN.matcher(user.getUsername()).matches()) {
            throw new ValidationException("Username has invalid format.");
        }
    }
}
