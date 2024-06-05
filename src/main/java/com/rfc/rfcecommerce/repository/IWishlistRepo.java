package com.rfc.rfcecommerce.repository;

import com.rfc.rfcecommerce.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWishlistRepo extends JpaRepository<Wishlist,Long> {
List<Wishlist> findAllByUserId(Long userId);
}
