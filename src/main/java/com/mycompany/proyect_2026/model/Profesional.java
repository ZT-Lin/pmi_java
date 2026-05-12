/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.model;

/**
 *
 * @author linzh
 */
public class Profesional extends Persona {

    private String matricula;

    public Profesional() {
        super();
        this.matricula = null;
    }

    public Profesional(String nombre, String apellido, String mail, String telefono, String matricula) {
        super(nombre, apellido, mail, telefono);
        this.matricula = matricula;
    }

    //matricula
    public void setMatricula(String dato) {
        this.matricula = dato;
    }

    public String getMatricula() {
        return this.matricula;
    }

    // agreagado en 11/04/2026 - Lin
    @Override
    public String toString() {
        return "Profesional: " + super.toString()
                + "\n\tmatricula:\t" + this.matricula;
    }
}
