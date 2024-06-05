package com.rfc.rfcecommerce.repository;

import com.rfc.rfcecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {
    List<Product> findAllByNameContaining(String title);
}
