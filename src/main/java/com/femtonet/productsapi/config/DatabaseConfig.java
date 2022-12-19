package com.femtonet.productsapi.config;

import com.femtonet.productsapi.domain.product.Product;
import com.femtonet.productsapi.domain.product.Stock;
import com.femtonet.productsapi.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepos) {
        LOGGER.info("initDatabase() - Initialising database...");
        return args -> {
            LOGGER.info("initDatabase() - New record: {}", productRepos.save(new Product("V-NECK BASIC SHIRT", 100, new Stock(4, 9, 0))));
            LOGGER.info("initDatabase() - New record: {}", productRepos.save(new Product("CONTRASTING FABRIC T-SHIRT", 50, new Stock(35, 9, 9))));
            LOGGER.info("initDatabase() - New record: {}", productRepos.save(new Product("RAISED PRINT T-SHIRT", 80, new Stock(20, 2, 20))));
            LOGGER.info("initDatabase() - Database initialised.");
        };
    }
}
