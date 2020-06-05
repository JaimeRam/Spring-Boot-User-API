package com.agile.content.user.api.model.random_user_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Name data class to store some data of DataResult class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
    /**
     * First name of the user.
     */
    private String first;

    /**
     * Last name of the user.
     */
    private String last;

    /**
     * Default empty constructor.
     */
    public Name() {
    }

    /**
     * Default constructor with params.
     *
     * @param first name string of the user.
     * @param last  name string of the user.
     */
    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    /**
     * Get first name of the user.
     *
     * @return first name String.
     */
    public String getFirst() {
        return first;
    }

    /**
     * Set the first name of the user.
     *
     * @param first name String.
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * Get last name of the user.
     *
     * @return last name String.
     */
    public String getLast() {
        return last;
    }

    /**
     * Set last name of the user.
     *
     * @param last name String.
     */
    public void setLast(String last) {
        this.last = last;
    }
}