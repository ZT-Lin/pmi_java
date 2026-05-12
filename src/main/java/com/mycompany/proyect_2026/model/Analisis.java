/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.model;

import java.util.HashMap;

/**
 *
 * @author linzh
 */
public class Analisis {

    public static final HashMap<Integer, String> TIPO_ANALYSIS = new HashMap<>();

    // Se carga una sola vez cuando la clase se inicializa
    static {
        TIPO_ANALYSIS.put(1, "Hemograma_Completo");
        TIPO_ANALYSIS.put(2, "Glucemia");
        TIPO_ANALYSIS.put(3, "Colesterol_Total");
        TIPO_ANALYSIS.put(4, "Trigliceridos");
        TIPO_ANALYSIS.put(5, "Orina_Completa");
        TIPO_ANALYSIS.put(6, "Hepatograma");
        TIPO_ANALYSIS.put(7, "Urea");
        TIPO_ANALYSIS.put(8, "Creatinina");
        TIPO_ANALYSIS.put(9, "Acido_Urico");
        TIPO_ANALYSIS.put(10, "TSH_Tiroideo");
    }
}
