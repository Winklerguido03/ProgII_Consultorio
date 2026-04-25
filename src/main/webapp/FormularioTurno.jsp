<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
  <head>
      <link rel="stylesheet" href="styles/styleFormTurno.css">
  </head>
<body>
  <header>
    <a class="titulo" href="seConsultorio">CONSULTORIO</a>
  </header>

<main>

  <div class="formulario">

    <h2>TURNO</h2>

    <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>

    <form action="seTurno" method="POST">

    <input type="hidden" name="txtId" id="txtId" value="-1" />
    <input type="hidden" name="operacion" id="operacion" value="nuevo" />

    <label for="txtNombre">Nombre y apellido</label>
    <input type="text" name="txtNombreApellido" id="txtNombreApellido" placeholder="Nombre y apellido" required />
    <br>
    <label for="txtTel">Número de teléfono</label>
    <input type="text" name="txtTel" id="txtTel" placeholder="Número de teléfono" required />
    <br>
    <label>Consultorio</label>
    <select name="txtConsultorio">
        <option value="1">Consultorio 1</option>
        <option value="2">Consultorio 2</option>
        <option value="3">Consultorio 3</option>
    </select>
    <br>
    <label for="txtDia">Dia</label>
    <input type="date" name="txtDia">
    <br>
    <label for="txtHora">Hora</label>
    <input type="time" name="txtHora">
    <br>
    <input class="btn" type="submit" value="Enviar" />

    </form>

  </div>

</main>

</body>
</html>



