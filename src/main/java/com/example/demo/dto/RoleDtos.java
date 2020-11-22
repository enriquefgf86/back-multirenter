package com.example.demo.dto;

import com.example.demo.entiities.Role;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RoleDtos {

    public Map<String,Object> makeRoleDto(Role role){
        Map<String,Object>dto=new HashMap<>();

        dto.put("role_id",role.getId());
        dto.put("role_type",role.getRoleType());
        return dto;
    }
}
