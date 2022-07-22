package validators;

import model.Product;

public class ProductQuantityValidator implements Validator<Product> {
    /**
     * Method that validates a given product depending on its stock
     * If the stock of the product is negative, it is not valid
     * @param product
     * @throws IllegalAccessException if the product stock is not validated
     */
    @Override
    public void validate(Product product) throws IllegalAccessException {
        if (product.getCurrentStock() < 0) {
            throw new IllegalAccessException("Invalid product quantity.");
        }
    }
}
