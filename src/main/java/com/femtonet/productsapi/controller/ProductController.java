package com.femtonet.productsapi.controller;

import com.femtonet.productsapi.payload.ApiResponse;
import com.femtonet.productsapi.service.ProductService;
import com.femtonet.productsapi.utils.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts(@RequestParam Map<String, String> reqParams) {
        try {
            return new ResponseEntity<>(new ApiResponse(service.getAllProducts(reqParams)), HttpStatus.OK);
        } catch (Exception e) {
            HttpStatus status = ApiConstants.ERROR_BAD_PARAMS.equals(e.getMessage()) ?
                    HttpStatus.BAD_REQUEST :
                    HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), status);
        }
    }
}
