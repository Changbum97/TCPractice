package com.study.tcpractice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.tcpractice.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.study.tcpractice.testModels.UserTestModel.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("Add User Test Success")
    void addUserSuccess() throws Exception {
        // given
        // testModels/UserTestModel에 Mocking 해둔 객체 사용 (static import)
        // userResponse1, userName1

        // when
        when(userService.saveUser(any())).thenReturn(userResponse);

        // then
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userRequest))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(userName1));
    }

    @Test
    @DisplayName("Get User Test Success")
    void getUserSuccess() throws Exception {
        // when
        when(userService.findById(any())).thenReturn(userResponseWithOrders);

        // then
        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(userName1))
                .andExpect(jsonPath("$.totalPrice").value(user1FormattedTotalPrice))
                .andExpect(jsonPath("$.orders.size()").value(2));
    }

}