package com.study.tcpractice.service;

import com.study.tcpractice.domain.dto.OrderDto;
import com.study.tcpractice.domain.entity.Order;
import com.study.tcpractice.repository.ItemRepository;
import com.study.tcpractice.repository.OrderRepository;
import com.study.tcpractice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.study.tcpractice.testModels.ItemTestModel.*;
import static com.study.tcpractice.testModels.OrderTestModel.*;
import static com.study.tcpractice.testModels.UserTestModel.SAVED_USER_1;
import static com.study.tcpractice.testModels.UserTestModel.USER_NAME_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final ItemRepository itemRepository = mock(ItemRepository.class);

    OrderService orderService;

    /**
     * OrderService 객체 생성 및 의존 주입
     */
    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, userRepository, itemRepository);
    }

    @Test
    @DisplayName("Save Order Success Test")
    void saveOrderSuccess() {
        Order order = Order.builder().id(1L).user(SAVED_USER_1).item(SAVED_ITEM_1).quantities(5).createdAt(NOW).build();

        // when
        when(userRepository.findById(any())).thenReturn(Optional.of(SAVED_USER_1));
        when(itemRepository.findById(any())).thenReturn(Optional.of(SAVED_ITEM_1));
        when(orderRepository.save(any())).thenReturn(order);

        // then
        OrderDto response = orderService.saveOrder(ORDER_REQUEST);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getUserName()).isEqualTo(USER_NAME_1);
        assertThat(response.getItemName()).isEqualTo(ITEM_NAME_1);
        assertThat(response.getItemPrice()).isEqualTo(ITEM_PRICE_1);
        assertThat(response.getQuantities()).isEqualTo(5);
        assertThat(response.getTotalPrice()).isEqualTo(5 * ITEM_PRICE_1);
    }

    @Test
    @DisplayName("Save Order Fail Test 1 - Empty User")
    void saveOrderFail1() {
        // when
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        when(itemRepository.findById(any())).thenReturn(Optional.of(SAVED_ITEM_1));

        // then
        assertThatThrownBy(() -> {
            orderService.saveOrder(ORDER_REQUEST);
        })
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("id에 해당하는 User가 없습니다.");
    }

    @Test
    @DisplayName("Save Order Fail Test 2 - Empty Item")
    void saveOrderFail2() {
        // when
        when(userRepository.findById(any())).thenReturn(Optional.of(SAVED_USER_1));
        when(itemRepository.findById(any())).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> {
            orderService.saveOrder(ORDER_REQUEST);
        })
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("id에 해당하는 Item이 없습니다.");
    }

    @Test
    @DisplayName("Delete Order Success Test")
    void deleteOrderSuccess() {
        // when
        when(orderRepository.findById(any())).thenReturn(Optional.of(SAVED_ORDER_1));

        // then
        String deleteResult = orderService.deleteOrder(1L);
        assertThat(deleteResult).isEqualTo("1번 Order가 삭제되었습니다.");

        verify(orderRepository).deleteById(any());
    }

    @Test
    @DisplayName("Delete Order Fail Test")
    void deleteOrderFail() {
        // when
        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        // then
        String deleteResult = orderService.deleteOrder(1L);
        assertThat(deleteResult).isEqualTo("id에 해당하는 Order가 없습니다.");
    }

    @Test
    @DisplayName("Find All Order Success Test")
    void findAllOrderSuccess() {
        List<Order> SAVED_ORDERS = List.of(SAVED_ORDER_1, SAVED_ORDER_2);

        // when
        when(orderRepository.findAll()).thenReturn(SAVED_ORDERS);

        // then
        List<OrderDto> response = orderService.findAll();
        assertThat(response.size()).isEqualTo(2);
        assertThat(response.get(0).getItemName()).isEqualTo(ITEM_NAME_1);
        assertThat(response.get(1).getItemName()).isEqualTo(ITEM_NAME_2);
    }
}