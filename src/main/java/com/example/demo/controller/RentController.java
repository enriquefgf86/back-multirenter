package com.example.demo.controller;


import com.example.demo.entiities.Rent;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.responses.AppResponse;
import com.example.demo.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cubancoder/multirenter")
public class RentController {
   @Autowired
    RentService rentService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rents/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<Map<String ,Object>> getAllRents() throws GeneralException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", rentService.getAllRents());
    }
    //ejecutando la el servicio referente a la traida de todos las rentas   con request al endpoint rents/all
    //vease que en el caso de los controllers el retorno es un response previamente disenado llamado AppResponse
    //trayendo un mapeable de par string Object.
    //En este caso al ser un request del get no se pasa parametro alguno. y entonces la data del contenido se obtendria a traves
    //del acceso al servicio rentService, y en el al metodo getallRents


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rents/rent/{productId}/product", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<String> rentAProduct(@PathVariable ("productId") Long productId,@RequestBody Rent rent) throws GeneralException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", rentService.CreateRentOfProduct(rent,productId));
    }
    //En este caso se procederia a realizar el controller referente  al realizado de una renta , vease que para ello se hace
    //necesario en el url del request pasar un parametro que haga referencia a ese producto a rentar.Para ello
    //entonces se inicializa un PathVariable  de tipo Long que recibiria como parametro dico paramtro pasado en
    //el Url del endpoint, en este caso refeente al id del producto, pr otra parte se hace necesario implantar
    //algunos valores para el inicio de la renta , de ahi que se requiera por parte del usuario cierta data
    //que seria traida en un RequestBody de tipo Renta  con valores tales como (costo, dias , etc...)
    //Como retorno entonces se traeria la previamente disenada AppResponse , y en su data vendria todo
    //lo referente a la ejecucion del servicio para este controller  accediendose a rentService , y a traves de
    //el al metodo CreateRent, pasandosele entonces el requestbody con lods datos de la renta a traves
    //de la variable rent, ademas del product id pasado como parametro en el url y recogido en el pathVariable de tipo
    //long llamado productId

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/rents/rent/{productId}/{rentId}/devolution", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<String> devolutionAProduct(@PathVariable ("productId") Long productId,@PathVariable ("rentId") Long rentId,@RequestBody Rent rent) throws GeneralException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", rentService.DevolutionOfProductRent(rent,rentId,productId));
    }
    //En este caso se procederia a realizar el controller referente  al realizado de una devolucion de producto rentado
    //  vease que para ello se hace necesario en el url del request pasar parmaetros que hagan refeencia tanto al id del producto
    //  rentado como al id de renta en cuestion .Para ello
    //entonces se inicializan  PathVariables  de tipo Long que recibirian como parametro dichos paramtros pasados en
    //el Url del endpoint, en este caso refeente al id del producto y al id de la renta respectivamente
    // (rentId,productId), por otra parte se hace necesario implantar
    //algunos valores para la devolucion  de la renta , de ahi que se requiera por parte del usuario cierta data
    //que seria traida en un RequestBody de tipo Renta  con valores tales como (dias reales, etc...)
    //Como retorno entonces se traeria la previamente disenada AppResponse , y en su data vendria todo
    //lo referente a la ejecucion del servicio para este controller  accediendose a rentService , y a traves de
    //el al metodo devolutionOfRent, pasandosele entonces el requestbody con lods datos de la renta a traves
    //de la variable rent, ademas del producid y rentId pasados como parametros en el url y recogidos en el
    // en los pathVariables de tipo long llamados productId y renterId respectivamente



}
