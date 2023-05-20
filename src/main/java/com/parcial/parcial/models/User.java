package com.parcial.parcial.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="first_name",length = 100,nullable = false)
    private String firstName;
    @Column(name ="last_name",length = 100,nullable = false)
    private String lastName;
    @Column(length = 100)
    private String address;
    @Column(length = 100,nullable = false)
    private String email;
    @Column(length = 64,nullable = false)
    private String password ;
    @Column(length = 30)
    private Date birthday;
}
