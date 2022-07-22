package presentation;

import bll.ProductBLL;
import model.Product;
import start.Main;
import start.ReflectionTechnique;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductsController {
    protected static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private ProductsView productsView;
    private ProductBLL productBll;
    private int id;
    private String name;
    private float price;
    private int stock;

    public ProductsController(ProductsView productsView) {
        this.productsView = productsView;
        this.productBll = new ProductBLL();
    }

    /**
     * Method that stores the value of the product ID retrieved from the corresponding textfield in id
     */
    private void extractProductID() {
        this.id = productsView.getProductIDAsInt();
    }

    /**
     * Method that extracts the data about the product from the GUI textfields and stores it
     */
    private void extractProductData() {
        this.name = productsView.getProductNameTxt().getText();
        this.price = productsView.getProductPriceAsFloat();
        this.stock = productsView.getProductQuantityAsInt();
    }

    /**
     * Method that creates the action listener of the addProduct Button
     * This method calls the insertProduct method of ProductBll
     * @param addProductBtn
     */
    public void createAddButtonActionListener(JButton addProductBtn) {
        addProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extractProductData();
                try {
                    productBll.insertProduct(new Product(name, price, stock));
                    System.out.println("A new product has been added.");
                }
                catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * Method that creates the action listener of the editProduct Button
     * This method calls the editProduct method of ProductBll
     * @param editProductBtn
     */
    public void createEditButtonActionListener(JButton editProductBtn) {
        editProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extractProductID();
                extractProductData();
                try {
                    productBll.editProduct(new Product(id, name, price, stock));
                    System.out.println("Product with id = " + id + " has been updated.");
                }
                catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * Method that creates the action listener corresponding to the deleteProduct button
     * This method calls the delete method of ProductBll
     * @param deleteProductBtn
     */
    public void createDeleteButtonActionListener(JButton deleteProductBtn) {
        deleteProductBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extractProductID();
                try {
                    productBll.deleteProduct(id);
                    System.out.println("Product with id = " + id + " has been removed.");
                }
                catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }
        });
    }

    /**
     * Method that creates the action listener for the button that displays all the products from the database in a JTable
     * @param viewBtn
     */
    public void createViewProductsButtonActionListener(JButton viewBtn) {
        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productsView.getProductsTable().setVisible(true);
                DefaultTableModel tableModel = new DefaultTableModel();
                configureProductsTable(productsView.getProductsTable(), tableModel);
            }
        });
    }

    /**
     * Method that configures the columns and rows of the JTable in which the products will be displayed
     * @param productsTable the table in which the prodcuts will be displayed
     * @param tableModel the table model the table is constructed after
     */
    public void configureProductsTable(JTable productsTable, DefaultTableModel tableModel) {
        String[] columnNames = {"Product ID", "Product Name", "Price", "Current Stock"};

        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }

        addProductToTable(tableModel);
        productsTable.setModel(tableModel);
    }

    /**
     * Method that adds a new row in the JTable of products
     * @param tableModel the model the table is constructed after
     */
    public void addProductToTable(DefaultTableModel tableModel) {
        List<Product> allProducts = null;
        try {
            allProducts = productBll.findAllProducts();
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }

        for (Product product : allProducts) {
            ReflectionTechnique.retrieveProperties(product);
            tableModel.addRow(new Object[]{String.valueOf(product.getProductID()), product.getProductName(),
            String.valueOf(product.getPrice()), String.valueOf(product.getCurrentStock())});
        }
    }

    /**
     * Method that creates an action listener for the button that takes the user back to the main window of the application
     * @param backButton
     */
    public void createBackButtonActionListener(JButton backButton) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainView();
                productsView.dispose();
            }
        });
    }
}
