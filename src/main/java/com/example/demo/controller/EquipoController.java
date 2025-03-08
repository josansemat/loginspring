package com.example.demo.controller;

import com.example.demo.config.EquiposFavoritosBean;
import com.example.demo.model.EquipoFutbol;
import com.example.demo.model.EquipoPrimera;
import com.example.demo.model.EquipoSegunda;
import com.example.demo.model.Usuario;
import com.example.demo.service.EquipoService;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private EquiposFavoritosBean equiposFavoritos;
    
    // Método para proporcionar equiposFavoritos a todas las vistas
    @ModelAttribute("equiposFavoritos")
    public EquiposFavoritosBean getEquiposFavoritos() {
        return equiposFavoritos;
    }
    
    // Listar todos los equipos
    @GetMapping
    public String listarEquipos(Model model, @RequestParam(required = false) String buscar) {
        List<EquipoFutbol> equipos;
        
        if (buscar != null && !buscar.isEmpty()) {
            equipos = equipoService.buscarPorPatron(buscar);
            model.addAttribute("busqueda", buscar);
        } else {
            equipos = equipoService.listarTodos();
        }
        
        model.addAttribute("equipos", equipos);
        return "equipos/lista";
    }
    
    // Ver detalles de un equipo
    @GetMapping("/{id}")
    public String verEquipo(@PathVariable Long id, Model model) {
        Optional<EquipoFutbol> equipo = equipoService.buscarPorId(id);
        
        if (equipo.isPresent()) {
            model.addAttribute("equipo", equipo.get());
            model.addAttribute("esFavorito", equiposFavoritos.esFavorito(id));
            return "equipos/detalle";
        } else {
            return "redirect:/equipos?error=equipo-no-encontrado";
        }
    }
    
    // Formulario para nuevo equipo
    @GetMapping("/nuevo")
    public String nuevoEquipoForm(Model model, @RequestParam(defaultValue = "PRIMERA") String tipo) {
        model.addAttribute("esNuevo", true);
        model.addAttribute("tipoEquipo", tipo);
        
        if ("PRIMERA".equals(tipo)) {
            model.addAttribute("equipo", new EquipoPrimera());
        } else {
            model.addAttribute("equipo", new EquipoSegunda());
        }
        
        return "equipos/formulario";
    }
    
    // Procesar formulario de nuevo equipo
    @PostMapping("/nuevo")
    public String guardarNuevoEquipo(@Valid @ModelAttribute("equipo") EquipoFutbol equipo, 
                                    BindingResult result, 
                                    Model model,
                                    @RequestParam String tipoEquipo,
                                    RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("esNuevo", true);
            model.addAttribute("tipoEquipo", tipoEquipo);
            return "equipos/formulario";
        }
        
        // Asignar usuario creador
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        usuarioService.buscarPorUsername(auth.getName()).ifPresent(equipo::setUsuarioCreador);
        
        equipoService.guardar(equipo);
        redirectAttributes.addFlashAttribute("mensaje", "Equipo guardado correctamente");
        return "redirect:/equipos";
    }
    
    // Formulario para editar equipo
    @GetMapping("/{id}/editar")
    public String editarEquipoForm(@PathVariable Long id, Model model) {
        Optional<EquipoFutbol> equipo = equipoService.buscarPorId(id);
        
        if (equipo.isPresent()) {
            model.addAttribute("equipo", equipo.get());
            model.addAttribute("esNuevo", false);
            model.addAttribute("tipoEquipo", equipoService.getTipoEquipo(equipo.get()));
            return "equipos/formulario";
        } else {
            return "redirect:/equipos?error=equipo-no-encontrado";
        }
    }
    
    // Procesar formulario de edición
    @PostMapping("/{id}/editar")
    public String actualizarEquipo(@PathVariable Long id, 
                                  @Valid @ModelAttribute("equipo") EquipoFutbol equipo,
                                  BindingResult result, 
                                  Model model,
                                  @RequestParam String tipoEquipo,
                                  RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("esNuevo", false);
            model.addAttribute("tipoEquipo", tipoEquipo);
            return "equipos/formulario";
        }
        
        equipo.setId(id);
        equipoService.guardar(equipo);
        
        redirectAttributes.addFlashAttribute("mensaje", "Equipo actualizado correctamente");
        return "redirect:/equipos";
    }
    
    // Eliminar equipo
    @PostMapping("/{id}/eliminar")
    public String eliminarEquipo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        equipoService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Equipo eliminado correctamente");
        return "redirect:/equipos";
    }
    
    // Gestión de favoritos (usando bean de sesión)
    @PostMapping("/{id}/favorito")
    public String toggleFavorito(@PathVariable Long id, @RequestParam(required = false) String redirect) {
        if (equiposFavoritos.esFavorito(id)) {
            equiposFavoritos.eliminarFavorito(id);
        } else {
            equiposFavoritos.agregarFavorito(id);
        }
        
        if (redirect != null && !redirect.isEmpty()) {
            return "redirect:" + redirect;
        }
        return "redirect:/equipos/" + id;
    }
    
    // Ver equipos favoritos (usando bean de sesión)
    @GetMapping("/favoritos")
    public String verFavoritos(Model model) {
        List<Long> idsFavoritos = equiposFavoritos.getEquiposFavoritosIds();
        List<EquipoFutbol> equiposFavs = idsFavoritos.stream()
                .map(id -> equipoService.buscarPorId(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        
        model.addAttribute("equipos", equiposFavs);
        return "equipos/favoritos";
    }
    
    // Ver mis equipos (usando HttpSession)
    @GetMapping("/mis-equipos")
    public String verMisEquipos(Model model, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioService.buscarPorUsername(auth.getName()).orElse(null);
        
        if (usuario != null) {
            List<EquipoFutbol> misEquipos = equipoService.listarEquiposPorUsuario(usuario.getId());
            model.addAttribute("equipos", misEquipos);
            
            // Usar HttpSession para contar visitas a esta página
            Integer visitas = (Integer) session.getAttribute("visitasMisEquipos");
            if (visitas == null) {
                visitas = 1;
            } else {
                visitas++;
            }
            session.setAttribute("visitasMisEquipos", visitas);
            model.addAttribute("visitas", visitas);
            
            return "equipos/mis-equipos";
        } else {
            return "redirect:/login";
        }
    }
    
    // Filtrar por categoría
    @GetMapping("/categoria/{categoria}")
    public String filtrarPorCategoria(@PathVariable String categoria, Model model) {
        List<EquipoFutbol> equipos;
        
        if ("primera".equalsIgnoreCase(categoria)) {
            equipos = equipoService.listarEquiposPrimera();
            model.addAttribute("categoriaActual", "Primera División");
        } else if ("segunda".equalsIgnoreCase(categoria)) {
            equipos = equipoService.listarEquiposSegunda();
            model.addAttribute("categoriaActual", "Segunda División");
        } else {
            return "redirect:/equipos";
        }
        
        model.addAttribute("equipos", equipos);
        return "equipos/lista";
    }
} 