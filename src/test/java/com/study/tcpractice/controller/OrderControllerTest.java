package com.study.tcpractice.controller;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.tcpractice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.study.tcpractice.testModels.ItemTestModel.*;
import static com.study.tcpractice.testModels.OrderTestModel.*;
import static com.study.tcpractice.testModels.UserTestModel.USER_NAME_1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Price가 Json에서 출력될 때, 가격 포맷을 적용하도록 해줬는데
     * Test 과정에서 Request도 String으로 바뀌는 문제 발생
     *   => Test Code에서는 가격 포맷 적용 X
     */
    @BeforeEach
    void nonApplyPriceUtil() {
        objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
    }


    @Test
    @DisplayName("Add Order Success Test")
    void addOrderSuccess() throws Exception {

        // when
        when(orderService.saveOrder(any())).thenReturn(ORDER_RESPONSE);

        // then
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(ORDER_REQUEST))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.userName").value(USER_NAME_1))
                .andExpect(jsonPath("$.itemName").value(ITEM_NAME_1))
                .andExpect(jsonPath("$.totalPrice").value(5 * ITEM_PRICE_1));
    }

    @Test
    @DisplayName("Delete Order Success Test")
    void deleteOrderSuccess() throws Exception {

        // when
        when(orderService.deleteOrder(any())).thenReturn("1번 Order가 삭제되었습니다.");

        // then
        mockMvc.perform(delete("/orders/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1번 Order가 삭제되었습니다."));
    }

    @Test
    @DisplayName("Delete Order Fail Test")
    void deleteOrderFail() throws Exception {

        // when
        when(orderService.deleteOrder(any())).thenReturn("id에 해당하는 Order가 없습니다.");

        // then
        mockMvc.perform(delete("/orders/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("id에 해당하는 Order가 없습니다."));
    }

    @Test
    @DisplayName("Find All Order Success Test")
    void findAllOrderSuccess() throws Exception {
        // when
        when(orderService.findAll()).thenReturn(ORDER_DTOS);

        // then
        mockMvc.perform(get("/orders"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].itemName").value(ITEM_NAME_1))
                .andExpect(jsonPath("$[0].totalPrice").value(5 * ITEM_PRICE_1))
                .andExpect(jsonPath("$[1].itemName").value(ITEM_NAME_2))
                .andExpect(jsonPath("$[1].totalPrice").value(3 * ITEM_PRICE_2));
    }
}