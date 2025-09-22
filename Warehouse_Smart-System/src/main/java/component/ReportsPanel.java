/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;

/**
 *
 * @author cvdev
 */
import com.mycompany.warehouse_smart.system.Styledpanel;
import java.awt.Color;
import java.awt.Font;
import util.FontLoader;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import util.FontLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.mycompany.warehouse_smart.system.SalesTracker;
import com.mycompany.warehouse_smart.system.ProductSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public class ReportsPanel extends javax.swing.JPanel {

    /**
     * Creates new form ReportsPanel
     */
    private ProductSearch productSearch;
    private SalesTracker tracker;
    public ReportsPanel(SalesTracker tracker) {
        initComponents();
        this.tracker = tracker;
        
    updateChart();
    loadProductDemand(tracker);
    locationAnalysis(tracker);
  
        
    }
   
    
      public void updateChart() {
           if (tracker == null) return; 
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

 
    for (String location : tracker.getSalesData().keySet()) {
        for (String product : tracker.getSalesData().get(location).keySet()) {
            int qty = tracker.getSalesData().get(location).get(product);
            dataset.addValue(qty, product, location);
        }
    }

    JFreeChart barChart = ChartFactory.createBarChart(
        "Reports",
        "Location",
        "Quantity",
        dataset,
        PlotOrientation.VERTICAL,
        true,
        true,
        false
    );

  
    barChart.setBackgroundPaint(null);
    CategoryPlot plot = barChart.getCategoryPlot();
    plot.setBackgroundPaint(null);
    plot.setOutlinePaint(null);

    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setSeriesPaint(0, new Color(137, 121, 255));
    renderer.setSeriesPaint(1, new Color(255, 146, 138));
    renderer.setSeriesPaint(2, new Color(60, 195, 223));
    renderer.setBarPainter(new StandardBarPainter());
    renderer.setShadowVisible(false);
    renderer.setDrawBarOutline(false);

   
    plot.setRangeGridlinePaint(new Color(200, 200, 200));

 
    barChart.getTitle().setPaint(Color.WHITE);

    CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setTickLabelPaint(Color.WHITE);
    domainAxis.setLabelPaint(Color.WHITE);

    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setTickLabelPaint(Color.WHITE);
    rangeAxis.setLabelPaint(Color.WHITE);

    if (barChart.getLegend() != null) {
        barChart.getLegend().setItemPaint(Color.WHITE);
        barChart.getLegend().setBackgroundPaint(null);
        barChart.getLegend().setFrame(BlockBorder.NONE);
    }


    ChartPanel chartPanel = new ChartPanel(barChart);
    chartPanel.setPreferredSize(new Dimension(770, 275));
    chartPanel.setOpaque(false);

    jPanel6.removeAll();
    jPanel6.setLayout(new BorderLayout());
    jPanel6.add(chartPanel, BorderLayout.CENTER);
    jPanel6.revalidate();
    jPanel6.repaint();
}
    public void recordSale(String location, String product, int qty) {
    tracker.recordSale(location, product, qty);
    updateChart();
     loadProductDemand(tracker);
     
}
    
   public void locationAnalysis(SalesTracker tracker) {
    jPanel14.removeAll();
    jPanel14.setLayout(new BoxLayout(jPanel14, BoxLayout.Y_AXIS));

    Map<String, Integer> locationSales = tracker.getLocationSales();
    List<Map.Entry<String, Integer>> locationSalesList = new ArrayList<>(locationSales.entrySet());
    locationSalesList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

    int rowHeight = 40;
    int rank = 1;

    for (Map.Entry<String, Integer> entry : locationSalesList) {
        JPanel row = new JPanel(new GridLayout(1, 3));
        row.setBackground(new Color(40, 40, 40));
        row.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel nameLabel = new JLabel(entry.getKey());
        nameLabel.setForeground(Color.WHITE);

        JLabel salesLabel = new JLabel(String.valueOf(entry.getValue()), SwingConstants.CENTER);
        salesLabel.setForeground(Color.CYAN);

        JLabel rankLabel = new JLabel(String.valueOf(rank), SwingConstants.CENTER);
        rankLabel.setForeground(Color.GREEN);

        row.add(nameLabel);
        row.add(salesLabel);
        row.add(rankLabel);

        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
        jPanel14.add(row);

        rank++;
    }

    // set preferred size once
    jPanel14.setPreferredSize(new Dimension(
        jPanel14.getWidth(),
        locationSalesList.size() * rowHeight
    ));

    jPanel14.revalidate();
    jPanel14.repaint();
}

    public void loadProductDemand(SalesTracker tracker) {
    productDemandPanel.removeAll();
productDemandPanel.setLayout(new BoxLayout(productDemandPanel, BoxLayout.Y_AXIS));

Map<String, Integer> productSales = tracker.getProductSales();
List<Map.Entry<String, Integer>> salesList = new ArrayList<>(productSales.entrySet());
salesList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

int rowHeight = 40;
int rank = 1;
for (Map.Entry<String, Integer> entry : salesList) {
    JPanel row = new JPanel(new GridLayout(1, 3));
    row.setBackground(new Color(40, 40, 40));
    row.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    JLabel nameLabel = new JLabel(entry.getKey());
    nameLabel.setForeground(Color.WHITE);

    JLabel salesLabel = new JLabel(String.valueOf(entry.getValue()), SwingConstants.CENTER);
    salesLabel.setForeground(Color.CYAN);

    JLabel rankLabel = new JLabel(String.valueOf(rank), SwingConstants.CENTER);
    rankLabel.setForeground(Color.GREEN);

    row.add(nameLabel);
    row.add(salesLabel);
    row.add(rankLabel);

    row.setMaximumSize(new Dimension(Integer.MAX_VALUE, rowHeight));
    productDemandPanel.add(row);

    rank++;
}


productDemandPanel.setPreferredSize(new Dimension(
        productDemandPanel.getWidth(),
        salesList.size() * rowHeight
));

productDemandPanel.revalidate();
productDemandPanel.repaint();

}

public void setTracker(SalesTracker tracker) {
    this.tracker = tracker;
}
public void refreshDemand() {
    loadProductDemand(tracker);
    locationAnalysis(tracker);
    updateChart();
}




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new Styledpanel();
        jPanel13 = new Styledpanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel14 = new Styledpanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productDemandPanel = new Styledpanel();

        setOpaque(false);

        jPanel6.setBackground(Color.decode("#212121"));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 776, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 285, Short.MAX_VALUE)
        );

        jPanel13.setBackground(Color.decode("#212121"));
        jPanel13.setPreferredSize(new java.awt.Dimension(177, 175));
        jPanel13.setLayout(null);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/notif.png"))); // NOI18N
        jPanel13.add(jLabel4);
        jLabel4.setBounds(10, 10, 24, 25);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Untitled (60 x 60 px).png"))); // NOI18N

        Font inter = FontLoader.loadFont("resources/fonts/Inter_28pt-ExtraBold.ttf", 25f);
        jLabel7.setFont(inter);
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Reports");

        jPanel14.setBackground(Color.decode("#212121"));
        jPanel14.setPreferredSize(new java.awt.Dimension(340, 144));
        jPanel14.setLayout(new java.awt.CardLayout());

        jScrollPane1.setViewportView(productDemandPanel);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setBackground(Color.decode("#1A1A1A"));
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // make sure it respects preferred size
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16); // smooth scroll
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(342, 144));

        productDemandPanel.setLayout(new BoxLayout(productDemandPanel, BoxLayout.Y_AXIS));
        productDemandPanel.setBackground(Color.decode("#212121"));
        productDemandPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        productDemandPanel.setPreferredSize(new java.awt.Dimension(340, 144));

        javax.swing.GroupLayout productDemandPanelLayout = new javax.swing.GroupLayout(productDemandPanel);
        productDemandPanel.setLayout(productDemandPanelLayout);
        productDemandPanelLayout.setHorizontalGroup(
            productDemandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );
        productDemandPanelLayout.setVerticalGroup(
            productDemandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(productDemandPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel productDemandPanel;
    // End of variables declaration//GEN-END:variables
}
