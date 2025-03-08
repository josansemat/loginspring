package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipos_futbol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoFutbol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;
    
    @Min(value = 1800, message = "El año de fundación debe ser posterior a 1800")
    private Integer añoFundacion;
    
    private String estadio;
    
    private String entrenador;
    
    private String liga;
    
    private String urlEscudo;
}