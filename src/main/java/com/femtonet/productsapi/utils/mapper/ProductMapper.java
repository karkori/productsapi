package com.femtonet.productsapi.utils.mapper;

import com.femtonet.productsapi.domain.product.Product;
import com.femtonet.productsapi.payload.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse productToProductResponse(Product product);
}
