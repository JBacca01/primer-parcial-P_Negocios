package com.parcial.parcial.services;

import com.parcial.parcial.models.User;
import com.parcial.parcial.models.Vehiculo;

import java.util.List;

public interface VehiculoService {
    Vehiculo getVehiculo(Long id);

    Boolean createVehiculo(Long vehiculo, User user);

    List<Vehiculo> allVehiculos();

    Boolean updateVehiculo(Long id, Vehiculo vehiculo);


}

