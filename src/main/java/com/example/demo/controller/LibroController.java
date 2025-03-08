package com.example.demo.controller;

import com.example.demo.model.Libro;
import com.example.demo.service.LibroService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public String listarLibros(Model model, HttpSession session) {
        model.addAttribute("libros", libroService.listarTodos());
        session.setAttribute("ultimaVista", "listaLibros");
        return "libros/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoLibro(Model model) {
        model.addAttribute("libro", new Libro());
        return "libros/formulario";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute("libro") @Valid Libro libro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "libros/formulario";
        }
        libroService.guardar(libro);
        return "redirect:/libros";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroService.eliminar(id);
        return "redirect:/libros";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam("patron") String patron, Model model) {
        model.addAttribute("libros", libroService.buscarPorPatron(patron));
        return "libros/lista";
    }
}
