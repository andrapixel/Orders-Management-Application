package model;

public class Product {
    // Attributes region
    private int productID;
    private String productName;
    private float price;
    private int currentStock;
    // end region

    // Constructor region
    public Product() {}

    public Product(int productID, String productName, float price, int currentStock) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.currentStock = currentStock;
    }

    public Product(String productName, float price, int currentStock) {
        this.productName = productName;
        this.price = price;
        this.currentStock = currentStock;
    }
    // end region

    // Getters & Setters region
    /**
     * Method that gets the ID of the product
     * @return the ID of the product
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Method that sets the ID of the product to the value of productID
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Method that gets the name of the product
     * @return the name of the product
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Method that sets the name of the product to the value of productName
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Method that gets the price of a product unit
     * @return the price of the product
     */
    public float getPrice() {
        return price;
    }

    /**
     * Method that sets the price of the product to the value of price
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Method that gets the current stock of the product
     * @return the amount of products that are in stock
     */
    public int getCurrentStock() {
        return currentStock;
    }

    /**
     * Method that sets the stock of the product to the value of currentStock
     * @param currentStock
     */
    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }
    // end region
}
