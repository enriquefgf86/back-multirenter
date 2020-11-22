package com.example.demo.entiities;

import javax.persistence.*;

@Entity
@Table( name="IMGS_SUBTYPE")
public class ImgsSubType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name="URL")
        private String url;


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="PRODUCT_SUBTYPE_ID",nullable = false)
    private ProductSubType productSubType;

    ////////////////////constructor////////////////////////
    public  ImgsSubType(){}
    public ImgsSubType(Long id, String url, ProductSubType productSubType) {
        this.id = id;
        this.url = url;
        this.productSubType = productSubType;
    }
    ////////////////////////////getters and setters/////////////////

    public Long getId() {   return id; }
    public void setId(Long id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) {  this.url = url;}

    public ProductSubType getProductSubType() {  return productSubType; }
    public void setProductSubType(ProductSubType productSubType) { this.productSubType = productSubType; }

    @Override
    public String toString() {
        return "ImgsSubType{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", productSubType=" + productSubType +
                '}';
    }
}
