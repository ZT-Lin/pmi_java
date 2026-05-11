/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.controller;
import com.mycompany.proyect_2026.controller.GestorArchivos;
import com.mycompany.proyect_2026.model.Profesional;
import java.util.ArrayList;

/**
 *
 * @author lucam
 */

public class ProfesionalController {
    private final Profesional profesional;

    public ProfesionalController() {
        this.profesional = new Profesional();
    }

    public void setNombre(String d) { profesional.setNombre(d); }
    public void setApellido(String d) { profesional.setApellido(d); }
    public void setMail(String d) { profesional.setMail(d); }
    public void setTelefono(String d) { profesional.setTel(d); }
    public void setMatricula(String d) { profesional.setMatricula(d); }
    public String getNombre() { return profesional.getNombre(); }
    public String getApellido() { return profesional.getApellido(); }
    public String getMail() { return profesional.getMail(); }
    public String getTelefono() { return profesional.getTel(); }
    public String getMatricula() { return profesional.getMatricula(); }

    public void registrarEnSistema(ArrayList<Profesional> listaMemoria) {
        if (listaMemoria != null) {
            listaMemoria.add(this.profesional);
        }
        GestorArchivos.agregarProfesionalFisico(this.profesional);
    }
}
