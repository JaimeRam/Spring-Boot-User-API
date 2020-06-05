package com.agile.content.user.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.agile.content.user.api.model.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetUserListPaginationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private final String API_URI = "/user";

    private final Integer PAGE = 3;

    private final Integer SIZE = 10;

    @Test
    public void getUserListTest() throws Exception {
        final ResultActions resultActions = this.mockMvc.perform(get(API_URI)
                .param("page", PAGE.toString())
                .param("size", SIZE.toString())
                .contentType(MediaType.APPLICATION_JSON.APPLICATION_JSON))
                .andExpect(jsonPath("$.pageable.offset").value((PAGE - 1) * SIZE))
                .andExpect(jsonPath("$.pageable.pageNumber").value(PAGE - 1))
                .andExpect(jsonPath("$.pageable.pageSize").value(SIZE))
                .andExpect(jsonPath("$.pageable.paged").value(true))
                .andExpect(jsonPath("$.totalElements").value(userRepository.count()))
                .andExpect(status().isOk());
    }
}