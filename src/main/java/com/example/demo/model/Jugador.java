package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "jugadores")
@Data
@EqualsAndHashCode(callSuper = true)
public class Jugador extends EntidadBase {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 2, max = 100, message = "Los apellidos deben tener entre 2 y 100 caracteres")
    private String apellidos;
    
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    
    @NotBlank(message = "La nacionalidad es obligatoria")
    private String nacionalidad;
    
    @NotNull(message = "La posición es obligatoria")
    @Enumerated(EnumType.STRING)
    private Posicion posicion;
    
    @Min(value = 1, message = "El número de camiseta debe ser positivo")
    @Max(value = 99, message = "El número de camiseta no puede ser mayor a 99")
    private Integer dorsal;
    
    @Min(value = 0, message = "La altura no puede ser negativa")
    @Max(value = 250, message = "La altura no parece válida")
    private Integer altura; // en cm
    
    private String foto;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;
    
    public enum Posicion {
        PORTERO("Portero"), 
        DEFENSA("Defensa"), 
        CENTROCAMPISTA("Centrocampista"), 
        DELANTERO("Delantero");
        
        private final String displayName;
        
        Posicion(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
} 