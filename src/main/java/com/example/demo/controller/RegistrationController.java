package com.example.demo.controller;


import com.example.demo.dto.RenterRegisterDto;
import com.example.demo.entiities.EnumRoles;
import com.example.demo.jsons.CreateRenterJson;
import com.example.demo.jsons.RenterJson;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.SignUprequest;
import com.example.demo.repositories.RenterRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.responses.AppResponse;
import com.example.demo.entiities.Renter;
import com.example.demo.entiities.Role;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.responses.JwtResponse;
import com.example.demo.services.RenterService;
import com.example.demo.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cubancoder/multirenter")
public class RegistrationController {

    @Autowired
    RenterService renterService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RenterRepository renterRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

     public static final Logger logger= LoggerFactory.getLogger(RegistrationController.class);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/register/renter")
    public AppResponse<String> createUser(@Valid @RequestBody SignUprequest signUprequest) throws GeneralException {

        if (renterRepository.existsByRenterName(signUprequest.getRenterName())) {
            return new AppResponse("Error",String.valueOf(HttpStatus.CONFLICT),"error","That user name exists");
        }

        if (renterRepository.existsByRenterEmail(signUprequest.getRenterEmail())) {
            return new AppResponse("Error",String.valueOf(HttpStatus.CONFLICT),"error","That user email exists");
        }

//        Renter renter=new Renter();
//
//        renter.setRenterName(signUprequest.getRenterName());
//        renter.setRenterEmail(signUprequest.getRenterEmail());
//        renter.setRenterPassword(passwordEncoder.encode((signUprequest.getRenterPassword())));
//
//        Set<String> rolesString=signUprequest.getRoleType();
//        Set<Role> roles=new HashSet<>();
//
//        if (rolesString== null) {
//            Role renterRole = roleRepository.findByRoleType(EnumRoles.ROLE_RENTER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(renterRole);
//        }
//        else {
//            rolesString.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByRoleType(EnumRoles.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//
//                    default:
//                        Role userRole = roleRepository.findByRoleType(EnumRoles.ROLE_RENTER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//        renter.setRoles(roles);
//        renterRepository.save(renter);

        return new AppResponse<>("Success",String.valueOf(HttpStatus.CREATED),"Ok",renterService.save(signUprequest));
    }
    //En este caso el acceso al endpoint register/renter daria paso a la creacion de un usuario.Para ello se
    //hace necesario recibir un RequestBody con la data necesaria que incluya la informacion del usuario registandose
    //en cuestion.Para ello se accede a uno de los componentes de la aplicacion del package de payloads
    //especificamente Signup request, para de el extraer todo lo necesario referente al proceso.
    //Al demarcarse en el servicio que dicho respuesta seria de tipo string notificando la creacion
    //del usuario en cuestion, el Appresponse tambien seria disenado de esa manera de ahi que en su data
    //se acceda al servicio pertinente (renterService ) y de al al metodo save el cual devuelve un string ,pasandosele
    //como parametro el payload signUpRequest

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login/renter")
    public AppResponse<ResponseEntity<?>>  logInUser(@Valid @RequestBody LoginRequest loginRequest) throws GeneralException {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getRenterName(), loginRequest.getRenterPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                roles));
        return new AppResponse<>("Success",String.valueOf(HttpStatus.CREATED),
                "Ok",renterService.loginUser(loginRequest));

    }
    //En este caso el acceso al endpoint login/renter daria paso al log in  de un usuario registrado.Para ello se
    //hace necesario recibir un RequestBody con la data necesaria que incluya la informacion del usuario loggeandose
    //en cuestion.Para ello se accede a uno de los componentes de la aplicacion del package de payloads
    //especificamente loginrequest, para de el extraer todo lo necesario referente al proceso.
    //Al demarcarse en el servicio que dicho respuesta seria de tipo Response Entity con una serie de datos tales
    // como el token generado por el usuario registrado nombre y demas   el Appresponse tambien seria disenado de
    // esa manera de ahi que en su data se acceda al servicio pertinente (renterService ) y de al al metodo
    // loginuser que  devuelve un ResponseEntity,pasandosele como parametro el payload loginRequest



//    @RequestMapping(value="/logout", method=RequestMethod.GET)
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//
//        return "user logged Out";
//    }

//    @PostMapping("/logout")
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    @PreAuthorize("hasRole('RENTER_ROLE')  or hasRole('ADMIN_ROLE')")
    public AppResponse<ResponseEntity<?>>  logoutUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);
        return new AppResponse<>("Success",String.valueOf(HttpStatus.CREATED),"Ok",ResponseEntity.ok("User logged out"));
    }

    private Map<String,Object>makeResponseEntity(String key,Object value){
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}

