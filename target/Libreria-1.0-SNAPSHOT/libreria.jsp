<%-- 
    Document   : libreria
    Author     : Moises Corpus Garcia
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Librería en línea</title>
    <link rel="stylesheet" href="css/Libreria.css">
</head>
<body>

    <header class="header">
        <h1>Nuestra Librería</h1>
    </header>

    <!-- BARRA 1: Registrar libro -->
    <form class="barra-registro" action="BookServlet" method="post">
        <input type="hidden" name="accion" value="agregar"> 
        <input type="text" name="nombre" placeholder="Título" required>
        <input type="text" name="autor" placeholder="Autor" required>
        <input type="number" step="0.01" name="precio" placeholder="Precio" required>
        <button type="submit">Registrar libro</button>
    </form>

    <!-- BARRA 2: Buscar y filtrar -->
    <form class="barra-busqueda" action="BookServlet" method="get" id="formBusqueda">
        <span class="etiqueta">Buscar:</span>
        <input type="hidden" name="accion" value="buscar">
        <input type="text" name="buscarTitulo" placeholder="Título">
        <input type="text" name="buscarAutor" placeholder="Autor">
        <select name="rangoPrecio">
            <option value="">Precio</option>
            <option value="0-100">Menos de $100</option>
            <option value="100-200">$100 - $200</option>
            <option value="200-300">$200 - $300</option>
            <option value="300-9999">Más de $300</option>
        </select>
        <button type="submit">Buscar</button>
    </form>

    <!-- RESULTADOS -->
    <main class="grid-libros" id="gridLibros">
        <c:forEach var="libro" items="${libros}">
            <div class="tarjeta-libro">
                <div class="portada"></div>
                <p class="titulo">${libro.nombre}</p> 
                <p class="autor">${libro.autor}</p>
                <p class="precio">$${libro.precio}</p>
            </div>
        </c:forEach>
    </main> 

    <p class="mensaje-vacio" id="mensajeVacio" style="display: none;">
        No se encontraron libros con esos criterios.
    </p>

    <script src="js/Libreria.js"></script>
</body>
</html>