package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    // Attributes region
    private int orderID;
    private String clientName;
    private String productName;
    private int productQuantity;
    private float total;
    // end region

    // Constructor region
    public Order() {}

    public Order(int orderID, String clientName, String productName, int productQuantity, float total) {
        this.orderID = orderID;
        this.clientName = clientName;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.total = total;
    }

    public Order(String clientName, String productName, int productQuantity, float total) {
        this.clientName = clientName;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.total = total;
    }
    // end region

    // Getters & Setters region
    /**
     * Method that gets the ID of the order
     * @return the ID of the order
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Method that sets the ID of the order to the value stored in orderID
     * @param orderID
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Method that gets the name of the client who placed the order
     * @return name of the client
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Method that sets the name of the client who placed the order to clientName
     * @param clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Method that gets the name of the ordered product
     * @return the name of the ordered product
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Method that sets the name of the ordered product to the value in productName
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Method that gets the quantity of products the client wants to order
     * @return the quantity of ordered products
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * Method that sets the value of ordered products to productQuantity
     * @param productQuantity
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * Method that gets the total price of the order
     * @return the total of the order
     */
    public float getTotal() {
        return total;
    }

    /**
     * Method that sets the total price of the order to the value stored in total
     * @param total
     */
    public void setTotal(float total) {
        this.total = total;
    }
    // end region
}
