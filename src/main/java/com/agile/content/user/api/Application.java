package com.agile.content.user.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.agile.content.user.api.controller.UserController;

/**
 * Start Spring Boot Application class.
 */
@SpringBootApplication
public class Application {
    /**
     * A initial random users to generate in the repository.
     */
    private static final Integer GENERATED_RANDOM_USERS = 50;

    /**
     * Start method of the Application Class.
     *
     * @param args to include.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * This method initialises the repository when the application is launched.
     *
     * @param userController @Controller class.
     * @return the CommandLineRunner.
     */
    @Bean
    public CommandLineRunner insertData(UserController userController) {
        return args -> userController.generateUsers(GENERATED_RANDOM_USERS);
    }
}