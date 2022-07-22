package presentation;

import javax.swing.*;

public class ClientsView extends JFrame {
    private JPanel clientsPanel;
    private JTextField firstNameTxt;
    private JTextField lastNameTxt;
    private JTextField ageTxt;
    private JComboBox genderCombo;
    private JTextField contactTxt;
    private JTextField addressTxt;
    private JButton addClientBtn;
    private JButton editClientBtn;
    private JTextField clientIdTxt;
    private JButton removeClientBtn;
    private JTable clientsTable;
    private JButton viewClientsBtn;
    private JButton backBtn;
    private ClientsController clientsController;

    public ClientsView() {
        super("Clients Manager");
        setContentPane(clientsPanel);
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        clientsController = new ClientsController(this);
        clientsTable.setVisible(false);

        clientsController.createAddButtonActionListener(addClientBtn);
        clientsController.createEditButtonActionListener(editClientBtn);
        clientsController.createDeleteButtonActionListener(removeClientBtn);
        clientsController.createViewClientsButtonActionListener(viewClientsBtn);
        clientsController.createBackButtonActionListener(backBtn);
    }

    /**
     * Method that returns an instance of the Client ID text field
     * @return the textfield in which the clientID is entered
     */
    public JTextField getClientIdTxt() {
        return clientIdTxt;
    }

    /**
     * Method that parses the value of the client ID retrieved from the textfield to an int
     * @return the int value of the client ID
     */
    public int getClientIDAsInt() {
        int clientID = 0;

        try {
            clientID = Integer.parseInt(clientIdTxt.getText());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return clientID;
    }

    /**
     * Method that returns an instance of the JTextField in which the first name of the client is written
     * @return an instance of the JTextField in which the first name of the client is written
     */
    public JTextField getFirstNameTxt() {
        return firstNameTxt;
    }

    /**
     * Method that returns an instance of the JTextField in which the last name of the client is written
     * @return an instance of the JTextField in which the last name of the client is written
     */
    public JTextField getLastNameTxt() {
        return lastNameTxt;
    }

    /**
     * Method that returns an instance of the JTextField in which the age of the client is written
     * @return an instance of the JTextField in which the age of the client is written
     */
    public JTextField getAgeTxt() {
        return ageTxt;
    }

    /**
     * Method that parses the value of the client age retrieved from the textfield to an int
     * @return the int value of the client's age
     */
    public int getAgeAsInt() {
        int age = 0;

        try {
            age = Integer.parseInt(ageTxt.getText());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return age;
    }

    /**
     * Method that returns the selected gender from the combo box as a String
     * @return the selected gender from the combo box as a String
     */
    public String getGenderTxt() {
        return genderCombo.getSelectedItem().toString();
    }

    /**
     * Method that returns an instance of the JTextField in which the contact of the client is written
     * @return an instance of the JTextField in which the contact of the client is written
     */
    public JTextField getContactTxt() {
        return contactTxt;
    }

    /**
     * Method that returns an instance of the JTextField in which the address of the client is written
     * @return an instance of the JTextField in which the address of the client is written
     */
    public JTextField getAddressTxt() {
        return addressTxt;
    }

    /**
     * Method that returns an instance of the JTable in which the clients are being displayed
     * @return an instance of the JTable in which the clients are being displayed
     */
    public JTable getClientsTable() {
        return clientsTable;
    }
}
