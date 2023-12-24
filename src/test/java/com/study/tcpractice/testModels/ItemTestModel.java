package com.study.tcpractice.testModels;

import com.study.tcpractice.domain.dto.ItemDto;

import java.util.List;

public class ItemTestModel {

    public static final String itemName1 = "가방";
    public static final Integer itemPrice1 = 100000;

    public static final String itemName2 = "신발";
    public static final Integer itemPrice2 = 72000;

    public static final ItemDto itemRequest1 = ItemDto.builder().name(itemName1).price(itemPrice1).build();
    public static final ItemDto itemResponse1 = ItemDto.builder().id(1L).name(itemName1).price(itemPrice1).build();
    public static final ItemDto itemResponse2 = ItemDto.builder().id(2L).name(itemName2).price(itemPrice2).build();

    public static final List<ItemDto> itemDtos = List.of(itemResponse1, itemResponse2);
}
