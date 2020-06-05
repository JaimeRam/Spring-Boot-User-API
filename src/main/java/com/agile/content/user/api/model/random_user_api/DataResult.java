package com.agile.content.user.api.model.random_user_api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A class to store the results from Random User Generator API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResult {
    /**
     * A list with the results from the request.
     */
    private List<Result> results;

    /**
     * Default empty constructor.
     */
    public DataResult() {
        this.results = new ArrayList<>();
    }

    /**
     * Default constructor with a param.
     *
     * @param results list of the request.
     */
    public DataResult(List<Result> results) {
        this.results = results;
    }

    /**
     * Get the result List.
     *
     * @return a List<Result> object.
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * Set the result List.
     *
     * @param results List<Result> object.
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }
}