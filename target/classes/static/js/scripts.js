// Esperar a que se cargue el DOM
document.addEventListener('DOMContentLoaded', function() {
    // Función para hacer desaparecer los mensajes después de 5 segundos
    const mensajes = document.querySelectorAll('.mensaje');
    if (mensajes.length > 0) {
        setTimeout(function() {
            mensajes.forEach(function(mensaje) {
                mensaje.style.opacity = '0';
                mensaje.style.transition = 'opacity 0.5s ease';
                
                setTimeout(function() {
                    mensaje.style.display = 'none';
                }, 500);
            });
        }, 5000);
    }
    
    // Validación del formulario de creación/edición en el lado del cliente
    const formulario = document.querySelector('form');
    if (formulario) {
        formulario.addEventListener('submit', function(event) {
            let valido = true;
            
            // Validar título
            const titulo = document.getElementById('titulo');
            if (titulo && (!titulo.value.trim() || titulo.value.length < 2 || titulo.value.length > 100)) {
                mostrarError(titulo, 'El título debe tener entre 2 y 100 caracteres.');
                valido = false;
            } else if (titulo) {
                eliminarError(titulo);
            }
            
            // Validar autor
            const autor = document.getElementById('autor');
            if (autor && (!autor.value.trim() || autor.value.length < 2 || autor.value.length > 100)) {
                mostrarError(autor, 'El autor debe tener entre 2 y 100 caracteres.');
                valido = false;
            } else if (autor) {
                eliminarError(autor);
            }
            
            // Validar año de publicación
            const anioPublicacion = document.getElementById('anioPublicacion');
            if (anioPublicacion) {
                const anio = parseInt(anioPublicacion.value.trim());
                if (isNaN(anio) || anio < 1000 || anio > 2100) {
                    mostrarError(anioPublicacion, 'El año debe estar entre 1000 y 2100.');
                    valido = false;
                } else {
                    eliminarError(anioPublicacion);
                }
            }
            
            // Validar ISBN
            const isbn = document.getElementById('isbn');
            if (isbn && isbn.value.trim() !== '') {
                const isbnPattern = /^(\d{10}|\d{13})$/;
                if (!isbnPattern.test(isbn.value.trim())) {
                    mostrarError(isbn, 'El ISBN debe tener 10 o 13 dígitos.');
                    valido = false;
                } else {
                    eliminarError(isbn);
                }
            } else if (isbn) {
                eliminarError(isbn);
            }
            
            // Validar descripción
            const descripcion = document.getElementById('descripcion');
            if (descripcion && descripcion.value.length > 500) {
                mostrarError(descripcion, 'La descripción no puede exceder los 500 caracteres.');
                valido = false;
            } else if (descripcion) {
                eliminarError(descripcion);
            }
            
            if (!valido) {
                event.preventDefault();
            }
        });
    }
    
    // Función para mostrar error debajo de un campo
    function mostrarError(campo, mensaje) {
        // Verificar si ya existe un mensaje de error para este campo
        let errorDiv = campo.parentNode.querySelector('.error');
        
        if (!errorDiv) {
            errorDiv = document.createElement('div');
            errorDiv.className = 'error';
            campo.parentNode.appendChild(errorDiv);
        }
        
        errorDiv.textContent = mensaje;
        campo.style.borderColor = '#e74c3c';
    }
    
    // Función para eliminar error de un campo
    function eliminarError(campo) {
        const errorDiv = campo.parentNode.querySelector('.error');
        if (errorDiv) {
            errorDiv.remove();
        }
        campo.style.borderColor = '#ddd';
    }
    
    // Resetear estilos al cambiar el valor de un campo
    const inputElements = document.querySelectorAll('input, textarea, select');
    inputElements.forEach(function(input) {
        input.addEventListener('input', function() {
            this.style.borderColor = '#ddd';
            const errorDiv = this.parentNode.querySelector('.error');
            if (errorDiv) {
                errorDiv.remove();
            }
        });
    });
}); 