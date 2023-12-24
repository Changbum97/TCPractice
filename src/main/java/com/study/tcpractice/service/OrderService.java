package com.study.tcpractice.service;

import com.study.tcpractice.domain.dto.ItemDto;
import com.study.tcpractice.domain.dto.OrderDto;
import com.study.tcpractice.domain.dto.UserDto;
import com.study.tcpractice.domain.entity.Item;
import com.study.tcpractice.domain.entity.Order;
import com.study.tcpractice.domain.entity.User;
import com.study.tcpractice.repository.ItemRepository;
import com.study.tcpractice.repository.OrderRepository;
import com.study.tcpractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public OrderDto saveOrder(OrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new NullPointerException("id에 해당하는 User가 없습니다."));
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(() -> new NullPointerException("id에 해당하는 Item이 없습니다."));

        Order savedOrder = orderRepository.save(orderDto.toEntity(user, item));
        return OrderDto.of(savedOrder);
    }

    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderDto::of)
                .collect(Collectors.toList());
    }

    public String deleteOrder(Long orderId) {
        if (orderRepository.findById(orderId).isEmpty()) {
            return "id에 해당하는 Order가 없습니다.";
        }

        orderRepository.deleteById(orderId);
        return orderId + "번 Order가 삭제되었습니다.";
    }

}
