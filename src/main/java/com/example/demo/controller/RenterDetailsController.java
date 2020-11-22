package com.example.demo.controller;

import com.example.demo.exceptions.GeneralException;
import com.example.demo.jsons.RenterJson;
import com.example.demo.responses.AppResponse;
import com.example.demo.services.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cubancoder/multirenter")

public class RenterDetailsController {

    @Autowired
    RenterService renterService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/renters/all", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public AppResponse<Map<String,Object>> getAllRenters() throws GeneralException {
        return new AppResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", renterService.getAllRenters());
    }
    //ejecutando a el servicio referente a la traida de todos los renters   con request al endpoint renters/all
    //vease que en el caso de los controllers el retorno es un response previamente disenado llamado AppResponse
    //trayendo un mapeable de par string Object.
    //En este caso al ser un request del get no se pasa parametro alguno. y entonces la data del contenido se obtendria a traves
    //del acceso al servicio renterService, y en el al metodo getallRenters

}
