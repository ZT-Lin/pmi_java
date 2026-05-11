package com.mycompany.proyect_2026.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

import com.mycompany.proyect_2026.model.Analisis;
import com.mycompany.proyect_2026.model.Paciente;
import com.mycompany.proyect_2026.model.Profesional;
import com.mycompany.proyect_2026.model.Estudio;
import com.mycompany.proyect_2026.model.Fecha;
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
    
    public static ArrayList<Estudio> cargarEstudiosDesdeArchivo(ArrayList<Paciente> listaPacientes, ArrayList<Profesional> listaProfesionales){
        ArrayList<Estudio> listaEstudios = new ArrayList<>();
        try{
            File e = new File("data/estudios.txt");
            Scanner lector = new Scanner(e);
            while(lector.hasNext()){
                int dia = lector.nextInt();
                int mes = lector.nextInt();
                int anio = lector.nextInt();
                String nombreEstudio = lector.next();
                String dniBusqueda = lector.next();
                String matriculaBusqueda = lector.next();
                Paciente pacienteEncontrado = null;
                for(Paciente p : listaPacientes){
                    if(p.getDNI().equals(dniBusqueda)){
                        pacienteEncontrado = p;
                        break;
                    }
                }
                Profesional profesionalEncontrado = null;
                for(Profesional prof : listaProfesionales){
                    if(prof.getMatricula().equals(matriculaBusqueda)){
                        profesionalEncontrado = prof;
                        break;
                    }
                }
                if(pacienteEncontrado != null && profesionalEncontrado != null){
                    Fecha fecha = new Fecha(dia, mes, anio);
                    Estudio nuevoEstudio = new Estudio(fecha, nombreEstudio, pacienteEncontrado, profesionalEncontrado);
                    listaEstudios.add(nuevoEstudio);
                }else{
                    System.out.println("Advertencia: No se pudo cargar el estudio '" + nombreEstudio + "' porque falta el paciente o el profesional en los registros.");
                }
            }
            lector.close();
        }catch(IOException ex){
            System.out.println("Error al cargar los estudios: " + ex.getMessage());
        }
        return listaEstudios;
    }
    
    public static void guardarEstudioEnArchivo(Estudio nuevoEst){
        try{
            FileWriter fw = new FileWriter("data/estudios.txt", true);
            int dia = nuevoEst.getFecha().getDia();
            int mes = nuevoEst.getFecha().getMes();
            int anio = nuevoEst.getFecha().getAnio();
            String dniPaciente = nuevoEst.getPaciente().getDNI();
            String matriculaProf = nuevoEst.getProfesional().getMatricula();
            String linea = dia + " " + mes + " " + anio + " " +
                            nuevoEst.getNombreEstudio() + " " +
                            dniPaciente + " " +
                            matriculaProf + "\n";
            fw.write(linea);
            fw.close();
        }catch(IOException e){
            System.out.println("Error al guardar el estudio: " + e.getMessage());
        }
    }
    
    public static void actualizarArchivoPacientes(ArrayList<Paciente> listaPacientes){
        try{
            FileWriter fw = new FileWriter("data/pacientes.txt");
            for(Paciente p : listaPacientes){
                String linea = p.getNombre() + " " +
                                p.getApellido() + " " +
                                p.getMail() + " " +
                                p.getDNI() + " " +
                                p.getTel() + "\n";
                                
                fw.write(linea);
            }
            fw.close();
        }catch(IOException e){
            System.out.println("Error al actualizar el archivo de pacientes: " + e.getMessage());
        }
    }
    
    public static void actualizarArchivoProfesionales(ArrayList<Profesional> listaProfesionales){
        try{
            FileWriter fw = new FileWriter("data/profesionales.txt");
            for(Profesional p : listaProfesionales){
                String linea = p.getNombre() + " " +
                                p.getApellido() + " " +
                                p.getMail() + " " +
                                p.getTel() + " " +
                                p.getMatricula()+ "\n";
                fw.write(linea);
            }
            fw.close();
        }catch(IOException e){
            System.out.println("Error al actualizar el archivo de profesionales: " + e.getMessage());
        }
    }
    
    public static void actualizarArchivoEstudios(ArrayList<Estudio> listaEstudios){
        try{
            FileWriter fw = new FileWriter("data/estudios.txt");
            for(Estudio e : listaEstudios){
                int dia = e.getFecha().getDia();
                int mes = e.getFecha().getMes();
                int anio = e.getFecha().getAnio();
                String dniPaciente = e.getPaciente().getDNI();
                String matriculaProf = e.getProfesional().getMatricula();
                String linea = dia + " " + mes + " " + anio + " " +
                               e.getNombreEstudio() + " " +
                               dniPaciente + " " +
                               matriculaProf + "\n";
                fw.write(linea);
            }
            fw.close();
        }catch(IOException ex){
            System.out.println("Error al actualizar el archivo de estudios: " + ex.getMessage());
        }
    }
    
    public static void agregarPacienteFisico(Paciente p){
        try{
            FileWriter fw = new FileWriter("data/pacientes.txt", true);
            String linea = p.getNombre() + " " +
                            p.getApellido() + " " +
                            p.getMail() + " " +
                            p.getDNI() + " " +
                            p.getTel() + "\n";
            fw.write(linea);
            fw.close();
        }catch(IOException e){
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    
    public static void agregarProfesionalFisico(Profesional p){
        try{
            FileWriter fw = new FileWriter("data/profesionales.txt", true);
            String linea = p.getNombre() + " " +
                            p.getApellido() + " " + 
                            p.getMail() + " " + 
                            p.getTel() + " " + 
                            p.getMatricula() + "\n";
            fw.write(linea);
            fw.close();
        }catch(IOException e){
            System.out.println("Error al escribir profesional: " + e.getMessage());
        }
    }
}
