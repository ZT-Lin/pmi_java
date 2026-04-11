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
    
    //Metodo toString
    @Override
    public String toString(){
        return "Estudio: " + nombreEstudio +
               "\nFecha: " + fecha.toString() + " " + paciente.getApellido() +
               "\nPaciente: " + paciente.getNombre() +
               "\nProfesional a cargo: " + profesional.getNombre() +
               "\nInforme: " + informeResultados;
    }
}
