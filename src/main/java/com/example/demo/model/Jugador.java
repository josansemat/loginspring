package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Jugador extends Persona {
    
    @NotBlank(message="La posición es obligatoria")
    private String posicion;
    
    @NotBlank(message="El equipo es obligatorio")
    private String equipo;
    
    @Min(value = 1, message="El número debe ser mayor que 0")
    @Max(value = 99, message="El número debe ser menor que 100")
    private int numeroCamiseta;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario creadoPor;
} 