package com.example.demo.payload;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
@Component
public class LoginRequest {

    @NotBlank
    private String renterName;

    @NotBlank
    private String renterPassword;

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName;
    }

    public String getRenterPassword() {
        return renterPassword;
    }

    public void setRenterPassword(String renterPassword) {
        this.renterPassword = renterPassword;
    }
    //Esta clase seria el interface que recogeria los valores predeterminados  para el registro
    //del usuario, los cuales se pasarian como payload en los procesos de autenticacion y demas
}
