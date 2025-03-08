package com.example.demo.session;

import com.example.demo.model.Equipo;
import com.example.demo.model.Jugador;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
@Data
public class FutbolManagedBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<Equipo> ultimosBusquedaEquipos = new ArrayList<>();
    private List<Jugador> ultimosBusquedaJugadores = new ArrayList<>();
    
    private Equipo equipoSeleccionado;
    private Jugador jugadorSeleccionado;
    
    private String ultimoPatronBusqueda;
    private String mensajeUltimaBusqueda;
    
    private int totalVisitasEquipos = 0;
    private int totalVisitasJugadores = 0;
    
    public void incrementarVisitasEquipos() {
        totalVisitasEquipos++;
    }
    
    public void incrementarVisitasJugadores() {
        totalVisitasJugadores++;
    }
    
    public void guardarBusquedaEquipos(List<Equipo> equipos, String patron) {
        this.ultimosBusquedaEquipos = equipos;
        this.ultimoPatronBusqueda = patron;
        this.mensajeUltimaBusqueda = "Se encontraron " + equipos.size() + " equipos con el patrón: " + patron;
    }
    
    public void guardarBusquedaJugadores(List<Jugador> jugadores, String patron) {
        this.ultimosBusquedaJugadores = jugadores;
        this.ultimoPatronBusqueda = patron;
        this.mensajeUltimaBusqueda = "Se encontraron " + jugadores.size() + " jugadores con el patrón: " + patron;
    }
    
    public void seleccionarEquipo(Equipo equipo) {
        this.equipoSeleccionado = equipo;
    }
    
    public void seleccionarJugador(Jugador jugador) {
        this.jugadorSeleccionado = jugador;
    }
} 