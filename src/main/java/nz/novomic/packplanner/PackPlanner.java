package nz.novomic.packplanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The pack planner is simply a program that takes a list of items and sorts
 * them into several packs (groups).
 *
 * @author Mikhail Novozhilov
 */
public class PackPlanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);
        String s;
//        System.out.print("Please enter paks' data according the format:\n" +
//            "[Sort order],[max pieces per pack],[max weight per pack].\n" +
//            "Sort order: 1 - NATURAL, 2 - SHORT_TO_LONG, 3 - LONG_TO_SHORT.\n" + 
//            "--> ");
//        s = in.next();
//        System.out.println(s);
        s = "NATURAL,40,500.0";
        System.out.println(java.util.Arrays.toString(s.split("\\,")));
        String[] params = s.split("\\,");
        SortType sortType = SortType.valueOf(params[0]);
        int itemsQuantity = Integer.parseInt(params[1]);
        float itemsWeight = Float.parseFloat(params[2]);
        System.out.println("sortType = " + sortType + "\n"
                + "itemsQuantity = " + itemsQuantity + "\n"
                + "itemsWeight = " + itemsWeight);
//        System.out.print("Please enter items data according the format:\n" +
//            "[item id],[item length],[item quantity],[piece weight].\n" +
//            "Empty row - end of the list.\n" + 
//            "--> ");
//        s = br.readLine();
//        while (s.length() > 0) {
//            System.out.println("'" + s + "' " + s.length());
//            System.out.print("--> ");
//            s = br.readLine();
//        }
//        System.out.println(s);

        List<Item> listOfItems = new ArrayList<Item>();
        listOfItems.add(new Item(1001, 6200, 30, 9.653));
        //listOfItems.add(new Item(2001, 8200, 20, 5.21));
        listOfItems.add(new Item(2001, 7200, 50, 11.21));

        PackCalculator packCalculator = new PackCalculator(sortType, listOfItems);
        List<Pack> result = packCalculator.calculate(itemsQuantity, itemsWeight);

        for (Pack pack : result) {
            System.out.println(pack.toString());
        }

//        packCalculator.toString();
    }

}
