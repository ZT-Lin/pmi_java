/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyect_2026.view;

import javax.swing.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author linzh
 */
public class MainView {

    public static void start() {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        //crear la ventana principal / 创建初始窗口
        JFrame mainframe = new JFrame("Laboratorio de Análisis Clínicos JAVA SRL");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //agreagar un mensaje de prueba / 新增测试信息
        JLabel temporary = new JLabel("Temporary test message");
        mainframe.add(temporary);
        
        //modificar tamaño y pisicion / 位置与大小
        mainframe.setLocationRelativeTo(null);
        mainframe.setSize(400, 200);
        
        //mostrar / 展示
        mainframe.setVisible(true);
    }
}
