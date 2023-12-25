package com.study.tcpractice.service;

import com.study.tcpractice.domain.dto.ItemDto;
import com.study.tcpractice.domain.entity.Item;
import com.study.tcpractice.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.study.tcpractice.testModels.ItemTestModel.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    private final ItemRepository itemRepository = mock(ItemRepository.class);

    ItemService itemService;

    /**
     * ItemService 객체 생성 및 의존 주입
     */
    @BeforeEach
    void setUp() {
        itemService = new ItemService(itemRepository);
    }

    @Test
    @DisplayName("Save Item Success Test")
    void saveItemSuccess() {
        // when
        when(itemRepository.save(any())).thenReturn(SAVED_ITEM_1);

        // then
        ItemDto response = itemService.saveItem(ITEM_REQUEST_1);
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo(ITEM_NAME_1);
        assertThat(response.getPrice()).isEqualTo(ITEM_PRICE_1);
    }

    @Test
    @DisplayName("Edit Item Success Test")
    void editItemSuccess() {
        // given
        // thenReturn에 SAVED_ITEM_1을 넣으면 값이 바뀌어 버림
        Item targetItem = Item.builder().id(1L).name(ITEM_NAME_1).price(ITEM_PRICE_1).build();

        // when
        when(itemRepository.findById(any())).thenReturn(Optional.ofNullable(targetItem));
        when(itemRepository.save(any())).thenReturn(EDITED_ITEM_1);

        // then
        ItemDto response = itemService.editItem(ITEM_REQUEST_2);
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo(ITEM_NAME_2);
        assertThat(response.getPrice()).isEqualTo(ITEM_PRICE_2);
    }

    @Test
    @DisplayName("Edit Item Fail Test")
    void editItemFail() {
        // when
        when(itemRepository.findById(any())).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> {
                    itemService.editItem(ITEM_REQUEST_1);
                })
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("id에 해당하는 Item이 없습니다.");
    }

    @Test
    @DisplayName("Delete Item Success Test")
    void deleteItemSuccess() {
        // when
        when(itemRepository.findById(any())).thenReturn(Optional.of(SAVED_ITEM_1));

        // then
        String deleteResult = itemService.deleteItem(1L);
        assertThat(deleteResult).isEqualTo("1번 Item이 삭제되었습니다.");

        verify(itemRepository).deleteById(any());
    }

    @Test
    @DisplayName("Delete Item Fail Test")
    void deleteItemFail() {
        // when
        when(itemRepository.findById(any())).thenReturn(Optional.empty());

        // then
        String deleteResult = itemService.deleteItem(1L);
        assertThat(deleteResult).isEqualTo("id에 해당하는 Item이 없습니다.");
    }

    @Test
    @DisplayName("Find All Item Success Test")
    void findAllItemSuccess() {
        List<Item> SAVED_ITEMS = List.of(SAVED_ITEM_1, SAVED_ITEM_2);

        // when
        when(itemRepository.findAll()).thenReturn(SAVED_ITEMS);

        // then
        List<ItemDto> response = itemService.findAll();
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(0).getName()).isEqualTo(ITEM_NAME_1);
        assertThat(response.get(1).getName()).isEqualTo(ITEM_NAME_2);
    }

    @Test
    @DisplayName("Find All Item Fail Test")
    void findAllItemFail() {
        // when
        when(itemRepository.findAll()).thenReturn(null);

        // then
        assertThatThrownBy(() -> {
            itemService.findAll();
        })
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Item이 존재하지 않습니다.");
    }
}