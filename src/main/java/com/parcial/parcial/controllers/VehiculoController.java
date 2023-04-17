package com.parcial.parcial.controllers;

import com.parcial.parcial.models.Vehiculo;
import com.parcial.parcial.services.VehiculoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class VehiculoController {
    @Autowired
    private VehiculoServiceImp VehiculoServiceImp;
    @GetMapping(value = "vehicular/{id}")
    public ResponseEntity findVehicularById(@PathVariable Long id){

        Map response = new HashMap();
        try {
            return new ResponseEntity(VehiculoServiceImp.getVehiculo(id), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No se encontro el Vehiculo");
            return  new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value = "/vehicular")
    public ResponseEntity saveVehiculo(@RequestBody Vehiculo vehiculo){
        Map response = new HashMap();
        Boolean userResp = VehiculoServiceImp.createVehiculo(vehiculo);
        if(userResp == true){
            response.put("status","201");
            response.put("message","se registro el Vehiculo");
            return  new ResponseEntity(response,HttpStatus.CREATED);
        }
        response.put("status","400");
        response.put("message","Hubo un error al registrar el Vehiculo");
        return  new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }
    @GetMapping(value = "vehicular")
    public ResponseEntity allvehiculo(){

        Map response = new HashMap();
        try {
            return new ResponseEntity(VehiculoServiceImp.allVehiculos(), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("messager","No hay Vehiculos");
            return  new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        Map response = new HashMap();
        Boolean vehiculoDB = VehiculoServiceImp.updateVehiculo(id, vehiculo);
        try {
            if (vehiculoDB == null) {
                response.put("status", "201");
                response.put("message", "se creo encontro usuarios");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(VehiculoServiceImp.getVehiculo(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", "201");
            response.put("message", "se encontro usuario");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
}
