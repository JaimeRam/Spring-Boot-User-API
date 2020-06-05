package com.agile.content.user.api.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Repository class to manage the system's users.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    /**
     * Get the user from user's repository.
     *
     * @param username of the user.
     * @return User class.
     */
    User findByUsernameIs(String username);

    /**
     * Get a random user from user's repository.
     *
     * @return User class (a random user).
     */
    @Query(value = "SELECT * FROM USERS ORDER BY RAND() LIMIT 1", nativeQuery = true)
    User getRandomUser();

    /**
     * Get all users in the repository as List<User> class.
     *
     * @return the List of users.
     */
    List<User> findAll();
}