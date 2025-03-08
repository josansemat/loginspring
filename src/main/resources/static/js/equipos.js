// Script para equipos de fútbol
document.addEventListener('DOMContentLoaded', function() {
    // Inicializar tooltips de Bootstrap si existen
    if (typeof bootstrap !== 'undefined' && bootstrap.Tooltip) {
        var tooltips = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
        tooltips.map(function(tooltip) {
            return new bootstrap.Tooltip(tooltip);
        });
    }
    
    // Mostrar vista previa de la URL del escudo en formulario si existe
    const urlEscudoInput = document.getElementById('urlEscudo');
    if (urlEscudoInput) {
        urlEscudoInput.addEventListener('blur', function() {
            const previewDiv = document.getElementById('escudoPreview');
            if (previewDiv) {
                if (this.value) {
                    previewDiv.innerHTML = `<img src="${this.value}" class="img-thumbnail mt-2" style="max-height: 100px;">`;
                    previewDiv.style.display = 'block';
                } else {
                    previewDiv.style.display = 'none';
                }
            }
        });
    }
}); 