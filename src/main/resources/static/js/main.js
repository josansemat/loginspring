// Funciones JavaScript personalizadas

// Mostrar confirmación antes de eliminar
function confirmarEliminar(id, nombre) {
    return confirm(`¿Estás seguro de eliminar al jugador ${nombre}?`);
}

// Inicialización cuando el documento está listo
document.addEventListener('DOMContentLoaded', function() {
    // Ocultar alertas después de 5 segundos
    const alertas = document.querySelectorAll('.alert');
    alertas.forEach(alerta => {
        setTimeout(() => {
            alerta.style.opacity = '0';
            alerta.style.transition = 'opacity 1s';
            
            // Remover después de la transición
            setTimeout(() => {
                alerta.style.display = 'none';
            }, 1000);
        }, 5000);
    });
    
    // Validación adicional del lado del cliente
    const formulario = document.getElementById('jugadorForm');
    if (formulario) {
        formulario.addEventListener('submit', function(e) {
            let esValido = true;
            
            // Validar nombre
            const nombre = document.getElementById('nombre');
            if (nombre.value.trim() === '') {
                nombre.classList.add('is-invalid');
                esValido = false;
            } else {
                nombre.classList.remove('is-invalid');
            }
            
            // Validar apellidos
            const apellidos = document.getElementById('apellidos');
            if (apellidos.value.trim() === '') {
                apellidos.classList.add('is-invalid');
                esValido = false;
            } else {
                apellidos.classList.remove('is-invalid');
            }
            
            // Si no es válido, prevenir envío
            if (!esValido) {
                e.preventDefault();
                window.scrollTo(0, 0);
            }
        });
    }
}); 