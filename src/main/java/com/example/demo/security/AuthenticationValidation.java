package com.example.demo.security;

import com.example.demo.entiities.Renter;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationValidation {

    @Autowired
    RenterRepository renterRepository;

//    @Autowired
//    Authentication authentication;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    public Renter securityUser()throws NotFoundException{
//
//        return renterRepository.findByRenterName(authentication.getName()).orElseThrow(()->new NotFoundException("SError","EmailNotFound"));
////        return renterRepository.findByRenterName(authentication.getName()).orElseThrow(()->new NotFoundException("SError","EmailNotFound"));
//    }
}
