/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.coltroller;

import com.mycompany.proyect_2026.model.Paciente;

/**
 *
 * @author linzh
 */
public class PacienteController {
    private final Paciente paciente;

    //constructor
    public PacienteController(){
        paciente = new Paciente();
    }
    public PacienteController(Paciente paciente){
        this.paciente = paciente;
    }
    
    //setter
    public void setNombre(String data){
        paciente.setNombre(data);
    }
    public void setApellido(String data){
        paciente.setApellido(data);
    }
    
    public void setDNI(String data){
        paciente.setDNI(data);
    }
    public void setMail(String data){
        paciente.setMail(data);
    }
    public void setTelefono(String data){
        paciente.setTel(data);
    }
    public void setObSocial(boolean data){
        paciente.setObraSocial(data);
    }
    //getter
    public String getNombre(){
        return paciente.getNombre();
    }
    public String hgetApellido(){
        return paciente.getApellido();
    }
    
    public String getDNI(){
        return paciente.getDNI();
    }
    public String getMail(){
        return paciente.getMail();
    }
    public String getTelefono(){
        return paciente.getTel();
    }
    public boolean getObSocial(){
        return paciente.getObraSocial();
    }
    
    public Paciente getPaciente(){
        return paciente;
    }
    
    public String getString(){
        return paciente.toString();
    }
}
