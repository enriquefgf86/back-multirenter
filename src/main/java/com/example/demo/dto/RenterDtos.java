package com.example.demo.dto;

import com.example.demo.entiities.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RenterDtos {
    @Autowired
    RoleDtos roleDtos;

    @Autowired
    ProductDtos productDtos;

    public Map<String,Object> makeRenterDto(Renter renter){
        Map<String,Object>dto=new HashMap<>();

        dto.put("user_name",renter.getRenterName());
        dto.put("user_role",renter.getRoles().stream().map(service->roleDtos.makeRoleDto(service)).collect(Collectors.toList()));
        dto.put("user",renter.getAllProductsRentedByUser().stream().map(service->productDtos.makeProductDto(service)).collect(Collectors.toList()));
        return dto;
    }
}
