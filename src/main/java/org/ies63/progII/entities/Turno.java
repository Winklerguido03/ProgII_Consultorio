package org.ies63.progII.entities;

import java.sql.Time;
import java.sql.Date;

public class Turno {

    private int idTurno;
    private Date dia;
    private Time hora;
    private Consultorio nroConsultorio;
    private Paciente nroPaciente;

    public Turno(){}

    public Turno(Date dia, Time hora, Consultorio nroConsultorio,Paciente nroPaciente) {
        this.dia = dia;
        this.hora = hora;
        this.nroConsultorio = new Consultorio();
        this.nroPaciente = new Paciente();
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Date getDia() {
        return dia;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Time getHora() {
        return hora;
    }

    public void setNroConsultorio(Consultorio nroConsultorio ) {
        this.nroConsultorio = nroConsultorio;
    }

    public Consultorio getNroConsultorio() {
        return nroConsultorio;
    }

    public void setNroPaciente(Paciente nroPaciente) {
        this.nroPaciente = nroPaciente;
    }

    public Paciente getNroPaciente() {
        return nroPaciente;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }
}
