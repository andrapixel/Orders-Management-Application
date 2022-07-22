package presentation;

import javax.swing.*;

public class OrdersView extends JFrame {
    private JPanel ordersPanel;
    private JComboBox clientsComboBox;
    private JComboBox productsComboBox;
    private JTextField prodQuantityTxt;
    private JButton createOrderBtn;
    private JButton backBtn;
    private JButton getBillBtn;
    private JTextField orderIdTxt;
    private JButton viewOrdersBtn;
    private JTable ordersTable;
    private OrdersController ordersController;

    public OrdersView() {
        super("Orders Manager");
        setContentPane(ordersPanel);
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        ordersController = new OrdersController(this);
        ordersTable.setVisible(false);
        ordersController.configureClientsComboBox();
        ordersController.configureProductsComboBox();
        ordersController.createAddButtonActionListener(createOrderBtn);
        ordersController.createViewOrdersButtonActionListener(viewOrdersBtn);
        ordersController.createBillButtonActionListener(getBillBtn);
        ordersController.createBackButtonActionListener(backBtn);
    }

    /**
     * Method that returns the selected client
     * @return the selected client (the client that places the order)
     */
    public String getSelectedClient() {
        return clientsComboBox.getSelectedItem().toString();
    }

    /**
     * Method that returns the selected product
     * @return the selected product (the product that is being ordered by the selected client)
     */
    public String getSelectedProduct() {
        return productsComboBox.getSelectedItem().toString();
    }

    /**
     * Method that parses the value of the order ID retrieved from the textfield to an int
     * @return the int value of the order ID
     */
    public int getOrderIDAsInt() {
        int orderID = 0;

        try {
            orderID = Integer.parseInt(orderIdTxt.getText());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return orderID;
    }

    /**
     * Method that parses the quantity of ordered products retrieved from the textfield to an int
     * @return the int value of the quantity of ordered products
     */
    public int getSelectedProductQuantity() {
        int productQuantity = 0;

        try {
            productQuantity = Integer.parseInt(prodQuantityTxt.getText());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return productQuantity;
    }

    /**
     * Method that returns an instance of the JTable in which the orders are being displayed
     * @return an instance of the JTable in which the orders are being displayed
     */
    public JTable getOrdersTable() {
        return ordersTable;
    }

    /**
     * Method that returns an instance of the clients JComboBox
     * @return an instance of the clients JComboBox
     */
    public JComboBox getClientsComboBox() {
        return clientsComboBox;
    }

    /**
     * Method that returns an instance of the products JComboBox
     * @return an instance of the products JComboBox
     */
    public JComboBox getProductsComboBox() {
        return productsComboBox;
    }
}
