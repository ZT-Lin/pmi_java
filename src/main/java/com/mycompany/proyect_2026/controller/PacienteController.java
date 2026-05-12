package com.mycompany.proyect_2026.controller;

import com.mycompany.proyect_2026.model.Paciente;
import java.util.ArrayList;

public class PacienteController {

    private final Paciente paciente;
    private ArrayList<Paciente> listaPacientes; // ← NUEVO: Maneja la lista

    //constructor original
    public PacienteController() {
        paciente = new Paciente();
    }

    // NUEVO: Constructor que recibe la lista
    public PacienteController(ArrayList<Paciente> listaPacientes) {
        this.paciente = new Paciente();
        this.listaPacientes = listaPacientes;
    }

    // ... ACÁ DEJAMOS TODOS TUS SETTERS Y GETTERS INTACTOS ...
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
    // ... FIN GETTERS Y SETTERS ...

    // --- NUEVO REDISEÑO MVC ---
    // Alta
    public boolean registrarPaciente(String nombre, String apellido, String mail, String tel, String dni, boolean obraSocial) {
        // Validación de DNI duplicado acá
        for (Paciente p : listaPacientes) {
            if (p.getDNI().equals(dni)) {
                return false;
            }
        }
        Paciente nuevo = new Paciente();
        nuevo.setNombre(nombre);
        nuevo.setApellido(apellido);
        nuevo.setMail(mail);
        nuevo.setTel(tel);
        nuevo.setDNI(dni);
        nuevo.setObraSocial(obraSocial);
        listaPacientes.add(nuevo);
        GestorArchivos.agregarPacienteFisico(nuevo);
        return true;
    }

    // Eliminación
    public boolean eliminarPaciente(int indice) {
        if (indice < 0 || indice >= listaPacientes.size()) {
            return false;
        }
        listaPacientes.remove(indice);
        GestorArchivos.actualizarArchivoPacientes(listaPacientes);
        return true;
    }

    // Modificación
    public void actualizarPaciente(int indice, String nombre, String apellido, String mail, String tel, boolean obraSocial) {
        Paciente p = listaPacientes.get(indice);
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setMail(mail);
        p.setTel(tel);
        p.setObraSocial(obraSocial);
        GestorArchivos.actualizarArchivoPacientes(listaPacientes);
    }

    // Filtro
    public ArrayList<Paciente> filtrarPorObraSocial(boolean tieneOS) {
        ArrayList<Paciente> resultado = new ArrayList<>();
        for (Paciente p : listaPacientes) {
            if (p.getObraSocial() == tieneOS) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
