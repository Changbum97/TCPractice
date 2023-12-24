package com.study.tcpractice.domain.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.study.tcpractice.domain.PriceUtil;
import com.study.tcpractice.domain.entity.Item;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemDto {

    private Long id;
    private String name;

    // 가격 출력 포맷 변경  ex) 100000 => 100,000원
    @JsonSerialize(using = PriceUtil.class)
    private Integer price;

    public Item toEntity() {
        return Item.builder()
                .name(this.name)
                .price(this.price)
                .build();
    }

    public static ItemDto of(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }
}
