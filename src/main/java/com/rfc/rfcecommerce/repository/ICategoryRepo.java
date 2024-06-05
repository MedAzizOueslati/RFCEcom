package com.rfc.rfcecommerce.repository;

import com.rfc.rfcecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepo extends JpaRepository<Category,Long> {
}
