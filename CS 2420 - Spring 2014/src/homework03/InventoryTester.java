package homework03;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * A few simple tests to evaluate the functionality of the Inventory, DatedItem, and Item classes
 *
 * @author	thehoule, ccortello
 * @version	1/21/14
 */

public class InventoryTester {

    public static void main(String[] args) {

        // Print header message
        System.out.println("CS 2420 Homework03\nCody Cortello + Nick Houle\n");

        //Different Item creations
        Item test = new Item("Milk");
        Item test2 = new Item("Bread");
        Item test3 = new Item("Eggs");
        
        //Inventory to add to
        Inventory<Item> inventory = new Inventory<Item>();
        
        //Different day tests for update and setting
        GregorianCalendar critical_date = new GregorianCalendar(2,2,2);
        GregorianCalendar test2_critical_date = new GregorianCalendar(3,3,3);
        GregorianCalendar test3_critical_date = new GregorianCalendar(4,4,4);
        
        //Add items to inventory
        inventory.addItem(test, critical_date, 8);
        inventory.addItem(test2, test2_critical_date, 10);
        inventory.addItem(test3, test3_critical_date, 15);
        
        //Update first item with different date and quantity
        inventory.removeItem(test2, test2_critical_date, 5);
        
//        invent.addItem(test, test2_critical_date, 12);
        int value = inventory.getQuantity(test2, test2_critical_date);
        System.out.println("Value: " + value);
        System.out.println("Finished.");
        
/*		Starting my debug code		*/
        System.out.println("-------------------------------------\nBegin ccortello debug\n-------------------------------------");
        
        SimpleDateFormat dfmt = new SimpleDateFormat("EEE, MMM d, ''yy");

        System.out.println("getDate and getQuantity tests:");

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

        System.out.println("Quantity of 'test3' Item = "+inventory.getQuantity(test3, inventory.getDate(test3)));
    }
}