package com.femtonet.productsapi.cucumber;

import com.femtonet.productsapi.payload.ProductResponse;
import com.femtonet.productsapi.service.ProductService;
import com.femtonet.productsapi.utils.ApiConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.femtonet.productsapi.utils.TestUtils.isInAscendingOrder;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductStepDefs extends SpringTest {

    @Autowired
    ProductService service;

    Map<String, String> reqParams = new HashMap<>();
    List<ProductResponse> response;
    String errorMessage;

    @Given("I have valid params, saleUnits: {word} and stock: {word}")
    public void InitValidParams(String arg0, String arg1) {
        reqParams.put("saleUnits", arg0);
        reqParams.put("stock", arg1);
    }

    @When("I call the getProducts service")
    public void callProductsService() {
        try {
            response = service.getAllProducts(reqParams);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I assert that I will get a sorted product list")
    public void assertProductListIsSorted() {
        assertThat(response).isNotNull();
        Assert.assertThat(response, isInAscendingOrder());
    }

    @Given("I have invalid params, saleUnits: {word} and stock: {word}")
    public void initInvalidParams(String arg0, String arg1) {
        reqParams.put("badParam1", arg0);
        reqParams.put("badParam2", arg1);
    }

    @Then("I assert that I will get an invalid params exception")
    public void assertInvalidParamsException() {
        assertThat(errorMessage).isEqualTo(ApiConstants.ERROR_BAD_PARAMS);
    }
}
