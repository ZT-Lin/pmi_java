/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyect_2026.view;

import com.mycompany.proyect_2026.controller.*;
import com.mycompany.proyect_2026.model.Analisis;
import com.mycompany.proyect_2026.model.Estudio;
import com.mycompany.proyect_2026.model.Fecha;
import com.mycompany.proyect_2026.model.Paciente;
import com.mycompany.proyect_2026.model.Profesional;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author linzh
 */
public class CrearEstudio extends javax.swing.JFrame {

    // Las tres listas que vienen de EstudioMenu
    private ArrayList<Paciente> listaPacientes;
    private ArrayList<Profesional> listaProfesionales;
    private ArrayList<Estudio> listaEstudios;
    private ListaEstudioController estudioController;

    // Referencia a EstudioMenu para avisarle cuando terminamos
    private EstudioMenu ventanaPadre;

    // Combos para seleccionar paciente y profesional
    // Los agregamos acá porque el form no los tiene
    private javax.swing.JComboBox<String> comboPaciente;
    private javax.swing.JComboBox<String> comboProfesional;

    // Constructor vacío — por compatibilidad con NetBeans
    public CrearEstudio() {
        this.listaPacientes = new ArrayList<>();
        this.listaProfesionales = new ArrayList<>();
        this.listaEstudios = new ArrayList<>();
        this.estudioController = new ListaEstudioController();
        this.ventanaPadre = null;
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        configurarFormulario();
        
        this.setTitle("Nuevo Estudio");
    }

    // Constructor real — recibe listas y referencia a EstudioMenu
    public CrearEstudio(ArrayList<Paciente> pacientes,
            ArrayList<Profesional> profesionales,
            ArrayList<Estudio> estudios,
            EstudioMenu padre) {
        this.listaPacientes = pacientes;
        this.listaProfesionales = profesionales;
        this.listaEstudios = estudios;
        this.estudioController = new ListaEstudioController(listaEstudios);
        this.ventanaPadre = padre;
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        configurarFormulario();
        this.setSize(420, 480);
        
        this.setTitle("Nuevo Estudio");
    }

    private void configurarFormulario() {

        // ── Hacer editables los campos de fecha ────────────
        // initComponents los pone como non-editable,
        // los sobreescribimos acá
        fRealizacion.setEditable(true);  // fecha de realización
        fRealizacion.setText("dd/mm/aaaa");

        fEntrega.setEditable(true);  // fecha de entrega
        fEntrega.setText("dd/mm/aaaa");

        // ── Agregar combo de pacientes ─────────────────────
        // Lo creamos programáticamente porque el form no lo tiene
        comboPaciente = new javax.swing.JComboBox<>();
        comboPaciente.addItem("<Seleccionar paciente>");
        for (Paciente p : listaPacientes) {
            comboPaciente.addItem(
                    p.getApellido() + ", " + p.getNombre()
                    + " - DNI: " + p.getDNI()
            );
        }

        // Cuando cambia la selección, poblar los campos de solo lectura
        comboPaciente.addActionListener(evt -> onPacienteSeleccionado());

        // Lo insertamos encima de identPaciente usando BorderLayout
        // El panel del form usa GroupLayout, así que usamos un JPanel auxiliar
        // y lo ponemos en la posición de identPaciente
        identPaciente.setVisible(false); // ocultamos el campo viejo

        // Agregamos el combo al contentPane con setBounds
        // como el form usa GroupLayout, lo más simple es agregarlo manualmente
        comboPaciente.setBounds(
                identPaciente.getX(),
                identPaciente.getY(),
                identPaciente.getWidth() + 50,
                identPaciente.getHeight()
        );
        getContentPane().add(comboPaciente);

        // ── Agregar combo de profesionales ─────────────────
        comboProfesional = new javax.swing.JComboBox<>();
        comboProfesional.addItem("<Seleccionar profesional>");
        for (Profesional pr : listaProfesionales) {
            comboProfesional.addItem(
                    pr.getApellido() + ", " + pr.getNombre()
                    + " - Mat: " + pr.getMatricula()
            );
        }
        comboProfesional.addActionListener(evt -> onProfesionalSeleccionado());

        identProfesional.setVisible(false); // ocultamos el campo viejo
        comboProfesional.setBounds(
                identProfesional.getX(),
                identProfesional.getY(),
                identProfesional.getWidth() + 50,
                identProfesional.getHeight()
        );
        getContentPane().add(comboProfesional);

        // ── Cargar checkboxes con nombres reales ───────────
        cargarCheckboxes();

    }

