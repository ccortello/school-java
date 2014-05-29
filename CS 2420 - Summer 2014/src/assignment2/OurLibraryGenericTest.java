package assignment2;

import junit.framework.TestCase;

import java.util.ArrayList;

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
    LibraryBookGeneric<String> book1, book2, book3, book4, book5;

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

        // add books to library
        testLibraryString.add(book1.getIsbn(), book1.getAuthor(), book1.getTitle());
        testLibraryString.add(book2.getIsbn(), book2.getAuthor(), book2.getTitle());
        testLibraryString.add(book3.getIsbn(), book3.getAuthor(), book3.getTitle());
        testLibraryString.add(book4.getIsbn(), book4.getAuthor(), book4.getTitle());
        testLibraryString.add(book5.getIsbn(), book5.getAuthor(), book5.getTitle());
    }

    public void tearDown() throws Exception {
        testLibraryString = null;
    }

    public void testGetInventoryList() throws Exception {
        // use getInventoryList() to retrieve a sorted list
        ArrayList<LibraryBookGeneric<String>> stringInventoryList = testLibraryString.getInventoryList();

        // assertEquals for each book in the sorted array against the expected sorting of the books
        assertEquals(stringInventoryList.get(0), book1);
        assertEquals(stringInventoryList.get(1), book2);
        assertEquals(stringInventoryList.get(2), book4);
        assertEquals(stringInventoryList.get(3), book5);
        assertEquals(stringInventoryList.get(4), book3);
    }

    public void testGetOrderedByAuthor() throws Exception {

    }

    public void testGetOverdueList() throws Exception {

    }
}