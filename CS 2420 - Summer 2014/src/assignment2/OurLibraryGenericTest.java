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

    LibraryGeneric<String> testLibraryString;
    LibraryBookGeneric<String> book1, book2, book3, book4, book5;

    public void setUp() throws Exception {
        super.setUp();

        testLibraryString = new LibraryGeneric<String>();
        book1 = new LibraryBookGeneric<String>(1234567890123L, "first author", "first title");
        book2 = new LibraryBookGeneric<String>(1234567890354L, "second author", "second title");
        book3 = new LibraryBookGeneric<String>(1234567890687L, "third author", "third title");
        book4 = new LibraryBookGeneric<String>(1234567890500L, "fourth author", "fourth title");
        book5 = new LibraryBookGeneric<String>(1234567890501L, "fifth author", "fifth title");
    }

    public void tearDown() throws Exception {
        testLibraryString = null;
    }

    public void testGetInventoryList() throws Exception {
        ArrayList<LibraryBookGeneric<String>> stringInventoryList = testLibraryString.getInventoryList();
        System.out.println(stringInventoryList.get(0));
//        assertEquals(stringInventoryList, "baklk");
    }

    public void testGetOrderedByAuthor() throws Exception {

    }

    public void testGetOverdueList() throws Exception {

    }
}