package validators;

import model.Product;

public class ProductPriceValidator implements Validator<Product> {
    /**
     * Method that validates a given product depending on its price
     * If the price of the product is negative or 0, it is not valid
     * @param product
     * @throws IllegalAccessException if the product price is not validated
     */
    @Override
    public void validate(Product product) throws IllegalAccessException {
        if (product.getPrice() <= 0) {
            throw new IllegalAccessException("Invalid product price.");
        }
    }
}
