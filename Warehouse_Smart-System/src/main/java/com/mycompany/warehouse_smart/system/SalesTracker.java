/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.warehouse_smart.system;

/**
 *
 * @author cvdev
 */
import java.util.HashMap;
import java.util.Map;

public class SalesTracker {
 
    private Map<String, Map<String, Integer>> salesData;
     private HashMap<String, ProductSearch> productMap;
     
    private int INITIAL_STOCK = 1015;

    public SalesTracker() {
        salesData = new HashMap<>();
        productMap = new HashMap<>();
        
        
        addLocation("Manila");
        addLocation("Taguig");
        addLocation("Pasay");

        addProduct("AquaThirst Colorwave Flip");
        addProduct("AquaThirst Dream 532ml");
        addProduct("Cylinder Collections 24oz");
        addProduct("Slate Cup Collection 16oz");
        addProduct("Tumbler Collection (30oz)");

        
        recordSale("Manila", "AquaThirst Colorwave Flip", 120);
        recordSale("Taguig", "AquaThirst Colorwave Flip", 100);
        recordSale("Pasay", "AquaThirst Colorwave Flip", 140);

        recordSale("Manila", "AquaThirst Dream 532ml", 90);
        recordSale("Taguig", "AquaThirst Dream 532ml", 110);
        recordSale("Pasay", "AquaThirst Dream 532ml", 130);

        recordSale("Manila", "Cylinder Collections 24oz", 150);
        recordSale("Taguig", "Cylinder Collections 24oz", 80);
        recordSale("Pasay", "Cylinder Collections 24oz", 95);
        
        
        recordSale("Manila", "Slate Cup Collection 16oz", 150);
        recordSale("Taguig", "Slate Cup Collection 16oz", 80);
        recordSale("Pasay", "Slate Cup Collection 16oz", 95);
        
        recordSale("Manila", "Tumbler Collection (30oz)", 150);
        recordSale("Taguig", "Tumbler Collection (30oz)", 80);
        recordSale("Pasay", "Tumbler Collection (30oz)", 95);
        
        
        
    }

    private void addLocation(String location) {
        salesData.putIfAbsent(location, new HashMap<>());
    }

    private void addProduct(String product) {
        for (String location : salesData.keySet()) {
            salesData.get(location).putIfAbsent(product, 0);
        }
    }
    

    public void recordSale(String location, String product, int quantity) {
        addLocation(location);
        addProduct(product);
        int current = salesData.get(location).get(product);
        salesData.get(location).put(product, current + quantity);
    }

    public Map<String, Map<String, Integer>> getSalesData() {
        return salesData;
    }

public String getTopLocation() {
    String bestLocation = null;
    int maxLocSales = 0;
    for (String location : salesData.keySet()) {
        int total = salesData.get(location).values().stream().mapToInt(Integer::intValue).sum();
        if (total > maxLocSales) {
            maxLocSales = total;
            bestLocation = location;
        }
    }
    return bestLocation;
}

public String getTopProduct() {
    Map<String, Integer> productTotals = new HashMap<>();
    for (Map<String, Integer> productMap : salesData.values()) {
        for (Map.Entry<String, Integer> e : productMap.entrySet()) {
            productTotals.put(e.getKey(), productTotals.getOrDefault(e.getKey(), 0) + e.getValue());
        }
    }
    String bestProduct = null;
    int maxProdSales = 0;
    for (Map.Entry<String, Integer> e : productTotals.entrySet()) {
        if (e.getValue() > maxProdSales) {
            maxProdSales = e.getValue();
            bestProduct = e.getKey();
        }
    }
    return bestProduct;
}

public int getRemainingProducts() {
   
    int totalSold = getTotalSales();
    return INITIAL_STOCK - totalSold;
}


private int getTotalAddedProducts() {
    return salesData.values().stream()
        .flatMap(m -> m.values().stream())
        .mapToInt(Integer::intValue)
        .sum();
}
public int getTotalSales() {
    return salesData.values().stream()
        .flatMap(m -> m.values().stream())
        .mapToInt(Integer::intValue)
        .sum();
}

}

