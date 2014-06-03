package assignment3;

import junit.framework.TestCase;

public class ArrayBasedCollectionTest extends TestCase {
    private ArrayBasedCollection<String> nullCollection, emptyCollection, testCollection1, testCollection2;

    public void setUp() throws Exception {
        super.setUp();

        emptyCollection = new ArrayBasedCollection<String>(0);
        testCollection1 = new ArrayBasedCollection<String>();
        testCollection2 = new ArrayBasedCollection<String>(100);
    }

    public void tearDown() throws Exception {

    }

    public void testAdd() throws Exception {
        testCollection1.add("Hello");
        testCollection1.add("World");
        if (testCollection1.contains("Hello"))
            System.out.println("Correctly contains \"Hello\"");
    }

    public void testAddAll() throws Exception {

    }

    public void testClear() throws Exception {

    }

    public void testContains() throws Exception {

    }

    public void testContainsAll() throws Exception {

    }

    public void testIsEmpty() throws Exception {

    }

    public void testIterator() throws Exception {

    }

    public void testRemove() throws Exception {

    }

    public void testRemoveAll() throws Exception {

    }

    public void testRetainAll() throws Exception {

    }

    public void testSize() throws Exception {

    }

    public void testToArray() throws Exception {

    }
}