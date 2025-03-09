package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jugadores")
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
    
    // Opcional: para saber quién registró al jugador
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario creadoPor;
} 