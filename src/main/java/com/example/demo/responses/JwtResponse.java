package com.example.demo.responses;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String renterName;
    private String renterEmail;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String renterName, String renterEmail, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.renterName = renterName;
        this.renterEmail= renterEmail;
        this.roles = roles;
    }
    /////////////////////////////////getters y setters//////////////////////////////
    public String getToken() { return token;}
    public void setToken(String token) {  this.token = token; }

    public String getType() { return type;}
    public void setType(String type) {  this.type = type;}

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id; }

    public String getRenterName() {return renterName; }
    public void setRenterName(String renterName) { this.renterName = renterName;}

    public String getRenterEmail() { return renterEmail; }
    public void setRenterEmail(String renterEmail) { this.renterEmail = renterEmail; }

    public List<String> getRoles() {return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}
