package assignment4;

import java.util.ArrayList;

/**
 * Implements and tests the 'Quicksort' algorithm
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 6/12/2014
 */
public class QuicksortAnalysisPart5 {
    public static void main(String[] args) {
        new QuicksortAnalysisPart5();
    }

    public QuicksortAnalysisPart5() {

        int[] arraySizes = new int[]{5, 10, 50, 100, 500, 1000, 2000, 5000, 10000, 20000, 50000};

        // print the report header
        RecursiveSortingUtility sort = new RecursiveSortingUtility();

        // loop through cutoff values and find the average time and number of comparisons for sorting an array of size
        // 100,000 with that cutoff value, then print the results
        for (int i = 0; i < 51; i++) {

            // change the cutoff value
            sort.setMergeSortThreshold(i);

    		/* timing code modified from Peter Jensen's TimingExperiment08.java from his CS 2420 class of Spring 2014 */

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.

            long timesToLoop = 20;


            for (long j = 0; j < timesToLoop; j++) {
                // create a random array of data and sort it
                ArrayList<Integer> sortList = sort.generateAverageCase(100000);
                startTime = System.nanoTime();
                sort.quickSortDriver(sortList);
            }

            midpointTime = System.nanoTime();

            // Calculate the cost of looping

            for (long j = 0; j < timesToLoop; j++) {
                // empty block
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
            averageTime /= 1e9;

            // print out tab-delimited results
            System.out.println(i + "\t" + averageTime + "\t");
        }
    }
}