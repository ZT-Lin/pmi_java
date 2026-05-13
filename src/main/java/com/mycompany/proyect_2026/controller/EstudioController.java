/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.proyect_2026.model.Estudio;
import com.mycompany.proyect_2026.model.Fecha;
import com.mycompany.proyect_2026.model.Paciente;
import com.mycompany.proyect_2026.model.Profesional;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author lamas Controlador para la gestion de estudios. Concentra la logica de
 * filtrado, creacion y consultas
 *
 */
public class EstudioController {
    private Estudio estudio;
    
    public EstudioController(){
         estudio = new Estudio();
    }
    public EstudioController(Estudio data){
         this.estudio = data;
    }
    
    public Fecha getFecha() {
        return estudio.getFecha();
    }

    public Fecha getFechaEntrega() {
        return estudio.getFechaEntrega();
    }

    // FILTROS
    /**
     * Devuelve todos los estudios cuyo estado coincida. Si el estado es
     * "<Todos>", devuelve la lista completa.
     */
    public ArrayList<Estudio> filtrarPorEstado(String estado) {
        if (estado == null || estado.equalsIgnoreCase("<Todos>")) {
            return new ArrayList<>(listaEstudios);
        }
        ArrayList<Estudio> resultado = new ArrayList<>();
        for (Estudio e : listaEstudios) {
            if (e.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    /**
     * Devuelve todos los estudios del profesional dado (por matrícula). Si la
     * matrícula es null o vacía, devuelve la lista completa.
     */
    public ArrayList<Estudio> filtrarPorProfesional(Profesional profesional) {
        if (profesional == null) {
            return new ArrayList<>(listaEstudios);
        }
        ArrayList<Estudio> resultado = new ArrayList<>();
        for (Estudio e : listaEstudios) {
            if (e.getProfesional().getMatricula()
                    .equals(profesional.getMatricula())) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public void setEstado(String estado) {
        estudio.setEstado(estado);
    }

    public void setFecha(Fecha fecha) {
        estudio.setFecha( fecha );
    }

    public String getNombreEstudio() {
        return estudio.getNombreEstudio();
    }

    // ALTA
    /**
     * Crea un estudio nuevo, lo agrega a la lista en memoria y lo persiste en
     * archivo. Devuelve el estudio creado, o null si los parámetros son
     * inválidos.
     */
    public Estudio crearEstudio(Fecha fechaRealizacion,
            Fecha fechaEntrega,
            Paciente paciente,
            Profesional profesional,
            String[] analisis) {
        if (fechaRealizacion == null || paciente == null
                || profesional == null || analisis == null
                || analisis.length == 0) {
            return null;
        }

        String nombreEstudio = "Estudio_" + paciente.getDNI();
        Estudio nuevo = new Estudio(fechaRealizacion, nombreEstudio,
                paciente, profesional);
        nuevo.setFechaEntrega(fechaEntrega);
        nuevo.setEstado("En Elaboración");
        nuevo.setAnalisis(analisis);
        try {
            listaEstudios.add(nuevo);
            GestorArchivos.guardarEstudioEnArchivo(nuevo);
            return nuevo;
        } catch (IOException ex) {
            //Si el archivo falló, lo sacamos de la lista para que no haya inconsistencia
            listaEstudios.remove(nuevo);
            return null; // Retornamos null para que la Vista sepa que algo salió mal
        }
    }

    // MODIFICACIÓN
    /**
     * Modifica el estado de un estudio existente y persiste el cambio.
     */
    public void modificarEstado(Estudio estudio, String nuevoEstado) {
        if (estudio == null || nuevoEstado == null) {
            return;
        }
        String estadoAnterior = estudio.getEstado(); //Guardamos por si hay que volver atras
        estudio.setEstado(nuevoEstado);
        try{
        GestorArchivos.actualizarArchivoEstudios(listaEstudios);
        }catch(IOException ex){
            estudio.setEstado(estadoAnterior);
        }
    }

    /**
     * Modifica la fecha de entrega de un estudio y persiste el cambio.
     */
    public void modificarFechaEntrega(Estudio estudio, Fecha nuevaFecha) {
        if (estudio == null) {
            return;
        }
        Fecha fechaAnterior = estudio.getFechaEntrega();
        estudio.setFechaEntrega(nuevaFecha);
        try{
        GestorArchivos.actualizarArchivoEstudios(listaEstudios);
        }catch(IOException e){
            estudio.setFechaEntrega(fechaAnterior);
            System.err.println("Error al guardar fehca" + e.getMessage());
        }
    }

    public Paciente getPaciente() {
        return estudio.getPaciente();
    }

    public void setPaciente(Paciente paciente) {
        estudio.setPaciente ( paciente);
    }

    public Profesional getProfesional() {
        return estudio.getProfesional();
    }

}
