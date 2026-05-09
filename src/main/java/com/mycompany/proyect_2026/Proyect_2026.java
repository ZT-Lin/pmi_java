/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyect_2026;

import com.mycompany.proyect_2026.model.Analisis;
import com.mycompany.proyect_2026.model.Fecha;
import com.mycompany.proyect_2026.model.Paciente;
import com.mycompany.proyect_2026.model.Profesional;
import com.mycompany.proyect_2026.model.Estudio;
import com.mycompany.proyect_2026.coltroller.GestorArchivos;
import com.mycompany.proyect_2026.view.*;
import java.util.ArrayList;



/**
 *
 * @author linzh
 */
public class Proyect_2026 {
    
public static void main(String[] args) {
    // Carga de datos
    GestorArchivos.cargarAnalisisDesdeArchivo();
    
    // Cargamo Lista de Profesionales
    ArrayList<Profesional> listaProfesionales = //Saltamos para mas legibilidad
    GestorArchivos.cargarProfesionalesDesdeArchivo();
    
    //Cargamos Lista de Pacientes
    ArrayList<Paciente> listaPacientes = //Saltamos para mas legibilidad
    GestorArchivos.cargarPacientesDesdeArchivo();
    
    //Cargamos Estudios
    ArrayList<Estudio> listaEstudios = //Saltamos para mas legibilidad
    GestorArchivos.cargarEstudiosDesdeArchivo(listaPacientes, listaProfesionales);

    // Abrir con las listas cargadas
    java.awt.EventQueue.invokeLater(() -> {
        new MainMenu(listaPacientes, listaProfesionales, listaEstudios).setVisible(true);
    });
}
}
