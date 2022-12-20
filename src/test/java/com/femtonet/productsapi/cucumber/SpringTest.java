package com.femtonet.productsapi.cucumber;

import com.femtonet.productsapi.ProductsApiApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = ProductsApiApplication.class)
public class SpringTest {
}
