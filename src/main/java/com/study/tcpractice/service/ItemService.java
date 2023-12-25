package com.study.tcpractice.service;

import com.study.tcpractice.domain.dto.ItemDto;
import com.study.tcpractice.domain.entity.Item;
import com.study.tcpractice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemDto saveItem(ItemDto itemDto) {
        Item savedItem = itemRepository.save(itemDto.toEntity());
        return ItemDto.of(savedItem);
    }

    @Transactional
    public ItemDto editItem(ItemDto itemDto) {
        Item targetItem = itemRepository.findById(itemDto.getId())
                .orElseThrow(() -> new NullPointerException("id에 해당하는 Item이 없습니다."));

        targetItem.edit(itemDto);
        return ItemDto.of(targetItem);
    }

    public String deleteItem(Long itemId) {
        if (itemRepository.findById(itemId).isEmpty()) {
            return "id에 해당하는 Item이 없습니다.";
        }

        itemRepository.deleteById(itemId);
        return itemId + "번 Item이 삭제되었습니다.";
    }

    public List<ItemDto> findAll() {
        try {
            return itemRepository.findAll()
                    .stream()
                    .map(ItemDto::of)
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new NullPointerException("Item이 존재하지 않습니다.");
        }
    }
}
