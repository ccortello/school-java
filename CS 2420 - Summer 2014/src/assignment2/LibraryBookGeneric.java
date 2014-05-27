package assignment2;

import java.util.GregorianCalendar;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * <p/>
 * Note that ISBNs are unique.
 */
public class LibraryBookGeneric<Type> extends Book {

    private Type holder;
    private GregorianCalendar dueDate;

    public LibraryBookGeneric(long isbn, String author, String title) {
        super(isbn, author, title);
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

    public void checkIn() {
        // remove 'holder' and 'dueDate'
        this.holder = null;
        this.dueDate = null;
    }

    /**
     * Checks out a book,
     *
     * @param holder
     * @param dueDate
     */
    public void checkOut(Type holder, GregorianCalendar dueDate) {
        // set 'holder' and 'dueDate'
        this.holder = holder;
        this.dueDate = dueDate;
    }
}
