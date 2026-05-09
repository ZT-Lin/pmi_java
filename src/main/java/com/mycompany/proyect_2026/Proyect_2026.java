/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyect_2026;

import com.mycompany.proyect_2026.model.*;
import com.mycompany.proyect_2026.coltroller.GestorArchivos;
import com.mycompany.proyect_2026.view.*;

import java.util.ArrayList;




/**
 *
 * @author linzh
 */
public class Proyect_2026 {
    
    public static void main(String[] args){
        
        
        System.out.println("--- PRUEBA DE ESCRITURA ---");
        GestorArchivos.cargarAnalisisDesdeArchivo();
        System.out.println("Cantidad de analisis cargados: " + Analisis.TIPO_ANALYSIS.size()); //Print de prueba para analisis.txt
        
        java.util.ArrayList<Profesional> listaProfesionales = GestorArchivos.cargarProfesionalesDesdeArchivo();
        System.out.println("Cantidad de profesionales cargados: " + listaProfesionales.size());
        
        java.util.ArrayList<Paciente> listaPacientes = GestorArchivos.cargarPacientesDesdeArchivo();
        System.out.println("Cantidad de pacientes cargados: " + listaPacientes.size());
        
        Fecha fechaPrueba = new com.mycompany.proyect_2026.model.Fecha(5, 5, 2026);
        Paciente pacientePrueba = new com.mycompany.proyect_2026.model.Paciente("Luca", "Masotti", "luca@mail.com", "45111222", "2664112233");
        Profesional profPrueba = new com.mycompany.proyect_2026.model.Profesional("Gregory", "House", "house@hospital.com", "11223344", "9999");
        Estudio estudioPrueba = new com.mycompany.proyect_2026.model.Estudio(fechaPrueba, "Resonancia_Magnetica", pacientePrueba, profPrueba);
        
        GestorArchivos.guardarEstudioEnArchivo(estudioPrueba);
        System.out.println("Estudio de prueba guardado exitosamente!");
        
        java.awt.EventQueue.invokeLater(() -> {
            new MainMenu(listaPacientes).setVisible(true);
        });
    }
}
