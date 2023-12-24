package com.study.tcpractice.testModels;

import com.study.tcpractice.domain.dto.UserDto;
import com.study.tcpractice.domain.dto.UserResponse;

import java.text.DecimalFormat;

import static com.study.tcpractice.testModels.OrderTestModel.orderDtos;

public class UserTestModel {

    public static final String userName1 = "test_user_1";

    public static final UserDto userRequest = UserDto.builder().name(userName1).build();
    public static final UserDto userResponse = UserDto.builder().id(1L).name(userName1).build();
    public static final Integer user1TotalPrice = orderDtos.get(0).getTotalPrice() + orderDtos.get(1).getTotalPrice();
    public static final String user1FormattedTotalPrice = (new DecimalFormat("###,###").format(user1TotalPrice)) + "원";

    public static final UserResponse userResponseWithOrders =
            UserResponse.builder()
                    .id(1L)
                    .name(userName1)
                    .orders(orderDtos)
                    .totalPrice(user1TotalPrice)
                    .build();

}
