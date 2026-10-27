package Service;

import Model.Product;
import Persistence.ProductRepository;
import java.util.List;

/**
 *
 * @author kevin
 */
public class ProductService {
    
    ProductRepository productrepository = new ProductRepository();
    
    /**
     * List the data returned in the getProducts method of ProductRepository.
     * 
     * @return Returns an object that stores the data returned from the getProducts method of ProductRepository.
     */
    public List<Product> listProductsTable(){
        return productrepository.getProducts();
    }
    
    /**
     * Validates whether the product has been registered with all fields filled in correctly so that they can be saved.
     * 
     * @param p the saved product.
     * @return  A message describing the result.
     */
    public String validateProduct(Product p){
        if(p.getGTIN().trim().isEmpty() || p.getName().trim().isEmpty()){
            return "The GTIN and the product name are required";
        }
        
        if(p.getPrice() <= 0){
            return "The product's price must be greater than 0";
        }
        
        if(p.getCategory().trim().isEmpty()){
            return "You must select a category";
        }
        
        if(p.getStock()< 0 || p.getStockMin() < 0 ){
            return "Stock levels cannot be negative";
        }
        
        return null;
    }
    
    public String saveProduct(Product p){
        String error = validateProduct(p);
        
        if (error != null) {
            return error;
        }
        
        if(productrepository.insertProduct(p)){
            return "Product successfully saved";
        }
        
        return "The product could not be saved";
    }
    
    /**
     * Check the validity of a product's new data and update it if all the rules are met.
     * 
     * @param p the product with the new information.
     * @return A message describing the result.
     */
    public String updateProduct(Product p){
        String error = validateProduct(p);
        
        if (error != null) {
            return error;
        }
        
        if(productrepository.updateProduct(p)){
            return "Product successfully updated";
        }
        
        return "The product could not be updated";
    }
    
    /**
    * Deletes the product with the given GTIN.
     * 
    * @param gtin the GTIN of the product to delete.
    * @return A message describing the result.
    */
    public String deleteProduct(String gtin){
        if(gtin == null || gtin.trim().isEmpty()){
            return "The GTIN is required to delete a product";
        }
    
        if(productrepository.deleteProduct(gtin)){
            return "Product successfully deleted";
        }
    
        return "The product could not be deleted";
    }
}
