package com.agile.content.user.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.agile.content.user.api.model.User;
import com.agile.content.user.api.model.UserRepository;
import com.agile.content.user.api.service.RandomUserAPIService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateNewUserTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RandomUserAPIService randomUserAPIService;

    @Autowired
    private ObjectMapper objectMapper;

    private final String API_URI = "/user/";

    @Test
    public void createNewUserTest() throws Exception {
        final User newUser = randomUserAPIService.getRandomUser();

        String json = objectMapper.writeValueAsString(newUser);

        final ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post(API_URI)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        User createdUser = userRepository.findByUsernameIs(newUser.getUsername());
        assertThat(createdUser.getUsername()).isEqualTo(newUser.getUsername());
    }
}