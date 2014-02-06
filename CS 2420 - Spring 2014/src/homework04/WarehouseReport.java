package homework04;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private Map<String, Inventory<Item>> warehouseInventories;
    private Map<Inventory<Item>, String> warehouseNames;
    private GregorianCalendar date;

    //  debug
    private int requestNumber = 0;
    private int receiveNumber = 0;
    private int nextNumber = 0;
    private int warehouseNumber = 0;
//    

    public static void main(String[] args) {
        new WarehouseReport();
    }

    public WarehouseReport() {

        // initialize fields
        foodNames = new HashMap<Integer, String>();
        shelfLives = new HashMap<Integer, Integer>();
        warehouseInventories = new HashMap<String, Inventory<Item>>();
        date = null;

//        debug
        warehouseNames = new HashMap<Inventory<Item>, String>();

        // prompt user for input
        System.out.print("Enter a filename to parse: ");
        Scanner userInput = new Scanner(System.in);

        // initialize file from specified input
//        debug, for speed
        System.out.println("");
//        File dataFile = new File("userInput.next());
        File dataFile = new File("src/homework04/data4.txt");

        // scan the file
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileScanner.hasNextLine())
            processNextLine(fileScanner.nextLine().trim());

//      debug
//        int warehouseNumber = 1;
//        for (String warehouseName : warehouses.keySet()){
//        	System.out.println("Warehouse #"+warehouseNumber+":"+warehouseName);
//        	warehouseNumber++;
//        }

        printReport();

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

        if (firstWord.equals("FoodItem")) {

            //  extract the upc
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

        if (firstWord.equals("Warehouse")) {

            // extract warehouse name
            s.next();
            String warehouseName = "";
            while (s.hasNext())
                warehouseName += s.next() + " ";
            warehouseName = warehouseName.trim();

            // put warehouse in map of warehouses
            Inventory<Item> warehouseInventory = new Inventory<Item>();
            warehouseInventories.put(warehouseName, warehouseInventory);

//        	debug
            warehouseNumber++;
            warehouseNames.put(warehouseInventory, warehouseName);
            System.out.println("Warehouse #" + warehouseNumber + ": " + warehouseName);
        }

        if (firstWord.equals("Start")) {

            // extract values from date String
            s.next();
            String dateString = s.next();
            Integer month = Integer.parseInt(dateString.substring(0, 2));
            Integer day = Integer.parseInt(dateString.substring(3, 5));
            Integer year = Integer.parseInt(dateString.substring(6, 10));

            // store values to 'date' variable
            date = new GregorianCalendar(year, month - 1, day);

//            debug
//            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
//            System.out.println(df.format(date.getTime()));
//            
        }

        if (firstWord.equals("Receive:")) {
            // scan the relevant information
            int upc = Integer.parseInt(s.next());
            int quantity = Integer.parseInt(s.next());
            String warehouseName = "";
            while (s.hasNext())
                warehouseName += s.next() + " ";
            warehouseName = warehouseName.trim();

            // add the items to the warehouse
            Item itemToAdd = new Item(foodNames.get(upc));
            GregorianCalendar newDate = (GregorianCalendar) date.clone();

            newDate.roll(GregorianCalendar.DAY_OF_MONTH, shelfLives.get(upc));

//          debug
//            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
//            System.out.println("date: "+df.format(date.getTime()));
//            System.out.println("newDate: "+df.format(newDate.getTime()));
//          
            warehouseInventories.get(warehouseName).addItem(itemToAdd, newDate, quantity);
        }

        if (firstWord.equals("Request:")) {
            // scan the relevant information
            int upc = Integer.parseInt(s.next());
            int quantity = Integer.parseInt(s.next());
            String warehouseName = "";
            while (s.hasNext())
                warehouseName += s.next() + " ";
            warehouseName = warehouseName.trim();

            // remove the items from the warehouse
            Item itemToRemove = new Item(foodNames.get(upc));
            GregorianCalendar newDate = (GregorianCalendar) date.clone();
            warehouseInventories.get(warehouseName).removeItem(itemToRemove, newDate, quantity);
        }

        if (firstWord.equals("Next")) {
//        	debug
            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            System.out.println(df.format(date.getTime()));
//            

            //Advance the effective date one day
            date.add(Calendar.DAY_OF_MONTH, 1);

            // loop through the inventories and expire the old items
            for (Inventory<Item> i : warehouseInventories.values())
                i.expireItems(i, date);
        }

        if (firstWord.equals("End")) {

//        	debug
            System.out.println("\nAnd the final tally is:");
            for (Inventory<Item> inv : warehouseInventories.values()) {
                if (warehouseNames.get(inv) != null)
                    inv.outputToConsole(warehouseNames.get(inv));
                warehouseNumber++;
            }
//        	
            return;
        }
    }

    /*
     * Calculate the products which don't exist in any warehouse, which products still exist in
     * every warehouse, and the single busiest day for each warehouse, and prints the information
     * with a simple header.
     */
    private void printReport() {

        // print header
        System.out.println("Report by Cody Cortello and Nick Houle\n");

        // initialize lists for items not stocked by any warehouse and items stocked by every warehouse
        ArrayList<Integer> unstockedItems = new ArrayList<Integer>();
        ArrayList<Integer> universalItems = new ArrayList<Integer>();

		/* Find unstocked items */

        // print first line
        System.out.println("Unstocked Products:");

        // loop through the items in the upcs list
        for (int checkUpc : foodNames.keySet()) {
            Item checkItem = new Item(foodNames.get(checkUpc));

            // assume that an inventory has the item
            boolean unstocked = true;

            // check if the item is in any warehouse's inventory
            for (Inventory<Item> checkInv : warehouseInventories.values()) {

                GregorianCalendar checkDate = checkInv.getDate(checkItem);
                int checkQuantity = checkInv.getQuantity(checkItem, checkDate);

                // the item isn't in the warehouse's inventory if the getDate() returns null or the item quantity is zero
                if (checkDate != null || checkQuantity != 0) {

                    // if the inventory contains the item set the unstocked flag to false and exit the warehouses loop
//					System.out.println("Break 1 reached!");
                    unstocked = false;
                    break;
                }
            }

            // add the item to the 'unstocked' list if this statement is reached
            if (unstocked)
                unstockedItems.add(checkUpc);
        }

        // print the items in unstockedItems
        for (int upc : unstockedItems)
            System.out.println(upc + " " + foodNames.get(upc));
        System.out.print("\n");
		
		/* Find items stocked by all warehouses */

        // print first line
        System.out.println("Fully-Stocked Products:");

        // loop through the items in the upcs list
        for (int checkUpc : foodNames.keySet()) {
            Item checkItem = new Item(foodNames.get(checkUpc));

            // assume item is in all inventories
            boolean inAll = true;

            // check if the item is in any warehouse's inventory
            for (Inventory<Item> checkInv : warehouseInventories.values()) {
                GregorianCalendar checkDate = checkInv.getDate(checkItem);
                int checkQuantity = checkInv.getQuantity(checkItem, checkDate);

                // the item isn't in a warehouse then change the flag and stop looping through warehouses
                if (checkDate == null || checkQuantity == 0) {
//					System.out.println("Break 2 reached! "+checkItem.name);
                    inAll = false;
                    break;
                }
            }

            // add the item to the 'stocked' list if this statement is reached
            if (inAll)
                universalItems.add(checkUpc);
        }

        // print the items in universalItems
        for (int upc : universalItems)
            System.out.println(upc + " " + foodNames.get(upc));
    }
}
