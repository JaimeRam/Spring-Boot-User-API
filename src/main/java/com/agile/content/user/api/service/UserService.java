package com.agile.content.user.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.agile.content.user.api.model.User;
import com.agile.content.user.api.model.UserRepository;

/**
 * This class manages some operations of the UserRepository @@Repository class.
 */
@Service
public class UserService {
    /**
     * The UserRepository.
     */
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * This method gets all users stored in the UserRepository.
     *
     * @return a List<User> with all users in the repository.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * This method generates a paginated user list.
     *
     * @param page number to show.
     * @param size of the page to show.
     * @return a page of the user repository.
     */
    public Page<User> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    /**
     * This method find a user in the UserRepository by its username.
     *
     * @param username of the user to find.
     * @return the user to find.
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsernameIs(username);
    }

    /**
     * This method stores an user class to the UserRepository.
     *
     * @param user to store.
     */
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * This class deletes a user by the username.
     *
     * @param username of the user to delete.
     */
    public void delete(String username) {
        userRepository.delete(userRepository.findByUsernameIs(username));
    }
}