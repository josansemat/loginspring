package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PRIMERA")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EquipoPrimera extends EquipoFutbol {
    
    @Min(value = 0, message = "Los títulos de liga no pueden ser negativos")
    private Integer titulosLiga;
    
    @Min(value = 0, message = "Los puntos UEFA no pueden ser negativos")
    private Integer puntosUEFA;
    
    private Boolean participaChampions;
    
    @Override
    public String getCategoria() {
        return "Primera División";
    }
} 