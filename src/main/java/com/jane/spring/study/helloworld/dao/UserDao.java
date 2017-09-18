package com.jane.spring.study.helloworld.dao;

import com.jane.spring.study.helloworld.entities.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * User data access object.
 */
@Component
@Scope(value = "singleton")
public class UserDao {

    private AtomicLong primaryKeyGenerator;
    private ConcurrentMap<Long, User> users;

    public UserDao() {
        this.users = new ConcurrentHashMap<>();
        this.primaryKeyGenerator = new AtomicLong(10000L);
    }

    public User get(long id) {
        User existingUser = users.get(id);
        return existingUser != null ? existingUser.copy() : null; // Thread-safe.
    }

    public User create(User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("Unable to create existent user. Try calling update.");
        }

        long newId = primaryKeyGenerator.incrementAndGet(); // Thread-safe.

        User copy = user.copy();
        copy.setId(newId); // Copy saved internally.
        user.setId(newId); // Caller can read primary key value created by storage.

        users.put(newId, copy); // Thread-safe.

        return user;
    }

    public User update(User user) {

        Long existingUserId = user.getId();
        if (existingUserId == null) {
            throw new IllegalArgumentException("Unable to update non-existent user.");
        }
        if (!users.containsKey(existingUserId)) {
            throw new IllegalArgumentException("Unable to update non-existent user: " + existingUserId);
        }
        users.put(existingUserId, user.copy()); // Thread-safe.
        return user;
    }

    public boolean remove(long id) {
        return users.remove(id) != null; // Thread-safe.
    }

    public Optional<User> getByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().startsWith(email))
                .findFirst();
    }

    public Optional<User> getByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().startsWith(username))
                .findFirst();
    }

    public Optional<User> searchFirst(String search) {
        List<User> result = search(search, null, 0, 1);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get(0));
    }

    public List<User> list(int start, int count) {
        return search(null, null, start, count);
    }

    public List<User> search(String search) {
        return search(search, "id", 0, Integer.MAX_VALUE);
    }

    public List<User> search(String search, String sortField, int start, int count) {
        return users.values().stream()
                .filter(getPredicateForSearch(search))
                .skip(start)
                .limit(count)
                .sorted(getComparatorForSearch(sortField))
                .collect(Collectors.toList());
    }

    public long count() {
        return count(null);
    }

    public long count(String search) {
        if (search == null) {
            return users.size();
        }
        return users.values().stream()
                .filter(getPredicateForSearch(search))
                .count();
    }

    private static Predicate<User> getPredicateForSearch(String search) {
        if (search == null || search.isEmpty()) {
            return user -> true;
        }
        return
                user -> user.getUsername().contains(search) ||
                        user.getEmail().contains(search) ||
                        user.getFirstName().contains(search) ||
                        user.getLastName().contains(search) ||
                        user.getCountry() != null && user.getCountry().equals(search);
    }

    private static Comparator<User> getComparatorForSearch(String sortField) {
        Comparator<User> userComparator = SORTS.get(sortField);
        return userComparator == null ? DEFAULT_COMPARATOR : userComparator;
    }

    private static Map<String, Comparator<User>> SORTS = new HashMap<>();

    static {
        SORTS.put("id", Comparator.comparing(User::getId));
        SORTS.put("email", Comparator.comparing(User::getEmail));
        SORTS.put("username", Comparator.comparing(User::getUsername));
        SORTS.put("firstName", Comparator.comparing(User::getFirstName));
        SORTS.put("lastName", Comparator.comparing(User::getLastName));
        SORTS = Collections.unmodifiableMap(SORTS);
    }

    private static final Comparator<User> DEFAULT_COMPARATOR = (u1, u2) -> 0;
}
