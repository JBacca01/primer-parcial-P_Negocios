package com.parcial.parcial.controllers;

import com.parcial.parcial.models.Vehiculo;
import com.parcial.parcial.services.VehiculoServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.parcial.parcial.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RequiredArgsConstructor
@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    private final RestTemplate restTemplate;
    @Autowired
    private VehiculoServiceImp vehiculoServiceImp;
    @Autowired
    private JWTUtil jwtUtil;
    public void VehiculoControllers(VehiculoServiceImp vehiculoServiceImp) {

        this.vehiculoServiceImp = vehiculoServiceImp;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity fi(@PathVariable long id, @RequestHeader(value = "Authorization") String token){

        Map response = new HashMap();
        try {
            if(!validateToken(token)){
                return new ResponseEntity("Token invalido", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(vehiculoServiceImp.getVehiculo(id), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("messager","No se encontro el Vehiculo");
            return  new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("")
    public ResponseEntity saveVehiculo(@RequestBody Vehiculo vehiculo, @RequestHeader(value = "Authorization") String token){
        System.out.println(vehiculo.getUser());
        Long id = vehiculo.getId();
        if(!validateToken(token)){
            return new ResponseEntity("Token invalido", HttpStatus.UNAUTHORIZED);
        }
        if (vehiculoServiceImp.validarId(id)) {
            return ResponseEntity.badRequest().body("El ID ya existe");
        }

        Map response = new HashMap();
        Boolean userResp = vehiculoServiceImp.createVehiculo(vehiculo.getId(),vehiculo.getUser());

        if(userResp == true){
            response.put("status","201");
            response.put("messager","se registro el Vehiculo");
            return  new ResponseEntity(response,HttpStatus.CREATED);
        }
        response.put("status","400");
        response.put("messager","Hubo un error al registrar el Vehiculo");
        return  new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("")
    public ResponseEntity allvehiculo(@RequestHeader(value = "Authorization") String token){

        Map response = new HashMap();
        try {
            if(!validateToken(token)){
                return new ResponseEntity("Token invalido", HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(vehiculoServiceImp.allVehiculos(), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("messager","No hay Vehiculos");
            return  new ResponseEntity(response,HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value = "/{id}")
    public  ResponseEntity updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo, @RequestHeader(value = "Authorization") String token) {
        Map response = new HashMap();
        Boolean vehiculoDB = vehiculoServiceImp.updateVehiculo(id, vehiculo);
        try {
            if(!validateToken(token)){
                return new ResponseEntity("Token invalido", HttpStatus.UNAUTHORIZED);
            }
            if (vehiculoDB == null) {
                response.put("status", "201");
                response.put("massage", "se actualizo el usuario");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(vehiculoServiceImp.getVehiculo(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", "201");
            response.put("massage", "se no se pudo actuaalizar el usuario");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
    private Boolean validateToken(String token){
        try{
            if(jwtUtil.getKey(token) != null){
                return true;
            }
            return  false;
        }catch (Exception e){
            return  false;
        }
    }
}
