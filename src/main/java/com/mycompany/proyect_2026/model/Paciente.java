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

    private String dni;
    // controlar que sea 7 u 8 caracter antes de usar
    private boolean obraSocial;

    public Paciente() {
        this.nombre = null;
        this.apellido = null;
        this.mail = null;
        this.telefono = 0;
        this.dni = null;
        this.obraSocial = false;
    }

    public Paciente(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = null;
        this.telefono = 0;
        this.dni = dni;
        this.obraSocial = false;
    }

    //dni
    public void setDni(String numero) {
        this.dni = numero;
    }

    public String getDni() {
        return this.dni;
    }

    //obraSocial  
    // modificado en 11/04/2026 - Lin
    // pequeña modificacion del nombre
    public void setObraSocial(String obraSocial) {
        if (obraSocial.compareToIgnoreCase("si") == 0) {
            this.obraSocial = true;
            return;
        }
        if (obraSocial.compareToIgnoreCase("no") == 0) {
            this.obraSocial = false;
            return;
        }
    }
    // modificado en 11/04/2026 - Lin
    // pequeña modificacion del nombre
    public String getObraSocial() {
        if (this.obraSocial) {
            return "SI";
        }
        return "NO";
    }
    
    // agreagado en 11/04/2026 - Lin
    @Override
    public String toString(){
        return "Paciente: "+super.toString()
                +"\n\tdni:\t\t\t"+this.dni
                +"\n\tobra social:\t"+this.getObraSocial();
    }
}
