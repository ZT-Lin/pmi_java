package com.mycompany.proyect_2026.controller;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.proyect_2026.model.Estudio;
import com.mycompany.proyect_2026.model.Fecha;
import com.mycompany.proyect_2026.model.Paciente;
import com.mycompany.proyect_2026.model.Profesional;

/**
 *
 * @author lamas Controlador para la gestion de estudios. Concentra la logica de filtrado, creacion y consultas
 *
 */
public class EstudioController {

    private Estudio estudio;

    public EstudioController() {
        estudio = new Estudio();
    }

    public EstudioController(Estudio data) {
        this.estudio = data;
    }

    public Fecha getFecha() {
        return estudio.getFecha();
    }

    public Fecha getFechaEntrega() {
        return estudio.getFechaEntrega();
    }

    public void setEstado(String estado) {
        estudio.setEstado(estado);
    }

    public void setFecha(Fecha fecha) {
        estudio.setFecha(fecha);
    }

    public String getNombreEstudio() {
        return estudio.getNombreEstudio();
    }

    public Paciente getPaciente() {
        return estudio.getPaciente();
    }

    public void setPaciente(Paciente paciente) {
        estudio.setPaciente(paciente);
    }

    public Profesional getProfesional() {
        return estudio.getProfesional();
    }

}
