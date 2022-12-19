package com.femtonet.productsapi.repository;

import com.femtonet.productsapi.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
