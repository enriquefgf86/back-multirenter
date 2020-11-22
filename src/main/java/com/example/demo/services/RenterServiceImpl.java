package com.example.demo.services;

import com.example.demo.dto.RenterDtos;
import com.example.demo.entiities.EnumRoles;
import com.example.demo.entiities.Renter;
import com.example.demo.entiities.Role;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.jsons.RenterJson;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.SignUprequest;
import com.example.demo.repositories.RenterRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.responses.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpServerErrorException;

import java.util.*;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;

import javax.validation.Valid;

@Service
public class RenterServiceImpl implements RenterService {

    Authentication auth3;

    @Autowired
    private RenterRepository renterRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RenterDtos renterDtos;

    @Autowired
    private SignUprequest signUprequest;

    public static final ModelMapper modelMapper = new ModelMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(RenterServiceImpl.class);


    public Map<String,Object> getAllRenters()  {
        Map<String,Object>dto=new HashMap<>();
        final List<Renter> renters=renterRepository.findAll();
         dto.put("all_renters",renters.stream().map(service -> renterDtos.makeRenterDto(service))
                .collect(Collectors.toList()));
         return dto;
    }

    public Renter findByEmail(String renterEmail) throws GeneralException {
        return renterRepository.findByRenterEmail(renterEmail).orElseThrow(() -> new NotFoundException("SNOT_404_1", "EmailNotFound"));
    }


    public Renter findRenterById(Long id) throws GeneralException {
        return renterRepository.findById(id).orElseThrow(() -> new NotFoundException("SNOT", "Id not found"));
    }

    public String save(SignUprequest signUprequest) {

        Renter renter=new Renter();
        renter.setRenterName(signUprequest.getRenterName());
        renter.setRenterEmail(signUprequest.getRenterEmail());
        renter.setRenterPassword(passwordEncoder.encode((signUprequest.getRenterPassword())));

        Set<String> rolesString=signUprequest.getRoleType();
        Set<Role> roles=new HashSet<>();

        if (rolesString== null) {
            Role renterRole = roleRepository.findByRoleType(EnumRoles.ROLE_RENTER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(renterRole);
        }
        else {
            rolesString.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleType(EnumRoles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByRoleType(EnumRoles.ROLE_RENTER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        renter.setRoles(roles);
        renterRepository.save(renter);

        return "user created";
    }

    @Override
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) throws GeneralException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getRenterName(), loginRequest.getRenterPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    private static List<GrantedAuthority> mapRoleUser(List<String> roles){
        List<GrantedAuthority>authorities=new ArrayList<>();
        for (String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
