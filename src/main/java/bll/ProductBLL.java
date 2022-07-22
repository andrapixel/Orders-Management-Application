package bll;

import dao.ProductDAO;
import model.Product;
import validators.ProductPriceValidator;
import validators.ProductQuantityValidator;
import validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductDAO productDAO;
    private List<Validator<Product>> productValidators;

    public ProductBLL() {
        productDAO = new ProductDAO();
        productValidators = new ArrayList<>();
        productValidators.add(new ProductPriceValidator());
        productValidators.add(new ProductQuantityValidator());
    }

    /**
     * This method searches the database for a product whose ID has been specified
     * The method calls the method that finds an Object by ID from DAO
     * @param productID the ID of the product that we're looking for
     * @return an instance of the class Product, or null in the case in which the product hasn't been found
     */
    public Product findProductByID(int productID) {
        Product product = productDAO.findById(productID);

        if (product == null) {
            throw new NoSuchElementException("Error! The product with ID " + productID + " could not be found.");
        }

        return product;
    }

    /**
     * This method searches the database for a product whose name has been specified
     * The method calls the method that finds an Object by name from DAO
     * @param productName the name of the product that we're looking for
     * @return an instance of the object Product, or null if the product hasn't been found
     */
    public Product findProductByName(String productName) {
        Product product = productDAO.findByName(productName);

        if (product == null) {
            throw new NoSuchElementException("Error! The product [" + productName + "] could not be found.");
        }

        return product;
    }

    /**
     * This method creates a list of all the existing products from the database
     * In the case in which there are no products, the method can throw a NoSuchElementException exception
     * @return a list of all the existing products
     */
    public List<Product> findAllProducts() throws NoSuchElementException {
        List<Product> products = productDAO.findAll();

        if (products == null) {
            throw new NoSuchElementException("Error! There are no products in the database.");
        }

        return products;
    }

    /**
     * This method inserts a new product into the database if the attributes of the product are validated
     * If the validators are not respected, then the method throws an IllegalAccessException exception
     * @param product the product that is going to be inserted into the database
     * @throws IllegalAccessException
     */
    public void insertProduct(Product product) throws IllegalAccessException {
        for (Validator<Product> productValidator : productValidators)
            productValidator.validate(product);

        productDAO.insert(product);
    }

    /**
     * This method removes from the database a product whose ID has been specified
     * @param productID the ID corresponding to the product that has to be deleted
     */
    public void deleteProduct(int productID) {
        productDAO.delete(productID);
    }

    /**
     * This method edits the fields of a product who has been specified, replacing them with new data if any changes occur
     * @param product the product whose data has to be updated
     * @throws IllegalAccessException the method throws this exception in the case in which the new data does not follow the validator restrictions
     */
    public void editProduct(Product product) throws IllegalAccessException {
        for (Validator<Product> productValidator : productValidators)
            productValidator.validate(product);

        productDAO.update(product);
    }

    /**
     * This method checks if the amount of products in stock is enough for an amount of wantedProductsQuantity products to be bought and for the order to be created
     * @param productID the ID corresponding to the product the stock checking is applied to
     * @param wantedProductsQuantity the quantity of products that a client wants to order
     * @return true if the client can order the wanted quantity, otherwise false
     */
    public boolean thereAreEnoughProducts(int productID, int wantedProductsQuantity) {
        if (productDAO.findById(productID).getCurrentStock() >= wantedProductsQuantity)
            return true;

        return false;
    }

    /**
     * This method updates the amount of products in stock after an order has been placed
     * The method calls the editProduct() method defined previously, in order to update the currentStock of the product
     * @param productID the ID of the product which has been ordered
     * @param orderedProductsAmount the amount of products that has been ordered -> this amount will be decremented from the previously existing stock
     */
    public void updateProductStock(int productID, int orderedProductsAmount) {
        Product product = productDAO.findById(productID);

        try {
            editProduct(new Product(productID, product.getProductName(), product.getPrice(), product.getCurrentStock() - orderedProductsAmount));
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
