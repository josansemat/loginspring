package com.biblioteca;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class SessionComponent {
    
    private String nombreUsuario;
    private boolean autenticado;
    private List<String> mensajes = new ArrayList<>();
    private String ultimaBusqueda;
    
    // Constructor
    public SessionComponent() {
        this.autenticado = false;
    }
    
    // Getters y setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public boolean isAutenticado() {
        return autenticado;
    }
    
    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
    
    public List<String> getMensajes() {
        return mensajes;
    }
    
    public void agregarMensaje(String mensaje) {
        this.mensajes.add(mensaje);
    }
    
    public void limpiarMensajes() {
        this.mensajes.clear();
    }
    
    public String getUltimaBusqueda() {
        return ultimaBusqueda;
    }
    
    public void setUltimaBusqueda(String ultimaBusqueda) {
        this.ultimaBusqueda = ultimaBusqueda;
    }
} 