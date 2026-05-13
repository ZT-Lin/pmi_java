package com.mycompany.proyect_2026.controller;

import com.mycompany.proyect_2026.model.Profesional;
import java.io.IOException;
import java.util.ArrayList;

public class ProfesionalController {

    private final Profesional profesional;
    private ArrayList<Profesional> listaProfesionales;

    public ProfesionalController() {
        this.profesional = new Profesional();
    }
    public ProfesionalController(Profesional data) {
        this.profesional = data;
    }

    // NUEVO: Constructor con lista
    public ProfesionalController(ArrayList<Profesional> listaProfesionales) {
        this.profesional = new Profesional();
        this.listaProfesionales = listaProfesionales;
    }

    // ... TUS GETTERS Y SETTERS INTACTOS ...
    public void setNombre(String d) {
        profesional.setNombre(d);
    }

    public void setApellido(String d) {
        profesional.setApellido(d);
    }

    public void setMail(String d) {
        profesional.setMail(d);
    }

    public void setTelefono(String d) {
        profesional.setTel(d);
    }

    public void setMatricula(String d) {
        profesional.setMatricula(d);
    }

    public String getNombre() {
        return profesional.getNombre();
    }

    public String getApellido() {
        return profesional.getApellido();
    }

    public String getMail() {
        return profesional.getMail();
    }

    public String getTelefono() {
        return profesional.getTel();
    }

    public String getMatricula() {
        return profesional.getMatricula();
    }
    // ... FIN GETTERS Y SETTERS ...

    // --- NUEVO REDISEÑO MVC ---
    public boolean registrarProfesional(String nombre, String apellido, String mail, String tel, String matricula) {
        for (Profesional pr : listaProfesionales) {
            if (pr.getMatricula().equals(matricula)) {
                return false; // Matrícula repetida
            }
        }
        Profesional nuevo = new Profesional(nombre, apellido, mail, tel, matricula);
        try {
            listaProfesionales.add(nuevo);
            GestorArchivos.agregarProfesionalFisico(nuevo);
            return true;
        } catch (IOException e) {
            listaProfesionales.remove(nuevo);
            System.out.println("No se pudo guardar el profesional" + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProfesional(int indice) {
        if (indice < 0 || indice >= listaProfesionales.size()) {
            return false;
        }
        Profesional eliminado = listaProfesionales.get(indice); //guardamos por si tenemos que revertir
        try {
            listaProfesionales.remove(indice);
            GestorArchivos.actualizarArchivoProfesionales(listaProfesionales);
            return true;
        } catch (IOException e) {
            //Lo volvemos a meter en la lista en la misma posición
            listaProfesionales.add(indice, eliminado);
            System.err.println("No se pudo eliminar el profesional: " + e.getMessage());
            return false;
        }
    }

    public void actualizarProfesional(int indice, String nombre, String apellido, String mail, String tel) {
        Profesional pr = listaProfesionales.get(indice);

        // Guardamos los datos viejos para hacer rollback si falla
        String oldNombre = pr.getNombre();
        String oldApellido = pr.getApellido();
        String oldMail = pr.getMail();
        String oldTel = pr.getTel();

        // Actualizamos en memoria
        pr.setNombre(nombre);
        pr.setApellido(apellido);
        pr.setMail(mail);
        pr.setTel(tel);

        try {
            GestorArchivos.actualizarArchivoProfesionales(listaProfesionales);
        } catch (IOException e) {
            // ROLLBACK: Volvemos a los datos viejos si falló el guardado
            pr.setNombre(oldNombre);
            pr.setApellido(oldApellido);
            pr.setMail(oldMail);
            pr.setTel(oldTel);
            System.err.println("No se pudieron actualizar los datos: " + e.getMessage());
        }
    }
}
