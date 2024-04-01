package com.rfc.rfcecommerce.Repository;

import com.rfc.rfcecommerce.Entity.Order;
import com.rfc.rfcecommerce.Entity.OrderStatus;
import com.rfc.rfcecommerce.dto.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Order,Long> {
    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);
}
