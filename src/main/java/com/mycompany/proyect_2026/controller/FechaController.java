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
    
    public static boolean esIgual(Fecha actual, Fecha data) {
        return actual.getAnio() == data.getAnio()
                && actual.getMes() == data.getMes()
                && actual.getDia() == data.getDia();
    }
}
