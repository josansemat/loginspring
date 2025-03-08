package com.example.demo.repository;

import com.example.demo.model.EquipoFutbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<EquipoFutbol, Long> {
    
    List<EquipoFutbol> findByNombreContainingIgnoreCase(String nombre);
    
    List<EquipoFutbol> findByCiudadContainingIgnoreCase(String ciudad);
    
    @Query("SELECT e FROM EquipoFutbol e WHERE e.nombre LIKE %:patron% OR e.ciudad LIKE %:patron% OR e.estadio LIKE %:patron%")
    List<EquipoFutbol> buscarPorPatron(@Param("patron") String patron);
    
    @Query("SELECT e FROM EquipoFutbol e WHERE TYPE(e) = com.example.demo.model.EquipoPrimera")
    List<EquipoFutbol> findAllPrimera();
    
    @Query("SELECT e FROM EquipoFutbol e WHERE TYPE(e) = com.example.demo.model.EquipoSegunda")
    List<EquipoFutbol> findAllSegunda();
    
    List<EquipoFutbol> findByUsuarioCreadorId(Long usuarioId);
} 