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
import com.agile.content.user.api.controller.ApiError;
import com.agile.content.user.api.controller.UserController;
import com.agile.content.user.api.model.User;
import com.agile.content.user.api.model.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateDuplicatedUserTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    private final String API_URI = "/user/";

    private final String EXPECTED_ERROR_MESSAGE = "The user \"@username\" already exists.";

    @Test
    public void createDuplicatedUserTest() throws Exception {
        final User newUser = userController.createRandomUser();

        String json = objectMapper.writeValueAsString(newUser);

        final ResultActions createNewUser = this.mockMvc.perform(MockMvcRequestBuilders
                .post(API_URI)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());

        MvcResult mvcResult = createNewUser.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        final ApiError apiError = objectMapper.readValue(contentAsString, ApiError.class);

        assertThat(EXPECTED_ERROR_MESSAGE.replace("@username", newUser.getUsername())).isEqualTo(apiError.getMessage());
    }
}