package org.ies63.progII.entities;

import java.sql.Time;
import java.util.Date;

public class Turno {

    private Date dia;
    private Time hora;
    private int nroConsultorio;
    private int nroPaciente;

    public Turno(Date dia, Time hora, int nroConsultorio, int nroPaciente) {
        this.dia = dia;
        this.hora = hora;
        this.nroConsultorio = nroConsultorio;
        this.nroPaciente = nroPaciente;
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

    public void setNroConsultorio(int nroConsultorio) {
        this.nroConsultorio = nroConsultorio;
    }

    public int getNroConsultorio() {
        return nroConsultorio;
    }

    public void setNroPaciente(int nroPaciente) {
        this.nroPaciente = nroPaciente;
    }

    public int getNroPaciente() {
        return nroPaciente;
    }


}