    private void cargarCheckboxes() {
        // Array con los 10 checkboxes en orden
        javax.swing.JCheckBox[] checks = {
            jCheckBox1, jCheckBox2, jCheckBox3, jCheckBox4, jCheckBox5,
            jCheckBox6, jCheckBox7, jCheckBox8, jCheckBox9, jCheckBox10
        };

        // Poner el nombre real de cada análisis desde el HashMap
        for (int i = 0; i < checks.length; i++) {
            int clave = i + 1;
            String nombre = Analisis.TIPO_ANALYSIS.getOrDefault(
                    clave, "Análisis " + clave
            );
            checks[i].setText(nombre);
            checks[i].setSelected(false); // desmarcados por defecto
            checks[i].setEnabled(true);   // habilitados para que el usuario elija
        }
    }

    private void onPacienteSeleccionado() {
        int idx = comboPaciente.getSelectedIndex();

        // Índice 0 es "<Seleccionar paciente>" — limpiar campos
        if (idx <= 0) {
            identPaciente.setText("");
            DNI.setText("");
            return;
        }

        // idx - 1 porque el índice 0 del combo es el placeholder
        Paciente p = listaPacientes.get(idx - 1);

        // Mostramos los datos del paciente seleccionado
        // en los campos de solo lectura como confirmación visual
        identPaciente.setText(p.getNombre() + " " + p.getApellido());
        DNI.setText(p.getDNI());
    }

    private void onProfesionalSeleccionado() {
        int idx = comboProfesional.getSelectedIndex();

        if (idx <= 0) {
            identProfesional.setText("");
            matricula.setText("");
            return;
        }

        Profesional pr = listaProfesionales.get(idx - 1);
        identProfesional.setText(pr.getNombre() + " " + pr.getApellido());
        matricula.setText(pr.getMatricula());
    }

    private Fecha parsearFecha(String texto) throws Exception {
        // Esperamos formato dd/mm/aaaa
        String[] partes = texto.split("/");

        if (partes.length != 3) {
            throw new Exception("Formato incorrecto. Usá dd/mm/aaaa");
        }

        try {
            int dia = Integer.parseInt(partes[0].trim());
            int mes = Integer.parseInt(partes[1].trim());
            int anio = Integer.parseInt(partes[2].trim());

            // Validaciones básicas
            if (dia < 1 || dia > 31) {
                throw new Exception("Día inválido");
            }
            if (mes < 1 || mes > 12) {
                throw new Exception("Mes inválido");
            }
            if (anio < 2000 || anio > 2100) {
                throw new Exception("Año inválido");
            }

            return new Fecha(dia, mes, anio);

        } catch (NumberFormatException e) {
            throw new Exception("La fecha debe contener solo números. Usá dd/mm/aaaa");
        }
    }

