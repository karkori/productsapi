package com.femtonet.productsapi.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse implements Serializable{

    private Long id;
    private String name;
    private Integer saleUnits;
    private StockResponse stock;

    @JsonIgnore
    private Integer sortPoint;

    public ProductResponse(Long id, String name, Integer saleUnits, StockResponse stock) {
        this.id = id;
        this.name = name;
        this.saleUnits = saleUnits;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSaleUnits() {
        return saleUnits;
    }

    public void setSaleUnits(Integer saleUnits) {
        this.saleUnits = saleUnits;
    }

    public StockResponse getStock() {
        return stock;
    }

    public void setStock(StockResponse stock) {
        this.stock = stock;
    }

    public Integer getSortPoint() {
        return sortPoint;
    }

    public void setSortPoint(Integer sortPoint) {
        this.sortPoint = sortPoint;
    }

}
