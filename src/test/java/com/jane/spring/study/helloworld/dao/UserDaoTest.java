package com.jane.spring.study.helloworld.dao;

import com.jane.spring.study.helloworld.entities.User;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Test for user DAO.
 */
public class UserDaoTest {

    private Random random;
    private UserDao userDao;

    @Before
    public void setUp() {
        this.random = new SecureRandom();
        this.userDao = new UserDao();

        // Create some users.
        this.userDao.create(buildNewUser("root@google.com", "root"));
        this.userDao.create(buildNewUser("admin@google.com", "admin"));
        userDao.create(buildNewUser("e@google.com", "root"));
        userDao.create(buildNewUser("a@google.com", "guest"));
        userDao.create(buildNewUser("root@google.com", "he"));
        userDao.create(buildNewUser("b@google.com", "she"));
        userDao.create(buildNewUser("root@gmail.com", "me"));
    }

    @Test
    public void testCreateReadUpdateDelete() {
        User testUser = buildNewUser("jane@google.com", "jane");

        userDao.create(testUser);
        assertNotNull("Primary key must exist", testUser.getId());

        assertNull("User with next primary must not exist.", userDao.get(testUser.getId() + 1L));

        testUser.setUsername("jane_update_2");

        User testUserInStorage = userDao.get(testUser.getId());
        assertEquals("jane_update_2", testUser.getUsername());
        assertEquals("jane", testUserInStorage.getUsername());

        userDao.update(testUser);

        testUserInStorage = userDao.get(testUser.getId());
        assertEquals("jane_update_2", testUserInStorage.getUsername());

        assertTrue("Deletion deletes existing user.", userDao.remove(testUser.getId()));
        assertFalse("Deletion ignores non-existing user.", userDao.remove(testUser.getId() + 1L));
    }

    @Test
    public void testCount() {
        assertEquals(7, userDao.count());
    }

    @Test
    public void testSearch() {
        List<User> users = userDao.search("root");
        assertEquals(4, users.size());
    }

    @Test
    public void testSearchFirst() {
        Optional<User> searchResult = userDao.searchFirst("root");
        assertTrue("User is found", searchResult.isPresent());

        searchResult = userDao.searchFirst("root_that_doesnot_exist");
        assertFalse("User is not found", searchResult.isPresent());
    }


    private User buildNewUser(String email, String username) {
        User user = new User();

        user.setEmail(email);
        user.setUsername(username);
        user.setBirthDay(1901 + random.nextInt(100), 1 + random.nextInt(12), 1 + random.nextInt(28));
        user.setFirstName("Jane" + random.nextInt(100));
        user.setLastName("Nick" + random.nextInt(100));

        return user;
    }
}
