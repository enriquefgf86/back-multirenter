package com.example.demo.services;

import com.example.demo.dto.RentDtos;
import com.example.demo.entiities.Product;
import com.example.demo.entiities.ProductInventary;
import com.example.demo.entiities.Rent;
import com.example.demo.entiities.Renter;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ProductInventaryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class RentServiceImpl implements RentService {


    @Autowired
    RentRepository rentRepository;

    @Autowired
    ProductInventaryRepository productInventaryRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RentDtos rentDtos;


    @Override
    public Map<String, Object> getAllRents() {

        Map<String, Object> dto=new HashMap<>();
         final List<Rent> rents=rentRepository.findAll();

         dto.put("all_rents",rents.stream().map(service->rentDtos.makeRentDto(service)).collect(Collectors.toList()));

        return dto;
    }

    @Override
    public String CreateRentOfProduct(@RequestBody Rent rent, @PathVariable Long productId) throws GeneralException {

        Rent newRent=new Rent();
        Long inventary=Long.valueOf(1);
        ProductInventary productInventary=productInventaryRepository.findById(inventary).orElseThrow(()->new NotFoundException("State","State not gfound"));



        Product productForRent=productRepository.findById(productId).orElseThrow(()->new NotFoundException("ERROR","This Product Doesn't exist"));

        if(productForRent.getProductInventary().getStateInventary().equals(true)) {
            System.out.println("Product is already being rented");
            return "Error,Product is already being rented";
        }
        else{
//            newRent.setId(rent.getId());
            productForRent.setProductInventary(productInventary);
            newRent.setRentDays(rent.getRentDays());
            newRent.setRentCost(rent.getRentDays()*productForRent.getProductPrice().getPricePerDay());
            newRent.setRentCostDelay(0.0);
            newRent.setRentRealDays(rent.getRentDays());
            newRent.setRentRealCost(rent.getRentDays()*productForRent.getProductPrice().getPricePerDay());
            newRent.setRenter(rent.getRenter());
            newRent.setProduct(productForRent);
            newRent.setRentClosed(false);

            productRepository.save(productForRent);
            rentRepository.save(newRent);

            System.out.println("product rented");
             return "product rented";
        }
}

    @Override
    public String DevolutionOfProductRent(@RequestBody Rent rent,@PathVariable Long rentId, @PathVariable Long productId) throws GeneralException {

       Long inventary=Long.valueOf(2);
        ProductInventary productInventary=productInventaryRepository.findById(inventary).orElseThrow(()->new NotFoundException("State","State not gfound"));

        Rent rentDevolution=rentRepository.findById(rentId).orElseThrow(()->new NotFoundException("Error","Doesnt exist this rent"));

        Product productToDevolution=productRepository.findById(productId).orElseThrow(()->new NotFoundException("Error","Doesnt exist this product"));

        rentDevolution.setRentRealDays(rent.getRentRealDays());

        rentDevolution.setRentCostDelay((rent.getRentRealDays()-rentDevolution.getRentDays())
                *productToDevolution.getProductFeeDelay().getProductFeeDelayPerDay());

        rentDevolution.setRentRealCost(((rent.getRentRealDays()-rentDevolution.getRentDays())
                *productToDevolution.getProductFeeDelay().getProductFeeDelayPerDay())
                +rentDevolution.getRentCost());

        rentDevolution.setRentClosed(true);

        productToDevolution.setProductInventary(productInventary);

        rentRepository.save(rentDevolution);
        productRepository.save(productToDevolution);

        System.out.println("Devolution already done");

        return "Devolution already done";
    }
}

