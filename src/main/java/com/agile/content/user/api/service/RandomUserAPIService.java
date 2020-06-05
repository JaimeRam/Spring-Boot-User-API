package com.agile.content.user.api.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.agile.content.user.api.model.User;
import com.agile.content.user.api.model.random_user_api.DataResult;
import com.agile.content.user.api.model.random_user_api.Result;

/**
 * This class allows to get some random users data from the Random User API.
 */
@Service
public class RandomUserAPIService {
    /**
     * A RestTemplate to make the requests to the API.
     */
    private final RestTemplate restTemplate;
    /**
     * The URI String of the API GET Request. Note that we use some params in order to consume less CPU resources.
     */
    private static final String API_URI = "https://randomuser.me/api/?inc=name,gender,email,picture,login&results=";

    /**
     * Default constructor with params.
     *
     * @param restTemplateBuilder RestTemplate.
     */
    @Autowired
    public RandomUserAPIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * This method makes a GET Request to the Random User API to get some random users data.
     *
     * @param n number of users to generate.
     * @return a List<User> of random users generated.
     * @throws MalformedURLException
     */
    public List<User> getRandomUsers(int n) throws MalformedURLException {
        List<User> lUsers = new ArrayList<>();

        DataResult randomUserData = restTemplate.getForObject(API_URI + n, DataResult.class);

        for (Result result : randomUserData.getResults()) {
            lUsers.add(this.setDataIntoEntity(result));
        }

        return lUsers;
    }

    /**
     * This method generate a random user from the Random User API.
     *
     * @return a random User.
     * @throws MalformedURLException
     */
    public User getRandomUser() throws MalformedURLException {
        return this.getRandomUsers(1).get(0);
    }

    /**
     * This method converts the results from the Random User API to our User @Entity class.
     *
     * @param result from the Random User API request.
     * @return a @Entity User class.
     * @throws MalformedURLException
     */
    private User setDataIntoEntity(Result result) throws MalformedURLException {
        User user = new User();
        user.setUsername(result.getLogin().getUsername());
        user.setName(result.getName().getFirst() + " " + result.getName().getLast());
        user.setGender(result.getGender());
        user.setEmail(result.getEmail());
        user.setPicture(new URL(result.getPicture().getMedium()));
        return user;
    }
}