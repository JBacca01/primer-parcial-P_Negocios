package com.parcial.parcial.models;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String car;
    private String car_model;
    private String car_color;
    private int car_model_year;
    private String car_vin;
    private String price;
    private String availability;
}