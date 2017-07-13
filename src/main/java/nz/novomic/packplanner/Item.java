package nz.novomic.packplanner;

/**
 * Represents an Item
 *
 * @author Mikhail
 * @date 11/07/2017
 */
public class Item {

    private final int id;
    private final Integer length;
    private int quantity;
    private final double weight;

    public Item(int id, Integer length, int quantity, double weight) {
        this.id = id;
        this.length = length;
        this.quantity = quantity;
        this.weight = weight;
    }

    public Integer getLength() {
        return length;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * Presentation of the Item object
     *
     * @return The string with Item's parameters
     */
    public String toString() {
        return "" + id + "," + length + "," + quantity + "," + weight + "\n";
    }

    /**
     * Split the Item object into two Item Objects
     *
     * @param quantity The quantity ot the Item has to be devided out of the
     * Item into a new Object
     * @return New Object of Item class with the parameters of the original
     * object and with quantity get as parameter
     */
    public Item split(int quantity) {
        this.quantity -= quantity;
        return new Item(id, length, quantity, weight);
    }
}
    