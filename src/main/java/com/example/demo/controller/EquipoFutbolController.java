package com.example.demo.controller;

import com.example.demo.model.EquipoFutbol;
import com.example.demo.service.EquipoFutbolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/equipos")
public class EquipoFutbolController {

    private final EquipoFutbolService equipoService;
    
    @Autowired
    public EquipoFutbolController(EquipoFutbolService equipoService) {
        this.equipoService = equipoService;
    }
    
    // Listar todos los equipos
    @GetMapping
    public String listarEquipos(Model model) {
        model.addAttribute("equipos", equipoService.obtenerTodosEquipos());
        return "equipos_lista";
    }
    
    // Formulario para nuevo equipo
    @GetMapping("/nuevo")
    public String formularioNuevoEquipo(Model model) {
        model.addAttribute("equipo", new EquipoFutbol());
        return "equipos_formulario";
    }
    
    // Guardar nuevo equipo
    @PostMapping("/guardar")
    public String guardarEquipo(@Valid @ModelAttribute("equipo") EquipoFutbol equipo, 
                              BindingResult result, 
                              RedirectAttributes flash) {
        if (result.hasErrors()) {
            return "equipos_formulario";
        }
        
        equipoService.guardarEquipo(equipo);
        flash.addFlashAttribute("mensaje", "Equipo guardado con éxito");
        return "redirect:/equipos";
    }
    
    // Formulario para editar equipo
    @GetMapping("/editar/{id}")
    public String formularioEditarEquipo(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Optional<EquipoFutbol> equipo = equipoService.obtenerEquipoPorId(id);
        
        if (equipo.isPresent()) {
            model.addAttribute("equipo", equipo.get());
            return "equipos_formulario";
        } else {
            flash.addFlashAttribute("error", "El equipo no existe");
            return "redirect:/equipos";
        }
    }
    
    // Ver detalles de un equipo
    @GetMapping("/ver/{id}")
    public String verEquipo(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Optional<EquipoFutbol> equipo = equipoService.obtenerEquipoPorId(id);
        
        if (equipo.isPresent()) {
            model.addAttribute("equipo", equipo.get());
            return "equipos_ver";
        } else {
            flash.addFlashAttribute("error", "El equipo no existe");
            return "redirect:/equipos";
        }
    }
    
    // Eliminar equipo
    @GetMapping("/eliminar/{id}")
    public String eliminarEquipo(@PathVariable Long id, RedirectAttributes flash) {
        equipoService.eliminarEquipo(id);
        flash.addFlashAttribute("mensaje", "Equipo eliminado con éxito");
        return "redirect:/equipos";
    }
} 