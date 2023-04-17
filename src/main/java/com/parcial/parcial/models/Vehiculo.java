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
    @Column(name ="car_model")
    private String car_model;
    @Column(name ="car_color")
    private String car_color;
    @Column(name ="car_model_year")
    private int car_model_year;
    @Column(name ="car_vin")
    private String car_vin;
    private String price;
    private String availability;
}