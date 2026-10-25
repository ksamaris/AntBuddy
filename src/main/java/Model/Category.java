package Model;

/**
 *
 * @author kevin
 */
public abstract class Category {
    
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
}
