package com.study.tcpractice.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.study.tcpractice.domain.PriceUtil;
import com.study.tcpractice.domain.entity.Item;
import com.study.tcpractice.domain.entity.Order;
import com.study.tcpractice.domain.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long itemId;
    private Integer quantities;

    private String userName;
    private String itemName;

    // 가격 출력 포맷 변경  ex) 100000 => 100,000원
    @JsonSerialize(using = PriceUtil.class)
    private Integer itemPrice;
    @JsonSerialize(using = PriceUtil.class)
    private Integer totalPrice;

    private LocalDateTime createdAt;

    public Order toEntity(User user, Item item) {
        return Order.builder()
                .quantities(this.quantities)
                .user(user)
                .item(item)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static OrderDto of(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .userName(order.getUser().getName())
                .itemName(order.getItem().getName())
                .itemPrice(order.getItem().getPrice())
                .quantities(order.getQuantities())
                .totalPrice(order.getQuantities() * order.getItem().getPrice())
                .build();
    }
}
