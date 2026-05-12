package com.mycompany.proyect_2026.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.mycompany.proyect_2026.model.Estudio;
import com.mycompany.proyect_2026.model.Fecha;
import com.mycompany.proyect_2026.model.Paciente;
import com.mycompany.proyect_2026.model.Profesional;
import java.util.ArrayList;

/**
 *
 * @author lamas Controlador para la gestion de estudios. Concentra la logica de filtrado, creacion y consultas
 *
 */
public class EstudioController {

    private ArrayList<Estudio> listaEstudios;

    public EstudioController() {
        this.listaEstudios = new ArrayList<>();
    }

    public EstudioController(ArrayList<Estudio> listaEstudios) {
        this.listaEstudios = listaEstudios;
    }

    // FILTROS
    /**
     * Devuelve todos los estudios cuyo estado coincida. Si el estado es "<Todos>", devuelve la lista completa.
     */
    public ArrayList<Estudio> filtrarPorEstado(String estado) {
        if (estado == null || estado.equalsIgnoreCase("<Todos>")) {
            return new ArrayList<>(listaEstudios);
        }
        ArrayList<Estudio> resultado = new ArrayList<>();
        for (Estudio e : listaEstudios) {
            if (e.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    /**
     * Devuelve todos los estudios del profesional dado (por matrícula). Si la matrícula es null o vacía, devuelve la lista completa.
     */
    public ArrayList<Estudio> filtrarPorProfesional(Profesional profesional) {
        if (profesional == null) {
            return new ArrayList<>(listaEstudios);
        }
        ArrayList<Estudio> resultado = new ArrayList<>();
        for (Estudio e : listaEstudios) {
            if (e.getProfesional().getMatricula()
                    .equals(profesional.getMatricula())) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    /**
     * Devuelve todos los estudios realizados en la fecha indicada.
     */
    public ArrayList<Estudio> filtrarPorFecha(int dia, int mes, int anio) {
        ArrayList<Estudio> resultado = new ArrayList<>();
        for (Estudio e : listaEstudios) {
            if (e.getFecha().getDia() == dia
                    && e.getFecha().getMes() == mes
                    && e.getFecha().getAnio() == anio) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // CONSULTAS
    /**
     * Cuenta cuántos estudios tiene un paciente dado (por DNI).
     */
    public int contarEstudiosDePaciente(Paciente paciente) {
        if (paciente == null) {
            return 0;
        }
        int contador = 0;
        for (Estudio e : listaEstudios) {
            if (e.getPaciente().getDNI().equals(paciente.getDNI())) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Devuelve todos los estudios de un paciente dado (por DNI).
     */
    public ArrayList<Estudio> obtenerEstudiosDePaciente(Paciente paciente) {
        ArrayList<Estudio> resultado = new ArrayList<>();
        if (paciente == null) {
            return resultado;
        }
        for (Estudio e : listaEstudios) {
            if (e.getPaciente().getDNI().equals(paciente.getDNI())) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // ALTA
    /**
     * Crea un estudio nuevo, lo agrega a la lista en memoria y lo persiste en archivo. Devuelve el estudio creado, o null si los parámetros son inválidos.
     */
    public Estudio crearEstudio(Fecha fechaRealizacion,
            Fecha fechaEntrega,
            Paciente paciente,
            Profesional profesional,
            String[] analisis) {
        if (fechaRealizacion == null || paciente == null
                || profesional == null || analisis == null
                || analisis.length == 0) {
            return null;
        }

        String nombreEstudio = "Estudio_" + paciente.getDNI();
        Estudio nuevo = new Estudio(fechaRealizacion, nombreEstudio,
                paciente, profesional);
        nuevo.setFechaEntrega(fechaEntrega);
        nuevo.setEstado("En Elaboración");
        nuevo.setAnalisis(analisis);

        listaEstudios.add(nuevo);
        GestorArchivos.guardarEstudioEnArchivo(nuevo);

        return nuevo;
    }

    // MODIFICACIÓN
    /**
     * Modifica el estado de un estudio existente y persiste el cambio.
     */
    public void modificarEstado(Estudio estudio, String nuevoEstado) {
        if (estudio == null || nuevoEstado == null) {
            return;
        }
        estudio.setEstado(nuevoEstado);
        GestorArchivos.actualizarArchivoEstudios(listaEstudios);
    }

    /**
     * Modifica la fecha de entrega de un estudio y persiste el cambio.
     */
    public void modificarFechaEntrega(Estudio estudio, Fecha nuevaFecha) {
        if (estudio == null) {
            return;
        }
        estudio.setFechaEntrega(nuevaFecha);
        GestorArchivos.actualizarArchivoEstudios(listaEstudios);
    }

    // GETTER
    public ArrayList<Estudio> getListaEstudios() {
        return listaEstudios;
    }

    public boolean pacienteTieneEstudios(Paciente paciente) {
        if (paciente == null) {
            return false;
        }
        for (Estudio e : listaEstudios) {
            if (e.getPaciente().getDNI().equals(paciente.getDNI())) {
                return true;
            }
        }
        return false;
    }

    public boolean profesionalTieneEstudios(Profesional profesional) {
        if (profesional == null) {
            return false;
        }
        for (Estudio e : listaEstudios) {
            if (e.getProfesional().getMatricula().equals(profesional.getMatricula())) {
                return true;
            }
        }
        return false;
    }


}
