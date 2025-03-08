package com.example.demo.service;

import com.example.demo.model.Equipo;
import com.example.demo.repository.EquipoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;
    
    @Transactional(readOnly = true)
    public List<Equipo> listarTodos() {
        return equipoRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Equipo buscarPorId(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado con ID: " + id));
    }
    
    @Transactional
    public Equipo guardar(Equipo equipo) {
        return equipoRepository.save(equipo);
    }
    
    @Transactional
    public Equipo actualizar(Long id, Equipo equipoActualizado) {
        Equipo equipoExistente = buscarPorId(id);
        
        equipoExistente.setNombre(equipoActualizado.getNombre());
        equipoExistente.setCiudad(equipoActualizado.getCiudad());
        equipoExistente.setAnioFundacion(equipoActualizado.getAnioFundacion());
        equipoExistente.setEstadio(equipoActualizado.getEstadio());
        equipoExistente.setCategoria(equipoActualizado.getCategoria());
        
        if (equipoActualizado.getEscudo() != null && !equipoActualizado.getEscudo().isEmpty()) {
            equipoExistente.setEscudo(equipoActualizado.getEscudo());
        }
        
        return equipoRepository.save(equipoExistente);
    }
    
    @Transactional
    public void eliminar(Long id) {
        equipoRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Equipo> buscarPorPatron(String patron) {
        if (patron == null || patron.trim().isEmpty()) {
            return listarTodos();
        }
        return equipoRepository.buscarPorPatron(patron);
    }
    
    @Transactional(readOnly = true)
    public List<Equipo> buscarPorCategoria(Equipo.CategoriaEquipo categoria) {
        return equipoRepository.findByCategoria(categoria);
    }
} 