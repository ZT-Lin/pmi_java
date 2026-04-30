/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyect_2026;

import com.mycompany.proyect_2026.model.Analisis;
import com.mycompany.proyect_2026.view.*;




/**
 *
 * @author linzh
 */
public class Proyect_2026 {
    
    public static void main(String[] args) {
        Analisis.cargarAnalisisDesdeArchivo();
        System.out.println("Cantidad de analisis cargados: " + Analisis.TIPO_ANALYSIS.size()); //Print de prueba para analisis.txt
        
        
        java.awt.EventQueue.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}
