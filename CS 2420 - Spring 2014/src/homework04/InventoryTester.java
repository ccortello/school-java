package homework04;

import java.util.GregorianCalendar;

/**
 * A few simple tests to evaluate the functionality of the Inventory, DatedItem, and Item classes
 *
 * @author Nick Houle, Cody Cortello
 * @version January 21, 2014
 */

public class InventoryTester {

    public static void main(String[] args) {

        // Print header message
        System.out.println("CS 2420 Homework03\nCody Cortello + Nick Houle\n");

        //Different Item creations
        System.out.println("Testing Item creation");
        Item test = new Item("Milk");
        Item test2 = new Item("Bread");
        Item test3 = new Item("Eggs");

        //Inventory to add to
        System.out.println("\nTesting Inventory creation");
        Inventory<Item> inventory = new Inventory<Item>();

        //Different day tests for update and setting
        System.out.println("\nCreating dates for items");
        GregorianCalendar critical_date = new GregorianCalendar(2, 2, 2);
        GregorianCalendar test2_critical_date = new GregorianCalendar(2, 2, 3);
        GregorianCalendar test3_critical_date = new GregorianCalendar(2, 2, 4);

        //Add items to inventory
        System.out.println("\nAdding items to Inventory");
        inventory.addItem(test, critical_date, 8);
        inventory.addItem(test2, test2_critical_date, 10);
        inventory.addItem(test3, test3_critical_date, 15);

        inventory.expireItems(inventory, test2_critical_date);
        
/*
        //Update first item with different date and quantity
        System.out.println("\nTesting item removal under normal circumstances");
        inventory.removeItem(test2, test2_critical_date, 5);

        //Test for correct updated value
        int value = inventory.getQuantity(test2, test2_critical_date);
        System.out.println("Updated value: " + value);

        //Date testing
        SimpleDateFormat dfmt = new SimpleDateFormat("EEE, MMM d, yyyy");
        System.out.println("\ngetDate and getQuantity tests:");

        System.out.println("Date of 'test' Item = "+dfmt.format(inventory.getDate(test).getTime()));
        System.out.println("Date of 'test2' Item = "+dfmt.format(inventory.getDate(test2).getTime()));
        System.out.println("Date of 'test3' Item = "+dfmt.format(inventory.getDate(test3).getTime()));

        System.out.println("Quantity of 'test' Item = "+inventory.getQuantity(test, inventory.getDate(test)));
        System.out.println("Quantity of 'test2' Item = "+inventory.getQuantity(test2, inventory.getDate(test2)));
        System.out.println("Quantity of 'test3' Item = "+inventory.getQuantity(test3, inventory.getDate(test3)));

        System.out.println("\nRemoving 10 of 'test3'");
        inventory.removeItem(test3, inventory.getDate(test3), 10);

        System.out.println("\nNew quantity of 'test3' = "+inventory.getQuantity(test3, inventory.getDate(test3)));

        System.out.println("\nRemoving another 10 of 'test3'");
        inventory.removeItem(test3, inventory.getDate(test3), 10);

        System.out.println("\nQuantity of 'test3' Item = " + inventory.getQuantity(test3, inventory.getDate(test3)));

        System.out.println("\ngetItemsPastDate test");
        System.out.println("Creating new inventory with ten items");

        Inventory newInventory = new Inventory();
        Item testItem1 = new Item("Milk");
        Item testItem2 = new Item("Bread");
        Item testItem3 = new Item("Eggs");
        Item testItem4 = new Item("Pasta");
        Item testItem5 = new Item("Cheese");
        Item testItem6 = new Item("Cookies");
        Item testItem7 = new Item("Bananas");

        GregorianCalendar date1 = new GregorianCalendar(2000, 2, 2);
        GregorianCalendar date2 = new GregorianCalendar(2003, 3, 3);
        GregorianCalendar date3 = new GregorianCalendar(2013, 4, 2);
        GregorianCalendar date4 = new GregorianCalendar(2005, 5, 2);
        GregorianCalendar date5 = new GregorianCalendar(2006, 6, 2);
        GregorianCalendar date6 = new GregorianCalendar(2007, 7, 2);
        GregorianCalendar date7 = new GregorianCalendar(2008, 8, 2);

        //Add items to inventory
        newInventory.addItem(testItem1, date1, 8);
        newInventory.addItem(testItem2, date2, 10);
        newInventory.addItem(testItem3, date3, 15);
        newInventory.addItem(testItem4, date4, 15);
        newInventory.addItem(testItem5, date5, 15);
        newInventory.addItem(testItem6, date6, 15);
        newInventory.addItem(testItem7, date7, 15);

        GregorianCalendar testExpiration = new GregorianCalendar(2004, 9, 21);

        System.out.println("Number of items older than " + dfmt.format(testExpiration.getTime()) + " (should return five items)");

        // create array of items past the testExpiration date
        ArrayList<Item> itemsPastDate = new ArrayList<Item>();
        itemsPastDate.addAll(newInventory.getItemsPastDate(testExpiration));

        System.out.println(itemsPastDate.size());

        System.out.println("\nThose items are:");
        for (Item itemIndex : itemsPastDate)
            System.out.println(itemIndex.getName());
*/
    }
}