package org.ies63.progII.DAO;

import org.ies63.progII.entities.Turno;
import org.ies63.progII.interfaces.DAO;
import org.ies63.progII.interfaces.adminConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class turnoDao implements DAO<Turno,Integer>, adminConexion {

    private static final String SQL_INSERT = "INSERT INTO turno (Dia,Hora,Consultorio_nroConsultorio,Paciente_nroPaciente)" +
            "VALUES (?,?,?,?)";

    private static final String SQL_GETALL = "SELECT Dia,Hora,Consultorio_nroConsultorio,Paciente_nroPaciente FROM turno";

    private static final String SQL_DELETE="DELETE FROM turno WHERE idTurno=?";

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

                  turno.setDia(rs.getDate("Dia"));
                  turno.setHora(rs.getTime("Hora"));
                  turno.setNroConsultorio(rs.getInt("Consultorio_nroConsultorio"));
                  turno.setNroPaciente(rs.getInt("Paciente_NroPaciente"));

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

        try{

            pst= conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            //pst.setDate(1,turno.getDia());
            pst.setTime(2,turno.getHora());
            pst.setInt(3,turno.getNroConsultorio());
            pst.setInt(4,turno.getNroPaciente());

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
