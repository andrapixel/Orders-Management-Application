package bll;

import dao.OrderDAO;
import model.Order;
import validators.OrderTotalPriceValidator;
import validators.OrderedProductsQuantityValidator;
import validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private OrderDAO orderDAO;
    private List<Validator<Order>> orderValidators;

    public OrderBLL() {
        orderDAO = new OrderDAO();
        orderValidators = new ArrayList<>();
        orderValidators.add(new OrderedProductsQuantityValidator());
        orderValidators.add(new OrderTotalPriceValidator());
    }

    /**
     * This method searches the database for an order whose ID has been specified
     * The method calls the method that finds an Object by ID from DAO
     * @param orderID the ID of the order that we're looking for
     * @return an instance of the class Order, or null in the case in which the order hasn't been found
     */
    public Order findOrderByID(int orderID) {
        Order order = orderDAO.findById(orderID);

        if (order == null) {
            throw new NoSuchElementException("Error! The order with ID " + orderID + " could not be found.");
        }

        return order;
    }

    /**
     * This method inserts a new order into the database if the attributes of the order are validated
     * If the validators are not respected, then the method throws an IllegalAccessException exception
     * @param order the order that is going to be inserted into the database
     * @throws IllegalAccessException
     */
    public void insertOrder(Order order) throws IllegalAccessException {
        for (Validator<Order> orderValidator : orderValidators)
            orderValidator.validate(order);

        orderDAO.insert(order);
    }

    /**
     * This method creates a list of all the existing orders from the database
     * In the case in which there are no orders, the method can throw a NoSuchElementException exception
     * @return a list of all the existing orders
     */
    public List<Order> findAllOrders() throws NoSuchElementException {
        List<Order> orders = orderDAO.findAll();

        if (orders == null) {
            throw new NoSuchElementException("Error! There are no orders in the database.");
        }

        return orders;
    }
}
