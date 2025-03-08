package com.biblioteca.repository;

import com.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
    // Buscar libros por título que contenga cierto patrón
    List<Libro> findByTituloContainingIgnoreCase(String titulo);
    
    // Buscar libros por autor que contenga cierto patrón
    List<Libro> findByAutorContainingIgnoreCase(String autor);
    
    // Buscar libros por género
    List<Libro> findByGeneroIgnoreCase(String genero);
    
    // Buscar libros por ISBN
    Libro findByIsbn(String isbn);
    
    // Método personalizado para buscar por patrón en título, autor o descripción
    @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :patron, '%')) " +
           "OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :patron, '%')) " +
           "OR LOWER(l.descripcion) LIKE LOWER(CONCAT('%', :patron, '%'))")
    List<Libro> buscarPorPatron(@Param("patron") String patron);
} 