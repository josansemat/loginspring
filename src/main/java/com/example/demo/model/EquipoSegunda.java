package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("SEGUNDA")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EquipoSegunda extends EquipoFutbol {
    
    @Min(value = 0, message = "Las temporadas en Primera no pueden ser negativas")
    private Integer temporadasEnPrimera;
    
    private Boolean aspiranteAscenso;
    
    @Min(value = 1, message = "El presupuesto debe ser positivo")
    private Integer presupuestoAnual;
    
    @Override
    public String getCategoria() {
        return "Segunda División";
    }
} 