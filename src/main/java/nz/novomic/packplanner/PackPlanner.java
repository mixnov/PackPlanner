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
        // Make BufferedReader object to read the info from console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);
        String s;
        System.out.print("Please enter paks' data according the format:\n"
                + "[Sort order],[max pieces per pack],[max weight per pack].\n"
                + "Sort order: NATURAL, SHORT_TO_LONG, LONG_TO_SHORT.\n"
                + "--> ");

        s = in.next();
//        System.out.println(java.util.Arrays.toString(s.split("\\,")));
        String[] params = s.split("\\,");
        if (params.length < 3) {
            System.out.println("The number of parameters is less then required!\n"
                    + "Please, make sure you folow the instruction.\n");
            System.exit(1);
        }
        SortType sortType = SortType.NATURAL;
        int itemsQuantity = 0;
        float itemsWeight = 0;
        try {
            // Get the sort param and params of the pack
            sortType = SortType.valueOf(params[0]);
            itemsQuantity = Integer.parseInt(params[1]);
            itemsWeight = Float.parseFloat(params[2]);
        } catch (NumberFormatException e) {
            System.out.println("Something wrong with the numbers you entered!\n"
                    + "Please, make sure you folow the instruction.\n");
            System.exit(1);

        } catch (IllegalArgumentException e) {
            System.out.println("The sort order you entered is not from the list!\n"
                    + "Please, make sure you folow the instruction.\n");
            System.exit(1);

        }

        System.out.println("sortType = " + sortType + "\n"
                + "itemsQuantity = " + itemsQuantity + "\n"
                + "itemsWeight = " + itemsWeight);
        System.out.print("Please enter items data according the format:\n"
                + "[item id],[item length],[item quantity],[piece weight].\n"
                + "Empty row - end of the list.\n"
                + "--> ");

        // Get the items list
        s = br.readLine();
        List<Item> listOfItems = new ArrayList<Item>();
        while (s.length() > 0) {
            params = s.split("\\,");
            if (params.length < 4) {
                System.out.println("The number of parameters is less then required!\n"
                        + "Please, make sure you folow the instruction.\n");
                System.exit(1);
            }

            try {
                listOfItems.add(new Item(Integer.parseInt(params[0]),
                        Integer.parseInt(params[1]), Integer.parseInt(params[2]),
                        Double.parseDouble(params[3])));
            } catch (NumberFormatException e) {
                System.out.println("Something wrong with the numbers you entered!\n"
                        + "Please, make sure you folow the instruction.\n");
                System.exit(1);
            }
            System.out.println("'" + s + "' " + s.length());
            System.out.print("--> ");
            s = br.readLine();
        }

        // Create the object of the PackCalculator class and run the calculation
        PackCalculator packCalculator = new PackCalculator(sortType, listOfItems);
        List<Pack> result = packCalculator.calculate(itemsQuantity, itemsWeight);

        // Show the result.
        for (Pack pack : result) {
            System.out.println(pack.toString());
        }
    }
}
