/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026;

/**
 *
 * @author Usuario
 */
public class Estudio {
    private Fecha fecha;
    private String nombreEstudio;
    private String informeResultados;
    
    //Variales de instancia por composicion (Relacion con las clases de Lin)
    private Paciente paciente;
    private Profesional profesional;
    
    
    private String[] analisis;


    //Constructor
    public Estudio(Fecha fecha, String nombreEstudio, Paciente paciente, Profesional profesional) {
        this.fecha = fecha;
        this.nombreEstudio = nombreEstudio;
        this.informeResultados = "Pendiente de resultados"; //Valor por defecto
        this.paciente = paciente;
        this.profesional = profesional;
    }

    //Getters y Setters
    public Fecha getFecha() {
        return fecha;
    }
    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
    public String getNombreEstudio() {
        return nombreEstudio;
    }
    public void setNombreEstudio(String nombreEstudio) {
        this.nombreEstudio = nombreEstudio;
    }
    public String getInformeResultados() {
        return informeResultados;
    }
    public void setInformeResultados(String informeResultados) {
        this.informeResultados = informeResultados;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Profesional getProfesional() {
        return profesional;
    }
    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }
    
    
    /*creado en 11/04/2026-Lin*/
    public void setAnalisis(String... analisis){
        if(analisis == null || analisis.length == 0){
            this.analisis = new String[0];
            return;
        }
        this.analisis = new String[analisis.length];
        System.arraycopy(analisis, 0,this.analisis, 0,analisis.length);
    }
    
    //Metodo toString
    @Override
    public String toString(){
        String str =
               "Estudio: " + nombreEstudio +
               "\n\tFecha: " + fecha.toString() + " " + paciente.getApellido() +
               "\n\tPaciente: " + paciente.getNombre() +
               "\n\tProfesional a cargo: " + profesional.getNombre() +
               "\n\tInforme: " + informeResultados;
        
        StringBuilder an = new StringBuilder();
        
        if (this.analisis == null || this.analisis.length == 0) {
            an.append("\n\t(No hay estudio cargado)");
            return str + an.toString();
        }
        
        for(String s: this.analisis){
           an.append("\n\t- ").append(s);
        }
        return str + an.toString();
    }
}
