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
                product.setStockMin(rs.getInt(6));
                
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
    
    public boolean updateProduct(Product p){
        boolean updated = false; 
        
        try{
            ConnectionDB objcon = new ConnectionDB();
            Connection conn = objcon.establishConnection();
            
            String sql = "UPDATE product SET product_name = ?, price = ?, name_category = ?, "
                   + "stock = ?, stock_minimum = ? WHERE gtin = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setString(3, p.getCategory());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getStockMin());
            ps.setString(6, p.getGTIN());
            
            if (ps.executeUpdate() > 0) {
            updated = true;
            }
            
            ps.close();
            conn.close();
            
        }catch(SQLException e){
            System.err.println("Error: "+ e.toString());
            return false;
        }
        
        return updated;
    }
    
    /**
    * Deletes a product from the database, identified by its GTIN.
     * 
    * @param gtin the GTIN of the product to delete.
    * @return true if the product was deleted, false otherwise.
    */
    public boolean deleteProduct(String gtin){
        boolean deleted = false;
    
        try{
            ConnectionDB objcon = new ConnectionDB();
            Connection conn = objcon.establishConnection();
        
            String sql = "DELETE FROM product WHERE gtin = ?";
        
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, gtin);
        
            if (ps.executeUpdate() > 0) {
                deleted = true;
            }
        
            ps.close();
            conn.close();
        
        }catch(SQLException e){
            System.err.println("ERROR : " + e.toString());
        }
    
        return deleted;
    }
    
    /**
     * Receives an SQL statement to retrieve a numeric result from the database.
     * 
     * @param sql the SQL query to execute; it must return a single numeric value
     * @return numerical result of the query, or 0 if no rows are found or an error occurs.
     */
    private int getNumber(String sql) {
        int result = 0;
        
        ConnectionDB objcon = new ConnectionDB();
        
        try (Connection conn = objcon.establishConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
                
            ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                result = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    /**
    * Calculates the total number of units in stock across all products.
    *
    * @return the sum of the stock column, or 0 if there are no products
    */
    public int getTotalStock() {
        return getNumber("SELECT COALESCE(SUM(stock), 0) FROM PRODUCT");
    }

    /**
    * Counts the products whose stock has reached or fallen below the minimum.
    *
    * @return the number of products with low stock
    */
    public int getLowStockCount() {
        return getNumber("SELECT COUNT(*) FROM PRODUCT WHERE stock <= stock_minimum");
    }

    /**
    * Counts the categories registered in the database.
    *
    * @return the total number of categories
    */
    public int getCategoriesCount() {
        return getNumber("SELECT COUNT(*) FROM CATEGORY");
    }
}
