package org.ies63.progII.DAO;

import org.ies63.progII.entities.Paciente;
import org.ies63.progII.entities.Turno;
import org.ies63.progII.interfaces.DAO;
import org.ies63.progII.interfaces.adminConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class pacienteDao implements DAO<Paciente,Integer>, adminConexion {

    List<Paciente> pacientes=new ArrayList<>();

    private static final String SQL_INSERT="INSERT INTO paciente (telefono,nombre)"+"VALUES (?,?)";

    private static final String SQL_DELETE="DELETE FROM turno WHERE idTurno=?";

    @Override
    public List<Paciente> getAll() {

        List <Paciente> listaPacientes=new ArrayList<>();
        return listaPacientes;
    }

    @Override
    public void insert(Paciente objeto) {
        Paciente paciente =objeto;

        Connection conn = obtenerConexion();

        PreparedStatement pst = null;

        try{

            pst= conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1,paciente.getTelefono());
            pst.setString(2,paciente.getNombre());

            int resultado=pst.executeUpdate();
            if (resultado==1){
                System.out.println("paciente agregado correctamente");
            }else {
                System.out.println("Error al agregar paciente");
            }
        }
        catch (RuntimeException e){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pacientes.add(paciente);
    }

    @Override
    public void update(Paciente objeto) {

    }


    @Override
    public void delete(Integer id) {
        Connection conn = this.obtenerConexion();

        try {
            PreparedStatement pst = conn.prepareStatement(SQL_DELETE);
            pst.setInt(1, id);
            int resultado = pst.executeUpdate();
            if (resultado == 1) {
                System.out.println("Turno eliminado correctamente");
            } else {
                System.out.println("No se pudo eliminar el turno");
            }
            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el turno. Error: " + e.getMessage());
        }

    }

    @Override
    public Paciente getById(Integer id) {
        return null;
    }

    public int insertReturnId(Paciente p) {
        try {
            Connection conn = obtenerConexion();

            PreparedStatement pst = conn.prepareStatement(SQL_INSERT,Statement.RETURN_GENERATED_KEYS);

            pst.setInt(1, p.getTelefono());
            pst.setString(2, p.getNombre());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsById(Integer id) {
        return false;
    }
}
