package org.ies63.progII.entities;

public class Paciente {

    private int nroPaciente;
    private int telefono;
    private String nombre;


    public Paciente(){}

    public Paciente(int telefono,String nombre){
        this.telefono=telefono;
        this.nombre=nombre;
    }

    public void setNroPaciente (int nroPaciente){this.nroPaciente=nroPaciente;}

    public int getNroPaciente(){return nroPaciente;}

    public void setNombre(String nombre){this.nombre=nombre;}

    public String getNombre (){
        return nombre;
    }

    public int getTelefono(){return telefono;}


    public void setApellido(String apellido) {
    }

    public void setTelefono(String tel) {
        this.telefono=telefono;
    }
}
