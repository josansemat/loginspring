package com.example.demo.component;

import com.example.demo.model.Jugador;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JugadorSessionComponent implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<Jugador> ultimosVisitados = new ArrayList<>();
    private Jugador ultimoJugadorCreado;
    
    public void addJugadorVisitado(Jugador jugador) {
        if (ultimosVisitados.size() >= 5) {
            ultimosVisitados.remove(0);
        }
        ultimosVisitados.add(jugador);
    }
    
    public List<Jugador> getUltimosVisitados() {
        return ultimosVisitados;
    }
    
    public void setUltimoJugadorCreado(Jugador jugador) {
        this.ultimoJugadorCreado = jugador;
    }
    
    public Jugador getUltimoJugadorCreado() {
        return ultimoJugadorCreado;
    }
} 