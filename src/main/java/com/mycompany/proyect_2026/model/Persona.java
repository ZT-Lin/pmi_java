/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.model;

/**
 *
 * @author linzh
 */
public abstract class Persona {
    protected String nombre, apellido;
    /*
        ↑no se modifica↑
        implementar el set y get 
        pero no le deja cambiar en el sistema
     */
    protected String mail;
    protected int telefono;

    public Persona() {
        this.nombre = null;
        this.apellido = null;
        this.mail = null;
        this.telefono = 0;
    }
    //nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    //apellido
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido() {
        return this.apellido;
    }

    //mail
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return this.mail;
    }

    //telefono
    public void setTelef(int numero) {
        this.telefono = numero;
    }

    public int getTelef() {
        return this.telefono;
    }
    
    @Override
    public String toString(){
        return this.nombre + " "+ this.apellido
                +"\n\ttelefono:\t\t"+this.telefono
                +"\n\tcorreo:\t\t"+this.mail;
    }
}
