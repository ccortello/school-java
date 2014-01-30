package homework03;

import java.util.GregorianCalendar;

/**
 * A simple container class to store Item objects
 *
 * @author Nick Houle, Cody Cortello
 * @version January 21, 2014
 */
public class DatedItem<I> {

    private I item;
    private int quantity;
    private GregorianCalendar exp_date;

    public DatedItem(I item, int quantity, GregorianCalendar exp_date) {

        // Store parameters to fields
        this.item=item;
        this.quantity=quantity;
        this.exp_date=exp_date;
    }

    /* Simple accessor methods to return field values */

    public I getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public GregorianCalendar getDate() {
        return exp_date;
    }

    public void setExp_date(GregorianCalendar date) {
        exp_date = date;
    }

    /* Mutator method */

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }
}