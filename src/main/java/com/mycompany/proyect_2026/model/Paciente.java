/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.model;

/**
 *
 * @author linzh
 */
public class Paciente extends Persona {

    private boolean obraSocial;
    private String dni;

    public Paciente() {
        super();
        this.obraSocial = false;
    }

    public Paciente(String nombre, String apellido, String mail, String dni, String telefono) {
        super(nombre, apellido, mail, telefono);
        this.dni = dni;
        this.obraSocial = false;
    }

    //obraSocial  
    // modificado en 24/04/2026 - Tomas
    // pequeña modificacion de variable
    public void setObraSocial(boolean obraSocial) {
        this.obraSocial = obraSocial;
    }

    // modificado en 24/04/2026 - Tomas
    // pequeña modificacion del variable
    public boolean getObraSocial() {
        return this.obraSocial;
    }

    //modificado en 25/04/2026 - Lin
    //DNI no va en Persona
    //por que el Profesional no lo pide
    public void setDNI(String dni) {
        this.dni = dni;
    }

    public String getDNI() {
        return this.dni;
    }

    // agreagado en 11/04/2026 - Lin
    @Override
    public String toString() {
        return "Paciente: " + super.toString()
                + "\n\tobra social:\t" + this.getObraSocial();
    }
}
