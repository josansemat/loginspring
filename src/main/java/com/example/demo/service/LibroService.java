package com.example.demo.service;

import com.example.demo.model.Libro;
import com.example.demo.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }

    public List<Libro> buscarPorPatron(String patron) {
        return libroRepository.findByNombreContainingIgnoreCase(patron);
    }

    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }
}
