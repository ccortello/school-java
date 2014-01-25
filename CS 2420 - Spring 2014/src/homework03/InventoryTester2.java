package homework03;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * A few simple tests to evaluate the functionality of the Inventory, DatedItem, and Item classes
 *
 * @author thehoule, ccortello
 * @date 1/21/14
 */

public class InventoryTester2 {

    public static void main(String[] args) {
        //Different Item creations
        Item test = new Item("Milk");
        Item test2 = new Item("Bread");
        Item test3 = new Item("Eggs");
        Item test4 = new Item("Crack");
        Item test5 = new Item("Beer");
        Item test6 = new Item("Cookies");
        Item test7 = new Item("Dogs");

        //Inventory to add to
        Inventory<Item> invent = new Inventory<Item>();

        //Different day tests for update and setting
        GregorianCalendar critical_date = new GregorianCalendar(2000, 2, 2);
        GregorianCalendar test2_critical_date = new GregorianCalendar(2003, 3, 3);
        GregorianCalendar test3_critical_date = new GregorianCalendar(2013, 4, 2);
        GregorianCalendar test4_critical_date = new GregorianCalendar(2005, 5, 2);
        GregorianCalendar test5_critical_date = new GregorianCalendar(2006, 6, 2);
        GregorianCalendar test6_critical_date = new GregorianCalendar(2007, 7, 2);
        GregorianCalendar test7_critical_date = new GregorianCalendar(2008, 8, 2);

        //Add items to inventory
        invent.addItem(test, critical_date, 8);
        invent.addItem(test2, test2_critical_date, 10);
        invent.addItem(test3, test3_critical_date, 15);
        invent.addItem(test4, test4_critical_date, 15);
        invent.addItem(test5, test5_critical_date, 15);
        invent.addItem(test6, test6_critical_date, 15);
        invent.addItem(test7, test7_critical_date, 15);


        invent.removeItem(test2, test2_critical_date, 5);

        int value = invent.getQuantity(test2, test2_critical_date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar date = invent.getDate(test3);

        System.out.println("Date: " + sdf.format(date.getTime()));
        System.out.println("Value: " + value);
        System.out.println("Finished.");
    }
}