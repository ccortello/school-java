package assignment2;

import junit.framework.TestCase;

public class OurLibraryGenericTest extends TestCase {

    LibraryGeneric<LibraryBookGeneric<PhoneNumber>> testLibraryPhone = new LibraryGeneric<LibraryBookGeneric<PhoneNumber>>();
    LibraryGeneric<LibraryBookGeneric<String>> testLibraryString = new LibraryGeneric<LibraryBookGeneric<String>>();

    public void setUp() throws Exception {
        super.setUp();

//        testLibraryPhone.add();
    }

    public void tearDown() throws Exception {

    }

    public void testBookList() throws Exception {

    }

    public void testGetInventoryList() throws Exception {

    }

    public void testGetOrderedByAuthor() throws Exception {

    }

    public void testGetOverdueList() throws Exception {

    }
}