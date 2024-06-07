package com.rfc.rfcecommerce.service.admin.adminorder;

import org.junit.jupiter.api.Test;
import com.rfc.rfcecommerce.entity.*;

import static org.junit.jupiter.api.Assertions.*;

import com.rfc.rfcecommerce.entity.Order;
import com.rfc.rfcecommerce.repository.IOrderRepo;
import com.rfc.rfcecommerce.dto.OrderDto;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdminOrderServiceImplTest {

    @Mock
    private IOrderRepo orderRepo;

    @InjectMocks
    private AdminOrderServiceImpl adminOrderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllPlacedOrders() {
        User user = new User();
        user.setName("John Doe");

        Order order1 = new Order();
        order1.setOrderStatus(OrderStatus.PLACED);
        order1.setUser(user);  // Initialiser la propriété `user`

        Order order2 = new Order();
        order2.setOrderStatus(OrderStatus.SHIPPED);
        order2.setUser(user);  // Initialiser la propriété `user`

        Order order3 = new Order();
        order3.setOrderStatus(OrderStatus.DELIVERED);
        order3.setUser(user);  // Initialiser la propriété `user`

        when(orderRepo.findAllByOrderStatusIn(any())).thenReturn(List.of(order1, order2, order3));

        List<OrderDto> result = adminOrderService.getAllPlacedOrders();

        assertEquals(3, result.size());
        verify(orderRepo, times(1)).findAllByOrderStatusIn(any());
    }

    @Test
    void changeOrderStatus() {
        User user = new User();
        user.setName("John Doe");

        Order order = new Order();
        order.setOrderStatus(OrderStatus.PLACED);
        order.setUser(user);  // Initialiser la propriété `user`

        when(orderRepo.findById(anyLong())).thenReturn(Optional.of(order));
        when(orderRepo.save(any(Order.class))).thenReturn(order);

        OrderDto result = adminOrderService.changeOrderStatus(1L, "SHIPPED");

        assertNotNull(result);
        assertEquals(OrderStatus.SHIPPED, order.getOrderStatus());
        verify(orderRepo, times(1)).findById(anyLong());
        verify(orderRepo, times(1)).save(any(Order.class));
    }




}