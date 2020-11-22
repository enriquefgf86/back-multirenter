package com.example.demo.entiities;

import javax.persistence.*;

@Entity
@Table(name="RENT")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name="RENT_COST")
    private Double rentCost;

    @Column(name="RENT_COST_DELAY")
    private Double rentCostDelay;

    @Column(name="RENT_DAYS")
    private int rentDays;

    @Column(name="RENT_CLOSED")
    private boolean rentClosed;

    @Column(name="RENT_REAL_DAYS")
    private int rentRealDays;

    @Column(name="RENT_REAL_COST")
    private Double rentRealCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="RENTER_ID",nullable = false)
    private Renter renter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID",nullable = false)
    private Product product;

    //////////constructors///////////////////////
    public Rent(){};
    public Rent( Double rentCost, Double rentCostDelay, int rentDays, int rentRealDays, Double rentRealCost,Boolean rentClosed, Renter renter, Product product) {
//        this.id = id;
        this.rentCost = rentCost;
        this.rentCostDelay = rentCostDelay;
        this.rentDays = rentDays;
        this.rentRealDays = rentRealDays;
        this.rentRealCost = rentRealCost;
        this.renter = renter;
        this.product = product;
        this.rentClosed=rentClosed;
        renter.addRent(this);
        product.addRent(this);
    }


    /////////////////////////////getters y setters////////////////////////////////
    public Long getId() {    return id;}
    public void setId(Long id) {    this.id = id; }

    public Double getRentCost() {    return rentCost; }
    public void setRentCost(Double rentCost) {   this.rentCost = rentCost;}

    public Double getRentCostDelay() {    return rentCostDelay;  }
    public void setRentCostDelay(Double rentCostDelay) {    this.rentCostDelay = rentCostDelay;  }

    public int getRentDays() {    return rentDays; }
    public void setRentDays(int rentDays) {   this.rentDays = rentDays;}

    public int getRentRealDays() {  return rentRealDays;}
    public void setRentRealDays(int rentRealDays) {   this.rentRealDays = rentRealDays;}

    public Double getRentRealCost() {    return rentRealCost; }
    public void setRentRealCost(Double rentRealCost) {   this.rentRealCost = rentRealCost; }

    public boolean isRentClosed() { return rentClosed; }
    public void setRentClosed(boolean rentClosed) { this.rentClosed = rentClosed; }

    public Renter getRenter() {   return renter; }
    public void setRenter(Renter renter) {    this.renter = renter; }

    public Product getProduct() {    return product; }
    public void setProduct(Product product) {    this.product = product;  }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", rentCost=" + rentCost +
                ", rentCostDelay=" + rentCostDelay +
                ", rentDays=" + rentDays +
                ", rentRealDays=" + rentRealDays +
                ", rentRealCost=" + rentRealCost +
                ", renter=" + renter +
                ", product=" + product +
                '}';
    }
}
