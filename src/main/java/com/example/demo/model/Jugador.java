package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(nullable = false)
    private String nombre;
    
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 2, max = 100, message = "Los apellidos deben tener entre 2 y 100 caracteres")
    @Column(nullable = false)
    private String apellidos;
    
    @Min(value = 15, message = "La edad mínima es 15 años")
    @Max(value = 50, message = "La edad máxima es 50 años")
    private int edad;
    
    private String posicion;
    
    private String equipo;
    
    private int numeroCamiseta;
    
    private String nacionalidad = "España";
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario creadoPor;
} 