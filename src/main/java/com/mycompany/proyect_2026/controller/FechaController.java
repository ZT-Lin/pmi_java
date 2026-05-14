/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.controller;

import com.mycompany.proyect_2026.model.Fecha;

/**
 *
 * @author linzh
 */
public class FechaController {

    private Fecha actual;

    public FechaController() {
        actual = new Fecha();
    }

    public FechaController(Fecha data) {
        actual = data;
    }

    public void setAnio(int data) {
        actual.setAnio(data);
    }

    public void setMes(int data) {
        actual.setMes(data);
    }

    public void setDia(int data) {
        actual.setDia(data);
    }

    public int getAnio() {
        return actual.getAnio();
    }

    public int getMes() {
        return actual.getMes();
    }

    public int getDia() {
        return actual.getDia();
    }

    public Fecha getFecha() {
        return actual;
    }

    @Override
    public String toString() {
        return actual.toString();
    }

    public static boolean esMayor(Fecha actual, Fecha data) {
        // Si los años son distintos, el resultado depende solo del año
        if (actual.getAnio() != data.getAnio()) {
            return actual.getAnio() > data.getAnio();
        }
        // Los años son iguales: comparamos meses
        if (actual.getMes() != data.getMes()) {
            return actual.getMes() > data.getMes();
        }
        // Los años Y meses son iguales: comparamos días
        return actual.getDia() > data.getDia();
    }

    public static boolean esMenor(Fecha actual, Fecha data) {
        // Si los años son distintos, el resultado depende solo del año
        if (actual.getAnio() != data.getAnio()) {
            return actual.getAnio() < data.getAnio();
        }
        // Los años son iguales: comparamos meses
        if (actual.getMes() != data.getMes()) {
            return actual.getMes() < data.getMes();
        }
        // Los años Y meses son iguales: comparamos días
        return actual.getDia() < data.getDia();
    }

    /**
     * Parsea un String con formato dd/mm/aaaa y devuelve un objeto Fecha. Lanza Exception con mensaje descriptivo si el formato o los valores son inválidos.
     */
    public static Fecha parsear(String texto) throws Exception {
        if (texto == null || texto.trim().isEmpty()) {
            throw new Exception("La fecha no puede estar vacía.");
        }

        String[] partes = texto.trim().split("/");

        if (partes.length != 3) {
            throw new Exception("Formato incorrecto. Usá dd/mm/aaaa");
        }

        try {
            int dia = Integer.parseInt(partes[0].trim());
            int mes = Integer.parseInt(partes[1].trim());
            int anio = Integer.parseInt(partes[2].trim());

            if (dia < 1 || dia > 31) {
                throw new Exception("Día inválido: debe estar entre 1 y 31.");
            }
            if (mes < 1 || mes > 12) {
                throw new Exception("Mes inválido: debe estar entre 1 y 12.");
            }
            if (anio < 2000 || anio > 2100) {
                throw new Exception("Año inválido: debe estar entre 2000 y 2100.");
            }

            return new Fecha(dia, mes, anio);

        } catch (NumberFormatException e) {
            throw new Exception("La fecha debe contener solo números. Usá dd/mm/aaaa");
        }
    }

    public static boolean esIgual(Fecha actual, Fecha data) {
        return actual.getAnio() == data.getAnio()
                && actual.getMes() == data.getMes()
                && actual.getDia() == data.getDia();
    }
}
