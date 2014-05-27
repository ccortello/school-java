package assignment2;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.UUID;

/**
 * Testing class for Library.
 */
public class LibraryTest {

    public static void main(String[] args) {
        // test an empty library
        Library lib = new Library();

        if (lib.lookup(978037429279L) != null)
            System.err.println("TEST FAILED -- empty library: lookup(isbn)");
        ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
        if (booksCheckedOut == null || booksCheckedOut.size() != 0)
            System.err.println("TEST FAILED -- empty library: lookup(holder)");
        if (lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008))
            System.err.println("TEST FAILED -- empty library: checkout");
        if (lib.checkin(978037429279L))
            System.err.println("TEST FAILED -- empty library: checkin(isbn)");
        if (lib.checkin("Jane Doe"))
            System.err.println("TEST FAILED -- empty library: checkin(holder)");

        // test a small library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        if (lib.lookup(9780330351690L) != null)
            System.err.println("TEST FAILED -- small library: lookup(isbn)");
        if (!lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008))
            System.err.println("TEST FAILED -- small library: checkout");
        booksCheckedOut = lib.lookup("Jane Doe");
        if (booksCheckedOut == null
                || booksCheckedOut.size() != 1
                || !booksCheckedOut.get(0).equals(
                new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
                || !booksCheckedOut.get(0).getHolder().equals("Jane Doe")
                || !booksCheckedOut.get(0).getDueDate().equals(
                new GregorianCalendar(2008, 1, 1)))
            System.err.println("TEST FAILED -- small library: lookup(holder)");
        if (!lib.checkin(9780330351690L))
            System.err.println("TEST FAILED -- small library: checkin(isbn)");
        if (lib.checkin("Jane Doe"))
            System.err.println("TEST FAILED -- small library: checkin(holder)");

        // test a medium library
        lib.addAll("C:\\Users\\Cody\\Java\\school-java\\CS 2420 - Summer 2014\\src\\assignment2\\Mushroom_Publishing.txt");

        // test a large library
        Library testLibrary = new Library();
        int numberOfBooks = 10000;
        ArrayList<Long> isbnList = new ArrayList<Long>(numberOfBooks);
        ArrayList<String> holderList = new ArrayList<String>(numberOfBooks);
//        ArrayList<String> authorList = new ArrayList<String>(numberOfBooks);
//        ArrayList<String> titleList = new ArrayList<String>(numberOfBooks);
        for (int i = 0; i < numberOfBooks; i++) {
            long isbn = generateIsbn();
            String author = UUID.randomUUID().toString() + "Author #" + i;
            String title = UUID.randomUUID().toString() + "Title #" + i;
            String holder = UUID.randomUUID().toString() + " Holder #" + i;
            testLibrary.add(isbn, author, title);
            testLibrary.checkout(isbn, holder, 0, 0, 0);
            isbnList.add(isbn);
            holderList.add(holder);
//            authorList.add(author);
//            titleList.add(title);
        }
        int numberOfLookups = 1000;
        for (int i = 0; i < numberOfLookups; i++) {
            int randIndex = (int) (Math.random() * numberOfBooks);
            long isbnTest = isbnList.get(randIndex);
//            System.out.println("Holder #" + i + "\t" + testLibrary.lookup(isbnTest));
            if (!testLibrary.lookup(isbnTest).equals(holderList.get(randIndex)))
                System.out.println("Holder lookup failed");
        }

        System.out.println("Testing done.");
    }

    /**
     * Returns a library of "dummy" books (random ISBN and placeholders for author
     * and title).
     * <p/>
     * Useful for collecting running times for operations on libraries of varying
     * size.
     *
     * @param size --
     *             size of the library to be generated
     */
    public static ArrayList<LibraryBook> generateLibrary(int size) {
        ArrayList<LibraryBook> result = new ArrayList<LibraryBook>();

        for (int i = 0; i < size; i++) {
            // generate random ISBN
            Random randomNumGen = new Random();
            String isbn = "";
            for (int j = 0; j < 13; j++)
                isbn += randomNumGen.nextInt(10);

            result.add(new LibraryBook(Long.parseLong(isbn), "An Author", "A Title"));
        }

        return result;
    }

    /**
     * Returns a randomly-generated ISBN (a long with 13 digits).
     * <p/>
     * Useful for collecting running times for operations on libraries of varying
     * size.
     */
    public static long generateIsbn() {
        Random randomNumGen = new Random();

        String isbn = "";
        for (int j = 0; j < 13; j++)
            isbn += randomNumGen.nextInt(10);

        return Long.parseLong(isbn);
    }
}
