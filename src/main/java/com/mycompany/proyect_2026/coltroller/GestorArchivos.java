/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.proyect_2026.coltroller;

import com.mycompany.proyect_2026.model.Analisis;
import com.mycompany.proyect_2026.model.Paciente;
import com.mycompany.proyect_2026.model.Profesional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Usuario
 */
public class GestorArchivos {
    public static void cargarAnalisisDesdeArchivo(){
        try{
            File a = new File("data/analisis.txt");
            Scanner lector = new Scanner(a);
            while(lector.hasNext()){
                int codigo = lector.nextInt();
                String nombre = lector.next();
                Analisis.TIPO_ANALYSIS.put(codigo, nombre);
            }
            lector.close();
        }catch(IOException e){
            System.out.println("Error al cargar los analisis: " + e.getMessage());
        }
    }
    
    public static ArrayList<Profesional> cargarProfesionalesDesdeArchivo(){
        ArrayList<Profesional> lista = new ArrayList<>();
        try{
            File p = new File("data/profesionales.txt");
            Scanner lector = new Scanner(p);
            while(lector.hasNext()){
                String nombre = lector.next();
                String apellido = lector.next();
                String mail = lector.next();
                String telefono = lector.next();
                String matricula = lector.next();
                Profesional prof = new Profesional(nombre, apellido, mail, telefono, matricula);
                lista.add(prof);
            }
            lector.close();
        }catch(IOException e){
            System.out.println("Error al cargar profesionales: " + e.getMessage());
        }
        return lista;
    }
    
    public static ArrayList<Paciente> cargarPacientesDesdeArchivo(){
        ArrayList<Paciente> lista = new ArrayList<>();
        try {
            File z = new File("data/pacientes.txt");
            Scanner lector = new Scanner(z);
            while (lector.hasNext()) {
                String nombre = lector.next();
                String apellido = lector.next();
                String mail = lector.next();
                String telefono = lector.next();
                String dni = lector.next();
                Paciente paci = new Paciente(nombre, apellido, mail, dni, telefono);
                lista.add(paci);
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al cargar pacientes: " + e.getMessage());
        }
        return lista;
    }
    
    public static ArrayList<Estudio> cargarEstudiosDesdeArchivo(ArrayList<Paciente> listaPacientes, ArrayList<Porfesional> listaProfesionales){
        
    }
    
    public static void guardarPacienteEnArchivo(Paciente nuevoPaciente){
        try {
            FileWriter fw = new FileWriter("data/pacientes.txt", true);
            String linea = nuevoPaciente.getNombre() + " " +
                           nuevoPaciente.getApellido() + " " +
                           nuevoPaciente.getMail() + " " +
                           nuevoPaciente.getTel() + " " +
                           nuevoPaciente.getDNI() + "\n";
            fw.write(linea);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el paciente: " + e.getMessage());
        }
    }
    
    public static void guardarProfesionalEnArchivo(Profesional nuevoProf){
        try{
            FileWriter fw = new FileWriter("data/profesionales.txt", true);
            String linea = nuevoProf.getNombre() + " " +
                            nuevoProf.getApellido() + " " +
                            nuevoProf.getMail() + " " +
                            nuevoProf.getMatricula() + "\n";
            fw.write(linea);
            fw.close();
        }catch(IOException e){
            System.out.println("Error al guardar el profesional: " + e.getMessage());
        }
    }
}
