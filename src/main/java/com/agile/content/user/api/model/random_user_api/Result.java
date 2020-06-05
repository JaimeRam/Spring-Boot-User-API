package com.agile.content.user.api.model.random_user_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Result data class to store some data of DataResult class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    /**
     * Gender of the user.
     */
    private String gender;
    /**
     * Name class of the user.
     */
    private Name name;
    /**
     * Email of the user.
     */
    private String email;
    /**
     * Login class of the user.
     */
    private Login login;
    /**
     * Picture class of the user.
     */
    private Picture picture;

    /**
     * Default empty constructor.
     */
    public Result() {
    }

    /**
     * Default constructor with params.
     *
     * @param gender  of the user.
     * @param name    of the user
     * @param email   of the user.
     * @param login   data class of the user.
     * @param picture data class of the user.
     */
    public Result(String gender, Name name, String email, Login login, Picture picture) {
        this.gender = gender;
        this.name = name;
        this.email = email;
        this.login = login;
        this.picture = picture;
    }

    /**
     * Get gender of the user.
     *
     * @return gender String of the user.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set gender of the user.
     *
     * @param gender String of the user.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get Name data class of the user.
     *
     * @return Name data class.
     */
    public Name getName() {
        return name;
    }

    /**
     * Set Name data class of the user.
     *
     * @param name data class of the user.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * Get email of the user.
     *
     * @return email String of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email of the user.
     *
     * @param email String of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get Login data class of the user.
     *
     * @return Login data class.
     */
    public Login getLogin() {
        return login;
    }

    /**
     * Set Login data class of the user.
     *
     * @param login data class.
     */
    public void setLogin(Login login) {
        this.login = login;
    }

    /**
     * Get Picture data class of the user.
     *
     * @return Picture data class.
     */
    public Picture getPicture() {
        return picture;
    }

    /**
     * Set Picture data class of the user.
     *
     * @param picture data class.
     */
    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}