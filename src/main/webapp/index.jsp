<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
   <title>Consultorio</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
   <link rel="stylesheet" type="text/css" href="styles/style.css">
   <meta charset="UTF-8">
</head>
<body>
<header>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="seConsultorio">Consultorio</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="FormularioTurno.jsp">Solicitar un TURNO<span class="sr-only"></span></a>
      </li>
    </ul>
  </div>
</nav>
</header>
<main>

<form action="seConsultorio" method="GET">

    <p>Buscar turnos por fecha:</p>

    <input type="date" name="txtDia">

    <button type="submit" class="btn btn-primary">
        Buscar
    </button>

</form>
</div>
<div class="turnos">

<table class="table table-striped table-bordered">
    <thead>
        <tr>
            <th>Fecha</th>
            <th>Hora</th>
            <th>Consultorio</th>
            <th>Paciente</th>
            <th> </th>
        </tr>
    </thead>

    <tbody>

    <c:if test="${empty listaTurnos}">
        <tr>
            <td colspan="5" class="text-center">
                No hay turnos registrados
            </td>
        </tr>
    </c:if>

    <c:forEach var="t" items="${listaTurnos}">
        <tr>
            <td>${t.dia}</td>
            <td>${t.hora}</td>
            <td>${t.nroConsultorio.nroConsultorio}</td>
            <td>${t.nroPaciente.nombre}</td>
            <td>
                <a class="btn btn-danger"
                   href="seConsultorio?operacion=eliminar&id=${t.idTurno}">
                   Cancelar
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>

</div>

</main>
<footer>
  <p>
  © 2026, Consultorio.com
  </p>
  </footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>