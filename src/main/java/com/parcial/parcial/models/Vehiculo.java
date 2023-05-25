package com.parcial.parcial.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Vehiculo {
    @Id
    private Long id;
    private String car;
    private String car_model;
    private String car_color;
    private String car_model_year;
    private String car_vin;
    private String price;
    private String availability;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}