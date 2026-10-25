package Service;

import Model.Category;
import Persistence.CategoryRepository;
import java.util.List;

/**
 *
 * @author kevin
 */
public class CategoryService {
    
    CategoryRepository categoriesrepository = new CategoryRepository();
    
    /**
     * List the data returned in the getCategories method of CategoryRepository.
     * 
     * @return Returns an object that stores the data returned from the getProducts method of ProductRepository.
     */
    public List<Category> listProductsTable(){
        return categoriesrepository.getCategories();
    }
}
