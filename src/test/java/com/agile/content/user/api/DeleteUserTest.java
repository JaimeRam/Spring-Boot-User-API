package com.agile.content.user.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import com.agile.content.user.api.controller.ApiError;
import com.agile.content.user.api.model.User;
import com.agile.content.user.api.model.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteUserTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final String API_URI = "/user/";

    private final String SUCCESSFUL_DELETE_MESSAGE_REQUEST = "The user \"@username\" has been deleted.";

    private final String EXPECTED_ERROR_MESSAGE = "The user \"@username\" doesn't exist.";

    @Test
    public void deleteUserTest() throws Exception {
        final User userToDelete = userRepository.getRandomUser();

        final ResultActions deleteUser = this.mockMvc.perform(delete(API_URI + userToDelete.getUsername())
                .contentType(MediaType.APPLICATION_JSON.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(SUCCESSFUL_DELETE_MESSAGE_REQUEST.replace("@username", userToDelete.getUsername()))));

        final ResultActions deleteUserAgain = this.mockMvc.perform(delete(API_URI + userToDelete.getUsername())
                .contentType(MediaType.APPLICATION_JSON.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        MvcResult mvcResult = deleteUserAgain.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        final ApiError apiError = objectMapper.readValue(contentAsString, ApiError.class);

        assertThat(EXPECTED_ERROR_MESSAGE.replace("@username", userToDelete.getUsername())).isEqualTo(apiError.getMessage());
    }
}