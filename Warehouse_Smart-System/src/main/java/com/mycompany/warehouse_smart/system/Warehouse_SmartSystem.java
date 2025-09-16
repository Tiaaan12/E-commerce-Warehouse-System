/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.warehouse_smart.system;

/**
 *
 * @author cvdev
 */
import com.formdev.flatlaf.FlatDarculaLaf;
public class Warehouse_SmartSystem {
   
    public static void main(String[] args) {
         java.awt.EventQueue.invokeLater(() -> {
             FlatDarculaLaf.setup();
            new Login().setVisible(true);
        });
    }
}
