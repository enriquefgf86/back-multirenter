package com.example.demo.entiities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "ID")
            Long id;

    @Column(name="PRODUCT_NAME")
    private String productName;

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "product")
    private Set<Rent> listRentsInProduct=new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="RENTER_ID",nullable = false)
    private Renter renter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_TYPE_ID",nullable = false)
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_PRICE_ID",nullable = false)
    private ProductPrice productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_SUBTYPE_ID",nullable = false)
    private ProductSubType productSubType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_INVENTARY_ID",nullable = false)
    private ProductInventary productInventary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_FEE_DELAY_ID",nullable = false)
    private ProductFeeDelay productFeeDelay;

    /////////////////////////////constructor////////////////////////////
    public Product(){};
    public Product(Long id, String productName,
//                   Set<Rent> listRentsInProduct,
                   Renter renter, ProductType productType,ProductPrice productPrice,
                   ProductSubType productSubType, ProductInventary productInventary,
                   ProductFeeDelay productFeeDelay) {
        this.id = id;
        this.productName = productName;
        this.listRentsInProduct = listRentsInProduct;
        this.renter = renter;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productSubType = productSubType;
        this.productInventary = productInventary;
        this.productFeeDelay = productFeeDelay;
        renter.addProduct(this);
    }

    /////////////////////////other methods/////////////////////////////////
    public void addRent(Rent rent){
        listRentsInProduct.add(rent);
    }
    public Set<Rent>getAllRentsOfUser(){return listRentsInProduct;}

    ///////////////////////////getters and setters////////////////////////////////////
    public Long getId() {  return id; }
    public void setId(Long id) {  this.id = id; }

    public String getProductName() {  return productName;}
    public void setProductName(String productName) {  this.productName = productName;}

    public Set<Rent> getListRentsInProduct() {  return listRentsInProduct; }
    public void setListRentsInProduct(Set<Rent> listRentsInProduct) { this.listRentsInProduct = listRentsInProduct; }

    public Renter getRenter() { return renter;}
    public void setRenter(Renter renter) {   this.renter = renter; }

    public ProductType getProductType() {   return productType; }
    public void setProductType(ProductType productType) {    this.productType = productType; }

    public ProductPrice getProductPrice() {   return productPrice;}
    public void setProductPrice(ProductPrice productPrice) {   this.productPrice = productPrice;}

    public ProductSubType getProductSubType() {    return productSubType;}
    public void setProductSubType(ProductSubType productSubType) {   this.productSubType = productSubType;}

    public ProductInventary getProductInventary() {   return productInventary;}
    public void setProductInventary(ProductInventary productInventary) {    this.productInventary = productInventary;}

    public ProductFeeDelay getProductFeeDelay() { return productFeeDelay; }
    public void setProductFeeDelay(ProductFeeDelay productFeeDelay) { this.productFeeDelay = productFeeDelay; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", listRentsInProduct=" + listRentsInProduct +
                ", renter=" + renter +
                ", productType=" + productType +
                ", productPrice=" + productPrice +
                ", productSubType=" + productSubType +
                ", productInventary=" + productInventary +
                ", productFeeDelay=" + productFeeDelay +
                '}';
    }
}
