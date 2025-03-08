package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipos")
@Data
@EqualsAndHashCode(callSuper = true)
public class Equipo extends EntidadBase {
    
    @NotBlank(message = "El nombre del equipo es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;
    
    @Min(value = 1900, message = "El año de fundación debe ser posterior a 1900")
    private Integer anioFundacion;
    
    private String estadio;
    
    private String escudo;
    
    @NotNull(message = "La categoría es obligatoria")
    @Enumerated(EnumType.STRING)
    private CategoriaEquipo categoria;
    
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jugador> jugadores = new ArrayList<>();
    
    public enum CategoriaEquipo {
        PRIMERA("Primera División"),
        SEGUNDA("Segunda División"),
        TERCERA("Tercera División"),
        REGIONAL("Regional");
        
        private final String displayName;
        
        CategoriaEquipo(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    // Método para añadir un jugador y establecer la relación bidireccional
    public void addJugador(Jugador jugador) {
        jugadores.add(jugador);
        jugador.setEquipo(this);
    }
    
    // Método para eliminar un jugador y romper la relación bidireccional
    public void removeJugador(Jugador jugador) {
        jugadores.remove(jugador);
        jugador.setEquipo(null);
    }
} 