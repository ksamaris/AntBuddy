/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kevin
 */
public class Product {
    
    private String GTIN;
    private String Name;
    private double price;
    private String Category;
    private int Stock;
    private int StockMin;
    

    /**
     * Default constructor.
     */
    public Product() {
    }

    public Product(String GTIN, String Name, double price, String Category, int Stock, int StockMin) {
        this.GTIN = GTIN;
        this.Name = Name;
        this.price = price;
        this.Category = Category;
        this.Stock = Stock;
        this.StockMin = StockMin;
    }

    // --- Getters and Setters --

    public String getGTIN() {
        return GTIN;
    }

    public void setGTIN(String GTIN) {
        this.GTIN = GTIN;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public int getStockMin() {
        return StockMin;
    }

    public void setStockMin(int StockMin) {
        this.StockMin = StockMin;
    }
    
    public boolean isLowStock() {
        return Stock <= StockMin;
    }
    
    /**
    * Checks if the product's GTIN, name or category contains the searched text.
    *
    * @param text the searched text, in lowercase. If it is empty, the product always matches.
    * @return true if the product matches the search.
    */
    public boolean matches(String text){
        return GTIN.toLowerCase().contains(text)
                || Name.toLowerCase().contains(text)
                || Category.toLowerCase().contains(text);
    }
}