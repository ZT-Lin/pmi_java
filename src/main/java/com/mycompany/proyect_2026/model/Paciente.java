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

    // controlar que sea 7 u 8 caracter antes de usar
    private boolean obraSocial;

    public Paciente() {
        super();
        this.obraSocial = false;
    }

    public Paciente(String nombre, String apellido, String mail, String dni, int telefono) {
        super(nombre, apellido, mail, dni, telefono);
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
    
    // agreagado en 11/04/2026 - Lin
    @Override
    public String toString(){
        return "Paciente: "+super.toString()
                +"\n\tobra social:\t"+this.getObraSocial();
    }
}
