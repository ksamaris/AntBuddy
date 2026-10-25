package Persistence;

import Model.Category;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class CategoryRepository {
    /**
     * Search for the categories stored in the database using an SQL statement and save them in the category list.
     * 
     * @return returns the productslist list.
     */
    public List<Category> getCategories() {
        
        ConnectionDB objcon = new ConnectionDB();
        
        String sql = "SELECT * FROM CATEGORY;";
        
        List<Category> productslist = new ArrayList<>();

        try (Connection conn = objcon.establishConnection();                
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                
                Category product = new Category();
                
                product.setCodeCategory(rs.getString(1));
                product.setNameCategory(rs.getString(2));
                
                productslist.add(product);
            }

        } catch (SQLException e) {
            System.err.println("ERROR : " + e.toString());
        }

        return productslist;
    }
}
