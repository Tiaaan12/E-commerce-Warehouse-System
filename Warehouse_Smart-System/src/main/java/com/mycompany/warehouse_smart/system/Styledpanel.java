/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.warehouse_smart.system;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
/**
 *
 * @author cvdev
 */
public class Styledpanel extends JPanel {

    public Styledpanel() {
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

      
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

   
        g2.setColor(new Color(0, 0, 0, 50)); 
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);

  
        g2.setColor(new Color(33, 33, 33)); 
        g2.fillRoundRect(0, 0, getWidth() - 10, getHeight() - 10, 20, 20);

        g2.dispose();
    }
}