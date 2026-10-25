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
}
