package com.example.clothesshop.data.repositories;

import com.example.clothesshop.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductById(long id);
}
