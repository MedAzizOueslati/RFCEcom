package com.rfc.rfcecommerce.Repository;

import com.rfc.rfcecommerce.Entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICartItemRepo extends JpaRepository<CartItems, Long> {
    Optional<CartItems> findByProductIdAndOrderIdAndUserId(Long productId, Long order_id, Long userId);
}
