package homework03;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * A list of Items to be used in a hypothetical inventory
 *
 * @author Nick Houle, Cody Cortello
 * @version January 21, 2014
 */
public class Inventory<I extends Item> {

    private ArrayList<DatedItem> datedItemsList;
    private boolean update = false; //flag to determine list add conditions

    // A simple constructor which initializes the list of items in the inventory
    public Inventory() {
        datedItemsList = new ArrayList<DatedItem>();
    }

    public void addItem(I item, GregorianCalendar criticalDate, int quantity){

    	// Test for invalid items
        if ((item == null) || (criticalDate == null) || (quantity < 0))
            return;
        
        //Size tests
        if (datedItemsList.size() > 0) {
            for(DatedItem itemIndex : datedItemsList)
                if (itemIndex.getItem().hashCode() == item.hashCode()) { //hash comparison for same object
                    itemIndex.setQuantity(itemIndex.getQuantity() + quantity); //Add new quantity
                    itemIndex.setExp_date(itemIndex.getDate()); //Set new date
                    update = true; //Bool flag that prevent double adding to list
                }

            if (!update) {
                datedItemsList.add(new DatedItem(item, quantity, criticalDate)); //Add item to list
                update = false; //update flag
            }
            else
                update = false;
        }
        if (datedItemsList.size() == 0)
            datedItemsList.add(new DatedItem(item, quantity, criticalDate)); //Add if we're at the start of the list
    }

    public void removeItem(I item, GregorianCalendar criticalDate, int quantity){
        //Null check
        if ((item == null) || (criticalDate == null) || (quantity <= 0))
            return;

        for (DatedItem itemIndex : datedItemsList) //Check for correct item
            if ((itemIndex.getItem().hashCode() == item.hashCode()) && (itemIndex.getDate() == criticalDate)) { //hash comparison for same object and date
                if (itemIndex.getQuantity() <= quantity) { //check the quantity of items available so the quantity is never negative
                    datedItemsList.remove(itemIndex); // if too many items are removed simply delete the item entry
                    return;
                }
                itemIndex.setQuantity(itemIndex.getQuantity() - quantity); //Remove quantity
            }
    }

    public int getQuantity(I item, GregorianCalendar criticalDate){
        int quantity = 0;
        
        //Null check
        if((item == null) || (criticalDate == null))
            return -1;

        for(DatedItem itemIndex : datedItemsList) //Check for correct item
            if ((itemIndex.getItem().hashCode() == item.hashCode()) && (itemIndex.getDate() == criticalDate)) //hash comparison for same object and date
                quantity = itemIndex.getQuantity(); //Remove quantity
        return quantity; //to change
    }

    public GregorianCalendar getDate(I item){
        GregorianCalendar date = new GregorianCalendar();

        for(DatedItem itemIndex : datedItemsList) //Check for correct item
            if (itemIndex.getItem().hashCode() == item.hashCode()) //hash comparison for same object and date
                date = itemIndex.getDate();
        return date;
    }

    public ArrayList<I> getItemsPastDate(GregorianCalendar targetDate){
        ArrayList<I> itemsPastDate = new ArrayList<I>(); // initialize return list

        for (DatedItem<I> itemIndex : datedItemsList) // iterate through list
            if (targetDate.compareTo(itemIndex.getDate()) == -1) // add items if the given date is before their expiration
                itemsPastDate.add(itemIndex.getItem());

        return itemsPastDate;
    }
}