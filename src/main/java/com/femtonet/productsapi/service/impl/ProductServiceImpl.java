package com.femtonet.productsapi.service.impl;

import com.femtonet.productsapi.domain.product.Product;
import com.femtonet.productsapi.payload.ProductResponse;
import com.femtonet.productsapi.repository.ProductRepository;
import com.femtonet.productsapi.service.ProductService;
import com.femtonet.productsapi.utils.ApiConstants;
import com.femtonet.productsapi.utils.mapper.ProductMapper;
import com.femtonet.productsapi.utils.sort.SortUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductMapper mapper;
    @Value("#{${product.criteria.weight}}")
    private Map<String, Integer> criteriasProps;

    @Override
    public List<ProductResponse> getAllProducts(Map<String, String> reqParams) throws Exception {
        LOGGER.info("---- getAllProducts() - Starting... ----");
        LOGGER.info("getAllProducts() - reqParams: {}", reqParams);

        List<ProductResponse> productList = new ArrayList<>();
        List<Product> products;

        if (!criteriasProps.keySet().containsAll(reqParams.keySet())) {
            LOGGER.error("getAllProducts() - Error: Invalid Params!");
            throw new Exception(ApiConstants.ERROR_BAD_PARAMS);
        }

        try {
            products = repository.findAll();
        } catch (Exception e) {
            LOGGER.error("getAllProducts() - Error found while retrieving products from DB!");
            throw new Exception(ApiConstants.ERROR_DDBB);
        }

        products.forEach(product -> {
            LOGGER.info("getAllProducts() - product ID: {}", product.getId());
            ProductResponse productResponse = mapper.productToProductResponse(product);
            LOGGER.info("getAllProducts() - Setting weighted sum to product...");
            SortUtils.setWeightedSum(productResponse, reqParams);
            productList.add(productResponse);
        });

        LOGGER.info("getAllProducts() - Sorting the product list...");
        productList.sort(Comparator.comparing(ProductResponse::getSortPoint));
        LOGGER.info("getAllProducts() - End.");
        return productList;
    }
}