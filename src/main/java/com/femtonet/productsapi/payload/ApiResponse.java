package com.femtonet.productsapi.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse implements Serializable {

    private String error;
    private List<ProductResponse> body;

    public ApiResponse(String error) {
        this.error = error;
    }

    public ApiResponse(List<ProductResponse> body) {
        this.body = body;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ProductResponse> getBody() {
        return body;
    }

    public void setBody(List<ProductResponse> body) {
        this.body = body;
    }
}
