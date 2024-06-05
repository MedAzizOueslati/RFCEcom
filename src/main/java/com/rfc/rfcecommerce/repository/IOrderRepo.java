package com.rfc.rfcecommerce.repository;

import com.rfc.rfcecommerce.entity.Order;
import com.rfc.rfcecommerce.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Order,Long> {
    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);
    List<Order> findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);

}

