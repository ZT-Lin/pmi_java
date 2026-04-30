/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.model;
import java.util.HashMap;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author linzh
 */

public class Analisis {
    public static final HashMap<Integer,String> TIPO_ANALYSIS = new HashMap<>();
    
    public static void cargarAnalisisDesdeArchivo(){
        try{
            File a = new File("data/analisis.txt");
            Scanner lector = new Scanner(a);
            while(lector.hasNext()){
                int codigo = lector.nextInt();
                String nombre = lector.next();
                TIPO_ANALYSIS.put(codigo, nombre);
            }
            lector.close();
        }catch(IOException e){
            System.out.println("Error al cargar los analisis: " + e.getMessage());
        }
    }
}

