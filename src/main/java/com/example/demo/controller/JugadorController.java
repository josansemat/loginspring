package com.example.demo.controller;

import com.example.demo.model.Jugador;
import com.example.demo.model.Usuario;
import com.example.demo.service.JugadorService;
import com.example.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String buscarJugadores(@RequestParam(required = false) String termino, Model model) {
        List<Jugador> resultados;
        
        if (termino != null && !termino.isEmpty()) {
            resultados = jugadorService.buscarPorPatron(termino);
            model.addAttribute("terminoBusqueda", termino);
        } else {
            resultados = jugadorService.listarTodos();
        }
        
        model.addAttribute("jugadores", resultados);
        return "jugadores/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        return "jugadores/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarJugador(@Valid @ModelAttribute Jugador jugador, 
                                BindingResult result, 
                                Model model,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {
        if (result.hasErrors()) {
            return "jugadores/formulario";
        }
        
        try {
            // Obtener el usuario actual
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Optional<Usuario> usuarioOpt = usuarioService.buscarPorUsername(auth.getName());
            
            if (usuarioOpt.isPresent()) {
                jugador.setCreadoPor(usuarioOpt.get());
            }
            
            // Guardar en variable de sesión el último jugador guardado
            session.setAttribute("ultimoJugador", jugador);
            
            jugadorService.guardar(jugador);
            redirectAttributes.addFlashAttribute("mensaje", "Jugador guardado correctamente");
            return "redirect:/jugadores";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar el jugador: " + e.getMessage());
            return "jugadores/formulario";
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
            jugadorService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Jugador eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el jugador");
        }
        return "redirect:/jugadores";
    }
    
    @GetMapping("/ultimo")
    public String verUltimoJugador(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Jugador ultimoJugador = (Jugador) session.getAttribute("ultimoJugador");
        
        if (ultimoJugador != null) {
            model.addAttribute("jugador", ultimoJugador);
            return "jugadores/detalle";
        } else {
            redirectAttributes.addFlashAttribute("error", "No hay jugador reciente en sesión");
            return "redirect:/jugadores";
        }
    }
} 