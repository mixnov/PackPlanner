package nz.novomic.packplanner;

import java.util.ArrayList;
import java.util.List;

public class Pack {

    private static int maxId = 0;
    private int id;
    private List<Item> listOfItems;
    private int maxLength;

    public Pack() {
        maxId++;
        this.id = maxId;
        this.maxLength = 0;
        listOfItems = new ArrayList<Item>();
    }

    private int getMaxLength() {
        return this.maxLength;
    }

    private int getQuantity() {
        int quantity = 0;
        for (Item item : listOfItems) {
            quantity += item.getQuantity();
        }
        return quantity;
    }

    private double getWeight() {
        double weight = 0;
        for (Item item : listOfItems) {
            weight += item.getQuantity() * item.getWeight();
        }
        return weight;
    }

    void addItem(Item item) {
        listOfItems.add(item);
        this.maxLength = Math.max(this.maxLength, item.getLength());
    }

    int getAllowedQuantity(Item item, int itemsQuantity, double itemsWeight) {
        int quantity = 0;
        double weight = 0;
        quantity = Math.min(itemsQuantity - this.getQuantity(), item.getQuantity());
        weight = Math.min(itemsWeight - this.getWeight(), item.getQuantity() * item.getWeight());
        quantity = Math.min(quantity, (int) (weight / item.getWeight()));
        return quantity;
    }

    public String toString() {
        String output = "Pack Number: " + this.id + "\n";
        for (Item item : listOfItems) {
            output += item.toString();
        }
        output += "Pack Length: " + this.getMaxLength() + ", Pack Weight: "
                + (double) Math.round(this.getWeight() * 100) / 100 + "\n";
        return output;
    }
}
