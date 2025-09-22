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
import javax.swing.border.TitledBorder;

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
  
        
    }
   
    
      public void updateChart() {
           if (tracker == null) return; // safety check
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
  public void loadProductDemand(SalesTracker tracker) {
    productDemandPanel.removeAll();
    productDemandPanel.setLayout(new BorderLayout());

    // Container with title
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    container.setBackground(new Color(30, 30, 30));
    container.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Product Demand Analysis",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 14),
            Color.WHITE
    ));

    // Header row
    JPanel header = new JPanel(new GridLayout(1, 3));
    header.setBackground(new Color(20, 20, 20));

    JLabel nameHeader = new JLabel("Product");
    nameHeader.setForeground(Color.WHITE);

    JLabel salesHeader = new JLabel("Sales", SwingConstants.CENTER);
    salesHeader.setForeground(Color.WHITE);

    JLabel rankHeader = new JLabel("Rank", SwingConstants.CENTER);
    rankHeader.setForeground(Color.WHITE);

    header.add(nameHeader);
    header.add(salesHeader);
    header.add(rankHeader);

    container.add(header);

    // Data rows
    Map<String, Integer> productSales = tracker.getProductSales();
    List<Map.Entry<String, Integer>> salesList = new ArrayList<>(productSales.entrySet());
    salesList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

    int rank = 1;
    for (Map.Entry<String, Integer> entry : salesList) {
        String productName = entry.getKey();
        int sales = entry.getValue();

        JPanel row = new JPanel(new GridLayout(1, 3, 10, 0));
        row.setBackground(new Color(40, 40, 40));
        row.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel nameLabel = new JLabel(productName);
        nameLabel.setForeground(Color.WHITE);

        JLabel salesLabel = new JLabel(String.valueOf(sales), SwingConstants.CENTER);
        salesLabel.setForeground(Color.CYAN);

        JLabel rankLabel = new JLabel(String.valueOf(rank), SwingConstants.CENTER);
        rankLabel.setForeground(Color.GREEN);

        row.add(nameLabel);
        row.add(salesLabel);
        row.add(rankLabel);

        container.add(row);
        rank++;
    }

    // Put container inside scroll
    JScrollPane scrollPane = new JScrollPane(container);
    scrollPane.setBorder(null); // removes the ugly box
    scrollPane.getViewport().setBackground(new Color(30, 30, 30));

    productDemandPanel.add(scrollPane, BorderLayout.CENTER);

    productDemandPanel.revalidate();
    productDemandPanel.repaint();
}


public void setTracker(SalesTracker tracker) {
    this.tracker = tracker;
}
public void refreshDemand() {
    loadProductDemand(tracker); // reload the panel from tracker
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
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
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

        jLabel7.setFont(inter);
        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Location Analyis");

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Location");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sales");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Rank");

        jLabel5.setForeground(Color.decode("#D6D6D6"));
        jLabel5.setText("Manila");

        jLabel6.setForeground(Color.decode("#D6D6D6"));
        jLabel6.setText("Pasay");

        jLabel8.setForeground(Color.decode("#D6D6D6"));
        jLabel8.setText("Taguig");

        jLabel9.setForeground(Color.decode("#D6D6D6"));
        jLabel9.setText("1000");

        jLabel10.setForeground(Color.decode("#D6D6D6"));
        jLabel10.setText("500");

        jLabel12.setForeground(Color.decode("#D6D6D6"));
        jLabel12.setText("200");

        jLabel13.setForeground(Color.decode("#D6D6D6"));
        jLabel13.setText("1");
        jLabel13.setInheritsPopupMenu(false);

        jLabel14.setForeground(Color.decode("#D6D6D6"));
        jLabel14.setText("2");

        jLabel15.setForeground(Color.decode("#D6D6D6"));
        jLabel15.setText("3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel12))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(jLabel15))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel16)
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);
        jScrollPane1.setBackground(Color.decode("#1A1A1A"));
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // make sure it respects preferred size
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16); // smooth scroll
        jScrollPane1.setOpaque(false);

        productDemandPanel.setLayout(new BoxLayout(productDemandPanel, BoxLayout.Y_AXIS));
        productDemandPanel.setBackground(Color.decode("#212121"));
        productDemandPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        productDemandPanel.setOpaque(false);
        productDemandPanel.setPreferredSize(new java.awt.Dimension(340, 144));

        javax.swing.GroupLayout productDemandPanelLayout = new javax.swing.GroupLayout(productDemandPanel);
        productDemandPanel.setLayout(productDemandPanelLayout);
        productDemandPanelLayout.setHorizontalGroup(
            productDemandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );
        productDemandPanelLayout.setVerticalGroup(
            productDemandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
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
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(jScrollPane1)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel productDemandPanel;
    // End of variables declaration//GEN-END:variables
}
