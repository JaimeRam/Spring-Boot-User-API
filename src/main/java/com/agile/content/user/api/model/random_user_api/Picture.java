package com.agile.content.user.api.model.random_user_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Picture data class to store some data of Result class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Picture {
    /**
     * Medium URL of the picture.
     */
    private String medium;

    /**
     * Default empty constructor.
     */
    public Picture() {
    }

    /**
     * Default constructor with params.
     *
     * @param medium url of the picture.
     */
    public Picture(String medium) {
        this.medium = medium;
    }

    /**
     * Get medium URL of the picture.
     *
     * @return URL String of the picture.
     */
    public String getMedium() {
        return medium;
    }

    /**
     * Set medium URL of the picture.
     *
     * @param medium URL String of the picture.
     */
    public void setMedium(String medium) {
        this.medium = medium;
    }
}
