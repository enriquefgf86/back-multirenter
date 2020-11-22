package com.example.demo.services;

import com.example.demo.entiities.Rent;
import com.example.demo.exceptions.GeneralException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

public interface RentService {

    Map<String,Object> getAllRents();

    String CreateRentOfProduct(Rent rent,Long productId) throws GeneralException;
    String DevolutionOfProductRent(Rent rent, Long rentId, Long productId)throws GeneralException;
}
