package com.femtonet.productsapi.repository;

import com.femtonet.productsapi.domain.product.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
