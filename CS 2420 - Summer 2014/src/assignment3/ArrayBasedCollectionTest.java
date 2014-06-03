package assignment3;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class ArrayBasedCollectionTest extends TestCase {
    private ArrayBasedCollection<String> nullCollection, emptyCollection, testCollectionAdd, testCollectionAddAll,
            testCollectionClear, testCollectionContainsAll, testCollectionIterator,
            testCollectionRemove, testCollectionRemoveAll, testCollectionRetainAll, testCollectionToArray;

    public void setUp() throws Exception {
        super.setUp();

        // these constructors test the different possible values to be passed to the ArrayBasedCollection constructor
        emptyCollection = new ArrayBasedCollection<String>(0);
        testCollectionAdd = new ArrayBasedCollection<String>();
        testCollectionAddAll = new ArrayBasedCollection<String>(100);

        testCollectionClear = new ArrayBasedCollection<String>();
        testCollectionContainsAll = new ArrayBasedCollection<String>();
        testCollectionIterator = new ArrayBasedCollection<String>();
        testCollectionRemove = new ArrayBasedCollection<String>();
        testCollectionRemoveAll = new ArrayBasedCollection<String>();
        testCollectionRetainAll = new ArrayBasedCollection<String>();
        testCollectionToArray = new ArrayBasedCollection<String>();
    }

    public void tearDown() throws Exception {}

    public void testAdd() throws Exception {
        testCollectionAdd.add("Hello");
        testCollectionAdd.add("World");
        assertEquals(testCollectionAdd.contains("Hello"), true);
        assertEquals(testCollectionAdd.contains("World"), true);
    }

    public void testAddAll() throws Exception {
        ArrayList<String> addList = new ArrayList<String>();
        addList.add("test string 1");
        addList.add("test string 2");
        addList.add("test string 3");
        addList.add("test string 5"); // out of order on purpose
        addList.add("test string 4");

        testCollectionAddAll.addAll(addList);
        assertEquals(testCollectionAddAll.contains("test string 1"), true);
        assertEquals(testCollectionAddAll.contains("test string 2"), true);
        assertEquals(testCollectionAddAll.contains("test string 3"), true);
        assertEquals(testCollectionAddAll.contains("test string 5"), true);
        assertEquals(testCollectionAddAll.contains("test string 4"), true);
    }

    public void testClear() throws Exception {
        TreeSet<String> addTree = new TreeSet<String>();
        addTree.add("banana");
        addTree.add("apple");
        addTree.add("cherry");
        addTree.add("coconut");
        testCollectionClear.addAll(addTree);
        testCollectionClear.clear();
        assertEquals(testCollectionClear.contains("banana"), false);
        assertEquals(testCollectionClear.contains("apple"), false);
        assertEquals(testCollectionClear.contains("cherry"), false);
        assertEquals(testCollectionClear.contains("coconut"), false);
    }

    public void testContains() throws Exception {
        // contains in implemented in all other tests, so a standalone test isn't needed
    }

    public void testContainsAll() throws Exception {
        HashSet<String> addSet = new HashSet<String>();
        addSet.add("banana");
        addSet.add("pie");
        addSet.add("mork");
        addSet.add("help I'm trapped");
        addSet.add("silly String");

        testCollectionContainsAll.add("banana");
        testCollectionContainsAll.add("pie");
        testCollectionContainsAll.add("mork");
        testCollectionContainsAll.add("help I'm trapped");
        testCollectionContainsAll.add("silly String");

        assertEquals(testCollectionContainsAll.containsAll(addSet), true);
    }

    public void testIsEmpty() throws Exception {
        assertEquals(emptyCollection.isEmpty(), true);
        assertEquals(testCollectionClear.isEmpty(), true);
    }

    public void testIterator() throws Exception {
        // this test has no "assertEquals," but instead uses a foreach loop to show that the iterator implementation is valid
        testCollectionIterator.add("first");
        testCollectionIterator.add("second");
        testCollectionIterator.add("third");
        testCollectionIterator.add("fourth");

        for (String element : testCollectionIterator) {
            // empty block
        }
    }

    public void testRemove() throws Exception {
        testCollectionRemove.add("first");
        testCollectionRemove.add("second");
        testCollectionRemove.add("third");

        testCollectionRemove.remove("second");
        assertEquals(testCollectionRemove.contains("first"), true);
        assertEquals(testCollectionRemove.contains("second"), false);
        assertEquals(testCollectionRemove.contains("third"), true);
    }

    public void testRemoveAll() throws Exception {
        testCollectionRemoveAll.add("first");
        testCollectionRemoveAll.add("second");
        testCollectionRemoveAll.add("third");
        testCollectionRemoveAll.add("fourth");
        testCollectionRemoveAll.add("fifth");

        TreeSet<String> removeAllSet = new TreeSet<String>();
        removeAllSet.add("second");
        removeAllSet.add("fourth");

        testCollectionRemoveAll.removeAll(removeAllSet);

        assertEquals(testCollectionRemoveAll.contains("first"), true);
        assertEquals(testCollectionRemoveAll.contains("second"), false);
        assertEquals(testCollectionRemoveAll.contains("third"), true);
        assertEquals(testCollectionRemoveAll.contains("fourth"), false);
        assertEquals(testCollectionRemoveAll.contains("fifth"), true);
    }

    public void testRetainAll() throws Exception {
        testCollectionRetainAll.add("first");
        testCollectionRetainAll.add("second");
        testCollectionRetainAll.add("third");
        testCollectionRetainAll.add("fourth");
        testCollectionRetainAll.add("fifth");

        ArrayList<String> retainAllList = new ArrayList<String>();
        retainAllList.add("second");
        retainAllList.add("fourth");

        testCollectionRetainAll.retainAll(retainAllList);

        assertEquals(testCollectionRetainAll.contains("first"), false);
        assertEquals(testCollectionRetainAll.contains("second"), true);
        assertEquals(testCollectionRetainAll.contains("third"), false);
        assertEquals(testCollectionRetainAll.contains("fourth"), true);
        assertEquals(testCollectionRetainAll.contains("fifth"), false);
    }

    public void testSize() throws Exception {
        assertEquals(emptyCollection.size, 0);
    }

    public void testToArray() throws Exception {

    }
}