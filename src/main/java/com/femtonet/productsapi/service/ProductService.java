package com.femtonet.productsapi.service;


import com.femtonet.productsapi.payload.ProductResponse;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductResponse> getAllProducts(Map<String, String> reqParams) throws Exception;
}
