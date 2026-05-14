/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyect_2026.view;

import com.mycompany.proyect_2026.controller.FechaController;
import com.mycompany.proyect_2026.controller.ListaEstudioController;
import com.mycompany.proyect_2026.model.Estudio;
import com.mycompany.proyect_2026.model.Fecha;
import com.mycompany.proyect_2026.model.Paciente;
import com.mycompany.proyect_2026.model.Profesional;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author linzh
 */
public class EstudioMenu extends javax.swing.JFrame {

    private ArrayList<Profesional> listaProfesionales;
    private ArrayList<Paciente> listaPacientes;
    private ArrayList<Estudio> listaEstudios;
    private ArrayList<Estudio> listaFiltrada; // lista que refleja lo que se ve en pantalla
    private ListaEstudioController estudioController;

    /**
     * Creates new form EstudioMenu
     */
    public EstudioMenu() {
        this.listaPacientes = new ArrayList<>();
        this.listaProfesionales = new ArrayList<>();
        this.listaEstudios = new ArrayList<>();
        this.estudioController = new ListaEstudioController();
        initComponents();
        estadoSelector.setModel(new DefaultComboBoxModel<>(
                new String[]{"<Todos>", "En Elaboración", "Finalizado", "Retirado", "Enviado"}
        ));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cargarLista();
        cargarCombosProfesional();
        configurarFiltros();
        this.setTitle("Sistema de Estudios");
    }

    public EstudioMenu(ArrayList<Paciente> pacientes,
            ArrayList<Profesional> profesionales,
            ArrayList<Estudio> estudios) {
        this.listaPacientes = pacientes;
        this.listaProfesionales = profesionales;
        this.listaEstudios = estudios;
        this.estudioController = new ListaEstudioController(listaEstudios);
        initComponents();
        estadoSelector.setModel(new DefaultComboBoxModel<>(
                new String[]{"<Todos>", "En Elaboración", "Finalizado", "Retirado", "Enviado"}
        ));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cargarLista();
        cargarCombosProfesional();
        configurarFiltros();
        this.setSize(640, 530);
        this.setTitle("Sistema de Estudios");

    }

    private void cargarLista() {
        listaFiltrada = new ArrayList<>(listaEstudios);
        actualizarJList(listaFiltrada);
    }

    private void cargarCombosProfesional() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

        // Primera opción: sin filtro, muestra todos
        modelo.addElement("<Todos>");

