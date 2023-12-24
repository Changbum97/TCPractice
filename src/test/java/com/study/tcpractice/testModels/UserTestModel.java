package com.study.tcpractice.testModels;

import com.study.tcpractice.domain.PriceUtil;
import com.study.tcpractice.domain.dto.UserDto;
import com.study.tcpractice.domain.dto.UserResponse;

import static com.study.tcpractice.testModels.OrderTestModel.ORDER_DTOS;

public class UserTestModel {

    public static final String USER_NAME_1 = "test_user_1";

    public static final UserDto USER_REQUEST = UserDto.builder().name(USER_NAME_1).build();
    public static final UserDto USER_RESPONSE = UserDto.builder().id(1L).name(USER_NAME_1).build();
    public static final Integer USER_1_TOTAL_PRICE = ORDER_DTOS.get(0).getTotalPrice() + ORDER_DTOS.get(1).getTotalPrice();
    public static final String USER_1_FORMATTED_TOTAL_PRICE = PriceUtil.makeStringPrice(USER_1_TOTAL_PRICE);

    public static final UserResponse USER_RESPONSE_WITH_ORDERS =
            UserResponse.builder()
                    .id(1L)
                    .name(USER_NAME_1)
                    .orders(ORDER_DTOS)
                    .totalPrice(USER_1_TOTAL_PRICE)
                    .build();

}
