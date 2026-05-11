/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.controller;

import com.mycompany.proyect_2026.model.Estudio;
import com.mycompany.proyect_2026.model.Fecha;
import com.mycompany.proyect_2026.model.Paciente;
import com.mycompany.proyect_2026.model.Profesional;
import java.util.ArrayList;

/**
 *
 * @author lucam
 */
public class EstudioController {
    private final Estudio estudio;

    // El constructor recibe lo mínimo e indispensable para crear un Estudio
    public EstudioController(Fecha fechaRealizacion, String nombre, Paciente pac, Profesional prof) {
        this.estudio = new Estudio(fechaRealizacion, nombre, pac, prof);
    }

    // Setters para la información adicional que Tomi agregó
    public void setFechaEntrega(Fecha fecha) {
        this.estudio.setFechaEntrega(fecha);
    }

    public void setEstado(String estado) {
        this.estudio.setEstado(estado);
    }

    public void setAnalisis(String[] analisis) {
        this.estudio.setAnalisis(analisis);
    }

    // El método maestro que guarda en RAM y en el Disco
    public void registrarEnSistema(ArrayList<Estudio> listaMemoria) {
        if (listaMemoria != null) {
            listaMemoria.add(this.estudio);
        }
        GestorArchivos.agregarEstudioFisico(this.estudio);
    }
}
