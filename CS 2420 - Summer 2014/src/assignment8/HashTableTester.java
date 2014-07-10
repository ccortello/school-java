package assignment8;

import junit.framework.TestCase;

/**
 * Tester class to test various methods in assignment 8
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class HashTableTester extends TestCase {

//----------- Tested ChainingHashTable Methods ----------------------------------------------

    public void testChainingHashTable() {
        // declare new ChainingHashTable
        ChainingHashTable testTable = null;
        // assert that test == null first
        assertTrue(testTable == null);

        // test the constructor method
        testTable = new ChainingHashTable(7, new GoodHashFunctor());
        /* test that the member variables were initialized correctly and also tests
           the size, getCapacity, getCollisions & getLambdaMAX methods */
        assertEquals(0, testTable.size());
        assertEquals(7, testTable.getCapacity());
        assertEquals(0, testTable.getCollisions());
        assertEquals(3.0, testTable.getLambdaMAX());
        // tests the setLambdaMAX function
        testTable.setLambdaMAX(4.0);
        assertEquals(4.0, testTable.getLambdaMAX());

        // tests the add method, asserts returns true that they were added
        assertTrue(testTable.add("first"));
        assertTrue(testTable.add("second"));
        assertTrue(testTable.add("third"));
        // tests that duplicates are not allowed, asserts returns false in this case
        assertFalse(testTable.add("first"));
        // assert that size is still 3
        assertEquals(3, testTable.size());
        // test getLambda function now that size of testTable is not zero
        assertEquals(3.0 / 7, testTable.getLamda());

        //test the contains method
        assertTrue(testTable.contains("first"));
        assertTrue(testTable.contains("second"));
        assertTrue(testTable.contains("third"));
        //test contains returns false
        assertFalse(testTable.contains("fourth"));
    }
}
