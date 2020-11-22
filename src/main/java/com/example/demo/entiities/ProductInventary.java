package com.example.demo.entiities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PRODUCT_INVENTARY")
public class ProductInventary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name="STATE_INVENTARY")
    private Boolean stateInventary;

    @Column(name="STATE_NAME")
    private String stateName;

    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "productInventary")
    private List<Product> listProduct=new ArrayList<>();

    public ProductInventary(){}

    public ProductInventary(Long id, Boolean stateInventary,String stateName, List<Product> listProduct) {
        this.id = id;
        this.stateInventary = stateInventary;
        this.listProduct = listProduct;
        this.stateName=stateName;
    }

    ///////////////////////////setters y getters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Boolean getStateInventary() { return stateInventary; }
    public void setStateInventary(Boolean stateInventary) { this.stateInventary = stateInventary; }

    public String getStateName() { return stateName; }
    public void setStateName(String stateName) { this.stateName = stateName; }

    public List<Product> getListProduct() { return listProduct; }
    public void setListProduct(List<Product> listProduct) { this.listProduct = listProduct;}

    @Override
    public String toString() {
        return "ProductInventary{" +
                "id=" + id +
                ", stateInventary=" + stateInventary +
                ", listProduct=" + listProduct +
                '}';
    }
}
