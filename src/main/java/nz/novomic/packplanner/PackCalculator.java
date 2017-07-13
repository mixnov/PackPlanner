package nz.novomic.packplanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Containes the methods to sort the List of Items and put the Items from the 
 * List into the Packs
 *
 * @author Mikhail
 */
public class PackCalculator {

    private final SortType sortType;
    private final List<Item> listOfItems;

    public PackCalculator(SortType sortType, List<Item> listOfItems) {
        this.sortType = sortType;
        this.listOfItems = listOfItems;
    }

    /**
     * Runs the sorting the Items into the Packs
     *
     * @param itemsQuantity The max quantity of Items in the Pack
     * @param itemsWeight The max weight of Items in the Pack
     * @return
     */
    List<Pack> calculate(int itemsQuantity, double itemsWeight) {
        sort();
        return pack(itemsQuantity, itemsWeight);
    }

    /**
     * Sort the list of the Items by their length according the sortType param
     */
    private void sort() {
        switch (sortType) {
            case NATURAL:
                break;
            case SHORT_TO_LONG:
                listOfItems.sort((o1, o2) -> o1.getLength().compareTo(o2.getLength()));
                break;
            case LONG_TO_SHORT:
                listOfItems.sort((o1, o2) -> o2.getLength().compareTo(o1.getLength()));
                break;
        }
    }

    /**
     * Sorts the Items into the Packs
     *
     * @param itemsQuantity The max quantity of Items in the Pack
     * @param itemsWeight The max weight of Items in the Pack
     * @return The List of Packs of Items
     */
    private List<Pack> pack(int itemsQuantity, double itemsWeight) {
        ArrayList<Pack> result = new ArrayList<>();
        Pack pack = new Pack();

        // Loop through the Items list
        for (Item item : listOfItems) {
            // Loop while the current Item quantity above 0
            while (item.getQuantity() > 0) {

                // Get max quantity of the Item is possible to add into the Pack
                int quantity = pack.getAllowedQuantity(item, itemsQuantity,
                        itemsWeight);

                // If quantity is greater than 0 splits the Item and adds the 
                // allowed quantity into the Pack 
                // If not adds the Pack into the result List of Paks and create 
                // a new Pack
                if (quantity > 0) {
                    pack.addItem(item.split(quantity));
                } else {
                    result.add(pack);
                    pack = new Pack();
                }
            }
        }

        // Add the last Pack into the result List of Packs
        result.add(pack);
        return result;
    }
}
