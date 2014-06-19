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
        int timesToLoop = 10000, maxSize = 10000;
        long seed = 13489723891023874L;
        Random rand = new Random(seed);

        System.out.println("Size = " + testList.size() + ", loops = " + timesToLoop + "\n\nThreshold\tTime");

        // loop through cutoff values and find the average time and number of comparisons for sorting an array of size
        // 100,000 with that cutoff value, then print the results
        for (int i = 0; i <= maxSize; i += 100) {

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
                // create a MyLinkedList of the current size (by the outer for loop) and add one item. Note the 'k<=i'
                for (int k = 0; k <= i; k++)
                    testList.addFirst(rand.nextInt());

                // reset testList so it's an empty set for the next loop
                testList = new MyLinkedList<Integer>();
            }

            midpointTime = System.nanoTime();

            // reset random variable to assure that the execution time for the setup overhead is _exactly_ the same
            rand = new Random(seed);

            // Calculate the cost of looping, creating the test lists, and resetting testList

            for (long j = 0; j < timesToLoop; j++) {
                for (int k = 0; k < i; k++) // here 'k<i' is used because we want to subtract the list substantiation
                    //  overhead but leave the execution time of the final addFirst
                    testList.addFirst(rand.nextInt());
                testList = new MyLinkedList<Integer>();
            }


            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
            averageTime /= 1.0e9;

            // print out tab-delimited results
//            System.out.println(RecursiveSortingUtility.getMergesortThreshold() + "\t" + averageTime);
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
