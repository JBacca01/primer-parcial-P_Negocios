package com.parcial.parcial.controllers;

import com.parcial.parcial.models.Vehiculo;
import com.parcial.parcial.services.VehiculoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class VehiculoController {
    @Autowired
    private VehiculoServiceImp vehiculoServiceImp;
    @GetMapping(value = "vehiculo/{id}")
    public ResponseEntity fi(@PathVariable long id){

        Map response = new HashMap();
        try {
            return new ResponseEntity(vehiculoServiceImp.getVehiculo(id), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("messager","No se encontro el Vehiculo");
            return  new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value = "/vehiculo")
    public ResponseEntity saveVehiculo(@RequestBody Vehiculo vehiculo){
        Map response = new HashMap();
        Boolean userResp = vehiculoServiceImp.createVehiculo(vehiculo);
        if(userResp == true){
            response.put("status","201");
            response.put("messager","se registro el Vehiculo");
            return  new ResponseEntity(response,HttpStatus.CREATED);
        }
        response.put("status","400");
        response.put("messager","Hubo un error al registrar el Vehiculo");
        return  new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }
    @GetMapping(value = "vehiculo")
    public ResponseEntity allvehiculo(){

        Map response = new HashMap();
        try {
            return new ResponseEntity(vehiculoServiceImp.allVehiculos(), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("messager","No hay Vehiculos");
            return  new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
    }
}
