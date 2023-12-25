package com.study.tcpractice.testModels;

import com.study.tcpractice.domain.dto.OrderDto;
import com.study.tcpractice.domain.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

import static com.study.tcpractice.testModels.ItemTestModel.*;
import static com.study.tcpractice.testModels.UserTestModel.SAVED_USER_1;
import static com.study.tcpractice.testModels.UserTestModel.USER_NAME_1;

public class OrderTestModel {

    public static final LocalDateTime NOW = LocalDateTime.now();

    public static final OrderDto ORDER_REQUEST = OrderDto.builder().userId(1L).itemId(1L).quantities(5).build();
    public static final OrderDto ORDER_RESPONSE = OrderDto.builder()
            .id(1L).createdAt(NOW)
            .userId(1L).userName(USER_NAME_1)
            .itemId(1L).itemName(ITEM_NAME_1).itemPrice(ITEM_PRICE_1)
            .quantities(5).totalPrice(ITEM_PRICE_1 * 5)
            .build();

    public static final List<OrderDto> ORDER_DTOS = List.of(
            OrderDto.builder()
                    .id(1L)
                    .userId(1L)
                    .userName(USER_NAME_1)
                    .itemId(1L)
                    .itemName(ITEM_NAME_1)
                    .itemPrice(ITEM_PRICE_1)
                    .quantities(5)
                    .totalPrice(5 * ITEM_PRICE_1)
                    .createdAt(NOW)
                    .build(),
            OrderDto.builder()
                    .id(2L)
                    .userId(1L)
                    .userName(USER_NAME_1)
                    .itemId(2L)
                    .itemName(ITEM_NAME_2)
                    .itemPrice(ITEM_PRICE_2)
                    .quantities(3)
                    .totalPrice(3 * ITEM_PRICE_2)
                    .createdAt(NOW)
                    .build()
    );

    public static final Order SAVED_ORDER_1 = Order.builder()
            .id(1L)
            .user(SAVED_USER_1)
            .item(SAVED_ITEM_1)
            .quantities(5)
            .createdAt(NOW)
            .build();

    public static final Order SAVED_ORDER_2 = Order.builder()
            .id(2L)
            .user(SAVED_USER_1)
            .item(SAVED_ITEM_2)
            .quantities(3)
            .createdAt(NOW)
            .build();
}
