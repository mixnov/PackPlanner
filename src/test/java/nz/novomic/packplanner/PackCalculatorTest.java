package nz.novomic.packplanner;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PackCalculatorTest {

    @Test
    public void testCalculate() throws Exception {
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
}
