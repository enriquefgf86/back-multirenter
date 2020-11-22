package com.example.demo.entiities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PRODUCT_PRICE")
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name="PRICE_PER_DAY")
    private Double pricePerDay;

    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "productPrice")
    private List<Product> listProduct=new ArrayList<>();

    //////////////////////////////constructor//////////////////////
    public ProductPrice(){}
    public ProductPrice(Long id, Double pricePerDay, List<Product> listProduct) {
        this.id = id;
        this.pricePerDay = pricePerDay;
        this.listProduct = listProduct;
    }

    /////////////////getters and setters/////////////////////////////
    public Long getId() { return id; }
    public void setId(Long id) {  this.id = id; }

    public Double getPricePerDay() {  return pricePerDay; }
    public void setPricePerDay(Double pricePerDay) {  this.pricePerDay = pricePerDay; }

    public List<Product> getListProduct() {  return listProduct; }
    public void setListProduct(List<Product> listProduct) { this.listProduct = listProduct; }

    @Override
    public String toString() {
        return "ProductPrice{" +
                "id=" + id +
                ", pricePerDay=" + pricePerDay +
                ", listProduct=" + listProduct +
                '}';
    }
}
