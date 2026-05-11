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
 * @author lucam
 */
public class GestorArchivos {
    // Método auxiliar para buscar un Paciente en la memoria por su DNI
    private static Paciente buscarPaciente(ArrayList<Paciente> pacientes, String dniBuscado) {
        if (pacientes != null) {
            // Recorremos la lista usando un for-each clásico
            for (Paciente p : pacientes) {
                // Usamos .trim() y .equals() para comparar los Strings de forma segura
                if (p.getDNI().trim().equals(dniBuscado.trim())) {
                    return p; // Lo encontramos, lo devolvemos
                }
            }
        }
        return null; // Si termina el ciclo y no lo encontró, devuelve null
    }

    // Método auxiliar para buscar un Profesional en la memoria por su Matrícula
    private static Profesional buscarProfesional(ArrayList<Profesional> profesionales, String matriculaBuscada) {
        if (profesionales != null) {
            for (Profesional prof : profesionales) {
                if (prof.getMatricula().trim().equals(matriculaBuscada.trim())) {
                    return prof; // Lo encontramos, lo devolvemos
                }
            }
        }
        return null; // Si no lo encuentra, devuelve null
    }
    
    public static void cargarAnalisisDesdeArchivo() {
        try {
            File a = new File("data/analisis.txt");
            if (!a.exists()) return;
            Scanner lector = new Scanner(a);
            while (lector.hasNext()) {
                int codigo = lector.nextInt();
                String nombre = lector.next();
                Analisis.TIPO_ANALYSIS.put(codigo, nombre);
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al cargar los analisis: " + e.getMessage());
        }
    }

    public static ArrayList<Profesional> cargarProfesionalesDesdeArchivo() {
        ArrayList<Profesional> lista = new ArrayList<>();
        try {
            File p = new File("data/profesionales.txt");
            if (!p.exists()) return lista;
            Scanner lector = new Scanner(p);
            while (lector.hasNext()) {
                String nom = lector.next();
                String ape = lector.next();
                String mail = lector.next();
                String tel = lector.next();
                String mat = lector.next();
                lista.add(new Profesional(nom, ape, mail, tel, mat));
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al cargar profesionales: " + e.getMessage());
        }
        return lista;
    }

    public static ArrayList<Paciente> cargarPacientesDesdeArchivo() {
        ArrayList<Paciente> lista = new ArrayList<>();
        try {
            File p = new File("data/pacientes.txt");
            if (!p.exists()) return lista;
            Scanner lector = new Scanner(p);
            while (lector.hasNext()) {
                String nom = lector.next();
                String ape = lector.next();
                String mail = lector.next();
                String dni = lector.next();
                String tel = lector.next();
                lista.add(new Paciente(nom, ape, mail, dni, tel));
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al cargar pacientes: " + e.getMessage());
        }
        return lista;
    }

    public static ArrayList<Estudio> cargarEstudiosDesdeArchivo(ArrayList<Paciente> pacientes, ArrayList<Profesional> profesionales) {
        ArrayList<Estudio> lista = new ArrayList<>();
        File f = new File("data/estudios.txt");
    
        if (!f.exists()) return lista;

        try {
            Scanner lector = new Scanner(f);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(";");
            
                // datos[0]: Fecha Realiz, [1]: Nombre, [2]: DNI Pac, [3]: Mat Prof
                // datos[4]: Fecha Ent, [5]: Estado, [6]: Analisis

                // Parsear Fecha Realización
                String[] f1 = datos[0].split("/");
                Fecha fechaRealiz = new Fecha(Integer.parseInt(f1[0]), Integer.parseInt(f1[1]), Integer.parseInt(f1[2]));

                // Buscar Paciente y Profesional en las listas de RAM
                Paciente pac = buscarPaciente(pacientes, datos[2]);
                Profesional prof = buscarProfesional(profesionales, datos[3]);

                if (pac != null && prof != null) {
                    Estudio nuevo = new Estudio(fechaRealiz, datos[1], pac, prof);
                
                    // Cargar Fecha de Entrega si no es "null"
                    if (!datos[4].equals("null")) {
                        String[] f2 = datos[4].split("/");
                        nuevo.setFechaEntrega(new Fecha(Integer.parseInt(f2[0]), Integer.parseInt(f2[1]), Integer.parseInt(f2[2])));
                    }
                
                    nuevo.setEstado(datos[5]);
                
                    // Cargar Análisis (de String separado por coma a String[])
                    String[] analisisArray = datos[6].split(",");
                    nuevo.setAnalisis(analisisArray);

                    lista.add(nuevo);
                }
            }
            lector.close(); // Cierre manual purista
        } catch (IOException e) {
            System.out.println("Error al cargar estudios: " + e.getMessage());
        }
        return lista;
    }

    private static String formatearLineaEstudio(Estudio e) {
    // 1. Fecha de realización (siempre está)
    String fRealizacion = e.getFecha().getDia() + "/" + e.getFecha().getMes() + "/" + e.getFecha().getAnio();

    // 2. Fecha de entrega (puede ser null)
    String fEntrega = "null";
    if (e.getFechaEntrega() != null) {
        fEntrega = e.getFechaEntrega().getDia() + "/" + e.getFechaEntrega().getMes() + "/" + e.getFechaEntrega().getAnio();
    }

    // 3. Análisis (Convertir String[] a "Analisis1,Analisis2")
    String strAnalisis = "";
    String[] arrayAn = e.getAnalisis();
    if (arrayAn != null && arrayAn.length > 0) {
        for (int i = 0; i < arrayAn.length; i++) {
            strAnalisis += arrayAn[i];
            if (i < arrayAn.length - 1) strAnalisis += ","; // Sub-delimitador
        }
    } else {
        strAnalisis = "Sin_Analisis";
    }

    // 4. Unimos todo con el delimitador principal ;
    // Orden: FechaRealiz ; Nombre ; DNIPac ; MatProf ; FechaEnt ; Estado ; Analisis
    return fRealizacion + ";" + 
           e.getNombreEstudio() + ";" + 
           e.getPaciente().getDNI() + ";" + 
           e.getProfesional().getMatricula() + ";" +
           fEntrega + ";" +
           e.getEstado() + ";" +
           strAnalisis;
    }

    public static void actualizarArchivoPacientes(ArrayList<Paciente> listaPacientes) {
        try {
            FileWriter fw = new FileWriter("data/pacientes.txt");
            for (Paciente p : listaPacientes) {
                String linea = p.getNombre() + " " + p.getApellido() + " " + p.getMail() + " " + p.getDNI() + " " + p.getTel() + "\n";
                fw.write(linea);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar pacientes: " + e.getMessage());
        }
    }

    public static void actualizarArchivoProfesionales(ArrayList<Profesional> listaProfesionales) {
        try {
            FileWriter fw = new FileWriter("data/profesionales.txt");
            for (Profesional p : listaProfesionales) {
                String linea = p.getNombre() + " " + p.getApellido() + " " + p.getMail() + " " + p.getTel() + " " + p.getMatricula() + "\n";
                fw.write(linea);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar profesionales: " + e.getMessage());
        }
    }

    public static void actualizarArchivoEstudios(ArrayList<Estudio> listaEstudios) {
        try {
            FileWriter fw = new FileWriter("data/estudios.txt");
            for (Estudio e : listaEstudios) {
                fw.write(formatearLineaEstudio(e) + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error al actualizar estudios: " + ex.getMessage());
        }
    }

    public static void agregarPacienteFisico(Paciente p) {
        try {
            FileWriter fw = new FileWriter("data/pacientes.txt", true);
            String linea = p.getNombre() + " " + p.getApellido() + " " + p.getMail() + " " + p.getDNI() + " " + p.getTel() + "\n";
            fw.write(linea);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir paciente: " + e.getMessage());
        }
    }

    public static void agregarProfesionalFisico(Profesional p) {
        try {
            FileWriter fw = new FileWriter("data/profesionales.txt", true);
            String linea = p.getNombre() + " " + p.getApellido() + " " + p.getMail() + " " + p.getTel() + " " + p.getMatricula() + "\n";
            fw.write(linea);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir profesional: " + e.getMessage());
        }
    }

    public static void agregarEstudioFisico(Estudio e) {
        try {
            FileWriter fw = new FileWriter("data/estudios.txt", true);
            fw.write(formatearLineaEstudio(e) + "\n");
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error al escribir estudio: " + ex.getMessage());
        }
    }
}
