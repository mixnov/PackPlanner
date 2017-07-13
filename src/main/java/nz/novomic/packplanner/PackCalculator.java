package nz.novomic.packplanner;

import java.util.ArrayList;
import java.util.List;

public class PackCalculator {

    private final SortType sortType;
    private final List<Item> listOfItems;

    public PackCalculator(SortType sortType, List<Item> listOfItems) {
        this.sortType = sortType;
        this.listOfItems = listOfItems;
    }

    List<Pack> calculate(int itemsQuantity, double itemsWeight) {
        sort();
        return pack(itemsQuantity, itemsWeight);
    }

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

    private List<Pack> pack(int itemsQuantity, double itemsWeight) {
        ArrayList<Pack> result = new ArrayList<>();
        Pack pack = new Pack();
        for (Item item : listOfItems) {
            while (item.getQuantity() > 0) {
                int quantity = pack.getAllowedQuantity(item, itemsQuantity,
                        itemsWeight);
                if (quantity > 0) {
                    pack.addItem(item.split(quantity));
                } else {
                    result.add(pack);
                    pack = new Pack();
                }
            }
        }
        result.add(pack);
        return result;
    }
}
