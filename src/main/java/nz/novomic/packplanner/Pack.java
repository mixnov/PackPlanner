package nz.novomic.packplanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Pack of Items
 *
 * @author Mikhail
 */
public class Pack {

    public static int maxId;
    private int id;
    private List<Item> listOfItems;
    private int maxLength;

    public Pack() {
        maxId++;
        id = maxId;
        maxLength = 0;
        listOfItems = new ArrayList<Item>();
    }

    public int getMaxLength() {
        return maxLength;
    }

    /**
     *
     * @return Total quantity of the Items in the Pack
     */
    public int getQuantity() {
        int quantity = 0;
        for (Item item : listOfItems) {
            quantity += item.getQuantity();
        }
        return quantity;
    }

    /**
     *
     * @return Total weight of the Items in the Pack
     */
    public double getWeight() {
        double weight = 0;
        for (Item item : listOfItems) {
            weight += item.getQuantity() * item.getWeight();
        }
        return weight;
    }

    /**
     * Add the Item into the Pack and set the maximum length of Items in the
     * Pack
     *
     * @param item The Item has to be added to the Pack
     */
    public void addItem(Item item) {
        listOfItems.add(item);
        maxLength = Math.max(maxLength, item.getLength());
    }

    /**
     * Calculates the maximum quantity of the Items with current parameters
     * possible to add into the Pack to not exceed the Pack params
     *
     * @param item The Item object to add into the Pack
     * @param itemsQuantity The maximum quantity of Items in the Pack
     * @param itemsWeight The maximum weight of Items in the Pack
     * @return The quantity of the Items could be added into the Pack
     */
    public int getAllowedQuantity(Item item, int itemsQuantity, double itemsWeight) {
        int quantity = 0;
        double weight = 0;
        quantity = Math.min(itemsQuantity - getQuantity(), item.getQuantity());
        weight = Math.min(itemsWeight - getWeight(), item.getQuantity() * item.getWeight());
        quantity = Math.min(quantity, (int) (weight / item.getWeight()));
        return quantity;
    }

    /**
     *
     * @return The Pack presentation string
     */
    public String toString() {
        String output = "Pack Number: " + id + "\n";
        for (Item item : listOfItems) {
            output += item.toString();
        }
        output += "Pack Length: " + getMaxLength() + ", Pack Weight: "
                + (double) Math.round(getWeight() * 100) / 100 + "\n";
        return output;
    }
}
