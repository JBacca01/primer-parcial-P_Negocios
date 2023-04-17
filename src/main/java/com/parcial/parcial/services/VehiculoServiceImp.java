package com.parcial.parcial.services;

import com.parcial.parcial.models.Vehiculo;
import com.parcial.parcial.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VehiculoServiceImp implements VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public VehiculoServiceImp (RestTemplate restTemp1ate)
    {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/test")
    public Vehiculo getApi() {
        String url = "https://myfakeapi.com/api/cars/23";
        Vehiculo vehiculo = restTemplate.getForObject(url, Vehiculo.class);
        return vehiculo;
    }

    @Override
    public  Vehiculo getVehiculo(Long id){

        return vehiculoRepository.findById(id).get();
    }

    @Override
    public Boolean createVehiculo(Vehiculo vehiculo) {
        try {


            vehiculoRepository.save(vehiculo);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public List<Vehiculo> allVehiculos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Boolean updateVehiculo(long id, Vehiculo vehiculo) {
        try {


            Vehiculo vehiculoBD = vehiculoRepository.findById(id).get();

            vehiculoBD.setCar(vehiculoBD.getCar());
            vehiculoBD.setCar_model( vehiculo.getCar_model());
            vehiculoBD.setCar_color( vehiculo.getCar_color());
            vehiculoBD.setCar_model_year( vehiculo.getCar_model_year());
            vehiculoBD.setCar_vin( vehiculo.getCar_vin());
            vehiculoBD.setPrice( vehiculo.getPrice());
            vehiculoBD.setAvailability( vehiculo.getAvailability());
            Vehiculo vehiculoUp =  vehiculoRepository.save(vehiculoBD);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}