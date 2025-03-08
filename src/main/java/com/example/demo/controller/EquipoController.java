package com.example.demo.controller;

import com.example.demo.model.Equipo;
import com.example.demo.service.EquipoService;
import com.example.demo.session.FutbolManagedBean;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;
    
    @Autowired
    private FutbolManagedBean futbolManagedBean;
    
    @GetMapping
    public String listar(Model model, HttpSession session) {
        futbolManagedBean.incrementarVisitasEquipos();
        
        List<Equipo> equipos = equipoService.listarTodos();
        model.addAttribute("equipos", equipos);
        model.addAttribute("futbolBean", futbolManagedBean);
        
        return "equipos/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("equipo", new Equipo());
        model.addAttribute("categorias", Equipo.CategoriaEquipo.values());
        model.addAttribute("accion", "Crear");
        return "equipos/formulario";
    }
    
    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute("equipo") Equipo equipo, 
                         BindingResult result,
                         Model model,
                         RedirectAttributes flash) {
        
        if (result.hasErrors()) {
            model.addAttribute("categorias", Equipo.CategoriaEquipo.values());
            model.addAttribute("accion", "Crear");
            return "equipos/formulario";
        }
        
        equipoService.guardar(equipo);
        flash.addFlashAttribute("mensaje", "Equipo guardado con éxito");
        
        return "redirect:/equipos";
    }
    
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Equipo equipo = equipoService.buscarPorId(id);
        model.addAttribute("equipo", equipo);
        model.addAttribute("categorias", Equipo.CategoriaEquipo.values());
        model.addAttribute("accion", "Actualizar");
        
        return "equipos/formulario";
    }
    
    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id,
                           @Valid @ModelAttribute("equipo") Equipo equipo,
                           BindingResult result,
                           Model model,
                           RedirectAttributes flash) {
        
        if (result.hasErrors()) {
            model.addAttribute("categorias", Equipo.CategoriaEquipo.values());
            model.addAttribute("accion", "Actualizar");
            return "equipos/formulario";
        }
        
        equipoService.actualizar(id, equipo);
        flash.addFlashAttribute("mensaje", "Equipo actualizado con éxito");
        
        return "redirect:/equipos";
    }
    
    @GetMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        try {
            equipoService.eliminar(id);
            flash.addFlashAttribute("mensaje", "Equipo eliminado con éxito");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar el equipo. Verifica que no tenga jugadores asociados.");
        }
        
        return "redirect:/equipos";
    }
    
    @GetMapping("/{id}/ver")
    public String ver(@PathVariable Long id, Model model) {
        Equipo equipo = equipoService.buscarPorId(id);
        model.addAttribute("equipo", equipo);
        
        futbolManagedBean.seleccionarEquipo(equipo);
        
        return "equipos/detalle";
    }
    
    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String patron, Model model) {
        List<Equipo> equipos = equipoService.buscarPorPatron(patron);
        
        futbolManagedBean.guardarBusquedaEquipos(equipos, patron);
        
        model.addAttribute("equipos", equipos);
        model.addAttribute("patron", patron);
        model.addAttribute("futbolBean", futbolManagedBean);
        
        return "equipos/resultados-busqueda";
    }
    
    @GetMapping("/categoria/{categoria}")
    public String filtrarPorCategoria(@PathVariable Equipo.CategoriaEquipo categoria, Model model) {
        List<Equipo> equipos = equipoService.buscarPorCategoria(categoria);
        model.addAttribute("equipos", equipos);
        model.addAttribute("categoriaActual", categoria);
        model.addAttribute("categorias", Equipo.CategoriaEquipo.values());
        
        return "equipos/lista-por-categoria";
    }
} 