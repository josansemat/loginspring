package com.example.demo.controller;

import com.example.demo.service.EquipoService;
import com.example.demo.service.JugadorService;
import com.example.demo.session.FutbolManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    @Autowired
    private EquipoService equipoService;
    
    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private FutbolManagedBean futbolManagedBean;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalEquipos", equipoService.listarTodos().size());
        model.addAttribute("totalJugadores", jugadorService.listarTodos().size());
        model.addAttribute("futbolBean", futbolManagedBean);
        
        return "dashboard";
    }
} 