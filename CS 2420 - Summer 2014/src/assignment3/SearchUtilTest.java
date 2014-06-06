package assignment3;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Comparator;

public class SearchUtilTest extends TestCase {
    ArrayBasedCollection<Integer> testList;

    public void setUp() throws Exception {
        super.setUp();

        testList = new ArrayBasedCollection<Integer>();
    }

    public void tearDown() throws Exception {
    }

    public void testBinarySearch() throws Exception {
        // randomize 100 ints from 0 to 100 and add them to the ArrayBasedCollection
        int intsAdded = 100, range = 100;
        for (int i = 0; i < intsAdded; i++)
            testList.add((int) (Math.random() * (range + 1)));

        // use a default Comparator to compare Integers
        class intComparator implements Comparator<Integer> {
            public int compare(Integer left, Integer right) {
                return left.compareTo(right);
            }
        }
        Comparator<Integer> cmp = new intComparator();

        ArrayList<Integer> sortedList = testList.toSortedList(cmp);
        System.out.println("sortedList = " + sortedList);

        int tries = 50;
        for (int i = 0; i < tries; i++) {
            int intToTry = (int) (Math.random() * range);
            if (SearchUtil.binarySearch(sortedList, intToTry, cmp))
                System.out.println(intToTry + " found!");
            else
                System.out.println(intToTry + " not found!");
        }
    }
}