package com.example.demo.entiities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PRODUCT_FEE_DELAy")
public class ProductFeeDelay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name="PRODUCT_FEE_DELAY_PER_DAY")
    private Double productFeeDelayPerDay;


    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "productFeeDelay")
    private List<Product> listProduct=new ArrayList<>();

    public ProductFeeDelay(){}

    public ProductFeeDelay(Long id, Double productFeeDelayPerDay, List<Product> listProduct) {
        this.id = id;
        this.productFeeDelayPerDay = productFeeDelayPerDay;
        this.listProduct = listProduct;
    }

    //////////////////////////getters y setters////////////////////////////

    public Long getId() { return id;}
    public void setId(Long id) {  this.id = id; }

    public Double getProductFeeDelayPerDay() { return productFeeDelayPerDay; }
    public void setProductFeeDelayPerDay(Double productFeeDelayPerDay) {this.productFeeDelayPerDay = productFeeDelayPerDay; }

    public List<Product> getListProduct() { return listProduct; }
    public void setListProduct(List<Product> listProduct) { this.listProduct = listProduct; }

    @Override
    public String toString() {
        return "ProductFeeDelay{" +
                "id=" + id +
                ", productFeeDelayPerDay=" + productFeeDelayPerDay +
                ", listProduct=" + listProduct +
                '}';
    }
}
