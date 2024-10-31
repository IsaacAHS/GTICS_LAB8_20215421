package com.example.lab8.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double gpa;

    @Column(nullable = false)
    private String facultad;

    @Column(nullable = false)
    private Integer creditoscompletados;

    @Column(nullable = false)
    private Integer ranking;
}
