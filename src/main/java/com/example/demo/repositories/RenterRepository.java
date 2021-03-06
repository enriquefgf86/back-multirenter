package com.example.demo.repositories;

import com.example.demo.entiities.Renter;
import com.example.demo.entiities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RenterRepository extends JpaRepository <Renter,Long>{
    Optional<Renter>findByRenterEmail(String renterEmail);
    Optional<Renter> findByRenterName(String renterName);
    Optional <Renter> findById(Long id);
    boolean existsByRenterEmail(String renterEmail);
    boolean existsByRenterName(String renterName);
    List<Renter> findAll();

}
