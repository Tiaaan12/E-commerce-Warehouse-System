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
    private int INITIAL_STOCK = 1015;

    public SalesTracker() {
        salesData = new HashMap<>();

        addLocation("Manila");
        addLocation("Taguig");
        addLocation("Pasay");

        addProduct("Aquaflask A");
        addProduct("Aquaflask B");
        addProduct("Aquaflask C");

        // Default initial totals
        recordSale("Manila", "Aquaflask A", 120);
        recordSale("Taguig", "Aquaflask A", 100);
        recordSale("Pasay", "Aquaflask A", 140);

        recordSale("Manila", "Aquaflask B", 90);
        recordSale("Taguig", "Aquaflask B", 110);
        recordSale("Pasay", "Aquaflask B", 130);

        recordSale("Manila", "Aquaflask C", 150);
        recordSale("Taguig", "Aquaflask C", 80);
        recordSale("Pasay", "Aquaflask C", 95);
        
        
         recordSale("Manila", "Aquaflask D", 150);
        recordSale("Taguig", "Aquaflask D", 80);
        recordSale("Pasay", "Aquaflask D", 95);
        
        
        
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
    // In SalesTracker
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

// total quantity ever added
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

