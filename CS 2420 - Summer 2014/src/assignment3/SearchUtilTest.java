package assignment3;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class SearchUtilTest extends TestCase {
    ArrayBasedCollection<Integer> testList;

    public void setUp() throws Exception {
        super.setUp();

        testList = new ArrayBasedCollection<Integer>();
    }

    public void tearDown() throws Exception {
    }

    public void testBinarySearch() throws Exception {
        // randomize 100 ints from 0 to 100 and add them to the ArrayBasedCollection and a HashSet (to compare the
        //  final sorted list against)
        int intsAdded = 100, range = 100;
        HashSet<Integer> testSet = new HashSet<Integer>();
        for (int i = 0; i < intsAdded; i++) {
            int intToAdd = (int) ((Math.random() * range) + 1);
            testList.add(intToAdd);
            testSet.add(intToAdd);
        }

        // use a default Comparator to compare the ints
        Comparator<Integer> cmp = new IntegerComparator();

        // invoke the toSortedList method to sort the randomized ints
        ArrayList<Integer> sortedList = testList.toSortedList(cmp);

        // then search for 50 random ints in the sortedList and assert their existence
        int tries = 50;
        for (int i = 0; i < tries; i++) {
            int intToTry = (int) ((Math.random() * range) + 1);
            assertEquals(testSet.contains(intToTry), SearchUtil.binarySearch(sortedList, intToTry, cmp));
        }
    }
}