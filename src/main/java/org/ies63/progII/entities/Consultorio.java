package org.ies63.progII.entities;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Consultorio {

    private int nroConsultorio;
    private String medico;
    private List<Turno> turnos = new ArrayList<>();

    public Consultorio(){}

    public Consultorio(int nroConsultorio, String medico) {
        this.nroConsultorio = nroConsultorio;
        this.medico = medico;
    }

    public void setNroConsultorio(int nroConsultorio) {
        this.nroConsultorio = nroConsultorio;
    }

    public int getNroConsultorio() {
        return nroConsultorio;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getMedico() {
        return medico;
    }

    public void agregarTurno(Date dia, Time hora, Paciente nroPaciente,Consultorio nroConsultorio) {
        Turno nuevoTurno = new Turno(dia,hora,nroConsultorio,nroPaciente);
        turnos.add(nuevoTurno);
    }

    public void cancelarTurno(Date dia,Time hora){

        List<Turno> turnosAEliminar = new ArrayList<>();

    }

}
