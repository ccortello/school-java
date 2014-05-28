package assignment2;

import java.util.GregorianCalendar;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * <p/>
 * Note that ISBNs are unique.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 1.0
 */
public class LibraryBookGeneric<Type> extends Book {

    private Type holder;
    private GregorianCalendar dueDate;

    /**
     * sole constructor for the LibraryBookGeneric class, uses the book constructor
     * to assign ISBN, author, and title, and also requires holder & dueDate be set
     * to null until they are checked out.
     */
    public LibraryBookGeneric(long isbn, String author, String title) {
        super(isbn, author, title);
        holder = null;
        dueDate = null;
    }

    /**
     * @return the due date
     */
    public GregorianCalendar getDueDate() {
        return this.dueDate;
    }

    /**
     * @return the holder
     */
    public Type getHolder() {
        return this.holder;
    }

    /**
     * Sets the due date
     */
    public void setDueDate(int month, int day, int year) {
        this.dueDate = new GregorianCalendar(year, month, day);
    }

    /**
     * Sets the holder
     */
    public void setHolder(Type newHolder) {
        this.holder = newHolder;
    }

    /**
     * Sets the holder & dueDate fields to null to checkin the book.
     */
    public void checkIn() {
        // remove 'holder' and 'dueDate'
        this.holder = null;
        this.dueDate = null;
    }

    /**
     * Checks out a book,
     *
     * @param holder  defined by <Type> determines who has the book checkout.
     * @param dueDate date the book must be returned to the library.
     */
    public void checkOut(Type holder, GregorianCalendar dueDate) {
        // set 'holder' and 'dueDate'
        this.holder = holder;
        this.dueDate = dueDate;
    }
}
