package com.example.demo.repositories;

import com.example.demo.entiities.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {
    Optional<Rent> findById(Long rentId);
}
