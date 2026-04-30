/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

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
    
    public static ArrayList<Profesional> cargarProfesionalesDesdeArchivo(){
        ArrayList<Profesional> lista = new ArrayList<>();
        try{
            File p = new File("data/profesionales.txt");
            Scanner lector = new Scanner(p);
            while(lector.hasNext()){
                String nombre = lector.next();
                String apellido = lector.next();
                String mail = lector.next();
                String telefono = lector.next();
                String matricula = lector.next();
                Profesional prof = new Profesional(nombre, apellido, mail, telefono, matricula);
                lista.add(prof);
            }
            lector.close();
        }catch(IOException e){
            System.out.println("Error al cargar profesionales: " + e.getMessage());
        }
        return lista;
    }
    
    // agreagado en 11/04/2026 - Lin
    @Override
    public String toString(){
        return "Profesional: "+super.toString()
                +"\n\tmatricula:\t"+this.matricula;
    }
}
