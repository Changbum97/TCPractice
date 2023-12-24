package com.study.tcpractice.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.study.tcpractice.domain.PriceUtil;
import com.study.tcpractice.domain.entity.Order;
import com.study.tcpractice.domain.entity.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String name;

    private List<OrderDto> orders;

    @JsonSerialize(using = PriceUtil.class)
    private Integer totalPrice;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .orders(buildOrders(user.getOrders()))
                .totalPrice(calculateTotalPrice(user.getOrders()))
                .build();
    }

    private static List<OrderDto> buildOrders(List<Order> orders) {
        return orders.stream()
                .map(OrderDto::of)
                .collect(Collectors.toList());
    }

    private static Integer calculateTotalPrice(List<Order> orders) {
        int totalPrice = 0;

        for (Order order : orders) {
            totalPrice += order.getQuantities() * order.getItem().getPrice();
        }

        return totalPrice;
    }
}
