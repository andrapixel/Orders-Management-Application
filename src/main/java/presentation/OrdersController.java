package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.ComboBoxItem;
import model.Order;
import model.Product;
import start.Main;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrdersController {
    protected static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private OrdersView ordersView;
    private OrderBLL orderBll;
    private ProductBLL productBLL;
    private int orderID;
    private String clientName;
    private String productName;
    private int productQuantity;
    private float total;
    private File billFile;

    public OrdersController(OrdersView ordersView) {
        this.ordersView = ordersView;
        this.orderBll = new OrderBLL();
        this.productBLL = new ProductBLL();
    }

    /**
     * Method that extracts the order ID from the corresponding GUI textfield and stores it in orderID
     */
    private void extractOrderID() {
        this.orderID = ordersView.getOrderIDAsInt();
    }

    /**
     * Method that retrieves data about the order from the GUI components and stores it
     */
    private void extractOrderData() {
        this.clientName = ordersView.getSelectedClient();
        this.productName = ordersView.getSelectedProduct();
        this.productQuantity = ordersView.getSelectedProductQuantity();
    }

    /**
     * Method that computes the total price of the placed order
     */
    private void computeOrderTotal() {
        this.total = this.productQuantity * getSelectedProduct().getPrice();
    }

    /**
     * Method that retrieves the ordered product
     * @return the selected product to order
     */
    private Product getSelectedProduct() {
        Product product = null;
        ProductBLL productBLL = new ProductBLL();

        try {
            product = productBLL.findProductByName(this.productName);
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }

        return product;
    }

    /**
     * Method that creates the action listener of the button that creates a new order
     * This method calls a method of productBLL that checks whether the order can be placed (if the amount of products that the client wants
     * order are in stock)
     * If the order can be placed, a new order is created and the stock of products is updated, otherwise a warning understock messages is displayed
     * @param addOrderBtn
     */
    public void createAddButtonActionListener(JButton addOrderBtn) {
        addOrderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extractOrderData();
                computeOrderTotal();
                try {
                    if (productBLL.thereAreEnoughProducts(getSelectedProduct().getProductID(), productQuantity)) {
                        orderBll.insertOrder(new Order(clientName, productName, productQuantity, total));
                        productBLL.updateProductStock(getSelectedProduct().getProductID(), productQuantity);
                        System.out.println("A new order has been created.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "The selected product cannot be purchased currently.", "Understock!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * Method that created an action listener for the button that displays all the products of the database
     * @param viewBtn
     */
    public void createViewOrdersButtonActionListener(JButton viewBtn) {
        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordersView.getOrdersTable().setVisible(true);
                DefaultTableModel tableModel = new DefaultTableModel();
                configureOrdersTable(ordersView.getOrdersTable(), tableModel);
            }
        });
    }

    /**
     * Method that configures the columns and rows of the JTable in which the orders will be displayed
     * @param ordersTable the table in which the orders will be displayed
     * @param tableModel the table model the table is constructed after
     */
    public void configureOrdersTable(JTable ordersTable, DefaultTableModel tableModel) {
        String[] columnNames = {"Order ID", "Client Name", "Product Name", "Product Quantity", "Total Price"};

        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }

        addOrderToTable(tableModel);
        ordersTable.setModel(tableModel);
    }

    /**
     * Method that adds a new row in the JTable of orders
     * @param tableModel the model the table is constructed after
     */
    public void addOrderToTable(DefaultTableModel tableModel) {
        List<Order> allOrders= null;
        try {
            allOrders = orderBll.findAllOrders();
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }

        for (Order order : allOrders) {
            tableModel.addRow(new Object[]{String.valueOf(order.getOrderID()), order.getClientName(),
                    order.getProductName(), String.valueOf(order.getProductQuantity()), String.valueOf(order.getTotal())});
        }
    }

    /**
     * Method that creates an action listener for the button that generates the bill of given order
     * The order whose bill is generated is selected by ID
     * @param billButton
     */
    public void createBillButtonActionListener(JButton billButton) {
        billButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderBLL orderBLL = new OrderBLL();
                Order order = null;
                extractOrderID();

                try {
                    order = orderBLL.findOrderByID(orderID);
                }
                catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }

                generateOrderBill(order);
                System.out.println("Order bill was generated successfully.");
            }
        });
    }

    /**
     * Method that generates the bill of a given order
     * The method created a text file and calls a method that writes the contents of the bill in it
     * @param order the order whose bill is generated
     */
    private void generateOrderBill(Order order) {
       billFile = new File(getBillPath(order));
       if (billFile.exists()) {
           billFile.delete();
           billFile = new File(getBillPath(order));
       }

        writeBillTxt(billFile, order);
    }

    /**
     * Method that creates a custom path for the bill of a given order
     * @param order
     * @return the path at which the text file containing the bill can be found
     */
    private String getBillPath(Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("./bills/Bill_Order#");
        stringBuilder.append(order.getOrderID());
        stringBuilder.append(".txt");

        return stringBuilder.toString();
    }

    /**
     * Method that writes the bill contents of an order in a given file
     * @param billFile the file in which the contents are written using a FileWriter
     * @param order the order whose bill is being written
     */
    private void writeBillTxt(File billFile, Order order) {
        try {
            FileWriter writingBuffer = new FileWriter(billFile, true);

            writingBuffer.write("Order #" + order.getOrderID() + " Bill\n\n\n");
            writingBuffer.write("Client: " + order.getClientName() + "\n");
            writingBuffer.write("Ordered Product: " + order.getProductName() + " . . . . . x" + order.getProductQuantity() + " units\n");
            float pricePerUnit = order.getTotal() / order.getProductQuantity();
            writingBuffer.write("Price per Unit: " + pricePerUnit + " RON\n");
            writingBuffer.write("-------------------------------------------------------\n");
            writingBuffer.write("TOTAL: " + order.getTotal() + " RON\n");
            writingBuffer.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
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
                ordersView.dispose();
            }
        });
    }

    /**
     * Method that sets up the labels of the clients ComboBox
     */
    public void configureClientsComboBox() {
        ClientBLL clientBll = new ClientBLL();
        List<Client> allClients = null;

        try {
            allClients = clientBll.findAllClients();
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }

        for (Client client : allClients) {
            System.out.println(client.getClientFullName());
            ordersView.getClientsComboBox().addItem(new ComboBoxItem(client.getClientID(), client.getClientFullName()));
        }
    }

    /**
     * Method that sets up the labels of the products ComboBox
     */
    public void configureProductsComboBox() {
        ProductBLL productBll = new ProductBLL();
        List<Product> allProducts = null;

        try {
            allProducts = productBll.findAllProducts();
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }

        for (Product product : allProducts) {
            System.out.println(product.getProductName());
            ordersView.getProductsComboBox().addItem(new ComboBoxItem(product.getProductID(), product.getProductName()));
        }
    }
}
