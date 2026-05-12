/*  11/04/2026
    debe agregar "package ..." para incluir la clase en el app, sino sale error
 */ //--- Lin
package com.mycompany.proyect_2026.model;

public class Fecha {

    private int dia;
    private int mes;
    private int anio;

    public Fecha() {
        this.dia = 1;
        this.mes = 1;
        this.anio = 2000;
    }

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }
}
