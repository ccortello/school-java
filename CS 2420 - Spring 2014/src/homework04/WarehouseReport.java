package homework04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Prompts user to enter filename of warehouse data and computes and prints which products do not exist in any warehouse,
 * which products still exist in every warehouse, and the single busiest day for each warehouse.
 *
 * @author Cody Cortello and Nick Houle
 * @version 2/2/14.
 */
public class WarehouseReport {

    // initialize lists, sets, and maps to hold relevant information from the file
    private Map<Integer, String> foodNames;
    private Map<Integer, Integer> shelfLives;
    private Map<String, Inventory<Item>> warehouses;
    private GregorianCalendar date;
    private Map<String, Integer> transactions;

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
        String fileName = userInput.next();

        // initialize file from specified input
        File dataFile = new File("data3.txt");

        // scan the file
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // send each line of the file to be processed
        while (fileScanner.hasNextLine())
            processNextLine(fileScanner.nextLine().trim());

        // calculate and print the report, and close the file scanner
        printReport();
        userInput.close();
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
            Integer upc = Integer.parseInt(s.next());

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

            // close scanner
            s.close();
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
            warehouses.put(warehouseName, warehouseInventory);

            // close scanner
            s.close();
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

            // close scanner
            s.close();
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
            warehouses.get(warehouseName).addItem(itemToAdd, newDate, quantity);

            if (transactions.containsKey(warehouseName)) {
                int temp = (Integer) transactions.get(warehouseName);
                temp += quantity;
                transactions.remove(warehouseName);
                transactions.put(warehouseName, temp);
            } else
                transactions.put(warehouseName, quantity);

            // close scanner
            s.close();
        }

        if (firstWord.equals("Request:")) {
            // scan the relevant information
            int upc = Integer.parseInt(s.next());
            int quantity = Integer.parseInt(s.next());
            String warehouseName = "";
            while (s.hasNext()) // use concatenation and trim to construct warehouse name
                warehouseName += s.next() + " ";
            warehouseName = warehouseName.trim();
            
            /* remove the items from the warehouse */

            // establish item and inventory to remove from
            Item itemToRemove = new Item(foodNames.get(upc));
            Inventory<Item> warehouseRequested = warehouses.get(warehouseName);

            // decrease the inventory by the items from earliest expiration date to latest
            //  this is done by updating an 'itemsRemoved' counter and moving the date forward one day at a time so that the items with the earliest
            //  expiration dates get removed first
            int itemsRemoved = 0;
            GregorianCalendar removeDate = (GregorianCalendar) date.clone();
            int quantityToRemove = quantity;

            // while we still have items to remove and the inventory contains those items
            while (itemsRemoved < quantity && warehouseRequested.itemsInInventory().contains(itemToRemove)) {

                // request the quantity of the item at the current date
                int quantityOfEarliestItem = warehouseRequested.getQuantity(itemToRemove, removeDate);

                // if there is no item at the current date then advance the date and try again
                if (quantityOfEarliestItem == 0) {
                    removeDate.roll(GregorianCalendar.DAY_OF_MONTH, 1);
                    continue;
                }

                // remove the requested number of the item, update the counter, and move the date forward
                //  note: the requested amount is removed from the inventory and the items removed is increased by the original quantity of the
                //  earliest item. This updates the variables correctly when the requested amount is greater than, equal to, and less than the
                //  quantity of the earliest item.
                warehouseRequested.removeItem(itemToRemove, removeDate, quantityToRemove);
                if (transactions.containsKey(warehouseRequested)) {
                    int temp = (Integer) transactions.get(warehouseRequested);
                    temp += quantityToRemove;
                    transactions.remove(warehouseRequested);
                    transactions.put(warehouseName, temp);
                } else
                    transactions.put(warehouseName, quantityToRemove);
                itemsRemoved += quantityOfEarliestItem;
                quantityToRemove -= quantityOfEarliestItem;
                removeDate.roll(GregorianCalendar.DAY_OF_MONTH, 1);
            }

            // close scanner
            s.close();
        }

        if (firstWord.equals("Next")) {

            // advance the effective date one day
            date.add(Calendar.DAY_OF_MONTH, 1);

            // loop through the inventories and expire the old items
            for (Inventory<Item> i : warehouses.values())
                i.expireItems(i, date);

            // close scanner
            s.close();
        }

        if (firstWord.equals("End")) {
            // close scanner and return
            s.close();
            return;
        }

        // close scanner
        s.close();
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
            for (Inventory<Item> checkInv : warehouses.values()) {

                GregorianCalendar checkDate = checkInv.getDate(checkItem);
                int checkQuantity = checkInv.getQuantity(checkItem, checkDate);

                // the item isn't in the warehouse's inventory if the getDate() returns null or the item quantity is zero
                if (checkDate != null || checkQuantity != 0) {

                    // if the inventory contains the item set the unstocked flag to false and exit the warehouses loop
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
            for (Inventory<Item> checkInv : warehouses.values()) {
                GregorianCalendar checkDate = checkInv.getDate(checkItem);
                int checkQuantity = checkInv.getQuantity(checkItem, checkDate);

                // the item isn't in a warehouse then change the flag and stop looping through warehouses
                if (checkDate == null || checkQuantity == 0) {
                    inAll = false;
                    break;
                }
            }

            // add the item to the 'stocked' list if it's in all of the warehouses
            if (inAll)
                universalItems.add(checkUpc);
        }

        // print the items in universalItems
        for (int upc : universalItems)
            System.out.println(upc + " " + foodNames.get(upc));
        int mostTransactions = 0;
        String name = "a";

        // print busiest day header
        System.out.println("\nBusiest Days:");

        //Determine busiest days and print them out
        for (Object key : transactions.keySet()) {
            for (Object key_value : transactions.keySet()) {
                if ((Integer) transactions.get(key_value) > (Integer) transactions.get(key)) {
                    name = (String) key;
                    mostTransactions = (Integer) transactions.get(key_value);
                } else {
                    name = (String) key;
                    mostTransactions = (Integer) transactions.get(key);
                }
            }
        }
        System.out.println(name + " " + mostTransactions);
    }
}