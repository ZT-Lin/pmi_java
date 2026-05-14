package com.mycompany.proyect_2026.controller;

import com.mycompany.proyect_2026.model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorArchivos {

    // --- MÉTODOS DE ESCRITURA (Usando try-catch-finally) ---
    public static void guardarEstudioEnArchivo(Estudio e) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter("data/estudios.txt", true); // true para modo append
            fw.write(formatearEstudio(e) + "\n");
        } catch (IOException ex) {
            throw ex; // Lanzamos el error a la Vista
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    /* log error */ }
            }
        }
    }

    public static void actualizarArchivoPacientes(ArrayList<Paciente> lista) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter("data/pacientes.txt");
            for (Paciente p : lista) {
                fw.write(p.getNombre() + "|" + p.getApellido() + "|"
                        + p.getMail() + "|" + p.getDNI() + "|"
                        + p.getTel() + "|" + p.getObraSocial() + "\n");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    public static void actualizarArchivoProfesionales(ArrayList<Profesional> lista) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter("data/profesionales.txt");
            for (Profesional p : lista) {
                fw.write(p.getNombre() + "|" + p.getApellido() + "|"
                        + p.getMail() + "|" + p.getTel() + "|"
                        + p.getMatricula() + "\n");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    public static void actualizarArchivoEstudios(ArrayList<Estudio> lista) throws IOException {
        FileWriter fw = null;
        try {
            fw = new FileWriter("data/estudios.txt");
            for (Estudio e : lista) {
                fw.write(formatearEstudio(e) + "\n");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    // --- MÉTODOS DE LECTURA (Con jerarquía de errores específica) ---
    public static ArrayList<Estudio> cargarEstudiosDesdeArchivo(
            ArrayList<Paciente> listaPacientes,
            ArrayList<Profesional> listaProfesionales) throws IOException {

        ArrayList<Estudio> listaEstudios = new ArrayList<>();
        File f = new File("data/estudios.txt");
        if (!f.exists()) {
            return listaEstudios;
        }

        Scanner lector = null;
        try {
            lector = new Scanner(f);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }
                String[] partes = linea.split("\\|");

                // JERARQUÍA: parseInt puede lanzar NumberFormatException
                int dia = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]);
                int anio = Integer.parseInt(partes[2]);

                String dniBusqueda = partes[4];
                String matriculaBusqueda = partes[5];

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

                if (paciente != null && profesional != null) {
                    Estudio est = new Estudio(new Fecha(dia, mes, anio), partes[3], paciente, profesional);
                    est.setEstado(partes[6]);

                    // --- Cargar fecha de entrega ---
                    // partes[7] es "dd/mm/aaaa" o la palabra "null"
                    if (partes.length > 7 && !partes[7].equals("null") && !partes[7].isEmpty()) {
                        String[] partesEntrega = partes[7].split("/");
                        if (partesEntrega.length == 3) {
                            try {
                                int diaE = Integer.parseInt(partesEntrega[0]);
                                int mesE = Integer.parseInt(partesEntrega[1]);
                                int anioE = Integer.parseInt(partesEntrega[2]);
                                est.setFechaEntrega(new Fecha(diaE, mesE, anioE));
                            } catch (NumberFormatException ex) {
                                // Fecha de entrega corrupta: dejamos null, no es crítico
                                est.setFechaEntrega(null);
                            }
                        }
                    }

                    // --- Cargar análisis ---
                    // partes[8] es "Hemograma_Completo,Glucemia" o vacío
                    if (partes.length > 8 && !partes[8].isEmpty()) {
                        String[] analisis = partes[8].split(",");
                        est.setAnalisis(analisis);
                    } else {
                        est.setAnalisis(new String[0]);
                    }

                    listaEstudios.add(est);
                }
            }
        } catch (NumberFormatException e) {
            throw new IOException("Error de formato: El archivo de estudios tiene datos dañados.", e);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            if (lector != null) {
                lector.close();
            }
        }
        return listaEstudios;
    }

    private static String formatearEstudio(Estudio e) {
        String fechaEntrega = (e.getFechaEntrega() != null)
                ? e.getFechaEntrega().getDia() + "/" + e.getFechaEntrega().getMes() + "/" + e.getFechaEntrega().getAnio()
                : "null";
        String analisis = (e.getAnalisis() != null) ? String.join(",", e.getAnalisis()) : "";
        return e.getFecha().getDia() + "|" + e.getFecha().getMes() + "|" + e.getFecha().getAnio() + "|"
                + e.getNombreEstudio() + "|" + e.getPaciente().getDNI() + "|"
                + e.getProfesional().getMatricula() + "|" + e.getEstado() + "|"
                + fechaEntrega + "|" + analisis;
    }

    public static void agregarPacienteFisico(Paciente p) throws IOException {
    FileWriter fw = null;
    try {
        fw = new FileWriter("data/pacientes.txt", true);
        String linea = p.getNombre() + "|" + p.getApellido() + "|"
                + p.getMail() + "|" + p.getDNI() + "|"
                + p.getTel() + "|" + p.getObraSocial() + "\n";
        fw.write(linea);
    } finally {
        if (fw != null) {
            try { fw.close(); } catch (IOException ex) { /* ignorar */ }
        }
    }
}

    public static void agregarProfesionalFisico(Profesional p) throws IOException {
    FileWriter fw = null;
    try {
        fw = new FileWriter("data/profesionales.txt", true);
        String linea = p.getNombre() + "|" + p.getApellido() + "|"
                + p.getMail() + "|" + p.getTel() + "|"
                + p.getMatricula() + "\n";
        fw.write(linea);
    } finally {
        if (fw != null) {
            try { fw.close(); } catch (IOException ex) { /* ignorar */ }
        }
    }
}

    public static void cargarAnalisisDesdeArchivo() throws IOException {

        File a = new File("data/analisis.txt");

        if (!a.exists()) {

            return;

        }

        Scanner lector = new Scanner(a);

        while (lector.hasNext()) {

            int codigo = lector.nextInt();

            String nombre = lector.next();

            Analisis.TIPO_ANALYSIS.put(codigo, nombre);

        }

        lector.close();

    }

    public static void crearDatosIniciales() throws IOException {

        File fp = new File("data/pacientes.txt");

        if (!fp.exists()) {

            // Se asume que si no hay pacientes, tampoco hay lo demás, así que creamos todo
            // 3 Pacientes
            Paciente p1 = new Paciente("Juan", "García", "juan@mail.com", "48150524", "1122334455");

            p1.setObraSocial(true);

            Paciente p2 = new Paciente("Ana", "López", "ana@mail.com", "49172341", "1133445566");

            p2.setObraSocial(false);

            Paciente p3 = new Paciente("Carlos", "Martínez", "carlos@mail.com", "20476524", "1144556677");

            p3.setObraSocial(true);

            agregarPacienteFisico(p1);

            agregarPacienteFisico(p2);

            agregarPacienteFisico(p3);

            // 3 Profesionales
            Profesional pr1 = new Profesional("Laura", "Gómez", "laura@mail.com", "2664201976", "1020");

            Profesional pr2 = new Profesional("Diego", "Silva", "diego@mail.com", "2664185432", "1021");

            Profesional pr3 = new Profesional("Elena", "Ruiz", "elena@mail.com", "2664183257", "1022");

            agregarProfesionalFisico(pr1);

            agregarProfesionalFisico(pr2);

            agregarProfesionalFisico(pr3);

            // 3 Estudios (Básicos)
            Estudio e1 = new Estudio(new Fecha(1, 10, 2026), "Radiografía", p1, pr1);

            Estudio e2 = new Estudio(new Fecha(5, 10, 2026), "Ecografía", p2, pr2);

            Estudio e3 = new Estudio(new Fecha(10, 10, 2026), "Resonancia", p3, pr3);

            guardarEstudioEnArchivo(e1);

            guardarEstudioEnArchivo(e2);

            guardarEstudioEnArchivo(e3);

        }

    }

    public static ArrayList<Profesional> cargarProfesionalesDesdeArchivo() throws IOException {

        ArrayList<Profesional> lista = new ArrayList<>();

        File p = new File("data/profesionales.txt");

        if (!p.exists()) {

            return lista;

        }

        Scanner lector = new Scanner(p);

        while (lector.hasNextLine()) {

            String linea = lector.nextLine().trim();

            if (linea.isEmpty()) {

                continue;

            }

            // Usar split con "|"
            String[] partes = linea.split("\\|");

            if (partes.length < 5) {

                continue;

            }

            String nombre = partes[0];

            String apellido = partes[1];

            String mail = partes[2];

            String tel = partes[3];

            String mat = partes[4];

            lista.add(new Profesional(nombre, apellido, mail, tel, mat));

        }

        lector.close();

        return lista;

    }

    public static ArrayList<Paciente> cargarPacientesDesdeArchivo() throws IOException {

        ArrayList<Paciente> lista = new ArrayList<>();

        File p = new File("data/pacientes.txt");

        if (!p.exists()) {

            return lista;

        }

        Scanner lector = new Scanner(p);

        while (lector.hasNextLine()) {

            String linea = lector.nextLine().trim();

            if (linea.isEmpty()) {

                continue;

            }

            //  Usar split con "|"
            String[] partes = linea.split("\\|");

            if (partes.length < 6) {

                continue;

            }

            String nombre = partes[0];

            String apellido = partes[1];

            String mail = partes[2];

            String dni = partes[3];

            String telefono = partes[4];

            boolean obraSocial = Boolean.parseBoolean(partes[5]);

            Paciente paci = new Paciente(nombre, apellido, mail, dni, telefono);

            paci.setObraSocial(obraSocial);

            lista.add(paci);

        }

        lector.close();

        return lista;

    }
}
