package com.rfc.rfcecommerce.Repository;

import com.rfc.rfcecommerce.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<Order,Long> {
}
