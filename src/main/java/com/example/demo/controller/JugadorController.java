package com.example.demo.controller;

import com.example.demo.model.Equipo;
import com.example.demo.model.Jugador;
import com.example.demo.service.EquipoService;
import com.example.demo.service.JugadorService;
import com.example.demo.session.FutbolManagedBean;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private EquipoService equipoService;
    
    @Autowired
    private FutbolManagedBean futbolManagedBean;
    
    @GetMapping
    public String listar(Model model) {
        futbolManagedBean.incrementarVisitasJugadores();
        
        List<Jugador> jugadores = jugadorService.listarTodos();
        model.addAttribute("jugadores", jugadores);
        model.addAttribute("futbolBean", futbolManagedBean);
        
        return "jugadores/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("equipos", equipoService.listarTodos());
        model.addAttribute("posiciones", Jugador.Posicion.values());
        model.addAttribute("accion", "Crear");
        
        return "jugadores/formulario";
    }
    
    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute("jugador") Jugador jugador, 
                         BindingResult result,
                         Model model,
                         RedirectAttributes flash) {
        
        if (result.hasErrors()) {
            model.addAttribute("equipos", equipoService.listarTodos());
            model.addAttribute("posiciones", Jugador.Posicion.values());
            model.addAttribute("accion", "Crear");
            return "jugadores/formulario";
        }
        
        jugadorService.guardar(jugador);
        flash.addFlashAttribute("mensaje", "Jugador guardado con éxito");
        
        return "redirect:/jugadores";
    }
    
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorService.buscarPorId(id);
        model.addAttribute("jugador", jugador);
        model.addAttribute("equipos", equipoService.listarTodos());
        model.addAttribute("posiciones", Jugador.Posicion.values());
        model.addAttribute("accion", "Actualizar");
        
        return "jugadores/formulario";
    }
    
    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id,
                           @Valid @ModelAttribute("jugador") Jugador jugador,
                           BindingResult result,
                           Model model,
                           RedirectAttributes flash) {
        
        if (result.hasErrors()) {
            model.addAttribute("equipos", equipoService.listarTodos());
            model.addAttribute("posiciones", Jugador.Posicion.values());
            model.addAttribute("accion", "Actualizar");
            return "jugadores/formulario";
        }
        
        jugadorService.actualizar(id, jugador);
        flash.addFlashAttribute("mensaje", "Jugador actualizado con éxito");
        
        return "redirect:/jugadores";
    }
    
    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        jugadorService.eliminar(id);
        flash.addFlashAttribute("mensaje", "Jugador eliminado con éxito");
        
        return "redirect:/jugadores";
    }
    
    @GetMapping("/{id}/ver")
    public String ver(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorService.buscarPorId(id);
        model.addAttribute("jugador", jugador);
        
        futbolManagedBean.seleccionarJugador(jugador);
        
        return "jugadores/detalle";
    }
    
    @GetMapping("/equipo/{equipoId}")
    public String listarPorEquipo(@PathVariable Long equipoId, Model model) {
        Equipo equipo = equipoService.buscarPorId(equipoId);
        List<Jugador> jugadores = jugadorService.buscarPorEquipo(equipoId);
        
        model.addAttribute("jugadores", jugadores);
        model.addAttribute("equipo", equipo);
        
        return "jugadores/lista-por-equipo";
    }
    
    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String patron, Model model) {
        List<Jugador> jugadores = jugadorService.buscarPorPatron(patron);
        
        futbolManagedBean.guardarBusquedaJugadores(jugadores, patron);
        
        model.addAttribute("jugadores", jugadores);
        model.addAttribute("patron", patron);
        model.addAttribute("futbolBean", futbolManagedBean);
        
        return "jugadores/resultados-busqueda";
    }
    
    @GetMapping("/posicion/{posicion}")
    public String filtrarPorPosicion(@PathVariable Jugador.Posicion posicion, Model model) {
        List<Jugador> jugadores = jugadorService.buscarPorPosicion(posicion);
        model.addAttribute("jugadores", jugadores);
        model.addAttribute("posicionActual", posicion);
        model.addAttribute("posiciones", Jugador.Posicion.values());
        
        return "jugadores/lista-por-posicion";
    }
} 