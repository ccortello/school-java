package homework04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Prompts user to enter filename of warehouse data and computes and prints which products do not exist in any warehouse,
 * which products still exist in every warehouse, and the single busiest day for each warehouse.
 *
 * @version 2/2/14.
 * @author Cody Cortello
 */
public class WarehouseReport {

    // initialize lists, sets, and maps to hold relevant information from the file
    private Map<Integer, String> foodNames;
    private Map<Integer, Integer> shelfLives;
    private Map<String, Inventory<Item>> warehouses;
    private GregorianCalendar date;

    public static void main(String[] args) {
        new WarehouseReport();
    }

    public WarehouseReport() {

        // initialize fields
        foodNames = new HashMap<Integer, String>();
        shelfLives = new HashMap<Integer, Integer>();
        warehouses = new HashMap<String, Inventory<Item>>();
        date = null;

        // prompt user for input
        System.out.print("Enter a filename to parse: ");
        Scanner userInput = new Scanner(System.in);

        // initialize file from specified input
//        debug, for speed
        System.out.println("");
//        File dataFile = new File("src/homework04/"+userInput.next());
        File dataFile = new File("src/homework04/data3.txt");

        // scan the file
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileScanner.hasNextLine())
            processNextLine(fileScanner.nextLine().trim());
        
/*      debug
        int warehouseNumber = 1;
        for (String warehouseName : warehouses.keySet()){
        	System.out.println("Warehouse #"+warehouseNumber+":"+warehouseName);
        	warehouseNumber++;
        }
*/

        userInput.close();
        System.out.println("Done!");
    }

    /*
     * Checks the contents of the given String to determine how to process the data
     */
    private void processNextLine(String line) {
        Scanner s = new Scanner(line);  // initialize new Scanner to parse the given String
        String firstWord = s.next(); // store first word of the string
        
        /* check the first word and process the data accordingly */

        if (firstWord.equalsIgnoreCase("End"))
            return;

        if (firstWord.equalsIgnoreCase("FoodItem")) {

            //  extract the UPC
            s.next();
            s.next();
            s.next();
            Integer upc = s.nextInt();

            //  extract the shelf life
            s.next();
            s.next();
            int shelfLife = s.nextInt();

            //  extract the food name
            s.next();
            String foodName = "";
            while (s.hasNext())
                foodName += s.next() + " ";
            foodName = foodName.trim();

            // store the food name and shelf life to the appropriate map
            foodNames.put(upc, foodName);
            shelfLives.put(upc, shelfLife);

//    		debug
//    		System.out.println(upc+"\t"+foodName+"\t"+shelfLife);
        }

        if (firstWord.equalsIgnoreCase("Warehouse")) {

            // extract warehouse name
            s.next();
            String warehouseName = "";
            while (s.hasNext())
                warehouseName += s.next() + " ";
            warehouseName = warehouseName.trim();

            // put warehouse in map of warehouses
            Inventory<Item> warehouseInventory = new Inventory<Item>();
            warehouses.put(warehouseName, warehouseInventory);
        }

        if (firstWord.equalsIgnoreCase("Start")) {

            // extract values from date String
            s.next();
            String dateString = s.next();
            Integer month = Integer.parseInt(dateString.substring(0, 2));
            Integer day = Integer.parseInt(dateString.substring(3, 5));
            Integer year = Integer.parseInt(dateString.substring(6, 10));

            // store values to 'date' variable
            date = new GregorianCalendar(year, month, day);
        }

        if (firstWord.equalsIgnoreCase("Receive")) {

        }

        if (firstWord.equalsIgnoreCase("Request")) {

        }

        if (firstWord.equalsIgnoreCase("Next")) {

        }

        if (firstWord.equalsIgnoreCase("End"))
            return;
    }
}
