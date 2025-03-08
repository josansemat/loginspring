package com.example.demo.config;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Crear usuario administrador por defecto si no existe
        if (!usuarioRepository.existsByUsername("admin")) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setEmail("admin@ejemplo.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            admin.setActivo(true);
            usuarioRepository.save(admin);
            System.out.println("Usuario administrador creado con éxito");
        }

        // Crear usuario normal por defecto si no existe
        if (!usuarioRepository.existsByUsername("usuario")) {
            Usuario usuario = new Usuario();
            usuario.setUsername("usuario");
            usuario.setEmail("usuario@ejemplo.com");
            usuario.setPassword(passwordEncoder.encode("usuario123"));
            usuario.setRole("ROLE_USER");
            usuario.setActivo(true);
            usuarioRepository.save(usuario);
            System.out.println("Usuario normal creado con éxito");
        }
    }
} 