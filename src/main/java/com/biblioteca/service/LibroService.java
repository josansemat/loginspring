package com.biblioteca.service;

import com.biblioteca.model.Libro;
import com.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    
    @Autowired
    private LibroRepository libroRepository;
    
    // Obtener todos los libros
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }
    
    // Obtener un libro por ID
    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }
    
    // Guardar un libro (crear o actualizar)
    public Libro guardar(Libro libro) {
        if (libro.getId() != null) {
            // Si el libro ya existe, actualizamos la fecha de modificación
            libro.actualizarFechaModificacion();
        }
        return libroRepository.save(libro);
    }
    
    // Eliminar un libro por ID
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
    
    // Buscar libros por patrón
    public List<Libro> buscarPorPatron(String patron) {
        return libroRepository.buscarPorPatron(patron);
    }
    
    // Buscar libros por título
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }
    
    // Buscar libros por autor
    public List<Libro> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }
    
    // Buscar libros por género
    public List<Libro> buscarPorGenero(String genero) {
        return libroRepository.findByGeneroIgnoreCase(genero);
    }
    
    // Verificar si existe un libro con el mismo ISBN
    public boolean existeIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn) != null;
    }
} 