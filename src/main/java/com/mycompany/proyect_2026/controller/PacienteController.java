package com.mycompany.proyect_2026.controller;

import com.mycompany.proyect_2026.model.Paciente;
import java.io.IOException;
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
    public PacienteController(ArrayList<Paciente> listaPacientes, Paciente paciente) {
        this.paciente = paciente;
        this.listaPacientes = listaPacientes;
    }
    public PacienteController(Paciente paciente) {
        this.paciente = paciente;
        this.listaPacientes = new ArrayList();
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

    public boolean getObraSocial() {
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
        for (Paciente p : listaPacientes) {
            if (p.getDNI().equals(dni)) {
                return false;
            }
        }

        Paciente nuevo = new Paciente(nombre, apellido, mail, dni, tel);
        nuevo.setObraSocial(obraSocial);

        try {
            listaPacientes.add(nuevo);
            GestorArchivos.agregarPacienteFisico(nuevo);
            return true;
        } catch (IOException ex) {
            listaPacientes.remove(nuevo); // Quitamos de memoria si falló el disco
            return false;
        }
    }

    // Eliminación
    public boolean eliminarPaciente(int indice) {
        if (indice < 0 || indice >= listaPacientes.size()) {
            return false;
        }
        //Guardamos el paciente por si hay que restaurarlo
        Paciente borrado = listaPacientes.get(indice);
        try {
            listaPacientes.remove(indice);
            GestorArchivos.actualizarArchivoPacientes(listaPacientes);
            return true;
        } catch (IOException ex) {
            //Si falló el disco, lo volvemos a poner donde estaba
            listaPacientes.add(indice, borrado);
            System.err.println("Error al eliminar: " + ex.getMessage());
            return false;
        }
    }

    // Modificación
    public boolean actualizarPaciente(int indice, String nombre, String apellido, String mail, String tel, boolean obraSocial) {
        if (indice < 0 || indice >= listaPacientes.size()) {
            return false;
        }

        Paciente p = listaPacientes.get(indice);

        // Guardamos los datos viejos por si el archivo falla
        String oldNom = p.getNombre();
        String oldApe = p.getApellido();
        String oldMail = p.getMail();
        String oldTel = p.getTel();
        boolean oldOS = p.getObraSocial();

        // Aplicamos los cambios en memoria
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setMail(mail);
        p.setTel(tel);
        p.setObraSocial(obraSocial);

        try {
            GestorArchivos.actualizarArchivoPacientes(listaPacientes);
            return true;
        } catch (IOException ex) {
            //Restauramos los datos originales
            p.setNombre(oldNom);
            p.setApellido(oldApe);
            p.setMail(oldMail);
            p.setTel(oldTel);
            p.setObraSocial(oldOS);
            System.err.println("Error al actualizar: " + ex.getMessage());
            return false;
        }
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
    
    public Paciente getPaciente(int index){
        return listaPacientes.get(index);
    }
}
