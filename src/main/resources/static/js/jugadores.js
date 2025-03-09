// Función para confirmar eliminación
function confirmarEliminar(id, nombre) {
    if (confirm(`¿Estás seguro de que deseas eliminar al jugador ${nombre}?`)) {
        window.location.href = `/jugadores/eliminar/${id}`;
    }
}

// Función para validación del lado del cliente
function validarFormulario() {
    let nombre = document.getElementById('nombre').value;
    let apellidos = document.getElementById('apellidos').value;
    let edad = document.getElementById('edad').value;
    let posicion = document.getElementById('posicion').value;
    let equipo = document.getElementById('equipo').value;
    
    if (nombre.trim() === '') {
        mostrarError('El nombre es obligatorio');
        return false;
    }
    
    if (apellidos.trim() === '') {
        mostrarError('Los apellidos son obligatorios');
        return false;
    }
    
    if (isNaN(edad) || edad < 15 || edad > 50) {
        mostrarError('La edad debe estar entre 15 y 50 años');
        return false;
    }
    
    if (posicion === '') {
        mostrarError('Debe seleccionar una posición');
        return false;
    }
    
    if (equipo.trim() === '') {
        mostrarError('El equipo es obligatorio');
        return false;
    }
    
    return true;
}

function mostrarError(mensaje) {
    let errorDiv = document.getElementById('mensajeError');
    errorDiv.innerText = mensaje;
    errorDiv.style.display = 'block';
    
    // Ocultar después de 3 segundos
    setTimeout(() => {
        errorDiv.style.display = 'none';
    }, 3000);
}

// Filtro en tiempo real para la tabla de jugadores
function filtrarJugadores() {
    let input = document.getElementById('filtroRapido');
    let filter = input.value.toUpperCase();
    let table = document.getElementById('tablaJugadores');
    let tr = table.getElementsByTagName('tr');
    
    for (let i = 1; i < tr.length; i++) { // Empezar desde 1 para omitir cabecera
        let visible = false;
        let tds = tr[i].getElementsByTagName('td');
        
        for (let j = 0; j < tds.length - 1; j++) { // -1 para ignorar columna de acciones
            let cell = tds[j];
            if (cell) {
                let txtValue = cell.textContent || cell.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    visible = true;
                    break;
                }
            }
        }
        
        tr[i].style.display = visible ? '' : 'none';
    }
}

// Inicialización cuando el DOM está listo
document.addEventListener('DOMContentLoaded', function() {
    // Inicializar filtro rápido si existe
    const filtroRapido = document.getElementById('filtroRapido');
    if (filtroRapido) {
        filtroRapido.addEventListener('keyup', filtrarJugadores);
    }
    
    // Inicializar formulario si existe
    const formulario = document.getElementById('formularioJugador');
    if (formulario) {
        formulario.addEventListener('submit', function(event) {
            if (!validarFormulario()) {
                event.preventDefault();
            }
        });
    }
    
    // Añadir clase para animación
    document.querySelectorAll('.card').forEach(card => {
        card.classList.add('fade-in');
    });
}); 