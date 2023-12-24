package com.study.tcpractice.controller;

import com.study.tcpractice.domain.dto.OrderDto;
import com.study.tcpractice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.saveOrder(orderDto));
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<OrderDto>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "orderId") Long orderId) {
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }
}
