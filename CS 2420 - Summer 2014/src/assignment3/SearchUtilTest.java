package assignment3;

import junit.framework.TestCase;

import java.util.ArrayList;

public class SearchUtilTest extends TestCase {
    ArrayList<Integer> testList;

    public void setUp() throws Exception {
        super.setUp();

        testList = new ArrayList<Integer>();
    }

    public void tearDown() throws Exception {
    }

    public void testBinarySearch() throws Exception {
        int intsAdded = 100, range = 100;
        for (int i = 0; i < intsAdded; i++)
            testList.add((int) (Math.random() * range));

        int tries = 50;
        for (int i = 0; i < tries; i++) {
            int try=(int) (Math.random() * range);
//            if (SearchUtil.binarySearch(testList, ))
        }
    }
}