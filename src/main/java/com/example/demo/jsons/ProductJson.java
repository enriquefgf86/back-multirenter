package com.example.demo.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductJson {

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("productRenteBy")
    private List<RenterJson> productRentedBy;

    public String getProductName() {   return productName;}
    public void setProductName(String productName) {  this.productName = productName;}

    public List<RenterJson> getProductRentedBy() {   return productRentedBy; }
    public void setProductRentedBy(List<RenterJson> productRentedBy) { this.productRentedBy = productRentedBy;}
}
