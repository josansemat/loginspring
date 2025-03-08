package com.example.demo.service;

import com.example.demo.model.EquipoFutbol;
import com.example.demo.repository.EquipoFutbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoFutbolService {

    private final EquipoFutbolRepository equipoRepository;
    
    @Autowired
    public EquipoFutbolService(EquipoFutbolRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }
    
    // Crear o actualizar equipo
    public EquipoFutbol guardarEquipo(EquipoFutbol equipo) {
        return equipoRepository.save(equipo);
    }
    
    // Obtener todos los equipos
    public List<EquipoFutbol> obtenerTodosEquipos() {
        return equipoRepository.findAll();
    }
    
    // Obtener equipo por ID
    public Optional<EquipoFutbol> obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id);
    }
    
    // Obtener equipos por liga
    public List<EquipoFutbol> obtenerEquiposPorLiga(String liga) {
        return equipoRepository.findByLiga(liga);
    }
    
    // Obtener equipos por ciudad
    public List<EquipoFutbol> obtenerEquiposPorCiudad(String ciudad) {
        return equipoRepository.findByCiudad(ciudad);
    }
    
    // Eliminar equipo
    public void eliminarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }
} 