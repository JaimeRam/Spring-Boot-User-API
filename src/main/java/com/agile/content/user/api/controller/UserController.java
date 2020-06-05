package com.agile.content.user.api.controller;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.agile.content.user.api.model.User;
import com.agile.content.user.api.service.RandomUserAPIService;
import com.agile.content.user.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This class is a @RestController that manages all API operations with the users.
 */
@RestController
@Api(value = "User Management System", description = "Operations for the User Management System")
public class UserController {

    /**
     * User service.
     */
    private final UserService userService;

    /**
     * Random User Generator API Service.
     */
    private final RandomUserAPIService randomUserAPIService;

    public UserController(UserService userService, RandomUserAPIService randomUserAPIService) {
        this.userService = userService;
        this.randomUserAPIService = randomUserAPIService;
    }

    /**
     * Get the list of users in the system.
     *
     * @param page [optional] the number of the page to show.
     * @param size [optional] the size of items to show in each page.
     * @return the list of active users in the database.
     */
    @ApiOperation(value = "View a list of available users.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "The retrieved list is empty")
    })
    @GetMapping("/user")
    public ResponseEntity<Object> getAllUsers(
            @RequestParam(value = "page", required = false)
                    Integer page,
            @RequestParam(value = "size", required = false)
                    Integer size) {

        if (page != null && size != null) {

            // The way to get all users by Pagination

            Page<User> usersPage = userService.getAllUsers(page - 1, size);
            if (usersPage.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok().body(usersPage);
            }

        } else {

            // The way to get all users without Pagination

            List<User> userList = userService.getAllUsers();
            if (userList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok().body(userList);
            }
        }
    }

    /**
     * Get the information about an User.
     *
     * @param username of the requested User.
     * @return an User (with all User fields) created or an ApiError message if the user is not found.
     */
    @ApiOperation(value = "View the user's information.", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user data"),
            @ApiResponse(code = 404, message = "The user was not found")
    })
    @GetMapping("/user/{username}")
    public ResponseEntity<Object> getUser(
            @PathVariable("username")
                    String username) {

        User userToFind = userService.getUserByUsername(username);

        if (userToFind != null) {
            return ResponseEntity.ok().body(userToFind);
        } else {
            ApiError apiError =
                    new ApiError(HttpStatus.NOT_FOUND, "The user \"" + username + "\" doesn't exists.", "");
            return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        }
    }

    /**
     * Create a new User in the system.
     *
     * @param newUser the user as JSON object with all fields.
     * @return a ResponseEntity<Object> of the call result.
     */
    @ApiOperation(value = "Create a user in the system.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created user"),
            @ApiResponse(code = 409, message = "Duplicated user")
    })
    @PostMapping("/user")
    public ResponseEntity<Object> createUser(
            @RequestBody
                    User newUser) {
        User oldUser = userService.getUserByUsername(newUser.getUsername());

        if (oldUser != null) {
            ApiError apiError =
                    new ApiError(HttpStatus.CONFLICT, "The user \"" + oldUser.getUsername() + "\" already exists.", "");
            return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        } else {
            userService.saveUser(newUser);
            return new ResponseEntity<>("The user \"" + newUser.getUsername() + "\" has been created.", HttpStatus.CREATED);
        }
    }

    /**
     * Delete an User from the system.
     *
     * @param username of the User to delete.
     * @return ResponseEntity.ok() if the user has been deleted, ApiError object if the user to delete it doesn't exists.
     */
    @ApiOperation(value = "Delete a user in the system.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted user"),
            @ApiResponse(code = 404, message = "The user was not found")
    })
    @DeleteMapping("/user/{username}")
    public ResponseEntity<Object> deleteUser(
            @PathVariable("username")
                    String username) {
        User userToDelete = userService.getUserByUsername(username);

        if (userToDelete != null) {
            userService.delete(userToDelete.getUsername());
            return ResponseEntity.ok("The user \"" + username + "\" has been deleted.");
        } else {
            ApiError apiError =
                    new ApiError(HttpStatus.NOT_FOUND, "The user \"" + username + "\" doesn't exist.", "");
            return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        }
    }

    /**
     * Update information data about an User.
     *
     * @param username     of the User to edit.
     * @param modifiedUser User object with the new fields to update.
     * @return ResponseEntity.ok() if the user has been updated, ApiError object if the user to delete it doesn't exists.
     */
    @ApiOperation(value = "Update some user data in the system.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user data"),
            @ApiResponse(code = 404, message = "The user was not found")
    })
    @PutMapping("/user/{username}")
    public ResponseEntity<Object> updateUser(
            @PathVariable("username")
                    String username,
            @RequestBody
                    User modifiedUser) {

        User oldUser = userService.getUserByUsername(username);

        if (oldUser != null) {
            oldUser.setName(modifiedUser.getName());
            oldUser.setUsername(modifiedUser.getUsername());
            oldUser.setEmail(modifiedUser.getEmail());
            oldUser.setPicture(modifiedUser.getPicture());
            oldUser.setGender(modifiedUser.getGender());
            oldUser.setPicture(modifiedUser.getPicture());
            userService.saveUser(oldUser);

            return ResponseEntity.ok("The user \"" + username + "\" has been updated.");
        } else {
            ApiError apiError =
                    new ApiError(HttpStatus.NOT_FOUND, "The user \"" + username + "\" doesn't exist.", "");
            return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        }
    }

    /**
     * Generate a number of aleatory users in the system.
     *
     * @param number of random users to generate.
     * @return ResponseEntity.ok() with the number of the users created, ApiError object if the numbers of the users to generate is less than 1.
     * @throws MalformedURLException if there is some error when we create the user's picture URL.
     */
    @ApiOperation(value = "Generate some random new users in the system.", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created random users"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @GetMapping("/user/generate/{number}")
    public ResponseEntity<Object> generateUsers(
            @PathVariable("number")
                    int number) throws MalformedURLException {

        if (number > 0) {
            List<User> lUsers = randomUserAPIService.getRandomUsers(number);
            for (User u : lUsers) {
                this.userService.saveUser(u);
            }
            return ResponseEntity.ok(number + " users have been added!");
        } else {
            ApiError apiError =
                    new ApiError(HttpStatus.BAD_REQUEST, "The \"number\" param must be greater than 0.", "");
            return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        }

    }

    /**
     * Create a random User to the system for test purposes.
     *
     * @return The new User created in the system.
     * @throws MalformedURLException if there is some error when we create the user's picture URL.
     */
    public User createRandomUser() throws MalformedURLException {
        User user = randomUserAPIService.getRandomUsers(1).get(0);
        this.userService.saveUser(user);
        return user;
    }
}