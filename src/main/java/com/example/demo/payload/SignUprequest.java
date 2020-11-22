package com.example.demo.payload;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Component
public class SignUprequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String renterName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String renterEmail;

    private Set<String> roleType;

    @NotBlank
    @Size(min = 6, max = 40)
    private String renterPassword;

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

    public Set<String> getRoleType() {
        return roleType;
    }

    public void setRoleType(Set<String> roleType) {
        this.roleType = roleType;
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
