package assignment6;

import junit.framework.TestCase;

import java.util.ArrayList;

public class BSTtester extends TestCase {
    // null string reference to test case of NullPointer Exception, both item and array
    String nullString;
    ArrayList<String> nullStrOfStrings = new ArrayList<String>();
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
            assertEquals("Tried adding null", e.getMessage());
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
        // assert that BSTtestList no longer contains all of arrayListTest
        assertFalse(BSTtestList.containsAll(arrayListTest));
        // assert addAll with arrayListTest returns true, because "4th"  now is added
        assertTrue(BSTtestList.addAll(arrayListTest));
        // assert addAll now returns false with arrayListTest because nothing is added
        assertFalse(BSTtestList.addAll(arrayListTest));
        // assert that proper elements are present
        assertTrue(BSTtestList.contains("1st"));
        assertTrue(BSTtestList.contains("2nd"));
        assertTrue(BSTtestList.contains("3rd"));
        assertTrue(BSTtestList.contains("4th"));
        assertTrue(BSTtestList.containsAll(arrayListTest));
        // assert the size (number of elements) should only be 4
        assertEquals(4, BSTtestList.size());

        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.addAll(nullStrOfStrings);
        }
        catch (Exception e) {
            assertEquals("Tried to add a null Collection with addAll", e.getMessage());
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
     * Test the case of NullPointerException for the contains method. Test if BSTtestList is empty returns false.
     *
     * @throws NullPointerException
     */
    public void testContains() throws NullPointerException {
        // assert returns false when contains is used on an empty BinarySearchTree
        assertFalse(BSTtestList.contains("1st"));
        // add to BSTtestList so its not empty, assert return 'true' that it was indeed changed.
        assertTrue(BSTtestList.add("1st"));
        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.contains(nullString);
        }
        catch (Exception e) {
            assertEquals("Tried contains with null item", e.getMessage());
        }
    }

    /**
     * Test the case of NullPointerException for the containsAll method. containsAll method is re-tested as it is in
     * the addAll method test
     * @throws NullPointerException
     */
    public void testContainsAll() throws NullPointerException {
        // first assert returns false if this BinarySearchTree is empty when calling contains all.
        assertFalse(BSTtestList.containsAll(arrayListTest));
        // add to BSTtestList so its not empty, assert return 'true' that it was indeed changed.
        assertTrue(BSTtestList.addAll(arrayListTest));
        // assert that all items added from arrayListTest are contained in BSTtestList with containsAll
        assertTrue(BSTtestList.containsAll(arrayListTest));
        arrayListTest.add("4th");
        assertTrue(BSTtestList.addAll(arrayListTest));
        assertTrue(BSTtestList.contains("1st"));
        assertTrue(BSTtestList.contains("2nd"));
        assertTrue(BSTtestList.contains("3rd"));
        assertTrue(BSTtestList.contains("4th"));
        assertTrue(BSTtestList.containsAll(arrayListTest));

        //try-catch blocks to test case of NullPointerException
        try {
            BSTtestList.containsAll(nullStrOfStrings);
        }
        catch (Exception e) {
            assertEquals("Tried contains with null item", e.getMessage());
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