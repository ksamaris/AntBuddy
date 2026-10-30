package Model;

/**
 *
 * @author kevin
 */
public class Category {
    
    private String CodeCategory;
    private String NameCategory;

    /**
     * Default constructor.
     */
    public Category() {
    }

    public Category(String CodeCategory, String NameCategory) {
        this.CodeCategory = CodeCategory;
        this.NameCategory = NameCategory;
    }
    
    // --- Getters and Setters --

    public String getCodeCategory() {
        return CodeCategory;
    }

    public void setCodeCategory(String CodeCategory) {
        this.CodeCategory = CodeCategory;
    }

    public String getNameCategory() {
        return NameCategory;
    }

    public void setNameCategory(String NameCategory) {
        this.NameCategory = NameCategory;
    }
    
    /**
    * Checks if the product's category code or category name contains the searched text.
    *
    * @param text the searched text, in lowercase. If it is empty, the category always matches.
    * @return true if the category matches the search.
    */
    public boolean matches(String text){
        return CodeCategory.toLowerCase().contains(text)
                || NameCategory.toLowerCase().contains(text);
    }
}
