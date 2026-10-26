package Persistence;

import Model.Product;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class ProductRepository {
    
    /**
     * It searches for products stored in the database using an SQL statement and stores them in productslist.
     * 
     * @return returns the productslist list.
     */
    public List<Product> getProducts() {
        
        ConnectionDB objcon = new ConnectionDB();
        
        String sql = "SELECT * FROM PRODUCT;";
        
        List<Product> productslist = new ArrayList<>();

        try (Connection conn = objcon.establishConnection();                
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                
                Product product = new Product();
                
                product.setGTIN(rs.getString(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setCategory(rs.getString(4));
                product.setStock(rs.getInt(5));
                
                productslist.add(product);
            }

        } catch (SQLException e) {
            System.err.println("ERROR : " + e.toString());
        }

        return productslist;
    }
    
    /**
     * Establish a connection to the database to insert the new product data.
     * 
     * @return Returns a Boolean value to confirm whether or not the process was successful.
     */
    public boolean insertProduct(Product p){
        boolean saved = false; 
        
        
        try{
            ConnectionDB objcon = new ConnectionDB();
            Connection conn = objcon.establishConnection();
            
            String sql = "INSERT INTO product (gtin, product_name, price, name_category, stock, stock_minimum) "
               + "VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getGTIN());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getCategory());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getStockMin());
            
            if (ps.executeUpdate() > 0) {
            saved = true;
            
            ps.close();
            conn.close();
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: "+e.toString());
            return false;
        }
        
        return saved;
    }
}