    private String[] obtenerAnalisisSeleccionados() {
        javax.swing.JCheckBox[] checks = {
            jCheckBox1, jCheckBox2, jCheckBox3, jCheckBox4, jCheckBox5,
            jCheckBox6, jCheckBox7, jCheckBox8, jCheckBox9, jCheckBox10
        };

        // Primero contamos cuántos están marcados
        int cantidad = 0;
        for (javax.swing.JCheckBox cb : checks) {
            if (cb.isSelected()) {
                cantidad++;
            }
        }

        // Creamos el array con el tamaño exacto
        String[] seleccionados = new String[cantidad];
        int idx = 0;
        for (javax.swing.JCheckBox cb : checks) {
            if (cb.isSelected()) {
                seleccionados[idx++] = cb.getText();
            }
        }

        return seleccionados;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        identPaciente = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        DNI = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCheckBox9 = new javax.swing.JCheckBox();
        matricula = new javax.swing.JTextField();
        fEntrega = new javax.swing.JTextField();
        identProfesional = new javax.swing.JTextField();
        jCheckBox10 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        fRealizacion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        confirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jCheckBox1.setText("analisis 1");

        identPaciente.setEditable(false);

        jCheckBox2.setText("jCheckBox1");

        DNI.setEditable(false);

        jLabel1.setText("nombre y apellido: ");

        jCheckBox6.setText("analisis 1");

        jCheckBox3.setText("jCheckBox1");

        jCheckBox4.setText("analisis 1");

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel4.setText("Analisis");

        jCheckBox5.setText("jCheckBox1");

        jCheckBox7.setText("analisis 1");

        jCheckBox8.setText("jCheckBox1");

        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel3.setText("Paciente");

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel5.setText("Profesional");

        jLabel10.setText("fecha de entrega");

        jCheckBox9.setText("jCheckBox1");

        matricula.setEditable(false);

        fEntrega.setEditable(false);

        identProfesional.setEditable(false);

        jCheckBox10.setText("jCheckBox1");

        jLabel7.setText("nombre y apellido: ");

        fRealizacion.setEditable(false);
        fRealizacion.setToolTipText("");

        jLabel11.setText("fecha de realización");

        jLabel8.setText("Matricula:");

        jLabel2.setText("D.N.I. :");

        confirmar.setText("confirmar");
        confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(fEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox6)
                                    .addComponent(jCheckBox5)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jCheckBox2))
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox4)
                                    .addComponent(jCheckBox3)
                                    .addComponent(jCheckBox7)
                                    .addComponent(jCheckBox8)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox10)
                                .addGap(112, 112, 112)
                                .addComponent(jCheckBox9))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(matricula)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(identProfesional, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 30, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(DNI, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(fRealizacion, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(identPaciente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(confirmar, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(identPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(fRealizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox8))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(identProfesional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(matricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(fEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarActionPerformed
  
        // ── Validar selección de paciente ──────────────────
        int idxPaciente = comboPaciente.getSelectedIndex();
        if (idxPaciente <= 0) {
            JOptionPane.showMessageDialog(this, "Seleccioná un paciente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ── Validar selección de profesional ───────────────
        int idxProfesional = comboProfesional.getSelectedIndex();
        if (idxProfesional <= 0) {
            JOptionPane.showMessageDialog(this, "Seleccioná un profesional.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ── Validar y parsear fecha de realización ─────────
        Fecha fechaRealizacion;
        try {
            fechaRealizacion = parsearFecha(fRealizacion.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fecha de realización inválida: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ── Validar y parsear fecha de entrega ─────────────
        // La fecha de entrega es opcional — si el usuario dejó
        // el placeholder o la dejó vacía, se guarda como null
        Fecha fechaEntrega = null;
        String textoEntrega = fEntrega.getText().trim();
        if (!textoEntrega.isEmpty() && !textoEntrega.equals("dd/mm/aaaa")) {
            try {
                fechaEntrega = parsearFecha(textoEntrega);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Fecha de entrega inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        // -- Comparacion entre fechas --------
        
        if(FechaController.esMenor(fechaEntrega , fechaRealizacion)){
            JOptionPane.showMessageDialog(this,"la fecha de entrega no puede ser menor que fecha de realizacion","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ── Validar que haya al menos un análisis ──────────
        String[] analisisSeleccionados = obtenerAnalisisSeleccionados();
        if (analisisSeleccionados.length == 0) {
            JOptionPane.showMessageDialog(this, "Seleccioná al menos un análisis.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ── Obtener paciente y profesional seleccionados ───
        // idx - 1 porque el índice 0 de los combos es el placeholder
        Paciente paciente = listaPacientes.get(idxPaciente - 1);
        Profesional profesional = listaProfesionales.get(idxProfesional - 1);
        Estudio nuevoEstudio = estudioController.crearEstudio(
                fechaRealizacion,
                fechaEntrega,
                paciente,
                profesional,
                analisisSeleccionados
        );

        if (nuevoEstudio == null) {
            JOptionPane.showMessageDialog(this,
                    "Error al crear el estudio. Verificá los datos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // ── Avisar a EstudioMenu que refresque ─────────────
        if (ventanaPadre != null) {
            ventanaPadre.refrescarVista();
        }
        JOptionPane.showMessageDialog(this, "Estudio creado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }//GEN-LAST:event_confirmarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrearEstudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearEstudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearEstudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearEstudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearEstudio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DNI;
    private javax.swing.JButton confirmar;
    private javax.swing.JTextField fEntrega;
    private javax.swing.JTextField fRealizacion;
    private javax.swing.JTextField identPaciente;
    private javax.swing.JTextField identProfesional;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField matricula;
    // End of variables declaration//GEN-END:variables
}
