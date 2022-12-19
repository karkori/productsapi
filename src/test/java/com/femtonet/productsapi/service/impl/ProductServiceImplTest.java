package com.femtonet.productsapi.service.impl;

import com.femtonet.productsapi.domain.product.Product;
import com.femtonet.productsapi.domain.product.Stock;
import com.femtonet.productsapi.payload.ProductResponse;
import com.femtonet.productsapi.repository.ProductRepository;
import com.femtonet.productsapi.service.ProductService;
import com.femtonet.productsapi.utils.ApiConstants;
import com.femtonet.productsapi.utils.mapper.ProductMapper;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties ")
public class ProductServiceImplTest {

    ProductService service;

    @Mock
    private ProductRepository repository;

    @Value("#{${product.criteria.weight}}")
    private Map<String, Integer> criteriasProps;

    @Before
    public void init() {
        service = new ProductServiceImpl();
        ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
        ReflectionTestUtils.setField(service, "repository", repository);
        ReflectionTestUtils.setField(service, "mapper", mapper);
        ReflectionTestUtils.setField(service, "criteriasProps", criteriasProps);
    }

    @Test
    public void givenProducts_whenGetProducts_thenReturnSortedList() throws Exception {
        //Given
        when(repository.findAll()).thenReturn(generateProducts());
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("saleUnits", "2");
        reqParams.put("stock", "1");
        //When
        List<ProductResponse> allProducts = service.getAllProducts(reqParams);
        //Then
        assertThat(allProducts).isNotNull();
        Assert.assertThat(allProducts, isInAscendingOrder());
    }

    @Test
    public void givenInvalidParams_whenGetProducts_thenThrowInvalidParamsException() {
        //Given
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("invalidParams1", "2");
        reqParams.put("invalidParams2", "1");
        try {
            //When
            service.getAllProducts(reqParams);
        } catch (Exception e) {
            //Then
            assertThat(e.getMessage()).isEqualTo(ApiConstants.ERROR_BAD_PARAMS);
        }
    }

    @Test
    public void givenDDBBError_whenGetProducts_thenThrowDDBBErrorException() {
        //Given
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put("saleUnits", "2");
        reqParams.put("stock", "1");
        ReflectionTestUtils.setField(service, "repository", null);
        try {
            //When
            service.getAllProducts(reqParams);
        } catch (Exception e) {
            //Then
            assertThat(e.getMessage()).isEqualTo(ApiConstants.ERROR_DDBB);
        }
    }

    private List<Product> generateProducts() {
        List<Product> products = Arrays.asList(
                new Product(1L, "V-NECK BASIC SHIRT", 100, new Stock(4, 9, 0)),
                new Product(2L, "CONTRASTING FABRIC T-SHIRT", 50, new Stock(35, 9, 9)),
                new Product(3L, "RAISED PRINT T-SHIRT", 80, new Stock(20, 2, 20)));
        return products;
    }

    private Matcher<? super List<ProductResponse>> isInAscendingOrder() {
        return new TypeSafeMatcher<List<ProductResponse>>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("The product list is not sorted!");
            }

            @Override
            protected boolean matchesSafely(List<ProductResponse> item) {
                for (int i = 0; i < item.size() - 1; i++) {
                    if (item.get(i).getSortPoint() >= item.get(i + 1).getSortPoint()) return false;
                }
                return true;
            }
        };
    }

}