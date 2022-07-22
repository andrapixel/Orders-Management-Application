package presentation;

import bll.ClientBLL;
import model.Client;
import start.Main;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientsController {
    protected static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private ClientsView clientsView;
    private ClientBLL clientBll;
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String contact;
    private String address;

    public ClientsController(ClientsView clientsView) {
        this.clientsView = clientsView;
        this.clientBll = new ClientBLL();
    }

    /**
     * Method that stores the value of the client ID retrieved from the corresponding textfield in id
     */
    private void extractClientID() {
        this.id = clientsView.getClientIDAsInt();
    }

    /**
     * Method that extracts the data about the client from the GUI textfields and stores it
     */
    private void extractClientData() {
        this.firstName = clientsView.getFirstNameTxt().getText();
        this.lastName = clientsView.getLastNameTxt().getText();
        this.age = clientsView.getAgeAsInt();
        this.gender = clientsView.getGenderTxt();
        this.contact = clientsView.getContactTxt().getText();
        this.address = clientsView.getAddressTxt().getText();
    }

    /**
     * Method that creates the action listener of the addCLient Button
     * This method calls the insertClient method of ClientBll
     * @param addClientBtn
     */
    public void createAddButtonActionListener(JButton addClientBtn) {
        addClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extractClientData();
                try {
                    clientBll.insertClient(new Client(firstName, lastName, age, gender, contact, address));
                    System.out.println("A new client has been added.");
                }
                catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * Method that creates the action listener of the editClient Button
     * This method calls the editClient method of ClientBll
     * @param editClientBtn
     */
    public void createEditButtonActionListener(JButton editClientBtn) {
        editClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extractClientID();
                extractClientData();
                try {
                    clientBll.editClient(new Client(id, firstName, lastName, age, gender, contact, address));
                    System.out.println("Client with id = " + id + " has been updated.");
                }
                catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    /**
     * Method that creates the action listener corresponding to the deleteClient button
     * This method calls the delete method of ClientBll
     * @param deleteClientBtn
     */
    public void createDeleteButtonActionListener(JButton deleteClientBtn) {
        deleteClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extractClientID();
                try {
                    clientBll.deleteClient(id);
                    System.out.println("Client with id = " + id + " has been removed.");
                }
                catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                }
            }
        });
    }

    /**
     * Method that creates the action listener for the button that displays all the clients from the database in a JTable
     * @param viewBtn
     */
    public void createViewClientsButtonActionListener(JButton viewBtn) {
        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientsView.getClientsTable().setVisible(true);
                DefaultTableModel tableModel = new DefaultTableModel();
                configureClientsTable(clientsView.getClientsTable(), tableModel);
            }
        });
    }

    /**
     * Method that configures the columns and rows of the JTable in which the clients will be displayed
     * @param clientsTable  the table in which the clients will be displayed
     * @param tableModel the table model the table is constructed after
     */
    public void configureClientsTable(JTable clientsTable, DefaultTableModel tableModel) {
        String[] columnNames = {"Client ID", "First Name", "Last Name", "Age", "Gender", "Contact", "Address"};

        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }

        addClientToTable(tableModel);
        clientsTable.setModel(tableModel);
    }

    /**
     * Method that adds a new row in the JTable of clients
     * @param tableModel the model the table is constructed after
     */
    public void addClientToTable(DefaultTableModel tableModel) {
        List<Client> allClients = null;

        try {
            allClients = clientBll.findAllClients();
        }
        catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }

        for (Client client : allClients) {
            //ReflectionTechnique.retrieveProperties(client);
            tableModel.addRow(new Object[]{String.valueOf(client.getClientID()), client.getFirstName(), client.getLastName(),
            String.valueOf(client.getAge()), client.getGender(), client.getContact(), client.getAddress()});
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
                clientsView.dispose();
            }
        });
    }
}
