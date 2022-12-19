package com.femtonet.productsapi.domain.product;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STOCKS")
@EqualsAndHashCode
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_STOCK")
    private Long id;

    private Integer s;
    private Integer m;
    private Integer l;

    public Stock(){}

    public Stock(Integer s, Integer m, Integer l) {
        this.s = s;
        this.m = m;
        this.l = l;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", s=" + s +
                ", m=" + m +
                ", l=" + l +
                '}';
    }
}
