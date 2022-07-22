package validators;

import model.Client;

public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 14;

    /**
     * Method that validates a given client depending on their age
     * If a client is younger that 14 y.o., they will not be able to place an order
     * @param client
     * @throws IllegalAccessException if the age of the client is not validated
     */
    public void validate(Client client) throws IllegalAccessException {
        if (client.getAge() < MIN_AGE) {
            throw new IllegalAccessException("The client age limit is not respected!");
        }
    }
}
