package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player")
@Data
@NoArgsConstructor
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
    
    private String nacionalidad = "España";
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario creadoPor;
} 