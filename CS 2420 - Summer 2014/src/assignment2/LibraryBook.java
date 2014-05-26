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

    public LibraryBook(long _isbn, String _author, String _title, String holder, GregorianCalendar dueDate) {
        super(_isbn, _author, _title);
        this.holder = holder;
        this.dueDate = dueDate;

        // does super(...); make these redundant?
//        this.isbn = _isbn;
//        this.author = _author;
//        this.title = _title;
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
     * Two books are considered equal if they have the same ISBN, author, and
     * title.
     *
     * @param other --
     *              the object begin compared with "this"
     * @return true if "other" is a Book and is equal to "this", false otherwise
     */
    public boolean equals(Object other) {
        // verify that the given object is a Book
        if (!(other instanceof LibraryBook))
            return false;

        // cast object to Book type to remove casting from rest of code
        LibraryBook inputBook = (LibraryBook) other;

        // compare every field in the Book to the current object
        if (!inputBook.getAuthor().equals(author))
            return false;
        else if (inputBook.getIsbn() != isbn)
            return false;
        else if (!inputBook.getTitle().equals(title))
            return false;

        // only once all fields have been verified return true
        return true;
    }

    /**
     * Returns a string representation of the book.
     */
    public String toString() {
        return isbn + ", " + author + ", \"" + title + "\"";
    }
}
