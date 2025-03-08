package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "equipos_futbol")
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_equipo")
public abstract class EquipoFutbol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    
    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;
    
    @Min(value = 1900, message = "El año de fundación debe ser posterior a 1900")
    @Max(value = 2023, message = "El año de fundación no puede ser futuro")
    private Integer anioFundacion;
    
    @NotBlank(message = "El estadio es obligatorio")
    private String estadio;
    
    @Min(value = 1, message = "La capacidad debe ser positiva")
    private Integer capacidadEstadio;
    
    private String urlEscudo;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioCreador;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    
    // Método para clases hijas
    public abstract String getCategoria();
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = new Date();
    }
} 