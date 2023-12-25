package com.study.tcpractice.testModels;

import com.study.tcpractice.domain.dto.ItemDto;
import com.study.tcpractice.domain.entity.Item;

import java.util.List;

public class ItemTestModel {

    public static final String ITEM_NAME_1 = "가방";
    public static final Integer ITEM_PRICE_1 = 100000;

    public static final String ITEM_NAME_2 = "신발";
    public static final Integer ITEM_PRICE_2 = 72000;

    public static final ItemDto ITEM_REQUEST_1 = ItemDto.builder().name(ITEM_NAME_1).price(ITEM_PRICE_1).build();
    public static final ItemDto ITEM_REQUEST_2 = ItemDto.builder().name(ITEM_NAME_2).price(ITEM_PRICE_2).build();
    public static final ItemDto ITEM_RESPONSE_1 = ItemDto.builder().id(1L).name(ITEM_NAME_1).price(ITEM_PRICE_1).build();
    public static final ItemDto ITEM_RESPONSE_2 = ItemDto.builder().id(2L).name(ITEM_NAME_2).price(ITEM_PRICE_2).build();

    public static final List<ItemDto> ITEM_DTOS = List.of(ITEM_RESPONSE_1, ITEM_RESPONSE_2);

    public static final Item SAVED_ITEM_1 = Item.builder().id(1L).name(ITEM_NAME_1).price(ITEM_PRICE_1).build();
    public static final Item EDITED_ITEM_1 = Item.builder().id(1L).name(ITEM_NAME_2).price(ITEM_PRICE_2).build();
    public static final Item SAVED_ITEM_2 = Item.builder().id(2L).name(ITEM_NAME_2).price(ITEM_PRICE_2).build();

}
