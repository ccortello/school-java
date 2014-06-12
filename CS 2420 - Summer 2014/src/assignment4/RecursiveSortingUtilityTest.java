package assignment4;

import junit.framework.TestCase;

import java.util.ArrayList;

public class RecursiveSortingUtilityTest extends TestCase {

    ArrayList<Integer> mergeSortList, quickSortList;
    int listSize;

    public void setUp() throws Exception {
        super.setUp();

        listSize = 10000;
        mergeSortList = RecursiveSortingUtility.generateAverageCase(listSize);
        quickSortList = RecursiveSortingUtility.generateAverageCase(listSize);
    }


    public void testMergeSortDriver() throws Exception {
        RecursiveSortingUtility.mergeSortDriver(mergeSortList);
        for (int i = 0; i < listSize - 1; i++)
            assertFalse(mergeSortList.get(i).compareTo(mergeSortList.get(i + 1)) > 0);
    }

    public void testQuickSortDriver() throws Exception {
        RecursiveSortingUtility.quickSortDriver(quickSortList);
        for (int i = 0; i < listSize - 1; i++)
            assertFalse(quickSortList.get(i).compareTo(quickSortList.get(i + 1)) > 0);
    }

    public void testGoodPivotStrategy() throws Exception {

    }

    public void testBetterPivotStrategy() throws Exception {

    }

    public void testBestPivotStrategy() throws Exception {

    }

    public void testGenerateBestCase() throws Exception {

    }

    public void testGenerateWorstCase() throws Exception {

    }
}