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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Item> getListOfItems() {
        return this.listOfItems;
    }

    public void setId(List<Item> listOfItems) {
        this.listOfItems = listOfItems;
    }

    public int getLength() {
        return this.maxLength;
    }

    public int getQuantity() {
        int quantity = 0;
        for (Item item : listOfItems) {
            quantity += item.getQuantity();
        }
        return quantity;
    }

    public double getWeight() {
        double weight = 0;
        for (Item item : listOfItems) {
            weight += item.getQuantity() * item.getWeight();
        }
        return weight;
    }

    public void addItem(Item item) {
        listOfItems.add(item);
        maxLength = Math.max(maxLength, item.getLength());
    }

    public int getAllowedQuantity(Item item, int itemsQuantity, double itemsWeight) {
        int quantity = 0;
        double weight = 0;
        quantity = Math.min(itemsQuantity - this.getQuantity(), item.getQuantity());
        weight = Math.min(itemsWeight - this.getQuantity(), item.getQuantity() * item.getWeight());
        quantity = Math.min(quantity, (int) (weight / item.getWeight()));
        return quantity;
    }

    public String toString() {
        String output = "Pack Number: " + this.id + "\n";
        for (Item item : listOfItems) {
            output += item.toString();
        }
        output += "Pack Length: " + this.getLength() + ", Pack Weight: " + (double) Math.round(this.getWeight() * 100) / 100 + "\n";
        return output;
    }
}
