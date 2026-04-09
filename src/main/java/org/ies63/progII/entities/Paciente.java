package org.ies63.progII.entities;

public class Paciente {

    private int nroPaciente;
    private int telefono;
    private String nombre;

    public Paciente(int telefono,String nombre){
        this.telefono=telefono;
        this.nombre=nombre;
    }

    public void setNombre(String nombre){this.nombre=nombre;}

    public int getTelefono(){return telefono;}

}
