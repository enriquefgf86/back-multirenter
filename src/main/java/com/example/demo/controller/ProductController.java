package com.example.demo.controller;

import com.example.demo.dto.RenterDtos;
import com.example.demo.entiities.Product;
import com.example.demo.entiities.Renter;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.jsons.ProductJson;
import com.example.demo.jsons.RenterJson;
import com.example.demo.repositories.RenterRepository;
import com.example.demo.responses.AppResponse;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@EnableWebSecurity

@RequestMapping("/cubancoder/multirenter")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    RenterRepository renterRepository;
    @Autowired
    RenterDtos renterDtos;



//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/username", method = RequestMethod.GET)
//        @ResponseBody
//    public String currentUserNameSimple(HttpServletRequest request) {
//        Principal principal = request.getUserPrincipal();
//        return principal.getName();
//    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/products/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<Map<String ,Object>> getAllProducts() throws GeneralException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", productService.getAllProducts());
    }
    //ejecutando la el servicio referente a la traida de todos los productos  con request al endpoint products/all
    //vease que en el caso de los controllers el retorno es un response previamente disenado llamado AppResponse
    //trayendo un mapeable de par string Object.
    //En este caso al ser un request del get no se pasa parametro alguno. y entonces la data del contenido se obtendria a traves
    //del acceso al servicio productsService, y en el al metodo getallProducts

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/products/create", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<String> createAProduct(@RequestBody Product product) throws GeneralException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", productService.createProductForRent(product));
    }
    //ejecutando la el servicio referente a la creacion de un producto con request al endpoint /products/create
    //vease que en el caso de los controllers el retorno es un response previamente disenado llamado AppResponse
    //trayendo un mapeable en este caso de String como bien lo refiere el servicio y su implementacion , en donde
    //dicho string seria la notificacion de que el producto se ha creado.
    //En este caso al ser un request del post, y se necesitan ciertos elementos para crear dicho producto
    // se hace necesario la interaccion del usuario que facilite la data necesaria  para dicha creacion
    // de ahi que se requiera que se pase a traves de un requestBody todo lo referente
    //  a la creacion del mismo segun el constructor previamente inicializado en la entidad correspondiente
    //  Al ser un tipo de respuesta como string , en su retorno de data la AppResponse entoncess
    //  traeria un string , para ello se accede al serviciio correspondiente (productService, )y a traves de
    //  el a su metodo createProdcut, pasandosele como parametro dicho producto


}
