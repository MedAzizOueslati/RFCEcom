package com.rfc.rfcecommerce.service.admin.adminorder;

import com.rfc.rfcecommerce.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {
    public List<OrderDto> getAllPlacedOrders();
    public OrderDto changeOrderStatus(Long orderId, String status);

    }
