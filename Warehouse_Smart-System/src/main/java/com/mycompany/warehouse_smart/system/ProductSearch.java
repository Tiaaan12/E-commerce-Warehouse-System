/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.warehouse_smart.system;

import javax.swing.ImageIcon;

/**
 *
 * @author cvdev
 */
// Product class
public class ProductSearch {
    private String code;
    private String name;
    private String status;
    private String location;
    private String description;
    private ImageIcon image;

    public ProductSearch(String code, String name, String status, String location, String description, ImageIcon image) {
        this.code = code;
        this.name = name;
        this.status = status;
        this.location = location;
        this.description = description;
        this.image = image;
    }

   
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getStatus() { return status; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public ImageIcon getImage() { return image; }
}
