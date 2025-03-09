package com.example.demo.service;

import com.example.demo.model.Jugador;
import com.example.demo.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;
    
    public List<Jugador> listarTodos() {
        return jugadorRepository.findAll();
    }
    
    public Optional<Jugador> buscarPorId(Long id) {
        return jugadorRepository.findById(id);
    }
    
    public Jugador guardar(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }
    
    public void eliminar(Long id) {
        jugadorRepository.deleteById(id);
    }
    
    public List<Jugador> buscarPorEquipo(String equipo) {
        return jugadorRepository.findByEquipo(equipo);
    }
    
    public List<Jugador> buscarPorPosicion(String posicion) {
        return jugadorRepository.findByPosicion(posicion);
    }
    
    public List<Jugador> buscarPorPatron(String patron) {
        return jugadorRepository.buscarPorPatron(patron);
    }
} 