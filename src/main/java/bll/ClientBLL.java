package bll;

import dao.ClientDAO;
import model.Client;
import validators.ClientAgeValidator;
import validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private ClientDAO clientDAO;
    private List<Validator<Client>> clientValidators;

    public ClientBLL() {
        clientDAO = new ClientDAO();
        clientValidators = new ArrayList<>();
        clientValidators.add(new ClientAgeValidator());
    }

    /**
     * This method searches the database for a client whose ID has been specified
     * The method calls the method that finds an Object by ID from DAO
     * @param clientID the ID of the client that we're looking for
     * @return an instance of the class Client, or null in the case when the client hasn't been found
     */
    public Client findClientByID(int clientID) {
        Client client = clientDAO.findById(clientID);

        if (client == null) {
            throw new NoSuchElementException("Error! The client with ID " + clientID + " could not be found.");
        }

        return client;
    }

    /**
     * This method creates a list of all the existing clients from the database
     * In the case in which there are no clients, the method can throw  NoSuchElementException exception
     * @return a list of all the existing clients
     */
    public List<Client> findAllClients() {
        List<Client> clients = clientDAO.findAll();

        if (clients == null) {
            throw new NoSuchElementException("Error! There are no clients in the database.");
        }

        return clients;
    }

    /**
     * This method inserts a new client into the database if the attributes of the client are validated
     * If the validators are not respected, then the method throws an IllegalAccessException exception
     * @param client the client that is going to be inserted into the database
     * @throws IllegalAccessException
     */
    public void insertClient(Client client) throws IllegalAccessException {
        for (Validator<Client> clientValidator : clientValidators)
            clientValidator.validate(client);

        clientDAO.insert(client);
    }

    /**
     * This method removes from the database a client whose ID has been specified
     * @param clientID the ID corresponding to the client that has to be deleted
     */
    public void deleteClient(int clientID) {
        clientDAO.delete(clientID);
    }

    /**
     * This method edits the fields of a client who has been specified, replacing them with new data if any changes occur
     * @param client the client whose data has to be updated
     * @throws IllegalAccessException the method throws this exception in the case in which the new data does not follow the validator restrictions
     */
    public void editClient(Client client) throws IllegalAccessException {
        for (Validator<Client> clientValidator : clientValidators)
            clientValidator.validate(client);

        clientDAO.update(client);
    }
}
