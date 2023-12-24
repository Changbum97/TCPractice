package com.study.tcpractice.controller;

import com.study.tcpractice.domain.dto.ItemDto;
import com.study.tcpractice.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<ItemDto> saveItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.saveItem(itemDto));
    }

    @PutMapping(value = {"", "/"})
    public ResponseEntity<ItemDto> editItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.editItem(itemDto));
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<String> deleteItem(@PathVariable(name = "itemId") Long itemId) {
        return ResponseEntity.ok(itemService.deleteItem(itemId));
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<ItemDto>> findAll() {
        return ResponseEntity.ok(itemService.findAll());
    }
}
