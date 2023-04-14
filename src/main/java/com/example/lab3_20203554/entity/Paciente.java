package com.example.lab3_20203554.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private String diagnostico;

    @Column(name = "fecha_cita", nullable = false)
    private Date fechaCita; //sql

    @Column(name = "numero_habitacion", nullable = false)
    private int numeroHabitacion;

    @ManyToOne
    @JoinColumn(name ="doctor_id",nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name ="hospital_id",nullable = false)
    private Hospital hospital;


}
