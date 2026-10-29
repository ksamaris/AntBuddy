package UI;

import Model.Product;
import Service.ProductService;
import java.awt.Frame;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class InventaryUI extends javax.swing.JPanel {

    
    private ProductService productservice = new ProductService();
    
    /**
     * Creates new form Inventary
     */
    public InventaryUI() {
        initComponents();
        
        updateCards();
        loadTableData();
        tableProducts.setDefaultRenderer(Object.class, new ProductTableRenderer());
        setupSearch();
    }
    
    public void loadTableData(){
        
        DefaultTableModel modeltable=new DefaultTableModel();
        
        modeltable.addColumn("GTIN");
        modeltable.addColumn("Name");
        modeltable.addColumn("Price");
        modeltable.addColumn("Category");
        modeltable.addColumn("Stock");
        modeltable.addColumn("Stock minimum");
        
        try {
            List<Product> inventarylist = productservice.listProductsTable();
            String text = txtSearch.getText().trim().toLowerCase();
            
            for (Product p : inventarylist) {
                if (!p.matches(text)) {
                    continue;
                }
                
                Object[] row = {
                    p.getGTIN(),
                    p.getName(),
                    p.getPrice(),
                    p.getCategory(),
                    p.getStock(),
                    p.getStockMin()
                };
                modeltable.addRow(row);
            }
            
            tableProducts.setModel(modeltable);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR: " + e.toString());
        }
    }
    
    /**
     * Retrieves the data from the selected row.
     * 
     * @return a variable of type product that contains the data from the selected row:
     */
    public Product getRowData(){
        int row = tableProducts.getSelectedRow();
        
        if(row == -1){
            JOptionPane.showMessageDialog(null, "You must select a row first");
            return null;
        }
        
        try {
            Product p = new Product();
            p.setGTIN(tableProducts.getValueAt(row, 0).toString());
            p.setName(tableProducts.getValueAt(row, 1).toString());
            p.setPrice(Double.parseDouble(tableProducts.getValueAt(row, 2).toString()));
            p.setCategory(tableProducts.getValueAt(row, 3).toString());
            p.setStock(Integer.parseInt(tableProducts.getValueAt(row, 4).toString()));
            p.setStockMin(Integer.parseInt(tableProducts.getValueAt(row, 5).toString()));
            
            return p;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The row contains invalid numeric data");
            return null;
        }
        
    }
    
    /**
     * Opens a modal window with the edit form of the given product.
     * 
     * @param p the product to edit.
     */
    public void openEditPanel(Product p){   
        JDialog dialog = new JDialog((Frame) null, "Edit product", true);
        dialog.setContentPane(new editProductData(p));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    /**
     * Opens a modal window with the form for adding a product.
     *
     * @param p the product to be added.
     */
    public void openAddPanel(Product p){   
        JDialog dialog = new JDialog((Frame) null, "Add product", true);
        dialog.setContentPane(new AddProduct(p));
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    /**
    * Asks the user to confirm the deletion of a product.
    * 
    * @param p the product to delete.
    * @return true if the user confirmed, false otherwise.
    */
    private boolean confirmDeletion(Product p){
        int option = JOptionPane.showConfirmDialog(
                this,
                "Do you want to delete the product " + p.getName() + "?",
                "Confirm deletion",
                JOptionPane.YES_NO_OPTION);
    
        return option == JOptionPane.YES_OPTION;
    }
    
    /**
    * Deletes the product and shows the result to the user.
    * 
    * @param p the product to delete.
    */
    private void deleteProductData(Product p){
        String result = productservice.deleteProduct(p.getGTIN());
        JOptionPane.showMessageDialog(this, result);
    }
    
    /**
    * Makes the search field reload the table each time its text changes.
    */
    private void setupSearch(){
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                loadTableData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                loadTableData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                loadTableData();
            }
        });
    }
    
    /**
     * Updates the three dashboard cards with the current values from the database.
     */
    public void updateCards() {
        labelStockTotal1.setText(String.valueOf(productservice.getTotalStock()));
        labelStockLow.setText(String.valueOf(productservice.getLowStockCount()));
        labelStockTotal4.setText(String.valueOf(productservice.getCategoriesCount()));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        labelStockTotal3 = new javax.swing.JLabel();
        panelBaseInventaryUI = new javax.swing.JPanel();
        jPanel4 = new RoundedPanel(30);
        labelTextStock = new javax.swing.JLabel();
        labelStockTotal1 = new javax.swing.JLabel();
        panelTableProducts = new RoundedPanel(30);
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProducts = new javax.swing.JTable();
        panelBaseSearch = new RoundedPanel(37);
        txtSearch = new javax.swing.JTextField();
        jPanel7 = new RoundedPanel(30);
        labelTextStockLow = new javax.swing.JLabel();
        labelStockLow = new javax.swing.JLabel();
        jPanel9 = new RoundedPanel(30);
        jLabel6 = new javax.swing.JLabel();
        labelStockTotal4 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("jLabel1");

        labelStockTotal3.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        labelStockTotal3.setForeground(new java.awt.Color(0, 0, 0));
        labelStockTotal3.setText("[00]");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(labelStockTotal3)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(labelStockTotal3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        panelBaseInventaryUI.setBackground(new java.awt.Color(229, 229, 229));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        labelTextStock.setBackground(new java.awt.Color(102, 102, 102));
        labelTextStock.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        labelTextStock.setForeground(new java.awt.Color(153, 153, 153));
        labelTextStock.setText("Stock");

        labelStockTotal1.setFont(new java.awt.Font("Leelawadee", 1, 48)); // NOI18N
        labelStockTotal1.setForeground(new java.awt.Color(0, 0, 0));
        labelStockTotal1.setText("00");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStockTotal1)
                    .addComponent(labelTextStock))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelStockTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTextStock)
                .addContainerGap())
        );

        panelTableProducts.setBackground(new java.awt.Color(255, 255, 255));

        tableProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableProducts);

        javax.swing.GroupLayout panelTableProductsLayout = new javax.swing.GroupLayout(panelTableProducts);
        panelTableProducts.setLayout(panelTableProductsLayout);
        panelTableProductsLayout.setHorizontalGroup(
            panelTableProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableProductsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTableProductsLayout.setVerticalGroup(
            panelTableProductsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableProductsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBaseSearch.setBackground(new java.awt.Color(255, 255, 255));

        txtSearch.setBackground(new java.awt.Color(255, 255, 255));
        txtSearch.setFont(new java.awt.Font("Leelawadee", 0, 18)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(102, 102, 102));
        txtSearch.setBorder(null);
        txtSearch.addActionListener(this::txtSearchActionPerformed);

        javax.swing.GroupLayout panelBaseSearchLayout = new javax.swing.GroupLayout(panelBaseSearch);
        panelBaseSearch.setLayout(panelBaseSearchLayout);
        panelBaseSearchLayout.setHorizontalGroup(
            panelBaseSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaseSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBaseSearchLayout.setVerticalGroup(
            panelBaseSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaseSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        labelTextStockLow.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        labelTextStockLow.setForeground(new java.awt.Color(153, 153, 153));
        labelTextStockLow.setText("Stock Low");

        labelStockLow.setFont(new java.awt.Font("Leelawadee", 1, 48)); // NOI18N
        labelStockLow.setForeground(new java.awt.Color(0, 0, 0));
        labelStockLow.setText("00");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStockLow)
                    .addComponent(labelTextStockLow))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelStockLow, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTextStockLow)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Leelawadee", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Categorias");

        labelStockTotal4.setFont(new java.awt.Font("Leelawadee", 1, 48)); // NOI18N
        labelStockTotal4.setForeground(new java.awt.Color(0, 0, 0));
        labelStockTotal4.setText("00");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelStockTotal4)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelStockTotal4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnDelete.setBackground(new java.awt.Color(255, 0, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(51, 51, 51));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnEdit.setBackground(new java.awt.Color(0, 153, 255));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(51, 51, 51));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnNew.setBackground(new java.awt.Color(0, 204, 0));
        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(51, 51, 51));
        btnNew.setText("Add");
        btnNew.addActionListener(this::btnNewActionPerformed);

        javax.swing.GroupLayout panelBaseInventaryUILayout = new javax.swing.GroupLayout(panelBaseInventaryUI);
        panelBaseInventaryUI.setLayout(panelBaseInventaryUILayout);
        panelBaseInventaryUILayout.setHorizontalGroup(
            panelBaseInventaryUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaseInventaryUILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBaseInventaryUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBaseInventaryUILayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelTableProducts, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBaseInventaryUILayout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelBaseInventaryUILayout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(panelBaseSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBaseInventaryUILayout.setVerticalGroup(
            panelBaseInventaryUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaseInventaryUILayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(panelBaseInventaryUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBaseSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(panelTableProducts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelBaseInventaryUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBaseInventaryUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBaseInventaryUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        Product p = getRowData();
        
        if(p == null){
            return;
        }
        
        openEditPanel(p);
        loadTableData();
        updateCards();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        Product p = getRowData();

        if (p == null) {
            return;
        }

        if (confirmDeletion(p)) {
            deleteProductData(p);
            loadTableData();
            updateCards();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        Product p = new Product();
        openAddPanel(p);
    }//GEN-LAST:event_btnNewActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelStockLow;
    private javax.swing.JLabel labelStockTotal1;
    private javax.swing.JLabel labelStockTotal3;
    private javax.swing.JLabel labelStockTotal4;
    private javax.swing.JLabel labelTextStock;
    private javax.swing.JLabel labelTextStockLow;
    private javax.swing.JPanel panelBaseInventaryUI;
    private javax.swing.JPanel panelBaseSearch;
    private javax.swing.JPanel panelTableProducts;
    private javax.swing.JTable tableProducts;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
