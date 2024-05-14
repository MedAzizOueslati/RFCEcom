package com.rfc.rfcecommerce.Service.admin.adminOrder;

import com.rfc.rfcecommerce.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {
    public List<OrderDto> getAllPlacedOrders();
    public OrderDto changeOrderStatus(Long orderId, String status);

    }
