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
    
    public FechaController(){
        actual = new Fecha();
    }
    public FechaController(Fecha data){
        actual = data;
    }
    
    public void setAnio(int data){
        actual.setAnio(data);
    }
    public void setMes(int data){
        actual.setMes(data);
    }
    public void setDia(int data){
        actual.setDia(data);
    }
    public int getAnio(){
        return actual.getAnio();
    }
    public int getMes(){
        return actual.getMes();
    }
    public int getDia(){
        return actual.getDia();
    }
    
    public boolean esMayor(Fecha data){
        if(actual.getAnio() > data.getAnio()){
            return true;
        }else if(actual.getMes() > data.getMes()){
            return true;
        }else if(actual.getDia() > data.getDia()){
            return true;
        }
        return false;
    }
    public boolean esMenor(Fecha data){
        if(actual.getAnio() < data.getAnio()){
            return true;
        }else if(actual.getMes() < data.getMes()){
            return true;
        }else if(actual.getDia() < data.getDia()){
            return true;
        }
        return false;
    }
    public boolean esIgual(Fecha data){
        return (actual.getAnio() == data.getAnio()) 
                && (actual.getMes() == data.getMes())
                && (actual.getDia() == data.getDia());
    }
}
