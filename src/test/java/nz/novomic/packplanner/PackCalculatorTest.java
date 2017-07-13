package nz.novomic.packplanner;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;

public class PackCalculatorTest {

    @Test
    public void testCalculateDefault() throws Exception {
        List<Item> listOfItems = new ArrayList<Item>();
        listOfItems.add(new Item(1001, 6200, 30, 9.653));
        listOfItems.add(new Item(2001, 7200, 50, 11.21));

        SortType sortType = SortType.NATURAL;
        int itemsQuantity = 40;
        double itemsWeight = 500.0;

        PackCalculator packCalculator = new PackCalculator(sortType, listOfItems);
        List<Pack> result = packCalculator.calculate(itemsQuantity, itemsWeight);

        assertEquals(2, result.size());

        assertEquals("Pack Number: 1\n1001,6200,30,9.653\n2001,7200,10,11.21\n"
                + "Pack Length: 7200, Pack Weight: 401.69\n", result.get(0).toString());

        assertEquals("Pack Number: 2\n2001,7200,40,11.21\nPack Length: 7200, "
                + "Pack Weight: 448.4\n", result.get(1).toString());
    }

//    @Ignore
    @Test
    public void testCalculateNewDateAndSortUp() throws Exception {
        List<Item> listOfItems = new ArrayList<Item>();
        listOfItems.add(new Item(1001, 6200, 30, 9.653));
        listOfItems.add(new Item(2001, 8300, 40, 15.325));
        listOfItems.add(new Item(3001, 7200, 50, 11.21));

        SortType sortType = SortType.SHORT_TO_LONG;
        int itemsQuantity = 40;
        double itemsWeight = 500.0;

        PackCalculator packCalculator = new PackCalculator(sortType, listOfItems);
        List<Pack> result = packCalculator.calculate(itemsQuantity, itemsWeight);

        assertEquals(4, result.size());

        assertEquals("Pack Number: 1\n1001,6200,30,9.653\n3001,7200,10,11.21\n"
                + "Pack Length: 7200, Pack Weight: 401.69\n", result.get(0).toString());

        assertEquals("Pack Number: 2\n3001,7200,40,11.21\nPack Length: 7200, "
                + "Pack Weight: 448.4\n", result.get(1).toString());

        assertEquals("Pack Number: 3\n2001,8300,32,15.325\nPack Length: 8300, "
                + "Pack Weight: 490.4\n", result.get(2).toString());

        assertEquals("Pack Number: 4\n2001,8300,8,15.325\nPack Length: 8300, "
                + "Pack Weight: 122.6\n", result.get(3).toString());
    }

//    @Ignore
    @Test
    public void testCalculateNewDateAndSortDown() throws Exception {
        List<Item> listOfItems = new ArrayList<Item>();
        listOfItems.add(new Item(1001, 6200, 30, 9.653));
        listOfItems.add(new Item(2001, 8300, 40, 15.325));
        listOfItems.add(new Item(3001, 7200, 50, 11.21));

        SortType sortType = SortType.LONG_TO_SHORT;
        int itemsQuantity = 40;
        double itemsWeight = 500.0;

        PackCalculator packCalculator = new PackCalculator(sortType, listOfItems);
        List<Pack> result = packCalculator.calculate(itemsQuantity, itemsWeight);

        assertEquals(4, result.size());

        assertEquals("Pack Number: 1\n2001,8300,32,15.325\nPack Length: 8300, "
                + "Pack Weight: 490.4\n", result.get(0).toString());

        assertEquals("Pack Number: 2\n2001,8300,8,15.325\n3001,7200,32,11.21\n"
                + "Pack Length: 8300, Pack Weight: 481.32\n", result.get(1).toString());

        assertEquals("Pack Number: 3\n3001,7200,18,11.21\n1001,6200,22,9.653\n"
                + "Pack Length: 7200, Pack Weight: 414.15\n", result.get(2).toString());

        assertEquals("Pack Number: 4\n1001,6200,8,9.653\nPack Length: 6200, "
                + "Pack Weight: 77.22\n", result.get(3).toString());
    }
}
