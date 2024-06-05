package com.rfc.rfcecommerce.service.admin.adminorder;

import com.rfc.rfcecommerce.entity.Order;
import com.rfc.rfcecommerce.entity.OrderStatus;
import com.rfc.rfcecommerce.repository.IOrderRepo;
import com.rfc.rfcecommerce.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {
    private final IOrderRepo orderRepo;
    public List<OrderDto> getAllPlacedOrders(){
        List<Order> orderList = orderRepo.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Delivered, OrderStatus.Shipped));
    return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

    public OrderDto changeOrderStatus(Long orderId, String status){
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();

            if (Objects.equals(status, "Shipped")){
                order.setOrderStatus(OrderStatus.Shipped);

            } else if (Objects.equals(status,"Delivered")) {
                order.setOrderStatus(OrderStatus.Delivered);
            }
            return orderRepo.save(order).getOrderDto();

        }
        return null;
    }

}
