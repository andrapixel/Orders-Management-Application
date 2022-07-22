package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;
    }

    /**
     * Method that creates an action listener for the button that opens the Clients Manager Window
     * @param clientsBtn
     */
    void createClientsButtonActionListener(JButton clientsBtn) {
        clientsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClientsView();
                mainView.dispose();
            }
        });
    }

    /**
     * Method that creates an action listener for the button that opens the Products Manager Window
     * @param productsBtn
     */
    void createProductsButtonActionListener(JButton productsBtn) {
        productsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductsView();
                mainView.dispose();
            }
        });
    }

    /**
     * Method that creates an action listener for the button that opens the Orders Manager Window
     * @param ordersBtn
     */
    void createOrdersButtonActionListener(JButton ordersBtn) {
        ordersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrdersView();
                mainView.dispose();
            }
        });
    }

    /**
     * Method that creates an action listener for the button that exits the application
     * @param exitBtn
     */
    void createExitButtonActionListener(JButton exitBtn) {
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
