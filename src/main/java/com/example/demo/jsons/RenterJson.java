package com.example.demo.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RenterJson {

    @JsonProperty("renterName")
    private String renterName;

    @JsonProperty("renterEmail")
    private String renterEmail;

    @JsonProperty("renterPassword")
    private String renterPassword;

    @JsonProperty("renterRentedProducts")
    private List<ProductJson>productJsonList;

    @JsonProperty("roleType")
    private String roleType;
    /////////////////////getters and setters/////////////////////////////////////


    public List<ProductJson> getProductJsonList() {
        return productJsonList;
    }

    public void setProductJsonList(List<ProductJson> productJsonList) {
        this.productJsonList = productJsonList;
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getRenterEmail() {
        return renterEmail;
    }

    public void setRenterEmail(String renterEmail) {
        this.renterEmail = renterEmail;
    }

    public String getRenterPassword() {
        return renterPassword;
    }

    public void setRenterPassword(String renterPassword) {
        this.renterPassword = renterPassword;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}


