package com.example.demo.repository;

import com.example.demo.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    
    List<Equipo> findByNombreContainingIgnoreCase(String nombre);
    
    List<Equipo> findByCiudadContainingIgnoreCase(String ciudad);
    
    @Query("SELECT e FROM Equipo e WHERE " +
           "LOWER(e.nombre) LIKE LOWER(CONCAT('%', :patron, '%')) OR " +
           "LOWER(e.ciudad) LIKE LOWER(CONCAT('%', :patron, '%')) OR " +
           "CAST(e.anioFundacion AS string) LIKE CONCAT('%', :patron, '%')")
    List<Equipo> buscarPorPatron(@Param("patron") String patron);
    
    List<Equipo> findByCategoria(Equipo.CategoriaEquipo categoria);
} 