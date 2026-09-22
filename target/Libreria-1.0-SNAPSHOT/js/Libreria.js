document.addEventListener('DOMContentLoaded', function () {


    var grid = document.getElementById('gridLibros');
    var mensajeVacio = document.getElementById('mensajeVacio');
    if (grid && mensajeVacio && grid.children.length === 0) {
        mensajeVacio.style.display = 'block';
    }


    var formBusqueda = document.getElementById('formBusqueda');
    var params = new URLSearchParams(window.location.search);
    if (formBusqueda && params.get('accion') === 'buscar') {
        ['buscarTitulo', 'buscarAutor', 'rangoPrecio'].forEach(function (nombre) {
            var campo = formBusqueda.elements[nombre];
            if (campo && params.has(nombre)) {
                campo.value = params.get(nombre);
            }
        });
    }


    var tarjetas = document.querySelectorAll('.tarjeta-libro');
    tarjetas.forEach(function (tarjeta, i) {
        tarjeta.style.animationDelay = (i * 0.06) + 's';
    });


    var formRegistro = document.querySelector('.barra-registro');
    if (formRegistro) {
        formRegistro.addEventListener('submit', function (e) {
            var precio = formRegistro.querySelector('[name="precio"]');
            if (precio && parseFloat(precio.value) <= 0) {
                e.preventDefault();
                alert('El precio debe ser mayor a 0.');
                precio.focus();
            }
        });
    }

});