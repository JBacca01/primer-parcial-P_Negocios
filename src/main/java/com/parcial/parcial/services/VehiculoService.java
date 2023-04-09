package com.parcial.parcial.services;

import com.parcial.parcial.models.Vehiculo;

import java.util.List;

public interface VehiculoService {
    Vehiculo getVehiculo(Long id);

    Boolean createVehiculo(Vehiculo vehiculo);

    List<Vehiculo> allVehiculos();

    Boolean updateVehiculo(Long id, Vehiculo vehiculo);

    Boolean deleteVehiculo(Long id, Vehiculo vehiculo);
}

