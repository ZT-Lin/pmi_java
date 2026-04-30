/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyect_2026;

import com.mycompany.proyect_2026.model.*;
import com.mycompany.proyect_2026.view.MainView;


/**
 *
 * @author linzh
 */
public class Proyect_2026 {
    // agreagado en 11/04/2026 - Lin
    private static final Analisis TIPO = new Analisis();
    
    public static void main(String[] args) {
        Analisis.cargarAnalisisDesdeArchivo();
        MainView.start();
        System.out.println("Cantidad de analisis cargados: " + Analisis.TIPO_ANALYSIS.size()); //Print de prueba para analisis.txt
    }

}
