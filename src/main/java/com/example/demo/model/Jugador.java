package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jugador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String apellidos;
    
    private int edad;
    
    private String posicion;
    
    private String equipo;
    
    private int numeroCamiseta;
    
    // Campo para nacionalidad en caso de que exista en la base de datos
    private String nacionalidad = "España";
    
    // Opcional: para saber quién registró al jugador
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario creadoPor;
} 