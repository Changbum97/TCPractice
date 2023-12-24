package com.study.tcpractice.controller;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.tcpractice.service.ItemService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

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
    @DisplayName("Add Item Success Test")
    void addItemSuccess() throws Exception {

        // when
        when(itemService.saveItem(any())).thenReturn(ITEM_RESPONSE_1);

        // then
        mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(ITEM_REQUEST_1))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(ITEM_NAME_1))
                .andExpect(jsonPath("$.price").value(ITEM_PRICE_1));
    }

    @Test
    @DisplayName("Edit Item Success Test")
    void editItemSuccess() throws Exception {

        // when
        when(itemService.editItem(any())).thenReturn(ITEM_RESPONSE_1);

        // then
        mockMvc.perform(put("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(ITEM_REQUEST_1))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(ITEM_NAME_1))
                .andExpect(jsonPath("$.price").value(ITEM_PRICE_1));
    }

    @Test
    @DisplayName("Delete Item Success Test")
    void deleteItemSuccess() throws Exception {

        // when
        when(itemService.deleteItem(any())).thenReturn("1번 Item이 삭제되었습니다.");

        // then
        mockMvc.perform(delete("/items/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1번 Item이 삭제되었습니다."));
    }

    @Test
    @DisplayName("Delete Item Fail Test")
    void deleteItemFail() throws Exception {

        // when
        when(itemService.deleteItem(any())).thenReturn("id에 해당하는 Item이 없습니다.");

        // then
        mockMvc.perform(delete("/items/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("id에 해당하는 Item이 없습니다."));
    }

    @Test
    @DisplayName("Find All Item Success Test")
    void findAllItemSuccess() throws Exception {
        // when
        when(itemService.findAll()).thenReturn(ITEM_DTOS);

        // then
        mockMvc.perform(get("/items"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value(ITEM_NAME_1))
                .andExpect(jsonPath("$[1].name").value(ITEM_NAME_2));
    }
}