package com.bruno.application.product.repository;

import com.bruno.application.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtuctRepository extends JpaRepository<Product, Long> {
}
