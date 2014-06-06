package assignment3;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class ArrayBasedCollectionTest extends TestCase {
    private ArrayBasedCollection<String> emptyCollection, testCollectionAdd, testCollectionAddAll,
            testCollectionClear, testCollectionContainsAll, testCollectionIterator,
            testCollectionRemove, testCollectionRemoveAll, testCollectionRetainAll, testCollectionSize,
            testCollectionToArray, testCollectionGrow, testCollectionToSortedList;

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
        testCollectionSize = new ArrayBasedCollection<String>();
        testCollectionToArray = new ArrayBasedCollection<String>();
        testCollectionGrow = new ArrayBasedCollection<String>(0);
        testCollectionToSortedList = new ArrayBasedCollection<String>();
    }

    public void tearDown() throws Exception {}

    public void testAdd() throws Exception {
        testCollectionAdd.add("Hello");
        testCollectionAdd.add("World");
        assertEquals(true, testCollectionAdd.contains("Hello"));
        assertEquals(true, testCollectionAdd.contains("World"));
    }

    public void testAddAll() throws Exception {
        ArrayList<String> addList = new ArrayList<String>();
        addList.add("test string 1");
        addList.add("test string 2");
        addList.add("test string 3");
        addList.add("test string 5"); // out of order on purpose
        addList.add("test string 4");

        testCollectionAddAll.addAll(addList);
        assertEquals(true, testCollectionAddAll.contains("test string 1"));
        assertEquals(true, testCollectionAddAll.contains("test string 2"));
        assertEquals(true, testCollectionAddAll.contains("test string 3"));
        assertEquals(true, testCollectionAddAll.contains("test string 5"));
        assertEquals(true, testCollectionAddAll.contains("test string 4"));
    }

    public void testClear() throws Exception {
        TreeSet<String> addTree = new TreeSet<String>();
        addTree.add("banana");
        addTree.add("apple");
        addTree.add("cherry");
        addTree.add("coconut");
        testCollectionClear.addAll(addTree);
        testCollectionClear.clear();
        assertEquals(false, testCollectionClear.contains("banana"));
        assertEquals(false, testCollectionClear.contains("apple"));
        assertEquals(false, testCollectionClear.contains("cherry"));
        assertEquals(false, testCollectionClear.contains("coconut"));
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

        assertEquals(true, testCollectionContainsAll.containsAll(addSet));
    }

    public void testIsEmpty() throws Exception {
        assertEquals(true, emptyCollection.isEmpty());
        assertEquals(true, testCollectionClear.isEmpty());
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
        assertEquals(true, testCollectionRemove.contains("first"));
        assertEquals(false, testCollectionRemove.contains("second"));
        assertEquals(true, testCollectionRemove.contains("third"));
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

        assertEquals(true, testCollectionRemoveAll.contains("first"));
        assertEquals(false, testCollectionRemoveAll.contains("second"));
        assertEquals(true, testCollectionRemoveAll.contains("third"));
        assertEquals(false, testCollectionRemoveAll.contains("fourth"));
        assertEquals(true, testCollectionRemoveAll.contains("fifth"));
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

        assertEquals(false, testCollectionRetainAll.contains("first"));
        assertEquals(true, testCollectionRetainAll.contains("second"));
        assertEquals(false, testCollectionRetainAll.contains("third"));
        assertEquals(true, testCollectionRetainAll.contains("fourth"));
        assertEquals(false, testCollectionRetainAll.contains("fifth"));
    }

    public void testSize() throws Exception {
        assertEquals(0, testCollectionSize.size);
        testCollectionSize.add("first");
        assertEquals(1, testCollectionSize.size());
        testCollectionSize.add("second");
        assertEquals(2, testCollectionSize.size());
        testCollectionSize.add("third");
        assertEquals(3, testCollectionSize.size());
        testCollectionSize.add("fourth");
        assertEquals(4, testCollectionSize.size());
        testCollectionSize.add("fifth");
        assertEquals(5, testCollectionSize.size());
        testCollectionSize.remove("fifth");
        assertEquals(4, testCollectionSize.size());
        testCollectionSize.remove("second");
        assertEquals(3, testCollectionSize.size());
        testCollectionSize.remove("third");
        assertEquals(2, testCollectionSize.size());
    }

    public void testToArray() throws Exception {
        testCollectionToArray.add("first");
        testCollectionToArray.add("second");
        testCollectionToArray.add("eleventh");
        testCollectionToArray.add("fifth");

        Object[] testArray = testCollectionToArray.toArray();

        assertEquals("first", testArray[0]);
        assertEquals("second", testArray[1]);
        assertEquals("eleventh", testArray[2]);
        assertEquals("fifth", testArray[3]);
    }

    public void testGrow() throws Exception {
        // test adding to collection of size 0 up to size 8
        testCollectionGrow.add("first");
        assertEquals(1, testCollectionGrow.length());
        testCollectionGrow.add("second");
        assertEquals(2, testCollectionGrow.length());
        testCollectionGrow.add("third");
        assertEquals(4, testCollectionGrow.length());
        testCollectionGrow.add("fourth");
        assertEquals(4, testCollectionGrow.length());
        testCollectionGrow.add("fifth");
        assertEquals(8, testCollectionGrow.length());
    }

    public void testToSortedList() throws Exception {
        testCollectionToSortedList.add("banana");
        testCollectionToSortedList.add("dragon");
        testCollectionToSortedList.add("carrot");
        testCollectionToSortedList.add("apple");
        testCollectionToSortedList.add("gorilla");
        testCollectionToSortedList.add("zebra");

        class stringComparator implements Comparator<String> {
            public int compare(String left, String right) {
                return left.compareTo(right);
            }
        }

        ArrayList<String> sortedList = testCollectionToSortedList.toSortedList(new stringComparator());

        assertEquals("apple", sortedList.get(0));
        assertEquals("banana", sortedList.get(1));
        assertEquals("carrot", sortedList.get(2));
        assertEquals("dragon", sortedList.get(3));
        assertEquals("gorilla", sortedList.get(4));
        assertEquals("zebra", sortedList.get(5));
    }
}