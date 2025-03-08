package com.example.demo.service;

import com.example.demo.model.Jugador;
import com.example.demo.repository.JugadorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;
    
    @Transactional(readOnly = true)
    public List<Jugador> listarTodos() {
        return jugadorRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Jugador buscarPorId(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jugador no encontrado con ID: " + id));
    }
    
    @Transactional(readOnly = true)
    public List<Jugador> buscarPorEquipo(Long equipoId) {
        return jugadorRepository.findByEquipoId(equipoId);
    }
    
    @Transactional
    public Jugador guardar(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }
    
    @Transactional
    public Jugador actualizar(Long id, Jugador jugadorActualizado) {
        Jugador jugadorExistente = buscarPorId(id);
        
        jugadorExistente.setNombre(jugadorActualizado.getNombre());
        jugadorExistente.setApellidos(jugadorActualizado.getApellidos());
        jugadorExistente.setFechaNacimiento(jugadorActualizado.getFechaNacimiento());
        jugadorExistente.setNacionalidad(jugadorActualizado.getNacionalidad());
        jugadorExistente.setPosicion(jugadorActualizado.getPosicion());
        jugadorExistente.setDorsal(jugadorActualizado.getDorsal());
        jugadorExistente.setAltura(jugadorActualizado.getAltura());
        jugadorExistente.setEquipo(jugadorActualizado.getEquipo());
        
        if (jugadorActualizado.getFoto() != null && !jugadorActualizado.getFoto().isEmpty()) {
            jugadorExistente.setFoto(jugadorActualizado.getFoto());
        }
        
        return jugadorRepository.save(jugadorExistente);
    }
    
    @Transactional
    public void eliminar(Long id) {
        jugadorRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Jugador> buscarPorPatron(String patron) {
        if (patron == null || patron.trim().isEmpty()) {
            return listarTodos();
        }
        return jugadorRepository.buscarPorPatron(patron);
    }
    
    @Transactional(readOnly = true)
    public List<Jugador> buscarPorPosicion(Jugador.Posicion posicion) {
        return jugadorRepository.findByPosicion(posicion);
    }
} 