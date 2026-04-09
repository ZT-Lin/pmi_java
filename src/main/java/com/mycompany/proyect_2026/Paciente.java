/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026;

/**
 *
 * @author linzh
 */
class Paciente extends Persona {

    private String dni;
    // controlar que sea 7 u 8 caracter antes de usar
    private boolean obraSocial;

    Paciente() {
        this.nombre = null;
        this.apellido = null;
        this.mail = null;
        this.telefono = 0;
        this.dni = null;
        this.obraSocial = false;
    }

    Paciente(String nombre, String apellido, String dni) {
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
    public void setObSocial(String obraSocial) {
        if (obraSocial.compareToIgnoreCase("si") == 0) {
            this.obraSocial = true;
            return;
        }
        if (obraSocial.compareToIgnoreCase("no") == 0) {
            this.obraSocial = false;
            return;
        }
    }

    public String getObSocial() {
        if (this.obraSocial) {
            return "SI";
        }
        return "NO";
    }
}
