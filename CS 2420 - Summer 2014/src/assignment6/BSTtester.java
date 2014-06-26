package assignment6;

import junit.framework.TestCase;

import java.util.ArrayList;

public class BSTtester extends TestCase {
    // null string reference to test case of NullPointer Exception
    String nullString;
    // non-null BinarySearchTree reference to run other various test on
    BinarySearchTree<String> BSTtestList = new BinarySearchTree<String>();
    // BinarySearchTree object used to test addAll, removeAll, containsAll etc.
    ArrayList<String> arrayListTest = new ArrayList<String>(3);


    /**
     * Sets up various objects and arrays, as well as BinarySearchTree objects to test
     *
     * @throws Exception
     */
    protected void setUp() throws Exception {
        super.setUp();

        // set values for strOfStrings
        arrayListTest.add("1st");
        arrayListTest.add("2nd");
        arrayListTest.add("3rd");
    }

    /**
     * tests the add, isEmpty & contains methods along with a test for a NullPointerException
     *
     * @throws NullPointerException
     */
    public void testAdd() throws NullPointerException {
        // first, assert that there are no elements in the BinarySearchTree
        assertTrue(BSTtestList.isEmpty());
        // assert that 'true' is returned when adding "first" because the BinarySearchTree was changed
        assertTrue(BSTtestList.add("first"));
        // test that it is now not empty
        assertFalse(BSTtestList.isEmpty());
        // test the BSTtestList now contains "first"
        assertTrue(BSTtestList.contains("first"));
        // assert false that BSTtestList contains "third"
        assertFalse(BSTtestList.contains("third"));
        // now asserts that 'false' is returned because "first" is present & BinarySearchTree was not changed
        assertFalse(BSTtestList.add("first"));

        //re-do above test a second time
        assertTrue(BSTtestList.add("second"));
        assertTrue(BSTtestList.contains("second"));
        assertFalse(BSTtestList.add("second"));

        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.add(nullString);
        }
        catch (Exception e) {
            assertEquals("added null!", e.getMessage());
        }
    }

    /**
     * tests addAll, isEmpty, contains, containsAll, & size methods. Also test for case of NullPointerException
     *
     * @throws NullPointerException
     */
    public void testAddAll() throws NullPointerException {
        // first, assert that there are no elements in the BinarySearchTree
        assertTrue(BSTtestList.isEmpty());
        // use addAll with arrayListTest list, assert returns true as the BinarySearchTree is changed
        assertTrue(BSTtestList.addAll(arrayListTest));
        // assert that the BSTtestList contains the added items
        assertTrue(BSTtestList.contains("1st"));
        assertTrue(BSTtestList.contains("2nd"));
        assertTrue(BSTtestList.contains("3rd"));
        // assert false that BSTtestList contains "4th"
        assertFalse(BSTtestList.contains("4th"));
        // test contains all
        assertTrue(BSTtestList.containsAll(arrayListTest));
        // add to arrayListTest, to test that contains all then return false
        arrayListTest.add("4th");
        assertFalse(BSTtestList.containsAll(arrayListTest));
        // assert addAll with arrayListTest returns true, because "4th" is added
        assertTrue(BSTtestList.containsAll(arrayListTest));
        // assert addAll now returns false with arrayListTest because nothing is added
        assertFalse(BSTtestList.containsAll(arrayListTest));
        // assert the size (number of elements) should only be 4
        assertEquals(4, BSTtestList.size());

        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.add(nullString);
        }
        catch (Exception e) {
            assertEquals("Tried to add null with .addAll", e.getMessage());
        }
    }

    /**
     * tests the clear method, other methods within are already tested above to work.
     */
    public void testClear() {
        // first, assert that there are no elements in the BinarySearchTree
        assertTrue(BSTtestList.isEmpty());
        // use addAll with arrayListTest list, assert returns true as the BinarySearchTree is changed
        assertTrue(BSTtestList.addAll(arrayListTest));
        // assert that the BSTtestList contains the added items
        assertTrue(BSTtestList.contains("1st"));
        assertTrue(BSTtestList.contains("2nd"));
        assertTrue(BSTtestList.contains("3rd"));

        // test that it is now not empty
        assertFalse(BSTtestList.isEmpty());

        // clear BSTtestList
        BSTtestList.clear();
        // assert that BSTtestList is now empty
        assertTrue(BSTtestList.isEmpty());
        // assert non of the previous items added are now present
        assertFalse(BSTtestList.contains("1st"));
        assertFalse(BSTtestList.contains("2nd"));
        assertFalse(BSTtestList.contains("3rd"));
    }

    /**
     * Test the case of NullPointerException for the contains method. Contains method is already test multiple times, in
     * the tests above, to work.
     *
     * @throws NullPointerException
     */
    public void testContains() throws NullPointerException {
        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.add(nullString);
        }
        catch (Exception e) {
            assertEquals("Tried .contains with 'null'", e.getMessage());
        }
    }

    /**
     * Test the case of NullPointerException for the containsAll method. ContainsAll method is already tested in the
     * addAll test above to work.
     *
     * @throws NullPointerException
     */
    public void testContainsAll() throws NullPointerException {
        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.add(nullString);
        }
        catch (Exception e) {
            assertEquals("Tried to use .containsAll with a Collection containing 'null'", e.getMessage());
        }
    }

    /**
     * isEmpty is already tested multiple times to work in the above tests.
     */
    public void testIsEmpty() {}

//    public void testRemove() throws NullPointerException {
//
//        //try-catch blocks to test case of NullPointerException
//        try {
//            BSTtestList.add(nullString);
//        }
//        catch (Exception e) {
//            assertEquals("added null!", e.getMessage());
//        }
//    }
//
//    public void testRemoveAll() throws NullPointerException {
//
//        //try-catch blocks to test case of NullPointerException
//        try {
//            BSTtestList.add(nullString);
//        }
//        catch (Exception e) {
//            assertEquals("added null!", e.getMessage());
//        }
//    }
//
//    public void testSize() {
//
//    }
//
//    public void testToArrayList() {
//
//    }
//
//    public void testFirst() throws NoSuchElementException {
//
//        //try-catch blocks to test case of NullPointerException
//        try {
//            BSTtestList.add(nullString);
//        }
//        catch (Exception e) {
//            assertEquals("added null!", e.getMessage());
//        }
//    }
//
//    public void testLast() throws NoSuchElementException {
//
//        //try-catch blocks to test case of NullPointerException
//        try {
//            BSTtestList.add(nullString);
//        }
//        catch (Exception e) {
//            assertEquals("added null!", e.getMessage());
//        }
//    }
//
//    public void testInOrderDFT() {
//
//    }
//
//    public void testPreOrderDFT() {
//
//    }
//
//    public void testPostOrderDFT() {
//
//    }
//
//    public void testLevelOrderBFT() {
//
//    }
//
//    public void testWriteDot() {
//
//    }
}