package model;

public class ComboBoxItem {
    private int key;
    private String value;

    public ComboBoxItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Method that overrides the toString method of the combobox item in order to be able to insert custom items in the combobox
     * @return the String value of the label we want to insert
     */
    @Override
    public String toString(){
        return value;
    }

    /**
     * Method that gets the key of the combobox item
     * @return the key of the item
     */
    public int getKey() {
        return key;
    }

    /**
     * Method that gets the value stored in the combobox item
     * @return String value of the item
     */
    public String getValue() {
        return value;
    }
}
