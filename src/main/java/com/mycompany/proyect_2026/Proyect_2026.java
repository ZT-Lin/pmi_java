package com.mycompany.proyect_2026;

import com.mycompany.proyect_2026.controller.GestorArchivos;
import com.mycompany.proyect_2026.model.*;
import com.mycompany.proyect_2026.view.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Proyect_2026 {

    public static void main(String[] args) {
        
        try {
            new File("data").mkdirs();  // crea el directorio si no existe
            
            // Cargamos datos de prueba si los archivos no existen
            GestorArchivos.crearDatosIniciales();
            
            GestorArchivos.cargarAnalisisDesdeArchivo();
            
            // Como lo tenías antes: creamos y cargamos en la misma línea
            ArrayList<Profesional> listaProfesionales = GestorArchivos.cargarProfesionalesDesdeArchivo();
            ArrayList<Paciente> listaPacientes = GestorArchivos.cargarPacientesDesdeArchivo();
            ArrayList<Estudio> listaEstudios = GestorArchivos.cargarEstudiosDesdeArchivo(listaPacientes, listaProfesionales);

            // Abrir con las listas cargadas (lo metemos acá adentro)
            java.awt.EventQueue.invokeLater(() -> {
                new MainMenu(listaPacientes, listaProfesionales, listaEstudios).setVisible(true);
            });

        } catch (Exception e) {
            // Ahora sí el usuario se entera si falla la lectura inicial
            JOptionPane.showMessageDialog(null, 
                "Error crítico al cargar los datos del sistema:\n" + e.getMessage(),
                "Error de inicio", JOptionPane.ERROR_MESSAGE);
            // Si hay un error tan grave, no deberíamos continuar
            System.exit(1); 
        }
    }
}