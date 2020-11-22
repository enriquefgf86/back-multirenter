package com.example.demo.entiities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "RENTER")
public class Renter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "RENTER_NAME")
    private String renterName;
    @Column(name = "RENTER_EMAIL")
    private String renterEmail;
    @Column(name = "RENTER_PASSWORD")
    private String renterPassword;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "renter")
    private List<Rent> listRents=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "renter")
    private List<Product> listProductsRented=new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="RENTER_ROLE",
            joinColumns = @JoinColumn(name = "RENTER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();


///////////////////////////////constructors/////////////
    public Renter(){}
    public Renter(Long id, String renterName, String renterEmail, String renterPassword) {
        this.id = id;
        this.renterName = renterName;
        this.renterEmail = renterEmail;
        this.renterPassword = renterPassword;

    }

    ////////////////////////////methods added//////////////////////
    public void addRent(Rent rent){
        listRents.add(rent);
    }

    public void addProduct(Product product){
        listProductsRented.add(product);
    }
    public List<Product>getAllProductsRentedByUser(){return listProductsRented;}

    //////////////////////////////getter and setters//////////////////////
    public Long getId() {  return id;    }
    public void setId(Long id) {  this.id = id;    }

    public String getRenterName() {   return renterName;    }
    public void setRenterName(String renterName) {   this.renterName = renterName;    }

    public String getRenbterEmail() {  return renterEmail;  }
    public void setRenterEmail(String renterEmail) { this.renterEmail = renterEmail; }

    public String getRenterPassword() {   return renterPassword; }
    public void setRenterPassword(String renterPassword) { this.renterPassword = renterPassword; }

    public String getRenterEmail() {   return renterEmail; }
    public Set<Role> getRoles() {   return roles;  }
    public void setRoles(Set<Role> roles) {   this.roles = roles;  }

    public List<Rent> getListRents() {   return listRents; }
    public void setListRents(List<Rent> listRents) {  this.listRents = listRents; }

    public List<Product> getListProductsRented() {   return listProductsRented; }
    public void setListProductsRented(List<Product> listProductsRented) {this.listProductsRented = listProductsRented;   }

    //    public Role getRole() {  return role; }
//    public void setRole(Role role) {  this.role = role; }


    @Override
    public String toString() {
        return "Renter{" +
                "id=" + id +
                ", renterName='" + renterName + '\'' +
                ", renterEmail='" + renterEmail + '\'' +
                ", renterPassword='" + renterPassword + '\'' +
                ", roles=" + roles +
                '}';
    }
}
