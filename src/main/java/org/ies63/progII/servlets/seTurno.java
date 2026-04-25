package org.ies63.progII.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ies63.progII.DAO.consultorioDao;
import org.ies63.progII.DAO.pacienteDao;
import org.ies63.progII.entities.Consultorio;
import org.ies63.progII.entities.Paciente;
import org.ies63.progII.entities.Turno;
import org.ies63.progII.exceptions.FechaPasadaException;
import org.ies63.progII.exceptions.TurnosDuplicadosException;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

@WebServlet("/seTurno")
public class seTurno extends HttpServlet {

    private consultorioDao dao = new consultorioDao();
    private pacienteDao pacienteDao = new pacienteDao();

    public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{

        String accion =request.getParameter("operacion");

        switch (accion){
            case "eliminar":
            int id = Integer.parseInt(request.getParameter("id"));
            dao.delete(id);
            response.sendRedirect("seConsultorio");

        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        String accion = request.getParameter("operacion");

        switch (accion){
            case "nuevo":
                String nombre = request.getParameter("txtNombreApellido");
                String telefono = request.getParameter("txtTel");
                int consultorio = Integer.parseInt(request.getParameter("txtConsultorio"));
                String dia = request.getParameter("txtDia");
                String hora = request.getParameter("txtHora");

                //PACIENTE
                Paciente p = new Paciente();
                p.setNombre(nombre);
                p.setTelefono(telefono);

                int idPaciente = pacienteDao.insertReturnId(p);

                Paciente paciente = new Paciente();
                paciente.setNroPaciente(idPaciente);

                //CONSULTORIO
                Consultorio c = new Consultorio();
                c.setNroConsultorio(consultorio);

                //TURNO
                Turno turno = new Turno();
                turno.setDia(Date.valueOf(dia));
                turno.setHora(Time.valueOf(hora + ":00"));
                turno.setNroConsultorio(c);
                turno.setNroPaciente(paciente);

                try {

                    dao.insert(turno);
                    response.sendRedirect("seConsultorio");

                } catch (TurnosDuplicadosException | FechaPasadaException e) {

                    request.setAttribute("error", e.getMessage());
                    request.getRequestDispatcher("FormularioTurno.jsp")
                            .forward(request, response);
                }
                break;
        }

    }

}
