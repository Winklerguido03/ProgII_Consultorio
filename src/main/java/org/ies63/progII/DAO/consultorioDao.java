package org.ies63.progII.DAO;

import org.ies63.progII.entities.Consultorio;
import org.ies63.progII.entities.Paciente;
import org.ies63.progII.entities.Turno;
import org.ies63.progII.exceptions.FechaInválidaException;
import org.ies63.progII.exceptions.FechaPasadaException;
import org.ies63.progII.exceptions.TurnosDuplicadosException;
import org.ies63.progII.interfaces.DAO;
import org.ies63.progII.interfaces.adminConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class consultorioDao implements DAO<Turno,Integer>, adminConexion {

    private static final String SQL_INSERT = "INSERT INTO turno (Dia,Hora,Consultorio_nroConsultorio,Paciente_nroPaciente)" +
            "VALUES (?,?,?,?)";

    private static final String SQL_GETALL = "SELECT t.idTurno,t.Dia,t.Hora,c.nroConsultorio AS Consultorio_nroConsultorio,p.NroPaciente AS Paciente_NroPaciente,p.Nombre " +
            "FROM turno t " +
            "INNER JOIN paciente p ON t.Paciente_NroPaciente = p.NroPaciente " +
            "INNER JOIN consultorio c ON t.Consultorio_nroConsultorio = c.nroConsultorio;";

    private static final String SQL_DELETE="DELETE FROM turno WHERE idTurno=?";

    private static final String SQL_GETBYFECHA="SELECT t.idTurno, t.Dia, t.Hora, " +
            "c.nroConsultorio AS Consultorio_nroConsultorio, " +
            "p.NroPaciente AS Paciente_NroPaciente, p.Nombre " +
            "FROM turno t " +
            "INNER JOIN paciente p ON t.Paciente_NroPaciente = p.NroPaciente " +
            "INNER JOIN consultorio c ON t.Consultorio_nroConsultorio = c.nroConsultorio " +
            "WHERE t.Dia = ?";

    private static final String SQL_EXISTSBYFECHAYHORA="SELECT COUNT(*) FROM turno WHERE Dia = ? AND Hora = ?";

    List<Turno> turnos = new ArrayList<>();

    @Override
    public List<Turno> getAll() {
        Connection conn=obtenerConexion();
        List<Turno> listaTurnos = new ArrayList<>();
        PreparedStatement pst=null;
        ResultSet rs=null;

        try{
              pst=conn.prepareStatement(SQL_GETALL);
              rs=pst.executeQuery();

              while (rs.next()){

                  Turno turno = new Turno();
                  Consultorio consultorio = new Consultorio();
                  Paciente paciente = new Paciente();

                  turno.setIdTurno(rs.getInt("idTurno"));
                  turno.setDia(rs.getDate("Dia"));
                  turno.setHora(rs.getTime("Hora"));
                  consultorio.setNroConsultorio(rs.getInt("Consultorio_nroConsultorio"));
                  turno.setNroConsultorio(consultorio);
                  paciente.setNroPaciente(rs.getInt("Paciente_NroPaciente"));
                  paciente.setNombre(rs.getString("Nombre"));
                  turno.setNroPaciente(paciente);

                  listaTurnos.add(turno);
              }

              rs.close();
              pst.close();
              conn.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listaTurnos;
    }

    @Override
    public void insert(Turno objeto) {

        Turno turno =objeto;

        Connection conn = obtenerConexion();

        PreparedStatement pst = null;

        Date hoy = new Date(System.currentTimeMillis());

        if (turno.getDia().before(hoy)) {
            throw new FechaPasadaException("No se pueden asignar turnos en fechas pasadas");
        }

        if (existsByFechaYHora(turno.getDia(), turno.getHora())) {
            throw new TurnosDuplicadosException(
                    "Ya existe un turno para esa fecha y hora"
            );
        }

        try{

            pst= conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pst.setDate(1,turno.getDia());
            pst.setTime(2,turno.getHora());
            pst.setInt(3,turno.getNroConsultorio().getNroConsultorio());
            pst.setInt(4,turno.getNroPaciente().getNroPaciente());
            if (turno.getDia() == null) {
                throw new FechaInválidaException("La fecha no puede ser inválida");
            }
            int resultado=pst.executeUpdate();
            if (resultado==1){
                System.out.println("turno agendado correctamente");
            }else {
                System.out.println("Error al agendar turno");
            }
        }
        catch (RuntimeException e){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        turnos.add(turno);
    }

    public List<Turno> getByFecha(Date fecha) {

        Connection conn = obtenerConexion();
        List<Turno> listaTurnos = new ArrayList<>();

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {

            pst = conn.prepareStatement(SQL_GETBYFECHA);
            pst.setDate(1, fecha);

            rs = pst.executeQuery();

            while (rs.next()) {

                Turno turno = new Turno();
                Consultorio consultorio = new Consultorio();
                Paciente paciente = new Paciente();

                turno.setIdTurno(rs.getInt("idTurno"));
                turno.setDia(rs.getDate("Dia"));
                turno.setHora(rs.getTime("Hora"));

                consultorio.setNroConsultorio(rs.getInt("Consultorio_nroConsultorio"));
                turno.setNroConsultorio(consultorio);

                paciente.setNroPaciente(rs.getInt("Paciente_NroPaciente"));
                paciente.setNombre(rs.getString("Nombre"));
                turno.setNroPaciente(paciente);

                listaTurnos.add(turno);
            }

            if (listaTurnos.isEmpty()) {
                throw new RuntimeException("No hay turnos para esa fecha");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listaTurnos;
    }

    public boolean existsByFechaYHora(Date dia, Time hora) {

        Connection conn = obtenerConexion();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {

            pst = conn.prepareStatement(SQL_EXISTSBYFECHAYHORA);
            pst.setDate(1, dia);
            pst.setTime(2, hora);

            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public void update(Turno objeto) {

    }

    @Override
    public void delete(Integer id) {

        Connection conn=this.obtenerConexion();

        try{

            PreparedStatement pst=conn.prepareStatement(SQL_DELETE);
            pst.setInt(1, id);
            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                System.out.println("Turno cancelado correctamente");
            } else {
                System.out.println("No se pudo eliminar el turno");
            }
            pst.close();
            conn.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Turno getById(Integer id) {
        return null;
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }
}
