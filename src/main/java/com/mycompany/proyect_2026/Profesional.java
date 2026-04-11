/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026;

/**
 *
 * @author linzh
 */
public class Profesional extends Persona {

    private int matricula;

    public Profesional() {
        this.nombre = null; 
        this.apellido = null;
        this.mail = null;
        this.telefono = 0;
        this.matricula = 00000000;
    }

    public Profesional(String nombre, String apellido, int matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = null;
        this.telefono = 0;
        this.matricula = matricula;
    }

    //matricula
    public void setMatricula(int dato) {
        this.matricula = dato;
    }

    public int getMatricula() {
        return this.matricula;
    }
}
