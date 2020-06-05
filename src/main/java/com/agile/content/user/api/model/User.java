package com.agile.content.user.api.model;

import java.net.URL;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "users")
@ApiModel(description = "All details about the User in the system. ")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated user ID.")
    private Integer id;

    @Column(name = "username", unique = true, nullable = false)
    @ApiModelProperty(notes = "The user username.")
    private String username;

    @Column(name = "name", nullable = false)
    @ApiModelProperty(notes = "The user name.")
    private String name;

    @Column(name = "email", nullable = false)
    @ApiModelProperty(notes = "The user email.")
    private String email;

    @Column(name = "gender", nullable = false)
    @ApiModelProperty(notes = "The user gender.")
    private String gender;

    @Column(name = "picture", nullable = false)
    @ApiModelProperty(notes = "The user picture from an URL.")
    private URL picture;

    @Column(name = "created", nullable = false)
    @ApiModelProperty(notes = "The date when the user was created.")
    private Date created;

    @Column(name = "updated", nullable = false)
    @ApiModelProperty(notes = "The date when the user was updated.")
    private Date updated;

    /**
     * Default empty constructor.
     */
    public User() {
    }

    /**
     * Default constructor with params.
     *
     * @param username of the User.
     * @param name     of the User.
     * @param email    of the User.
     * @param gender   of the User.
     * @param picture  URL of the User.
     */
    public User(String username, String name, String email, String gender, URL picture) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.picture = picture;
    }

    /**
     * Get user's id.
     *
     * @return the user's id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the user's id.
     *
     * @param id of the user.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get user's username.
     *
     * @return String user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set user's username.
     *
     * @param username String of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get user's name.
     *
     * @return String user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set user's name.
     *
     * @param name String of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get user's email.
     *
     * @return String user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user's email.
     *
     * @param email String of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get user's gender.
     *
     * @return String user's gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set user's gender.
     *
     * @param gender String of the user.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get URL picture of the user.
     *
     * @return URL class of the user's picture.
     */
    public URL getPicture() {
        return picture;
    }

    /**
     * Set URL picture of the user.
     *
     * @param picture URL class.
     */
    public void setPicture(URL picture) {
        this.picture = picture;
    }

    /**
     * This method allows to fill some data about created and updated data of an user in database. It is running every time that an User class is
     * created.
     */
    @PrePersist
    protected void onCreate() {
        this.created = new Date();
        this.updated = new Date();
    }

    /**
     * This method allows to update the last updated date of an user in database. It is running every time that an User class is updated.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updated = new Date();
    }

    /**
     * Get the user's information as a JSON String format.
     *
     * @return String with the user's information.
     */
    @Override
    public String toString() {
        final String LINE_SEPARATOR = System.getProperty("line.separator");

        return "{\n"
                + "   \"id\":" + this.id + "," + LINE_SEPARATOR
                + "   \"username\":\"" + this.username + "\"," + LINE_SEPARATOR
                + "   \"name\":\"" + this.name + "\"," + LINE_SEPARATOR
                + "   \"email\":\"" + this.email + "\"," + LINE_SEPARATOR
                + "   \"gender\":\"" + this.gender + "\"," + LINE_SEPARATOR
                + "   \"picture\":\"" + this.picture + "\"" + LINE_SEPARATOR
                + "}";
    }
}