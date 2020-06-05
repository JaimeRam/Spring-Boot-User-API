package com.agile.content.user.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import com.agile.content.user.api.model.User;
import com.agile.content.user.api.model.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetUserTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final String API_URI = "/user/";

    @Test
    public void getUserTest() throws Exception {
        final User userToCheck = userRepository.getRandomUser();

        final ResultActions resultActions = this.mockMvc.perform(get(API_URI + userToCheck.getUsername())
                .contentType(MediaType.APPLICATION_JSON.APPLICATION_JSON))
                .andExpect(status().isOk());

        MvcResult mvcResult = resultActions.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        final User userFromApi = objectMapper.readValue(contentAsString, User.class);

        assertThat(userToCheck.getUsername()).isEqualTo(userFromApi.getUsername());
    }
}