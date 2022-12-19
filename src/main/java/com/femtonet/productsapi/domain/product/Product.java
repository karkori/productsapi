package com.femtonet.productsapi.domain.product;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
@EqualsAndHashCode
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "PRODUCT_NAME")
    private String name;
    @Column(name = "SALE_UNITS")
    private Integer saleUnits;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_STOCK")
    private Stock stock;


    public Product() {
    }

    public Product(String name, Integer saleUnits, Stock stock) {
        this.name = name;
        this.saleUnits = saleUnits;
        this.stock = stock;
    }

    public Product(Long id, String name, Integer saleUnits, Stock stock) {
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", saleUnits=" + saleUnits +
                ", stock=" + stock +
                '}';
    }
}
