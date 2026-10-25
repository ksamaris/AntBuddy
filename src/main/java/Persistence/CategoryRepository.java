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
        
        List<Category> categorieslist = new ArrayList<>();

        try (Connection conn = objcon.establishConnection();                
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                
                Category category = new Category();
                
                category.setCodeCategory(rs.getString(1));
                category.setNameCategory(rs.getString(2));
                
                categorieslist.add(category);
            }

        } catch (SQLException e) {
            System.err.println("ERROR : " + e.toString());
        }

        return categorieslist;
    }
}
