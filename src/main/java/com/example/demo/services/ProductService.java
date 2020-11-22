package com.example.demo.services;

import com.example.demo.entiities.Product;
import com.example.demo.exceptions.GeneralException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface ProductService {

    Map<String, Object> getAllProducts() throws GeneralException;

    String createProductForRent(@RequestBody Product product) throws GeneralException;
}
//Metodos a implementar para posteriormente triggerizar el controller correspondiente
//en este caso un matodo llamado getallProducts que devolveria un map de elementos
//ademas de un metodo que devolvera un String como respuesta  al requestbody enviado
// para la creacion de un producto
