package com.cmoiss.stockcontrolapi.repository;

import com.cmoiss.stockcontrolapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
