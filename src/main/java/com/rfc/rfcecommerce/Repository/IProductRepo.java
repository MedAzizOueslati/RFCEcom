package com.rfc.rfcecommerce.Repository;

import com.rfc.rfcecommerce.Entity.Category;
import com.rfc.rfcecommerce.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {
    List<Product> findAllByNameContaining(String title);
}
