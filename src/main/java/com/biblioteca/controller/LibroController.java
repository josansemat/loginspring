package com.biblioteca.controller;

import com.biblioteca.SessionComponent;
import com.biblioteca.model.Libro;
import com.biblioteca.service.LibroService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/libros")
public class LibroController {
    
    @Autowired
    private LibroService libroService;
    
    @Autowired
    private SessionComponent sessionComponent;
    
    // Middleware para verificar autenticación
    @ModelAttribute
    public void verificarAutenticacion(Model model, HttpSession session) {
        // Verificar si el usuario está autenticado
        Boolean autenticado = (Boolean) session.getAttribute("autenticado");
        if (autenticado == null || !autenticado) {
            throw new RuntimeException("Acceso no autorizado");
        }
        
        // Añadir información de sesión al modelo
        model.addAttribute("nombreUsuario", sessionComponent.getNombreUsuario());
        
        // Añadir mensajes de sesión al modelo
        if (!sessionComponent.getMensajes().isEmpty()) {
            model.addAttribute("mensajes", sessionComponent.getMensajes());
            sessionComponent.limpiarMensajes();
        }
    }
    
    // Listar todos los libros
    @GetMapping
    public String listarLibros(Model model) {
        List<Libro> libros = libroService.obtenerTodos();
        model.addAttribute("libros", libros);
        return "libros/lista";
    }
    
    // Mostrar formulario para crear un nuevo libro
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("libro", new Libro());
        return "libros/crear";
    }
    
    // Procesar la creación de un libro
    @PostMapping("/crear")
    public String procesarCreacion(@Valid @ModelAttribute("libro") Libro libro, 
                                  BindingResult result, 
                                  RedirectAttributes redirectAttributes) {
        
        // Verificar si hay errores de validación
        if (result.hasErrors()) {
            return "libros/crear";
        }
        
        // Verificar si el ISBN ya existe
        if (libro.getIsbn() != null && !libro.getIsbn().isEmpty() && libroService.existeIsbn(libro.getIsbn())) {
            result.rejectValue("isbn", "error.libro", "Ya existe un libro con este ISBN");
            return "libros/crear";
        }
        
        // Guardar el libro
        libroService.guardar(libro);
        
        // Agregar mensaje de éxito a la sesión
        sessionComponent.agregarMensaje("Libro creado correctamente");
        
        return "redirect:/libros";
    }
    
    // Mostrar formulario para editar un libro
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<Libro> libro = libroService.obtenerPorId(id);
        if (libro.isPresent()) {
            model.addAttribute("libro", libro.get());
            return "libros/editar";
        } else {
            sessionComponent.agregarMensaje("El libro no existe");
            return "redirect:/libros";
        }
    }
    
    // Procesar la edición de un libro
    @PostMapping("/editar/{id}")
    public String procesarEdicion(@PathVariable Long id, 
                                 @Valid @ModelAttribute("libro") Libro libro, 
                                 BindingResult result) {
        
        // Verificar si hay errores de validación
        if (result.hasErrors()) {
            return "libros/editar";
        }
        
        // Verificar si el ISBN ya existe y no es el mismo libro
        if (libro.getIsbn() != null && !libro.getIsbn().isEmpty()) {
            Libro existente = libroService.obtenerPorId(id).orElse(null);
            if (existente != null && !existente.getIsbn().equals(libro.getIsbn()) && libroService.existeIsbn(libro.getIsbn())) {
                result.rejectValue("isbn", "error.libro", "Ya existe un libro con este ISBN");
                return "libros/editar";
            }
        }
        
        // Actualizar el libro
        libro.setId(id);  // Asegurar que el ID es correcto
        libroService.guardar(libro);
        
        // Agregar mensaje de éxito a la sesión
        sessionComponent.agregarMensaje("Libro actualizado correctamente");
        
        return "redirect:/libros";
    }
    
    // Ver detalles de un libro
    @GetMapping("/detalles/{id}")
    public String verDetalles(@PathVariable Long id, Model model) {
        Optional<Libro> libro = libroService.obtenerPorId(id);
        if (libro.isPresent()) {
            model.addAttribute("libro", libro.get());
            return "libros/detalles";
        } else {
            sessionComponent.agregarMensaje("El libro no existe");
            return "redirect:/libros";
        }
    }
    
    // Eliminar un libro
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        try {
            libroService.eliminar(id);
            sessionComponent.agregarMensaje("Libro eliminado correctamente");
        } catch (Exception e) {
            sessionComponent.agregarMensaje("Error al eliminar el libro: " + e.getMessage());
        }
        return "redirect:/libros";
    }
    
    // Buscar libros por patrón
    @GetMapping("/buscar")
    public String buscarLibros(@RequestParam String patron, Model model) {
        if (patron.trim().isEmpty()) {
            return "redirect:/libros";
        }
        
        List<Libro> libros = libroService.buscarPorPatron(patron);
        model.addAttribute("libros", libros);
        model.addAttribute("patron", patron);
        
        // Guardar la última búsqueda en la sesión
        sessionComponent.setUltimaBusqueda(patron);
        
        return "libros/lista";
    }
} 