package com.rfc.rfcecommerce.repository;

import com.rfc.rfcecommerce.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepo extends JpaRepository<Review,Long> {

    List<Review> findAllByProductId(Long productId);
}
