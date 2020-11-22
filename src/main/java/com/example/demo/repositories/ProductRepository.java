package com.example.demo.repositories;

import com.example.demo.entiities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAll();
    Optional<String > findByProductName(String productName);
    Optional<Product>findById(Long productId);
//    Boolean productIsRented(boolean productInventary);

}
