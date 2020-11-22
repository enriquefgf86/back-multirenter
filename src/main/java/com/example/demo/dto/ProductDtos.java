package com.example.demo.dto;

import com.example.demo.entiities.ImgsSubType;
import com.example.demo.entiities.Product;
import com.example.demo.entiities.Rent;
import com.example.demo.entiities.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class ProductDtos {

    @Autowired
    RenterDtos renterDtos;

    @Autowired
    RentDtos rentDtos;

    public Map<String,Object> makeProductDto(Product product){
        Map<String,Object>dto=new HashMap<>();



        dto.put("product_name",product.getProductName());
        dto.put("product_type",product.getProductType().getProductTypeName());
        dto.put("product_in_inventary",product.getProductInventary().getStateInventary());
        dto.put("product_name",product.getProductSubType().getProductSubTypeName());
        dto.put("product_imgs",product.getProductSubType().getListImgsInProduct().stream().map(service->makeProductSubDto(service)).collect(Collectors.toList()));
        dto.put("product_rents",product.getAllRentsOfUser().stream().map(service->rentDtos.makeRentDto(service)).collect(Collectors.toList()));
        return dto;
    }



    private Map<String,Object> makeRenterDto(Renter renter){
        Map<String,Object>dto=new HashMap<>();

        dto.put("renter_name",renter.getRenterName());

        return dto;
    }

    private Map<String,Object>makeRentDto(Rent rent){
        Map<String,Object>dto=new HashMap<>();

        dto.put("rent_id",rent.getId());
        dto.put("rent_cost",rent.getRentCost());
        dto.put("rent",rent.getRentDays());

        return dto;
    }

    private Map<String,Object>makeProductSubDto(ImgsSubType product){
        Map<String,Object>dto=new HashMap<>();

        dto.put("url",product.getUrl());

        return dto;
    }
}
