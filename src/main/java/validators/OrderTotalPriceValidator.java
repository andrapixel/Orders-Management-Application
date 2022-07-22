package validators;

import model.Order;

public class OrderTotalPriceValidator implements Validator<Order> {
    /**
     * Method that validates a given order depending on its total price
     * If the total is a negative value or 0, then it means that the order cannot be created
     * @param order
     * @throws IllegalAccessException if the total price of the order is not validated
     */
    @Override
    public void validate(Order order) throws IllegalAccessException {
        if (order.getTotal() <= 0) {
            throw new IllegalAccessException("Invalid order total price.");
        }
    }
}
