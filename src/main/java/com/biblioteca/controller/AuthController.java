package com.biblioteca.controller;

import com.biblioteca.SessionComponent;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    
    @Autowired
    private SessionComponent sessionComponent;
    
    // Página de login
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
    
    // Procesar login
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session) {
        
        // En un sistema real, deberías validar el usuario contra una base de datos
        // Por simplicidad, usamos credenciales hardcodeadas aquí
        if ("admin".equals(username) && "admin123".equals(password)) {
            // Guardar información en el componente de sesión
            sessionComponent.setNombreUsuario(username);
            sessionComponent.setAutenticado(true);
            
            // También guardamos en la sesión HTTP como ejemplo
            session.setAttribute("usuario", username);
            session.setAttribute("autenticado", true);
            
            sessionComponent.agregarMensaje("¡Bienvenido " + username + "!");
            
            return "redirect:/libros";
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }
    
    // Cerrar sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        // Invalidar sesión HTTP
        session.invalidate();
        
        // Limpiar componente de sesión
        sessionComponent.setNombreUsuario(null);
        sessionComponent.setAutenticado(false);
        sessionComponent.limpiarMensajes();
        
        return "redirect:/login";
    }
} 