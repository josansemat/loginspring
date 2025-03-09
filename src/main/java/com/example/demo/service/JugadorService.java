package com.example.demo.service;

import com.example.demo.model.Jugador;
import com.example.demo.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    
    public List<Jugador> buscar(String busqueda, String posicion, String equipo) {
        // Obtener todos los jugadores primero
        List<Jugador> jugadores = jugadorRepository.findAll();
        
        // Aplicar filtros si están presentes
        if (StringUtils.hasText(busqueda)) {
            String busquedaLower = busqueda.toLowerCase();
            jugadores = jugadores.stream()
                .filter(j -> 
                    (j.getNombre() != null && j.getNombre().toLowerCase().contains(busquedaLower)) ||
                    (j.getApellidos() != null && j.getApellidos().toLowerCase().contains(busquedaLower))
                )
                .collect(Collectors.toList());
        }
        
        if (StringUtils.hasText(posicion)) {
            jugadores = jugadores.stream()
                .filter(j -> posicion.equals(j.getPosicion()))
                .collect(Collectors.toList());
        }
        
        if (StringUtils.hasText(equipo)) {
            String equipoLower = equipo.toLowerCase();
            jugadores = jugadores.stream()
                .filter(j -> j.getEquipo() != null && j.getEquipo().toLowerCase().contains(equipoLower))
                .collect(Collectors.toList());
        }
        
        return jugadores;
    }
} 