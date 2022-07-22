package presentation;

import javax.swing.*;

public class MainView extends JFrame {
    private JPanel mainPanel;
    private JButton clientsBtn;
    private JButton productsBtn;
    private JButton ordersBtn;
    private JButton exitBtn;
    private MainController mainController;

    public MainView() {
        super("Warehouse Menu");
        setContentPane(mainPanel);
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        mainController = new MainController(this);
        mainController.createClientsButtonActionListener(clientsBtn);
        mainController.createProductsButtonActionListener(productsBtn);
        mainController.createOrdersButtonActionListener(ordersBtn);
        mainController.createExitButtonActionListener(exitBtn);
    }
}
