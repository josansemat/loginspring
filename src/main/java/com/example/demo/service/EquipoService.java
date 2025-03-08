package com.example.demo.service;

import com.example.demo.model.EquipoFutbol;
import com.example.demo.model.EquipoPrimera;
import com.example.demo.model.EquipoSegunda;
import com.example.demo.model.Usuario;
import com.example.demo.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;
    
    // Métodos CRUD básicos
    public List<EquipoFutbol> listarTodos() {
        return equipoRepository.findAll();
    }
    
    public Optional<EquipoFutbol> buscarPorId(Long id) {
        return equipoRepository.findById(id);
    }
    
    public void eliminar(Long id) {
        equipoRepository.deleteById(id);
    }
    
    public EquipoFutbol guardar(EquipoFutbol equipo) {
        return equipoRepository.save(equipo);
    }
    
    // Métodos de búsqueda
    public List<EquipoFutbol> buscarPorNombre(String nombre) {
        return equipoRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    public List<EquipoFutbol> buscarPorCiudad(String ciudad) {
        return equipoRepository.findByCiudadContainingIgnoreCase(ciudad);
    }
    
    public List<EquipoFutbol> buscarPorPatron(String patron) {
        return equipoRepository.buscarPorPatron(patron);
    }
    
    // Métodos por categoría
    public List<EquipoFutbol> listarEquiposPrimera() {
        return equipoRepository.findAllPrimera();
    }
    
    public List<EquipoFutbol> listarEquiposSegunda() {
        return equipoRepository.findAllSegunda();
    }
    
    // Equipos por usuario
    public List<EquipoFutbol> listarEquiposPorUsuario(Long usuarioId) {
        return equipoRepository.findByUsuarioCreadorId(usuarioId);
    }
    
    // Método para determinar el tipo de equipo
    public String getTipoEquipo(EquipoFutbol equipo) {
        if (equipo instanceof EquipoPrimera) {
            return "PRIMERA";
        } else if (equipo instanceof EquipoSegunda) {
            return "SEGUNDA";
        }
        return null;
    }
} 