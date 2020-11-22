package com.example.demo.entiities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PRODUCT_SUBTYPE")
public class ProductSubType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name="PRODUCT_SUBTYPE_NAME")
    private String productSubTypeName;

    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "productSubType")
    private List<Product> listProduct=new ArrayList<>();

    @OneToMany(fetch=FetchType.LAZY,mappedBy = "productSubType")
    private List<ImgsSubType> listImgsInProduct=new ArrayList<>();


    //////////////////////////////constructor//////////////////////
    public ProductSubType(){}
    public ProductSubType(Long id, String productSubTypeName, List<Product> listProduct,
                          List<ImgsSubType> listImgsInProduct) {
        this.id = id;
        this.productSubTypeName = productSubTypeName;
        this.listProduct = listProduct;
        this.listImgsInProduct = listImgsInProduct;
    }

    ///////////////////////getters y setters///////////////////////
    public Long getId() {  return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductSubTypeName() { return productSubTypeName; }
    public void setProductSubTypeName(String productSubTypeName) { this.productSubTypeName = productSubTypeName; }

    public List<Product> getListProduct() { return listProduct; }
    public void setListProduct(List<Product> listProduct) { this.listProduct = listProduct;}

    public List<ImgsSubType> getListImgsInProduct() {  return listImgsInProduct;}
    public void setListImgsInProduct(List<ImgsSubType> listImgsInProduct) { this.listImgsInProduct = listImgsInProduct; }

    @Override
    public String toString() {
        return "ProductSubType{" +
                "id=" + id +
                ", productSubTypeName='" + productSubTypeName + '\'' +
                ", listProduct=" + listProduct +
                ", listImgsInProduct=" + listImgsInProduct +
                '}';
    }
}
