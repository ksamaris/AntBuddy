package Persistence;

import Model.Product;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
}
