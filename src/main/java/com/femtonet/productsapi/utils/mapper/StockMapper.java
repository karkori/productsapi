package com.femtonet.productsapi.utils.mapper;

import com.femtonet.productsapi.domain.product.Stock;
import com.femtonet.productsapi.payload.StockResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockResponse stockToStockResponse(Stock stock);
}
