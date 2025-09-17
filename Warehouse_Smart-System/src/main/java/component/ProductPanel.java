    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;

import com.mycompany.warehouse_smart.system.Styledpanel;
import java.awt.Color;
import com.mycompany.warehouse_smart.system.RoundTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import util.FontLoader;
import com.mycompany.warehouse_smart.system.ProductSearch;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author cvdev
 */
public class ProductPanel extends javax.swing.JPanel {
    private HashMap<String, ProductSearch> productMap;
    private DashboardPanel dashboardPanel;
    private HistoryPanel historyPanel;
    /**
     * Creates new form ProductPanel
     */
    public ProductPanel(DashboardPanel dashboardPanel, HistoryPanel historyPanel) {
        this.dashboardPanel = dashboardPanel;
        this.historyPanel = historyPanel;
        initComponents();
        
         productMap = new HashMap<>();
         
        loadProducts();
      
    }
 private void sellProduct(String location, String product, int qty) {
        dashboardPanel.recordSale(location, product, qty);
    }
 private void loadProducts() {
    String[][] products = {
        {"AquaThirst Colorwave Flip", "1012243", "100", "Manila"},
        {"AquaThirst Dream 532ml", "1012249", "100", "Pasay & Taguig"},
        {"Cylinder Collections 24oz", "1012279", "100", "Pasay"},
        {"Slate Cup Collection 16oz", "1012347", "100", "Manila"},
        {"Tumbler Collection (30oz)", "1012445", "100", "Taguig"},
    };

    jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));

    for (String[] p : products) {
        JPanel row = new JPanel(new BorderLayout(10, 10));
        row.setBackground(new java.awt.Color(40, 40, 40));
        row.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JLabel imgLabel = new JLabel(new ImageIcon(getClass().getResource("/" + p[1] + ".png")));
        row.add(imgLabel, BorderLayout.WEST);

        
        JPanel details = new JPanel(new GridLayout(1, 4, 10, 20));
        details.setBackground(new java.awt.Color(40, 40, 40));

        JLabel nameLabel = new JLabel(p[0]);
        nameLabel.setForeground(Color.WHITE);
        

        JLabel codeLabel = new JLabel(new ImageIcon(getClass().getResource("/barcodes/" + p[1] + ".png")));
         codeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel statusLabel = new JLabel(p[2]);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(Color.GREEN);

        JLabel locLabel = new JLabel(p[3]);
        locLabel.setForeground(Color.CYAN);
        locLabel.setHorizontalAlignment(SwingConstants.CENTER);

        details.add(nameLabel);
        details.add(codeLabel);
        details.add(statusLabel);
        details.add(locLabel);

        row.add(details, BorderLayout.CENTER);

        jPanel1.add(row);
    }

    jPanel1.revalidate();
    jPanel1.repaint();
   
    ProductSearch p1 = new ProductSearch("1012243", "AquaThirst Colorwave Flip", 
            "Available", "Manila", "Stylish flip-top bottle", 
            new ImageIcon(getClass().getResource("/1012243.png")));
    ProductSearch p2 = new ProductSearch("1012249", "AquaThirst Dream 532ml", 
            "Available", "Pasay & Taguig", "Dreamy hydration", 
            new ImageIcon(getClass().getResource("/1012249.png")));
   ProductSearch p3 = new ProductSearch("1012279", "Cylinder Collections 24oz", 
            "Available", "Pasay", "Sleek 24oz cylinder bottle", 
            new ImageIcon(getClass().getResource("/1012279.png")));
   ProductSearch p4 = new ProductSearch("1012347", "Slate Cup Collection 16oz", 
            "Available", "Manila", "Compact 16oz modern cup", 
            new ImageIcon(getClass().getResource("/1012347.png")));
   ProductSearch p5 = new ProductSearch("1012445", "Tumbler Collection (30oz)", 
            "Available", "Taguig", "Durable 30oz insulated tumbler", 
            new ImageIcon(getClass().getResource("/1012445.png")));

    productMap.put(p1.getCode(), p1);
    productMap.put(p2.getCode(), p2);
    productMap.put(p3.getCode(), p3);
    productMap.put(p4.getCode(), p4);
    productMap.put(p5.getCode(), p5);

    

}
 
 private void showProductDialog(ProductSearch p) {
    JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Product Details", true);
    dialog.setSize(430, 280);
    dialog.setLayout(new BorderLayout(10,10));
    dialog.setLocationRelativeTo(this);
    dialog.setBackground(Color.decode("#212121"));
    JLabel imgLabel = new JLabel();
    imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imgLabel.setIcon(new ImageIcon(p.getImage().getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
    dialog.add(imgLabel, BorderLayout.WEST);

    JPanel detailsPanel = new JPanel();
    detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
    detailsPanel.add(new JLabel("Name: " + p.getName()));
    detailsPanel.add(new JLabel("Code: " + p.getCode()));
    detailsPanel.add(new JLabel("Status: " + p.getStatus()));
    detailsPanel.add(new JLabel("Location: " + p.getLocation()));
    detailsPanel.add(new JLabel("<html><p style='width:200px;'>Description: " + p.getDescription() + "</p></html>"));
    
    
    JPanel salePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    salePanel.add(new JLabel("Enter Sales:"));
    JTextField qtyField = new JTextField(5);
    salePanel.add(qtyField);
    detailsPanel.add(salePanel);
   
JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
locationPanel.add(new JLabel("Select Location:"));


String[] locations = {"Pasay", "Manila", "Taguig"}; 
JComboBox<String> locationComboBox = new JComboBox<>(locations);
locationPanel.add(locationComboBox);
detailsPanel.add(locationPanel);


    dialog.add(detailsPanel, BorderLayout.CENTER);

    JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton saveBtn = new JButton("Record Sale");
    JButton closeBtn = new JButton("Close");

    btnPanel.add(saveBtn);
    btnPanel.add(closeBtn);
    dialog.add(btnPanel, BorderLayout.SOUTH);

    saveBtn.addActionListener(e -> {
        try {
            int qty = Integer.parseInt(qtyField.getText().trim());
            if (qty <= 0) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid quantity.");
                return;
            }

            if (dashboardPanel != null) {
                historyPanel.addSaleRecord(p.getName(), p.getLocation(), qty);

                dashboardPanel.recordSale(p.getLocation(), p.getName(), qty); 
            }

            JOptionPane.showMessageDialog(dialog, "Sale recorded successfully!");
            dialog.dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(dialog, "Invalid number entered.");
        }
    });

    closeBtn.addActionListener(e -> dialog.dispose());

    dialog.setVisible(true);
}



private ImageIcon loadImage(String path, int w, int h) {
    java.net.URL url = getClass().getResource(path);
    if (url != null) {
        return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
    } else {
        return new ImageIcon(new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)); 
    }
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new Styledpanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        searchText = new com.mycompany.warehouse_smart.system.RoundTextField(20);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel6.setForeground(Color.decode("#D6D6D6"));
        jLabel6.setText("Product Name");

        jLabel12.setForeground(Color.decode("#D6D6D6"));
        jLabel12.setText("Code");

        jLabel13.setForeground(Color.decode("#D6D6D6"));
        jLabel13.setText("Status");

        jLabel14.setForeground(Color.decode("#D6D6D6"));
        jLabel14.setText("Location");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Untitled (60 x 60 px).png"))); // NOI18N

        jPanel13.setBackground(Color.decode("#212121"));
        jPanel13.setPreferredSize(new java.awt.Dimension(177, 175));
        jPanel13.setLayout(null);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/notif.png"))); // NOI18N
        jPanel13.add(jLabel4);
        jLabel4.setBounds(10, 10, 24, 25);

        Font inter = FontLoader.loadFont("resources/fonts/Inter_28pt-ExtraBold.ttf", 25f);
        jLabel7.setFont(inter);
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Product");

        jScrollPane1.setViewportView(jPanel1);
        jScrollPane1.setBackground(Color.decode("#1A1A1A"));
        jScrollPane1.setForeground(Color.decode("#1A1A1A"));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        jPanel1.setBackground(Color.decode("#1A1A1A"));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(jPanel1);

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search-icon-png-4 (1).png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setEnabled(false);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jLayeredPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 10, 20, 30));

        searchText.setBackground(new Color(0,0,0,0));
        searchText.setForeground(new java.awt.Color(255, 255, 255));
        searchText.setText("Search");
        searchText.setCaretColor(new java.awt.Color(255, 255, 255));
        searchText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchTextFocusLost(evt);
            }
        });
        searchText.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                searchTextInputMethodTextChanged(evt);
            }
        });
        searchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextActionPerformed(evt);
            }
        });
        jLayeredPane1.add(searchText, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 370, 37));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jLabel6)
                .addGap(130, 130, 130)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(115, 115, 115)
                .addComponent(jLabel14)
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 38, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(106, 106, 106)))
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel7))
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFocusGained
        if(searchText.getText().equals("Search")) {
            searchText.setText("");
            searchText.setForeground(Color.WHITE);
        }
    }//GEN-LAST:event_searchTextFocusGained

    private void searchTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFocusLost
        if(searchText.getText().trim().isEmpty()) {
            searchText.setText("Search");
            searchText.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_searchTextFocusLost

    private void searchTextInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_searchTextInputMethodTextChanged

    }//GEN-LAST:event_searchTextInputMethodTextChanged

    private void searchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextActionPerformed
        String code = searchText.getText().trim();
        if (productMap.containsKey(code)) {
            ProductSearch p = productMap.get(code);
            showProductDialog(p);
            }     
        else {
        JOptionPane.showMessageDialog(this, "Product not found!");
            }
    }//GEN-LAST:event_searchTextActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        Point p = SwingUtilities.convertPoint(evt.getComponent(), evt.getPoint(), searchText);

        if (!searchText.getBounds().contains(p)) {
            searchText.transferFocus(); 
        }
    }//GEN-LAST:event_formMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
      String code = searchText.getText().trim();
        if (productMap.containsKey(code)) {
            ProductSearch p = productMap.get(code);
            showProductDialog(p);
            }     
        else {
        JOptionPane.showMessageDialog(this, "Product not found!");
            }
    }//GEN-LAST:event_jLabel1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField searchText;
    // End of variables declaration//GEN-END:variables
}
