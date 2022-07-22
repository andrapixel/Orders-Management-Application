package model;

public class Client {
    // Attributes region
    private int clientID;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String contact;
    private String address;
    // end region

    // Constructors region
    public Client() {}

    public Client(int clientID, String firstName, String lastName, int age, String gender, String contact, String address) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
    }

    public Client(String firstName, String lastName, int age, String gender, String contact, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
    }
    // end region

    // Getters & Setters region
    /**
     * Method that retrieves the ID of the client
     * @return the ID of the client
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Method that sets the ID of the client
     * @param clientID the value the ID of the client will be set to
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Method that retrieves the first name of the client
     * @return the first name of the client
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method that sets the first name of the client to the value stored in firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Method that retrieves the last name of the client
     * @return the last name of the client
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method that sets the last name of the client to the value stored in lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Method that gets the age of the client
     * @return the age of the client
     */
    public int getAge() {
        return age;
    }

    /**
     * Method that sets the age of the client to the value stored in age
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Method that retrieves the gender of the client
     * @return the gender of the client
     */
    public String getGender() {
        return gender;
    }

    /**
     * Method that sets the gender of the client to the value in gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Method that retrieves the contact information of the client
     * @return the contact of the client
     */
    public String getContact() {
        return contact;
    }

    /**
     * Method that sets the contact info of the client to the value stored in the String contact
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Method that retrieves the address of the client
     * @return the address of the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method that sets the address of the client to the value stored in address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    // end region

    /**
     * Method that gets the full name of the client by concatenating the first and last name of the client
     * @return a String representing the full name of the client
     */
    public String getClientFullName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getFirstName() + " " + this.getLastName());
        return stringBuilder.toString();
    }
}
