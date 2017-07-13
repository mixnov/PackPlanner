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
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);
        String s;
        System.out.print("Please enter paks' data according the format:\n" +
            "[Sort order],[max pieces per pack],[max weight per pack].\n" +
            "Sort order: NATURAL, SHORT_TO_LONG, LONG_TO_SHORT.\n" + 
            "--> ");


        s = in.next();
        System.out.println(java.util.Arrays.toString(s.split("\\,")));
        String[] params = s.split("\\,");
        SortType sortType = SortType.valueOf(params[0]);
        int itemsQuantity = Integer.parseInt(params[1]);
        float itemsWeight = Float.parseFloat(params[2]);

        System.out.println("sortType = " + sortType + "\n" +
            "itemsQuantity = " + itemsQuantity + "\n" +
            "itemsWeight = " + itemsWeight);
        System.out.print("Please enter items data according the format:\n" +
            "[item id],[item length],[item quantity],[piece weight].\n" +
            "Empty row - end of the list.\n" + 
            "--> ");
        s = br.readLine();
        List<Item> listOfItems = new ArrayList<Item>();
        while (s.length() > 0) {
            params = s.split("\\,");
            listOfItems.add(new Item(Integer.parseInt(params[0]),
                Integer.parseInt(params[1]), Integer.parseInt(params[2]),
                Double.parseDouble(params[3])));
            System.out.println("'" + s + "' " + s.length());
            System.out.print("--> ");
            s = br.readLine();
        }

        
        PackCalculator packCalculator = new PackCalculator(sortType, listOfItems);
        List<Pack> result = packCalculator.calculate(itemsQuantity, itemsWeight);

        for (Pack pack : result) {
            System.out.println(pack.toString());
        }

    }
}
