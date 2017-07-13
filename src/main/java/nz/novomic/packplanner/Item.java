package nz.novomic.packplanner;

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

    public String toString() {
        return "" + this.id + "," + this.length + "," + this.quantity + "," 
                + this.weight + "\n";
    }

    Item split(int quantity) {
        this.quantity -= quantity;
        return new Item(this.id, this.length, quantity, this.weight);
    }
}
