package com.rfc.rfcecommerce.Repository;

import com.rfc.rfcecommerce.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepo extends JpaRepository<Review,Long> {

    List<Review> findAllByProductId(Long productId);
}
