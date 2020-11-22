package com.example.demo.services;

import com.example.demo.entiities.Renter;
import com.example.demo.exceptions.GeneralException;
import com.example.demo.jsons.RenterJson;
import com.example.demo.payload.LoginRequest;
import com.example.demo.payload.SignUprequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface RenterService  {
    Map<String,Object> getAllRenters()throws GeneralException;
    Renter findByEmail(String renterEmail) throws GeneralException;
    Renter findRenterById(Long id)throws GeneralException;
    String save(SignUprequest signUprequest)throws GeneralException;
    ResponseEntity<?> loginUser(LoginRequest loginRequest)throws GeneralException;
}
//Metodos a implementar para posteriormente triggerizar el controller correspondiente
//en este caso un metodo llamado getAllRenters() que devolveria un map de elementos
//referente a todos los renters, ademas de metodos referentres al query de los re
//positorios sobre  encontrar renters por email y por id , devolviendo la entidad
//dentro de la base de datos que corresponda con el query en cuastion .
//El cuarto metodo devolveria  una respuesta a manera de string  como resultado del
//proceso de salvado en repositorio  de usuarios creados .
//En este ultimo metodo simplemente se procederia  a retornar un ResponseEntity con un retorno
//generico, el cual se le pasa como parametro sobre el cual extraer los datos necesarios
//el payload Loginrequest para el log in de un usuario

