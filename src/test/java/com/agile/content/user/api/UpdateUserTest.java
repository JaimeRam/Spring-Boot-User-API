package com.agile.content.user.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.agile.content.user.api.controller.UserController;
import com.agile.content.user.api.model.User;
import com.agile.content.user.api.model.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateUserTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    private final String API_URI = "/user/";
    private final String UPDATED_USER_EMAIL = "update@user.com";
    private final String SUCCESSFUL_PUT_MESSAGE_REQUEST = "The user \"@username\" has been updated.";

    @Test
    public void updateUserTest() throws Exception {
        final User newUser = userController.createRandomUser();
        userRepository.save(newUser);

        newUser.setEmail(UPDATED_USER_EMAIL);

        String json = objectMapper.writeValueAsString(newUser);

        final ResultActions updateUser = this.mockMvc.perform(MockMvcRequestBuilders
                .put(API_URI + newUser.getUsername())
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(SUCCESSFUL_PUT_MESSAGE_REQUEST.replace("@username", newUser.getUsername()))));

        final ResultActions checkUpdatedUser = this.mockMvc.perform(get(API_URI + newUser.getUsername())
                .contentType(MediaType.APPLICATION_JSON.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult mvcResult = checkUpdatedUser.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        final User userFromApi = objectMapper.readValue(contentAsString, User.class);

        assertThat(userFromApi.getEmail()).isEqualTo(UPDATED_USER_EMAIL);
    }
}