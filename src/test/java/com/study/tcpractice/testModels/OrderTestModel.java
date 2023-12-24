package com.study.tcpractice.testModels;

import com.study.tcpractice.domain.dto.OrderDto;

import java.time.LocalDateTime;
import java.util.List;

import static com.study.tcpractice.testModels.ItemTestModel.*;
import static com.study.tcpractice.testModels.UserTestModel.userName1;

public class OrderTestModel {

    public static final LocalDateTime now = LocalDateTime.now();

    public static final OrderDto orderRequest = OrderDto.builder().userId(1L).itemId(1L).quantities(5).build();
    public static final OrderDto orderResponse = OrderDto.builder()
            .id(1L).createdAt(now)
            .userId(1L).userName(userName1)
            .itemId(1L).itemName(itemName1).itemPrice(itemPrice1)
            .quantities(5).totalPrice(itemPrice1 * 5)
            .build();

    public static final List<OrderDto> orderDtos = List.of(
            OrderDto.builder()
                    .id(1L)
                    .userId(1L)
                    .userName(userName1)
                    .itemId(1L)
                    .itemName(itemName1)
                    .itemPrice(itemPrice1)
                    .quantities(5)
                    .totalPrice(5 * itemPrice1)
                    .createdAt(now)
                    .build(),
            OrderDto.builder()
                    .id(2L)
                    .userId(1L)
                    .userName(userName1)
                    .itemId(2L)
                    .itemName(itemName2)
                    .itemPrice(itemPrice2)
                    .quantities(3)
                    .totalPrice(3 * itemPrice2)
                    .createdAt(now)
                    .build()
    );

}
