package com.example.demo.services;

import com.example.demo.dto.ProductDtos;
import com.example.demo.dto.RenterDtos;
import com.example.demo.entiities.*;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.jwt.AuthEntryPointJwt;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.payload.LoginRequest;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.RenterRepository;
import com.example.demo.security.AuthenticationValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    public static final ModelMapper modelMapper = new ModelMapper();
//    @Autowired
//    AuthenticationProvider authenticationProvider;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    ProductDtos productDtos;

     @Autowired
    AuthEntryPointJwt authEntryPointJwt;

    @Autowired
    RenterDtos renterDtos;

    @Autowired
    RenterRepository renterRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    LoginRequest loginRequest;

    public Map<String, Object> getAllProducts() throws GeneralException {
        Map<String, Object> dto = new HashMap<>();
        //implementacion del metodo que trae todos los productos a manera de un objeto
        //mapeable

        List<Product> listProducts = productRepository.findAll();
        //En este accediendose al repositorio de productos se acceden a todos los
        //productos .Esto se le asigana a una variable llamada listProducts de tipo
        //List con contenido de productos

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Se inicializa una variable de tipo Authentication de
        // org.springframework.security.core.Authentication; llamada auth
        //trayendo una instancia del posible usuario autenticado

        if (auth.isAuthenticated()) {
            Object Authenticated = auth.getPrincipal();
            String renterLogged = ((UserDetails) Authenticated).getUsername();
            Renter renter = renterRepository.findByRenterName(renterLogged).orElseThrow(() -> new NotFoundException("SError", "User Not Found"));
            dto.put("renter", renterDtos.makeRenterDto(renter));
        }
        //teniendo en cuanta la importacion de la instancia del usuario aparentemente
        //loggeado a traves del SecurityContextHolder.getContext().getAuthentication() se procederia
        //a establecer una condicionante que accediendo a dicha instancia  establece si hay
        //alquien autenticado, en donde de ser asi se procedria a establecer  el objeto con dicha
        //data  getPrincipal(), y a traves del mismo  se accederia al nombre del renter en cuestion
        //y con dicho nombre se haria un query sobre el renterrepository  obteneindose  el renter
        //dentro de la entidad que coincida con el query, para posteriormente  proceder a establecer
        //un dto  con los datos del renter en cuestion.
        else {
            dto.put("renter", null);
        }//de no cunplirse lo anterior simplemente se retorna un usuario nulo

        dto.put("list_ofProducts", listProducts.stream().map(service -> productDtos.makeProductDto(service)).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public String createProductForRent(@RequestBody Product product) throws GeneralException {

        Product newProduct = new Product();

        newProduct.setId(product.getId());
        newProduct.setProductName(product.getProductName());
        newProduct.setRenter(product.getRenter());
        newProduct.setProductType(product.getProductType());
        newProduct.setProductPrice(product.getProductPrice());
        newProduct.setProductSubType(product.getProductSubType());
        newProduct.setProductInventary(product.getProductInventary());
        newProduct.setProductFeeDelay(product.getProductFeeDelay());
        productRepository.save(newProduct);

        System.out.println("product created");
        return "Product Created";
    }

    private Renter securityUser(Authentication auth) throws NotFoundException {

        return renterRepository.findByRenterName(auth.getName()).orElseThrow(() -> new NotFoundException("SError", "User Not Found"));
    }


}
