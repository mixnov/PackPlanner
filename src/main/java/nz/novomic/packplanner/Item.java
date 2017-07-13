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
        return this.length;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getWeight() {
        return this.weight;
    }

    /**
     * Presentation of the Item object
     *
     * @return The string with Item's parameters
     */
    public String toString() {
        return "" + this.id + "," + this.length + "," + this.quantity + ","
                + this.weight + "\n";
    }

    /**
     *
     * @param quantity The quantity ot the Item has to be devided out of the
     * Item into a new Object
     * @return New Object of Item class with the parameters of the original
     * object and with quantity get as parameter
     */
    Item split(int quantity) {
        this.quantity -= quantity;
        return new Item(this.id, this.length, quantity, this.weight);
    }
}
