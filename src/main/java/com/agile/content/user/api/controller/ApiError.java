package com.agile.content.user.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * This class allows to you to create a custom error when something was wrong from an API request.
 */
public class ApiError {

    /**
     * Status code returned by the API request.
     */
    private HttpStatus status;

    /**
     * Error message from the request.
     */
    private String message;

    /**
     * A list of errors from the request.
     */
    private List<String> errors;

    /**
     * Default empty constructor.
     */
    public ApiError() {
    }

    /**
     * Default constructor with parameters.
     *
     * @param status  of the request.
     * @param message error of the request.
     * @param errors  A list of errors from the request.
     */
    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Default constructor with parameters.
     *
     * @param status  of the request.
     * @param message error of the request.
     * @param error   type of the request.
     */
    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    /**
     * Get HttpStatus code.
     *
     * @return the status code
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Set the HttpStatus code.
     *
     * @param status code.
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * Get the message.
     *
     * @return the message String text.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set th message.
     *
     * @param message String text.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the errors.
     *
     * @return the list of errors.
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Set the errors.
     *
     * @param errors list.
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}