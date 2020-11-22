package com.example.demo.repositories;

import com.example.demo.entiities.ProductInventary;
import com.example.demo.entiities.Renter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductInventaryRepository extends JpaRepository<ProductInventary,Long> {
    Optional<ProductInventary> findById(Long id);
}
