/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.controller;

import com.mycompany.proyect_2026.model.Paciente;
import java.util.ArrayList;

/**
 *
 * @author linzh
 */
public class PacienteController {

    private final Paciente paciente;

    //constructor
    public PacienteController() {
        paciente = new Paciente();
    }

    public PacienteController(Paciente paciente) {
        this.paciente = paciente;
    }

    //setter
    public void setNombre(String data) {
        paciente.setNombre(data);
    }

    public void setApellido(String data) {
        paciente.setApellido(data);
    }

    public void setDNI(String data) {
        paciente.setDNI(data);
    }

    public void setMail(String data) {
        paciente.setMail(data);
    }

    public void setTelefono(String data) {
        paciente.setTel(data);
    }

    public void setObSocial(boolean data) {
        paciente.setObraSocial(data);
    }

    //getter
    public String getNombre() {
        return paciente.getNombre();
    }

    public String getApellido() {
        return paciente.getApellido();
    }

    public String getDNI() {
        return paciente.getDNI();
    }

    public String getMail() {
        return paciente.getMail();
    }

    public String getTel() {
        return paciente.getTel();
    }

    public boolean getOB() {
        return paciente.getObraSocial();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getString() {
        return paciente.toString();
    }

    public void registrarEnSistema(ArrayList<Paciente> listaMemoria) {
        if (listaMemoria != null) {
            listaMemoria.add(this.paciente);
        }
        GestorArchivos.agregarPacienteFisico(this.paciente);
    }
    // --- MÉTODOS PARA MVC (Eliminar y Actualizar) ---
    public void eliminarPaciente(ArrayList<Paciente> lista, int indice) {
        lista.remove(indice);
        GestorArchivos.actualizarArchivoPacientes(lista);
    }

    public void actualizarPaciente(ArrayList<Paciente> lista, int indice, 
            String nombre, String apellido, String mail, String tel, boolean obraSocial) {
        Paciente p = lista.get(indice);
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setMail(mail);
        p.setTel(tel);
        p.setObraSocial(obraSocial);
        GestorArchivos.actualizarArchivoPacientes(lista);
    }
    // --- FILTROS ---
    public ArrayList<Paciente> filtrarPorObraSocial(ArrayList<Paciente> todos, boolean tieneOS) {
        ArrayList<Paciente> resultado = new ArrayList<>();
        for (Paciente p : todos) {
            if (p.getObraSocial() == tieneOS) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
