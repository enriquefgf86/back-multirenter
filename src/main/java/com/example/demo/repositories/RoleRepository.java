package com.example.demo.repositories;

import com.example.demo.entiities.EnumRoles;
import com.example.demo.entiities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleType(String roleType);

   Optional<Role > findById(Long id);
   Optional<Role> findByRoleType(EnumRoles role);
}
