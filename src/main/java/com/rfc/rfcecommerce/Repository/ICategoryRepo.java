package com.rfc.rfcecommerce.Repository;

import com.rfc.rfcecommerce.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepo extends JpaRepository<Category,Long> {
}
