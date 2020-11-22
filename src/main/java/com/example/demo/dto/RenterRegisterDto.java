package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

public class RenterRegisterDto {

    @NotEmpty
    private String renterName ;
    @NotEmpty
    private String renterEmail;
    @NotEmpty
    private String renterPassword;
    @NotEmpty
    private String renterConfirmPassword;

    //////////////////////getters and setters/////////////////////////
    public String getRenterName() {  return renterName;  }
    public void setRenterName(String renterName) {   this.renterName = renterName;}

    public String getRenterEmail() {   return renterEmail;}
    public void setRenterEmail(String renterEmail) {   this.renterEmail = renterEmail;  }

    public String getRenterPassword() {    return renterPassword;}
    public void setRenterPassword(String renterPassword) {    this.renterPassword = renterPassword; }

    public String getRenterConfirmPassword() {     return renterConfirmPassword;  }
    public void setRenterConfirmPassword(String renterConfirmPassword) { this.renterConfirmPassword = renterConfirmPassword; }
}
