package presentation;

import javax.swing.*;

public class ProductsView extends JFrame {
    private JPanel productsPanel;
    private JButton removeProductBtn;
    private JTextField productIdTxt;
    private JTextField productNameTxt;
    private JTextField productPriceTxt;
    private JTextField productStockTxt;
    private JButton addProductBtn;
    private JButton editProductBtn;
    private JButton viewAllProductsBtn;
    private JTable productsTable;
    private JButton backBtn;
    private ProductsController productsController;

    public ProductsView() {
        super("Products Manager");
        setContentPane(productsPanel);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        productsController = new ProductsController(this);
        productsTable.setVisible(false);

        productsController.createAddButtonActionListener(addProductBtn);
        productsController.createEditButtonActionListener(editProductBtn);
        productsController.createDeleteButtonActionListener(removeProductBtn);
        productsController.createViewProductsButtonActionListener(viewAllProductsBtn);
        productsController.createBackButtonActionListener(backBtn);
    }

    /**
     * Method that parses the value of the product ID retrieved from the textfield to an int
     * @return the int value of the product ID
     */
    public int getProductIDAsInt() {
        int productID = 0;

        try {
            productID = Integer.parseInt(productIdTxt.getText());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return productID;
    }

    /**
     * Method that returns an instance of the JTextField in which the name of the product is written
     * @return an instance of the JTextField in which the name of the product is written
     */
    public JTextField getProductNameTxt() {
        return productNameTxt;
    }

    /**
     * Method that parses the value of the product price retrieved from the textfield to a float
     * @return the float value of the product's price
     */
    public float getProductPriceAsFloat() {
        float price = 0.0f;

        try {
            price = Float.parseFloat(productPriceTxt.getText());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return price;
    }

    /**
     * Method that parses the value of the ordered product quantity retrieved from the textfield to an int
     * @return the int value of the ordered product's quantity
     */
    public int getProductQuantityAsInt() {
        int quantity = 0;

        try {
            quantity = Integer.parseInt(productStockTxt.getText());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return quantity;
    }

    /**
     * Method that returns an instance of the JTable in which the products are being displayed
     * @return an instance of the JTable in which the products are being displayed
     */
    public JTable getProductsTable() {
        return productsTable;
    }
}
