package com.example.demo.config;

import com.example.demo.model.EquipoFutbol;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EquiposFavoritosBean implements Serializable {
    
    private List<Long> equiposFavoritosIds = new ArrayList<>();
    
    public void agregarFavorito(Long equipoId) {
        if (!equiposFavoritosIds.contains(equipoId)) {
            equiposFavoritosIds.add(equipoId);
        }
    }
    
    public void eliminarFavorito(Long equipoId) {
        equiposFavoritosIds.remove(equipoId);
    }
    
    public List<Long> getEquiposFavoritosIds() {
        return equiposFavoritosIds;
    }
    
    public boolean esFavorito(Long equipoId) {
        return equiposFavoritosIds.contains(equipoId);
    }
    
    public void limpiarFavoritos() {
        equiposFavoritosIds.clear();
    }
} 