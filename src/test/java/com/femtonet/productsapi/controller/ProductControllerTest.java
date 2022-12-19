package com.femtonet.productsapi.controller;

import com.femtonet.productsapi.domain.product.Product;
import com.femtonet.productsapi.domain.product.Stock;
import com.femtonet.productsapi.payload.ProductResponse;
import com.femtonet.productsapi.payload.StockResponse;
import com.femtonet.productsapi.service.ProductService;
import com.femtonet.productsapi.utils.ApiConstants;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService service;

    @Test
    void givenProduct_whenGetProducts_thenReturnJsonArray() throws Exception {

        //Given
        ProductResponse product = new ProductResponse(1L, "product_name1", 200, new StockResponse(5, 1, 9));
        List<ProductResponse> allProducts = Arrays.asList(product);

        given(service.getAllProducts(anyMap())).willReturn(allProducts);


        //when
        mvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("saleUnits", "2")
                        .param("stock, ", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("body[0].id", Matchers.is(1)))
                .andExpect(jsonPath("body[0].name", Matchers.is("product_name1")))
                .andExpect(jsonPath("body[0].saleUnits", Matchers.is(200)))
                .andExpect(jsonPath("body[0].stock", Matchers.hasToString("{s=5, m=1, l=9}")));
    }

    @Test
    void givenBadParams_whenGetAllProducts_thenReturnBadRequestMessage() throws Exception {
        //Given
        given(service.getAllProducts(anyMap())).willThrow(new Exception(ApiConstants.ERROR_BAD_PARAMS));

        //when
        mvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("invalidParam1", "2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("error", Matchers.is(ApiConstants.ERROR_BAD_PARAMS)));
    }

    @Test
    void givenDDBBError_whenGetAllProducts_thenReturnDDBBErrorMessage() throws Exception {
        //Given
        given(service.getAllProducts(anyMap())).willThrow(new Exception(ApiConstants.ERROR_DDBB));

        //when
        mvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("saleUnits", "2")
                        .param("stock, ", "1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("error", Matchers.is(ApiConstants.ERROR_DDBB)));
    }
}