        for (Profesional pr : listaProfesionales) {
            modelo.addElement(
                    pr.getApellido() + ", " + pr.getNombre()
                    + " - Mat: " + pr.getMatricula()
            );
        }
        profesionalSelector.setModel(modelo);
    }

    // CrearEstudio llama este método cuando termina de agregar
    public void refrescarVista() {
        cargarLista();
    }

    private void filtrarPorEstado() {
        limpiarSeleccion();
        String estadoSeleccionado = estadoSelector.getSelectedItem().toString();

        if (estadoSeleccionado.equalsIgnoreCase("<Todos>")) {
            cargarLista();
            return;
        }

        // Delegar lógica al controller
        listaFiltrada = estudioController.filtrarPorEstado(estadoSeleccionado);
        actualizarJList(listaFiltrada);

        if (listaFiltrada.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay estudios con estado: " + estadoSeleccionado,
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void filtrarPorProfesional() {
        limpiarSeleccion();
        int idx = profesionalSelector.getSelectedIndex();

        if (idx <= 0) {
            cargarLista();
            return;
        }

        Profesional prSeleccionado = listaProfesionales.get(idx - 1);

        // Delegar lógica al controller
        listaFiltrada = estudioController.filtrarPorProfesional(prSeleccionado);
        actualizarJList(listaFiltrada);

        if (listaFiltrada.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay estudios para ese profesional.",
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarJList(ArrayList<Estudio> lista) {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Estudio e : lista) {
            modelo.addElement(
                    e.getNombreEstudio()
                    + " | " + e.getPaciente().getApellido()
                    + ", " + e.getPaciente().getNombre()
                    + " | " + e.getEstado()
            );
        }
        estudioList.setModel(modelo);
    }

    private void filtrarPorFecha() {
        limpiarSeleccion();
        try {
            int dia = Integer.parseInt(diaTField.getText().trim());
            int mes = Integer.parseInt(mesTField.getText().trim());
            int anio = Integer.parseInt(anioTField.getText().trim());

            if ((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || anio < 1999) {
                JOptionPane.showMessageDialog(this,
                        "La fecha " + dia + "/" + mes + "/" + anio + " es inválida",
                        "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Delegar lógica al controller
            listaFiltrada = estudioController.filtrarPorFecha(dia, mes, anio);
            actualizarJList(listaFiltrada);

            if (listaFiltrada.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No hay estudios para la fecha " + dia + "/" + mes + "/" + anio,
                        "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Ingresá una fecha válida (solo números).",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarFiltros() {
        // Al cambiar el estado en el combo, filtra automáticamente
        estadoSelector.addActionListener(evt -> filtrarPorEstado());

        // Al cambiar el profesional en el combo, filtra automáticamente
        profesionalSelector.addActionListener(evt -> filtrarPorProfesional());

        // Al presionar Enter en el campo año, busca por fecha
        // El usuario completa día, mes, año y presiona Enter
        anioTField.addActionListener(evt -> filtrarPorFecha());
        mesTField.addActionListener(evt -> filtrarPorFecha());
        diaTField.addActionListener(evt -> filtrarPorFecha());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        estudioList = new javax.swing.JList<>();
        estadoSelector = new javax.swing.JComboBox<>();
        estadoLabel = new javax.swing.JLabel();
        profesionalLabel = new javax.swing.JLabel();
        profesionalSelector = new javax.swing.JComboBox<>();
        fechaLabel = new javax.swing.JLabel();
        diaTField = new javax.swing.JTextField();
        fechaLabel3 = new javax.swing.JLabel();
        mesTField = new javax.swing.JTextField();
        fechaLabel4 = new javax.swing.JLabel();
        anioTField = new javax.swing.JTextField();
        areaParaMostrar = new javax.swing.JPanel();
        panelVacio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        agregar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(614, 497));

        estudioList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        estudioList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                estudioListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(estudioList);

        estadoSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Todos>", "En Elaboracion", "Finalizado", "Retirado", "Enviado" }));

        estadoLabel.setText("Estado:");

        profesionalLabel.setText("Profesional");

        profesionalSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        fechaLabel.setText("dia");

        diaTField.setText("01");

        fechaLabel3.setText("mes");

        mesTField.setText("01");

        fechaLabel4.setText("año");

        anioTField.setText("2004");
        anioTField.setPreferredSize(new java.awt.Dimension(70, 23));

        areaParaMostrar.setPreferredSize(new java.awt.Dimension(295, 428));
        areaParaMostrar.setLayout(new java.awt.CardLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/estetoscopio.jpg"))); // NOI18N

        javax.swing.GroupLayout panelVacioLayout = new javax.swing.GroupLayout(panelVacio);
        panelVacio.setLayout(panelVacioLayout);
        panelVacioLayout.setHorizontalGroup(
            panelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVacioLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelVacioLayout.setVerticalGroup(
            panelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVacioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        areaParaMostrar.add(panelVacio, "VACIO");

        panelDatos.setLayout(new java.awt.BorderLayout());
        areaParaMostrar.add(panelDatos, "DATOS");

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });

        volver.setText("←");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(profesionalLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(volver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(estadoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(5, 5, 5)
                                .addComponent(fechaLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(estadoSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profesionalSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(diaTField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaLabel3)
                                .addGap(1, 1, 1)
                                .addComponent(mesTField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaLabel4)
                                .addGap(0, 0, 0)
                                .addComponent(anioTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 156, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(areaParaMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaLabel)
                            .addComponent(diaTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaLabel3)
                            .addComponent(mesTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaLabel4)
                            .addComponent(anioTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(volver))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(estadoSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(estadoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(profesionalSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(profesionalLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(modificar)
                            .addComponent(agregar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(areaParaMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void estudioListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_estudioListValueChanged
        if (evt.getValueIsAdjusting()) {
            return; // Evita doble disparo
        }
        int idx = estudioList.getSelectedIndex();
        if (idx < 0) {
            return; // Si el índice es -1, no hacemos nada (el limpiador ya se encargó)
        }

        // Instanciamos la carta y le decimos al panel que la muestre
        Estudio seleccionado = listaFiltrada.get(idx);
        mostrarDatosEnPanel(new CartaEstudio(seleccionado));
    }//GEN-LAST:event_estudioListValueChanged

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        // Pasamos las listas actualizadas a MainMenu
        new MainMenu(listaPacientes, listaProfesionales, listaEstudios).setVisible(true);
        dispose();
    }//GEN-LAST:event_volverActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        int idx = estudioList.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccioná un estudio primero.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Estudio seleccionado = listaFiltrada.get(idx);

        // --- 1. Diálogo para cambiar estado ---
        String[] opciones = {"En Elaboracion", "Finalizado", "Retirado", "Enviado"};
        String nuevoEstado = (String) JOptionPane.showInputDialog(
                this,
                "Seleccioná el nuevo estado:",
                "Modificar Estado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                seleccionado.getEstado()
        );

        if (nuevoEstado != null) {
            estudioController.modificarEstado(seleccionado, nuevoEstado);
        }

        // --- 2. BUCLE DE VALIDACIÓN DE FECHA ---
        boolean fechaValida = false;

        while (!fechaValida) {
            // Preparamos el valor inicial por si ya tenía fecha
            Object valorInicial = seleccionado.getFechaEntrega() != null ? seleccionado.getFechaEntrega().toString() : "";

            // Usamos esta versión de showInputDialog para poder cambiar el título "Input"
            String nuevaFechaStr = (String) JOptionPane.showInputDialog(
                    this,
                    "Nueva fecha de entrega (dd/mm/aaaa) — dejá vacío para no cambiar:",
                    "Modificar Fecha de Entrega", // <-- ACÁ LE CAMBIAMOS EL TÍTULO
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    valorInicial
            );

            // A. Si toca "Cancel" o la "X", nuevaFechaStr es null. Salimos del bucle.
            if (nuevaFechaStr == null) {
                break;
            }

            String textoLimpio = nuevaFechaStr.trim();

            // B. Si lo deja vacío y le da OK, no quiere cambiar la fecha. Salimos.
            if (textoLimpio.isEmpty()) {
                break;
            }

            // C. Intentamos parsear y validar la lógica
            try {
                Fecha nueva = FechaController.parsear(textoLimpio);

                // VALIDACIÓN LÓGICA: Que no sea menor a la de realización
                if (FechaController.esMenor(nueva, seleccionado.getFecha())) {
                    JOptionPane.showMessageDialog(this,
                            "La fecha de entrega no puede ser anterior a la de realización (" + seleccionado.getFecha().toString() + ").",
                            "Error de Lógica", JOptionPane.ERROR_MESSAGE);
                    continue; // El 'continue' hace que el bucle while vuelva a empezar a preguntar
                }

                // Si llegamos a esta línea, la fecha está perfecta
                estudioController.modificarFechaEntrega(seleccionado, nueva);
                fechaValida = true; // Al poner true, el bucle se rompe y avanza

            } catch (Exception e) {
                // Si el usuario puso letras o "0", cae acá
                JOptionPane.showMessageDialog(this,
                        "Fecha de entrega inválida: " + e.getMessage() + "\nUsá el formato dd/mm/aaaa",
                        "Error de formato", JOptionPane.ERROR_MESSAGE);
                // Como fechaValida sigue siendo false, el bucle vuelve a pedir la fecha
            }
        }
        // ---------------------------------------

        refrescarVista();
        JOptionPane.showMessageDialog(this, "Estudio actualizado.", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_modificarActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        // Pasamos las listas y referencia a esta ventana
        // para que CrearEstudio pueda avisarnos cuando termina
        new CrearEstudio(listaPacientes, listaProfesionales, listaEstudios, this)
                .setVisible(true);
    }//GEN-LAST:event_agregarActionPerformed

    // 1. Método para limpiar y mostrar la carta vacía
    private void limpiarSeleccion() {
        // Deselecciona el estudio de la lista
        estudioList.clearSelection();

        // Limpia el panel de datos
        panelDatos.removeAll();
        panelDatos.revalidate();
        panelDatos.repaint();

        // Cambia el CardLayout a la vista VACIO
        java.awt.CardLayout cl = (java.awt.CardLayout) areaParaMostrar.getLayout();
        cl.show(areaParaMostrar, "VACIO");
    }

    // 2. Método para incrustar la CartaEstudio y mostrarla
    private void mostrarDatosEnPanel(javax.swing.JComponent carta) {
        panelDatos.removeAll();
        panelDatos.add(carta, java.awt.BorderLayout.CENTER);
        panelDatos.revalidate();
        panelDatos.repaint();

        // Cambia el CardLayout a la vista DATOS
        java.awt.CardLayout cl = (java.awt.CardLayout) areaParaMostrar.getLayout();
        cl.show(areaParaMostrar, "DATOS");
    }

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
            java.util.logging.Logger.getLogger(EstudioMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstudioMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstudioMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstudioMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstudioMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JTextField anioTField;
    private javax.swing.JPanel areaParaMostrar;
    private javax.swing.JTextField diaTField;
    private javax.swing.JLabel estadoLabel;
    private javax.swing.JComboBox<String> estadoSelector;
    private javax.swing.JList<String> estudioList;
    private javax.swing.JLabel fechaLabel;
    private javax.swing.JLabel fechaLabel3;
    private javax.swing.JLabel fechaLabel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mesTField;
    private javax.swing.JButton modificar;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelVacio;
    private javax.swing.JLabel profesionalLabel;
    private javax.swing.JComboBox<String> profesionalSelector;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
