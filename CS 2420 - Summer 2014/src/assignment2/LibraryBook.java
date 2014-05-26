package assignment2;

import java.util.GregorianCalendar;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * <p/>
 * Note that ISBNs are unique.
 */
public class LibraryBook extends Book {

    private long isbn;
    private String author;
    private String title;
    private String holder;
    private GregorianCalendar dueDate;

    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title);

        // fields automatically initialized to null, but the statements here are added for clarity
        holder = null;
        dueDate = null;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * @return the ISBN
     */
    public long getIsbn() {
        return this.isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @return the due date
     */
    public GregorianCalendar getDueDate() {
        return this.dueDate;
    }

    /**
     * Returns a string representation of the book.
     */
    public String toString() {
        return isbn + ", " + author + ", \"" + title + "\"";
    }

    public void checkIn() {

    }

    public void checkOut() {

    }

    /**
     * @return
     */
    public String getHolder() {

    }
}
