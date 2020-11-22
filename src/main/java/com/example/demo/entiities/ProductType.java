package com.example.demo.entiities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PRODUCT_TYPE")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name="PRODUCT_TYPE_NAME")
    private String productTypeName;

    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "productType")
    private List<Product> listProduct=new ArrayList<>();

    //////////////////////////////constructor//////////////////////
    public ProductType(){}
    public ProductType(Long id, String productTypeName, List<Product> listProduct) {
        this.id = id;
        this.productTypeName = productTypeName;
        this.listProduct = listProduct;
    }

    /////////////////getters y setters///////////////
    public Long getId() {    return id; }
    public void setId(Long id) {    this.id = id; }

    public String getProductTypeName() {return productTypeName; }
    public void setProductTypeName(String productTypeName) { this.productTypeName = productTypeName;}

    public List<Product> getListProduct() {return listProduct; }
    public void setListProduct(List<Product> listProduct) { this.listProduct = listProduct; }

    @Override
    public String toString() {
        return "ProductType{" +
                "id=" + id +
                ", productTypeName='" + productTypeName + '\'' +
                ", listProduct=" + listProduct +
                '}';
    }
}
