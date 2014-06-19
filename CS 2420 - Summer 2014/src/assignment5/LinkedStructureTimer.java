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
        int timesToLoop = 10000, maxSize = 20000;
        long seed = 13489723891023874L;
        Random rand = new Random(seed);

        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nSize\tTime");

        // loop through cutoff values and find the average time and number of comparisons for sorting an array of size
        // 100,000 with that cutoff value, then print the results
        for (int i = 0; i <= maxSize; i += 500) {

    		/* timing code modified from Peter Jensen's TimingExperiment08.java from his CS 2420 class of Spring 2014 */

            long startTime, midpointTime1, midpointTime2, stopTime;

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
                for (int k = 0; k < i; k++)
                    testList.addFirst(rand.nextInt());

                // reset testList so it's an empty set for the next loop
                testList = new MyLinkedList<Integer>();
            }

            // midpointTime1 marks the end of the testing execution.
            midpointTime1 = System.nanoTime();

            // reset random variable to assure that the execution time for the setup overhead is _exactly_ the same.
            //  timing note: the statements between midpointTime1 and midpointTime2 do not affect the timing at all.
            rand = new Random(seed);


            // since resetting rand would necessarily take execution time we establish a new midpointTime in order to
            //  calculate the overhead alone (separate from the time taken to reset rand)
            midpointTime2 = System.nanoTime();

            // Calculate the cost of looping and resetting testList

            for (long j = 0; j < timesToLoop; j++) {
                testList = new MyLinkedList<Integer>();
            }


            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime1 - startTime) - (stopTime - midpointTime2)) / timesToLoop;
            averageTime /= 1.0e9;

            // print out tab-delimited results
            System.out.println(i + "\t" + averageTime);
        }
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
