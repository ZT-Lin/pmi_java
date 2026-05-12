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
                String nombre = lector.next();
                String apellido = lector.next();
                String mail = lector.next();
                String tel = lector.next();
                String mat = lector.next();
                lista.add(new Profesional(nombre, apellido, mail, tel, mat));
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
                String nombre = lector.next();
                String apellido = lector.next();
                String mail = lector.next();
                String dni = lector.next();
                String telefono = lector.next();
                boolean obraSocial = Boolean.parseBoolean(lector.next());
                Paciente paci = new Paciente(nombre, apellido, mail, dni, telefono);
                paci.setObraSocial(obraSocial);
                lista.add(paci);
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al cargar pacientes: " + e.getMessage());
        }
        return lista;
    }

    public static ArrayList<Estudio> cargarEstudiosDesdeArchivo(
            ArrayList<Paciente> listaPacientes,
            ArrayList<Profesional> listaProfesionales) {

        ArrayList<Estudio> listaEstudios = new ArrayList<>();
        try {
            File f = new File("data/estudios.txt");
            Scanner lector = new Scanner(f);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split("\\|");
                // partes: [0]dia [1]mes [2]anio [3]nombre [4]dni [5]matricula
                //         [6]estado [7]fechaEntrega [8]analisis (puede faltar)

                int dia = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]);
                int anio = Integer.parseInt(partes[2]);
                String nombreEstudio = partes[3];
                String dniBusqueda = partes[4];
                String matriculaBusqueda = partes[5];
                String estado = partes[6];
                String fechaEntregaStr = partes[7];

                // Buscar paciente y profesional
                Paciente paciente = null;
                for (Paciente p : listaPacientes) {
                    if (p.getDNI().equals(dniBusqueda)) {
                        paciente = p;
                        break;
                    }
                }

                Profesional profesional = null;
                for (Profesional pr : listaProfesionales) {
                    if (pr.getMatricula().equals(matriculaBusqueda)) {
                        profesional = pr;
                        break;
                    }
                }

                if (paciente == null || profesional == null) {
                    System.out.println("Advertencia: no se pudo cargar '" + nombreEstudio + "'");
                    continue;
                }

                Estudio est = new Estudio(new Fecha(dia, mes, anio),
                        nombreEstudio, paciente, profesional);
                est.setEstado(estado);

                // Fecha de entrega
                if (!fechaEntregaStr.equals("null")) {
                    String[] fe = fechaEntregaStr.split("/");
                    est.setFechaEntrega(new Fecha(
                            Integer.parseInt(fe[0]),
                            Integer.parseInt(fe[1]),
                            Integer.parseInt(fe[2])
                    ));
                }

                // Análisis
                if (partes.length > 8 && !partes[8].isEmpty()) {
                    est.setAnalisis(partes[8].split(","));
                }

                listaEstudios.add(est);
            }
            lector.close();
        } catch (IOException ex) {
            System.out.println("Error al cargar estudios: " + ex.getMessage());
        }
        return listaEstudios;
    }

    public static void guardarEstudioEnArchivo(Estudio e) {
        try {
            FileWriter fw = new FileWriter("data/estudios.txt", true);
            fw.write(formatearEstudio(e) + "\n");
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error al guardar estudio: " + ex.getMessage());
        }
    }

    public static void actualizarArchivoPacientes(ArrayList<Paciente> listaPacientes) {
        try {
            FileWriter fw = new FileWriter("data/pacientes.txt");
            for (Paciente p : listaPacientes) {
                String linea = p.getNombre() + " "
                        + p.getApellido() + " "
                        + p.getMail() + " "
                        + p.getDNI() + " "
                        + p.getTel() + " " 
                        + p.getObraSocial() + "\n";

                fw.write(linea);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo de pacientes: " + e.getMessage());
        }
    }

    public static void actualizarArchivoProfesionales(ArrayList<Profesional> listaProfesionales) {
        try {
            FileWriter fw = new FileWriter("data/profesionales.txt");
            for (Profesional p : listaProfesionales) {
                String linea = p.getNombre() + " "
                        + p.getApellido() + " "
                        + p.getMail() + " "
                        + p.getTel() + " "
                        + p.getMatricula() + "\n";
                fw.write(linea);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo de profesionales: " + e.getMessage());
        }
    }

    public static void actualizarArchivoEstudios(ArrayList<Estudio> lista) {
        try {
            FileWriter fw = new FileWriter("data/estudios.txt");
            for (Estudio e : lista) {
                fw.write(formatearEstudio(e) + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println("Error al actualizar estudios: " + ex.getMessage());
        }
    }

    public static void agregarPacienteFisico(Paciente p) {
        try {
            FileWriter fw = new FileWriter("data/pacientes.txt", true);
            String linea = p.getNombre() + " "
                    + p.getApellido() + " "
                    + p.getMail() + " "
                    + p.getDNI() + " "
                    + p.getTel() + " " 
                    + p.getObraSocial() + "\n";
            fw.write(linea);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static void agregarProfesionalFisico(Profesional p) {
        try {
            FileWriter fw = new FileWriter("data/profesionales.txt", true);
            String linea = p.getNombre() + " "
                    + p.getApellido() + " "
                    + p.getMail() + " "
                    + p.getTel() + " "
                    + p.getMatricula() + "\n";
            fw.write(linea);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir profesional: " + e.getMessage());
        }
    }

    // Método auxiliar privado:
    private static String formatearEstudio(Estudio e) {
        // Fecha entrega: guardamos "null" si no tiene
        String fechaEntrega = (e.getFechaEntrega() != null)
                ? e.getFechaEntrega().getDia() + "/" + e.getFechaEntrega().getMes()
                + "/" + e.getFechaEntrega().getAnio()
                : "null";

        // Análisis: unimos con coma
        String analisis = "";
        if (e.getAnalisis() != null && e.getAnalisis().length > 0) {
            analisis = String.join(",", e.getAnalisis());
        }

        return e.getFecha().getDia() + "|"
                + e.getFecha().getMes() + "|"
                + e.getFecha().getAnio() + "|"
                + e.getNombreEstudio() + "|"
                + e.getPaciente().getDNI() + "|"
                + e.getProfesional().getMatricula() + "|"
                + e.getEstado() + "|"
                + fechaEntrega + "|"
                + analisis;
    }
}
