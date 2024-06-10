package com.rfc.rfcecommerce.entity;

import com.rfc.rfcecommerce.dto.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order order;
    private User user;

    @BeforeEach
    void setUp() {
        user = Mockito.mock(User.class);
        Mockito.when(user.getName()).thenReturn("Test User");

        order = new Order();
        order.setId(1L);
        order.setOrderDescption("Test Order");
        order.setDate(new Date());
        order.setAmount(100L);
        order.setAddress("123 Test St");
        order.setPayment("Credit Card");
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalAmount(120L);
        order.setDiscount(20L);
        order.setTrackingId(UUID.randomUUID());
        order.setUser(user);
        order.setCartItems(new ArrayList<>());
    }

    @Test
    void testGetOrderDto() {
        OrderDto orderDto = order.getOrderDto();

        assertEquals(order.getId(), orderDto.getId());
        assertEquals(order.getOrderDescption(), orderDto.getOrderDescption());
        assertEquals(order.getDate(), orderDto.getDate());
        assertEquals(order.getAmount(), orderDto.getAmount());
        assertEquals(order.getAddress(), orderDto.getAddress());
        assertEquals(order.getOrderStatus(), orderDto.getOrderStatus());
        assertEquals(order.getTotalAmount(), orderDto.getTotalAmount());
        assertEquals(order.getTrackingId(), orderDto.getTrackingId());
        assertEquals(user.getName(), orderDto.getUserName());
    }
}