/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.warehouse_smart.system;

/**
 *
 * @author cvdev
 */
import com.formdev.flatlaf.FlatDarculaLaf;
import java.awt.Color;
import javax.swing.UIManager;
public class Warehouse_SmartSystem {
    
   
    public static void main(String[] args) {
        try {
            // Set FlatLaf theme
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());

            // Custom table look
            UIManager.put("Table.alternateRowColor", new Color(50, 50, 50));
            UIManager.put("Table.background", new Color(40, 40, 40));
            UIManager.put("Table.foreground", new Color(220, 220, 220));
            UIManager.put("Table.selectionBackground", new Color(111, 80, 45));
            UIManager.put("Table.selectionForeground", Color.WHITE);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
         java.awt.EventQueue.invokeLater(() -> {
             FlatDarculaLaf.setup();
            new Login().setVisible(true);
        });
    }
}
