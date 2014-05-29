package assignment2;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Unit tests for the methods added to LibraryGeneric
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 1.0
 */

public class OurLibraryGenericTest extends TestCase {

    // the only fields are a test library (of String type) and books to be added to the library
    LibraryGeneric<String> testLibraryString;
    LibraryBookGeneric<String> book1, book2, book3, book4, book5, book6;

    public void setUp() throws Exception {
        super.setUp();

        // create library
        testLibraryString = new LibraryGeneric<String>();

        // substantiate Book fields
        book1 = new LibraryBookGeneric<String>(1234567890123L, "first author", "first title");
        book2 = new LibraryBookGeneric<String>(1234567890354L, "second author", "second title");
        book3 = new LibraryBookGeneric<String>(1234567890687L, "third author", "third title");
        book4 = new LibraryBookGeneric<String>(1234567890500L, "fourth author", "fourth title");
        book5 = new LibraryBookGeneric<String>(1234567890501L, "fifth author", "fifth title");
        book6 = new LibraryBookGeneric<String>(1234567890506L, "fifth author", "sixth title");

        // add books to library
        testLibraryString.add(book1.getIsbn(), book1.getAuthor(), book1.getTitle());
        testLibraryString.add(book2.getIsbn(), book2.getAuthor(), book2.getTitle());
        testLibraryString.add(book3.getIsbn(), book3.getAuthor(), book3.getTitle());
        testLibraryString.add(book4.getIsbn(), book4.getAuthor(), book4.getTitle());
        testLibraryString.add(book5.getIsbn(), book5.getAuthor(), book5.getTitle());
        testLibraryString.add(book6.getIsbn(), book6.getAuthor(), book6.getTitle());
    }

    public void tearDown() throws Exception {
        testLibraryString = null;
    }

    public void testGetInventoryList() throws Exception {
        // use getInventoryList() to retrieve a sorted list
        ArrayList<LibraryBookGeneric<String>> stringInventoryList = testLibraryString.getInventoryList();

        // assertEquals for each book in the sorted array against the expected sorting of the books
        assertEquals(book1, stringInventoryList.get(0));
        assertEquals(book2, stringInventoryList.get(1));
        assertEquals(book4, stringInventoryList.get(2));
        assertEquals(book5, stringInventoryList.get(3));
        assertEquals(book3, stringInventoryList.get(4));
    }

    public void testGetOrderedByAuthor() throws Exception {
        // use getOrderedByAuthor() to retrieve a sorted list
        ArrayList<LibraryBookGeneric<String>> stringInventoryList = testLibraryString.getOrderedByAuthor();

        // assertEquals for each book in the sorted array against the expected sorting of the books
        assertEquals(book5, stringInventoryList.get(0));
        assertEquals(book1, stringInventoryList.get(1));
        assertEquals(book4, stringInventoryList.get(2));
        assertEquals(book2, stringInventoryList.get(3));
        assertEquals(book3, stringInventoryList.get(4));
    }

    public void testGetOverdueList() throws Exception {
        // checkout each book to set a due date
        book1.checkOut("first holder", new GregorianCalendar(2014, 5, 25));
        book2.checkOut("second holder", new GregorianCalendar(2014, 5, 26));
        book3.checkOut("third holder", new GregorianCalendar(2014, 5, 27));
        book4.checkOut("fourth holder", new GregorianCalendar(2014, 5, 28));
        book5.checkOut("fifth holder", new GregorianCalendar(2014, 5, 29));

        // use getOverdueList() to retrieve a sorted list
        ArrayList<LibraryBookGeneric<String>> stringInventoryList = testLibraryString.getOverdueList(5, 26, 2014);

        // assertEquals for each book in the sorted array against the expected sorting of the books
        assertEquals(book5, stringInventoryList.get(0));
        assertEquals(book1, stringInventoryList.get(1));
        assertEquals(book4, stringInventoryList.get(2));
        assertEquals(book2, stringInventoryList.get(3));
        assertEquals(book3, stringInventoryList.get(4));
    }
}