package validators;

import model.Order;

public class OrderedProductsQuantityValidator implements Validator<Order> {
    /**
     * Method that validates a given order depending on the ordered products quantity
     * If the ordered product quantity is <= 0, then it means that the order cannot be created
     * @param order
     * @throws IllegalAccessException if the number of ordered products is not validated
     */
    @Override
    public void validate(Order order) throws IllegalAccessException {
        if (order.getProductQuantity() <= 0) {
            throw new IllegalAccessException("Invalid quantity of ordered products.");
        }
    }
}
