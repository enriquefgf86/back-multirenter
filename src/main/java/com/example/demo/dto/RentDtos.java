package com.example.demo.dto;

import com.example.demo.entiities.Rent;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class RentDtos {
    public Map<String,Object> makeRentDto(Rent rent){
        Map<String,Object>dto=new HashMap<>();

        dto.put("rent_id",rent.getId());
        dto.put("rent_days",rent.getRentDays());
        dto.put("rent_costs",rent.getRentCost());
        dto.put("rent_by",rent.getRenter().getRenterName());
        dto.put("rent_real_days",rent.getRentRealDays());
        dto.put("rent_real_cost",rent.getRentRealCost());

        return dto;
    }
}
