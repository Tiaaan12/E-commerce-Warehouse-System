/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.warehouse_smart.system;

/**
 *
 * @author cvdev
 */
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

public class SalesTracker {
 
    private Map<String, Map<String, Integer>> salesData;
     private HashMap<String, ProductSearch> productMap;
     private Map<String, Integer> productTotals;
     private PriorityQueue<Map.Entry<String, Integer>> productHeap;
     private Map<String, Integer> locationTotals;
     private PriorityQueue<Map.Entry<String, Integer>> locationHeap;
     private int totalSales = 0;
     private int totalCustomers = 0;
    
    private int INITIAL_STOCK = 1015;

    public SalesTracker() {
        salesData = new HashMap<>();
        productMap = new HashMap<>();
        productTotals = new HashMap<>();
        productHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        locationTotals = new HashMap<>();
        locationHeap = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        
        
        addLocation("Manila");
        addLocation("Taguig");
        addLocation("Pasay");

        addProduct("AquaThirst Colorwave");
        addProduct("AquaThirst Dream");
        addProduct("Cylinder Collections");
        addProduct("Slate Cup Collection");
        addProduct("Tumbler Collection");
        addProduct("Jonathan Thirsty");
        addProduct("Turquoise Blue");
        addProduct("Mint Chip 16oz");
        addProduct("Wild Mushroom");

        
        recordSale("Manila", "AquaThirst Colorwave", 100);
        recordSale("Taguig", "AquaThirst Colorwave", 100);
        recordSale("Pasay", "AquaThirst Colorwave", 100);

        recordSale("Manila", "AquaThirst Dream", 100);
        recordSale("Taguig", "AquaThirst Dream", 100);
        recordSale("Pasay", "AquaThirst Dream", 100);

        recordSale("Manila", "Cylinder Collections", 100);
        recordSale("Taguig", "Cylinder Collections", 100);
        recordSale("Pasay", "Cylinder Collections", 100);
        
        
        recordSale("Manila", "Slate Cup Collection", 100);
        recordSale("Taguig", "Slate Cup Collection", 100);
        recordSale("Pasay", "Slate Cup Collection", 100);
        
        recordSale("Manila", "Tumbler Collection", 100);
        recordSale("Taguig", "Tumbler Collection", 100);
        recordSale("Pasay", "Tumbler Collection", 100);
        
        recordSale("Manila", "Jonathan Thirsty", 100);
        recordSale("Taguig", "Jonathan Thirsty", 100);
        recordSale("Pasay", "Jonathan Thirsty", 100);
        
        recordSale("Manila", "Turquoise Blue", 100);
        recordSale("Taguig", "Turquoise Blue", 100);
        recordSale("Pasay", "Turquoise Blue", 100);
        
        recordSale("Manila", "Mint Chip 16oz", 100);
        recordSale("Taguig", "Mint Chip 16oz", 100);
        recordSale("Pasay", "Mint Chip 16oz", 100);
        
        recordSale("Manila", "Wild Mushroom", 100);
        recordSale("Taguig", "Wild Mushroom", 100);
        recordSale("Pasay", "Wild Mushroom", 100);
        

        
        
    }

    private void addLocation(String location) {
        salesData.putIfAbsent(location, new HashMap<>());
    }

    private void addProduct(String product) {
        for (String location : salesData.keySet()) {
            salesData.get(location).putIfAbsent(product, 0);
        }
    }
    

  public void recordSale(String location, String product, int qty) {
   
    salesData.putIfAbsent(location, new HashMap<>());
    Map<String, Integer> productMap = salesData.get(location);
    productMap.put(product, productMap.getOrDefault(product, 0) + qty);

   
    productTotals.put(product, productTotals.getOrDefault(product, 0) + qty);
    productHeap.clear();
    productHeap.addAll(productTotals.entrySet());

 
    locationTotals.put(location, locationTotals.getOrDefault(location, 0) + qty);
    locationHeap.clear();
    locationHeap.addAll(locationTotals.entrySet());
    
    totalSales += qty;
    totalCustomers++;
}

 public int getTotalSales() {
    return totalSales; 
 }
 
 public int getTotalCustomers() {
     return totalCustomers;
 }
 
 
      

    public Map<String, Map<String, Integer>> getSalesData() {
        return salesData;
    }

public String getTopItem() {
    return productHeap.isEmpty() ? "None" : productHeap.peek().getKey();
}

public String getHotLocation() {
    return locationHeap.isEmpty() ? "None" : locationHeap.peek().getKey();
}
public List<String> getSortedProducts() {
    List<String> products = new ArrayList<>();
    for (Map<String, Integer> map : salesData.values()) {
         products.addAll(map.keySet());
    }


    products = new ArrayList<>(new HashSet<>(products));

    for (int i = 1; i < products.size(); i++) {
        String key = products.get(i);
        int j = i - 1;
        while (j >= 0 && products.get(j).compareTo(key) > 0) {
            products.set(j + 1, products.get(j));
            j--;
        }
        products.set(j + 1, key);
    }

    return products;
}


}

