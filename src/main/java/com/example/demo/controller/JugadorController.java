package com.example.demo.controller;

import com.example.demo.model.Jugador;
import com.example.demo.model.Usuario;
import com.example.demo.service.JugadorService;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public String listarJugadores(Model model) {
        model.addAttribute("jugadores", jugadorService.listarTodos());
        return "jugadores/lista";
    }
    
    @GetMapping("/buscar")
    public String buscarJugadores(
            @RequestParam(required = false) String busqueda,
            @RequestParam(required = false) String posicion,
            @RequestParam(required = false) String equipo,
            Model model) {
        
        // Aquí implementar la lógica de búsqueda
        List<Jugador> resultados = jugadorService.buscar(busqueda, posicion, equipo);
        model.addAttribute("jugadores", resultados);
        return "jugadores/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugadores/formulario";
    }
    
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Jugador> jugadorOpt = jugadorService.buscarPorId(id);
        
        if (jugadorOpt.isPresent()) {
            model.addAttribute("jugador", jugadorOpt.get());
            return "jugadores/detalle";
        } else {
            redirectAttributes.addFlashAttribute("error", "El jugador no existe");
            return "redirect:/jugadores";
        }
    }
    
    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador, RedirectAttributes redirectAttributes) {
        try {
            // Obtener el usuario actual
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Optional<Usuario> usuarioOpt = usuarioService.buscarPorUsername(auth.getName());
            
            if (usuarioOpt.isPresent()) {
                jugador.setCreadoPor(usuarioOpt.get());
            }
            
            jugadorService.guardar(jugador);
            
            String mensaje = (jugador.getId() == null) 
                ? "Jugador creado correctamente" 
                : "Jugador actualizado correctamente";
            
            redirectAttributes.addFlashAttribute("mensaje", mensaje);
            return "redirect:/jugadores";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el jugador: " + e.getMessage());
            return "redirect:/jugadores/nuevo";
        }
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Jugador> jugadorOpt = jugadorService.buscarPorId(id);
        
        if (jugadorOpt.isPresent()) {
            model.addAttribute("jugador", jugadorOpt.get());
            return "jugadores/formulario";
        } else {
            redirectAttributes.addFlashAttribute("error", "El jugador no existe");
            return "redirect:/jugadores";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Optional<Jugador> jugadorOpt = jugadorService.buscarPorId(id);
            
            if (jugadorOpt.isPresent()) {
                Jugador jugador = jugadorOpt.get();
                jugadorService.eliminar(id);
                redirectAttributes.addFlashAttribute("mensaje", 
                    "Jugador '" + jugador.getNombre() + " " + jugador.getApellidos() + "' eliminado correctamente");
            } else {
                redirectAttributes.addFlashAttribute("error", "El jugador no existe");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el jugador: " + e.getMessage());
        }
        return "redirect:/jugadores";
    }
} 