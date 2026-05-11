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

    public static ArrayList<Estudio> cargarEstudiosDesdeArchivo(ArrayList<Paciente> listaPacientes, ArrayList<Profesional> listaProfesionales) {
        ArrayList<Estudio> lista = new ArrayList<>();
        try {
            File archivo = new File("data/estudios.txt");
            if (!archivo.exists()) return lista;

            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(";");

                int diaR = Integer.parseInt(partes[0]);
                int mesR = Integer.parseInt(partes[1]);
                int anioR = Integer.parseInt(partes[2]);
                Fecha fechaRealizacion = new Fecha(diaR, mesR, anioR);

                String nombreEstudio = partes[3];
                String dniPac = partes[4];
                String matProf = partes[5];

                int diaE = Integer.parseInt(partes[6]);
                int mesE = Integer.parseInt(partes[7]);
                int anioE = Integer.parseInt(partes[8]);
                Fecha fechaEntrega = null;
                if (diaE != 0) {
                    fechaEntrega = new Fecha(diaE, mesE, anioE);
                }

                String estado = partes[9];
                String analisisCSV = partes[10];

                Paciente pacEncontrado = null;
                for (Paciente p : listaPacientes) {
                    if (p.getDNI().equals(dniPac)) {
                        pacEncontrado = p;
                        break;
                    }
                }

                Profesional profEncontrado = null;
                for (Profesional p : listaProfesionales) {
                    if (p.getMatricula().equals(matProf)) {
                        profEncontrado = p;
                        break;
                    }
                }

                if (pacEncontrado != null && profEncontrado != null) {
                    Estudio nuevoEstudio = new Estudio(fechaRealizacion, nombreEstudio, pacEncontrado, profEncontrado);
                    nuevoEstudio.setFechaEntrega(fechaEntrega);
                    nuevoEstudio.setEstado(estado);
                    if (!analisisCSV.equals("Ninguno")) {
                        nuevoEstudio.setAnalisis(analisisCSV.split(","));
                    }
                    lista.add(nuevoEstudio);
                }
            }
            lector.close();
        } catch (Exception ex) {
            System.out.println("Error al cargar estudios: " + ex.getMessage());
        }
        return lista;
    }

    private static String formatearLineaEstudio(Estudio e) {
        String fEntrega = "0;0;0";
        if (e.getFechaEntrega() != null) {
            fEntrega = e.getFechaEntrega().getDia() + ";" + e.getFechaEntrega().getMes() + ";" + e.getFechaEntrega().getAnio();
        }
        String estado = (e.getEstado() != null) ? e.getEstado() : "Pendiente";
        String analisisCSV = "Ninguno";
        if (e.getAnalisis() != null && e.getAnalisis().length > 0) {
            analisisCSV = String.join(",", e.getAnalisis());
        }
        return e.getFecha().getDia() + ";" + e.getFecha().getMes() + ";" + e.getFecha().getAnio() + ";" +
               e.getNombreEstudio() + ";" +
               e.getPaciente().getDNI() + ";" +
               e.getProfesional().getMatricula() + ";" +
               fEntrega + ";" +
               estado + ";" +
               analisisCSV;
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
