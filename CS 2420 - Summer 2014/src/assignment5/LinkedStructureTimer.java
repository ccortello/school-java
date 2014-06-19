package assignment5;

import java.util.Random;

/**
 * Runs timing algorithms to find the execution time and complexity of various MyLinkedList operations.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class LinkedStructureTimer {
    public static void main(String[] args) {
        new LinkedStructureTimer();
    }

    LinkedStructureTimer() {
        problem3a();
//        problem3b();
//        problem3c();
    }

    /**
     * Finds the average running time of the addFirst method
     * O(1) expected
     */
    void problem3a() {
        // substantiate a random (seeded) MyLinkedList to run tests upon
        MyLinkedList<Integer> testList = new MyLinkedList<Integer>();
        Random rand = new Random(13489723891023874L);
        for (int i = 0; i < 100000; i++)
            testList.addFirst(rand.nextInt());

        System.out.println("Size = " + arraySize + ", loops = " + timesToLoop + "\n\nThreshold\tTime");

        ArrayList<Integer> sortList = RecursiveSortingUtility.generateAverageCase(arraySize);
        ArrayList<Integer> testArray;

        // loop through cutoff values and find the average time and number of comparisons for sorting an array of size
        // 100,000 with that cutoff value, then print the results
        for (int i = 0; i <= 10000; i += 100) {

            // change the cutoff value
            RecursiveSortingUtility.setMergeSortThreshold(i);

    		/* timing code modified from Peter Jensen's TimingExperiment08.java from his CS 2420 class of Spring 2014 */

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) {
                // empty block
            }

            // Now, run the test.
            startTime = System.nanoTime();

            for (long j = 0; j < timesToLoop; j++) {
                // create a random array of data and sort it
                testArray = new ArrayList<Integer>(sortList);
//                startTime = System.nanoTime();
                RecursiveSortingUtility.mergeSortDriver(testArray);
            }

            midpointTime = System.nanoTime();

            // Calculate the cost of looping and copying the array

            for (long j = 0; j < timesToLoop; j++) {
                testArray = new ArrayList<Integer>(sortList);
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
            averageTime /= 1.0e9;

            // print out tab-delimited results
            System.out.println(RecursiveSortingUtility.getMergesortThreshold() + "\t" + averageTime);
    }

    /**
     * Finds the average running time of the get method
     * O(N) expected
     */
    void problem3b() {

    }

    /**
     * Finds the average running time of the remove method
     * O(N) expected
     */
    void problem3c() {

    }
}
