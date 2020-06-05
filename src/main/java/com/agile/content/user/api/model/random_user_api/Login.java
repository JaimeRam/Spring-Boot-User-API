package com.agile.content.user.api.model.random_user_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Login data class to store some data of DataResult class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Login {
    /**
     * An username of an User.
     */
    private String username;

    /**
     * Default empty constructor.
     */
    public Login() {
    }

    /**
     * Default constructor with a param.
     *
     * @param username String of an User.
     */
    public Login(String username) {
        this.username = username;
    }

    /**
     * Get the username of an User.
     *
     * @return the username String.
     */
    public String getUsername() {
        return username;
    }
}