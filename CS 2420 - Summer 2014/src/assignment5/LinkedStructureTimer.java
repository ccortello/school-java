package assignment5;

import java.util.ArrayList;
import java.util.Random;

/**
 * Runs timing algorithms to find the execution time and complexity of various MyLinkedList operations.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class LinkedStructureTimer {
    long seed = 13489723891023874L;
    public static void main(String[] args) {
        new LinkedStructureTimer();
    }

    LinkedStructureTimer() {
//        problem3ai();
//        problem3aii();
        problem3bi();
//        problem3bii();
//        problem3c();
//        prob3a();
    }

    /**
     * Finds the average running time of the addFirst method for MyLinkedList
     * O(1) expected
     */
    void problem3ai() {
        // substantiate a random (seeded) MyLinkedList to run tests upon
        MyLinkedList<Integer> testList = new MyLinkedList<Integer>();
        int timesToLoop = 10000, maxSize = 20000;
        long seed = 13489723891023874L;
        Random rand = new Random(seed);

        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nSize\tTime");

        // loop through list sizes and find the average time for addFirst on a list of each size, then print the results
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
                // create a MyLinkedList of the current size (the size is set by the outer for loop index 'i')
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
     * Finds the average running time of add(0, item) for an ArrayList
     */
    void problem3aii() {
        // substantiate a random (seeded) Arraylist to run tests upon
        ArrayList<Integer> testList = new ArrayList<Integer>();
        int timesToLoop = 10000, maxSize = 20000;
        Random rand = new Random(seed);

        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nSize\tTime");

        // loop through list sizes and find the average time for add(0, item) on a list of each size, then print the results
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
                // create an ArrayList of the current size (the size is set by the outer for loop index 'i')
                for (int k = 0; k < i; k++)
                    testList.add(0, rand.nextInt());

                // reset testList so it's an empty set for the next loop
                testList = new ArrayList<Integer>();
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
                testList = new ArrayList<Integer>();
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
     * Finds the average running time of the get method for a MyLinkedList
     * O(N) expected
     */
    void problem3bi() {

    }

    /**
     * Finds the average running time of the get(i) method for an ArrayList
     * O(N) expected
     */
    void problem3bii() {

    }

    /**
     * Finds the average running time of the remove method
     * O(N) expected
     */
    void problem3c() {

    }

    void prob3a() {
        int[] listElements = {- 14, 13, 52, 65, 81, - 7, 2, 122, 137, - 43};
        // create an initial linked list of 10 elements to start adding to during the timing.
        MyLinkedList<Integer> toTimeList = new MyLinkedList<Integer>();
        for (int i = 0; i < 10; i++) {
            toTimeList.addFirst(listElements[i]);
        }

        long startTime, endTime, middleTime;
        int randNum = 0;
        double totalTime = 0.0;
        Random rand = new Random(seed);

        System.out.println("Added\tTotalTime");
        for (int i = 1000; i <= 10000000; i *= 2) {

            // get the hardware and everything spooled up before we start timing
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 20000) {
                randNum = rand.nextInt();
                toTimeList.addFirst(randNum);
            }

            //start timing addFirst
            startTime = System.nanoTime();
            for (int j = 0; j < i; j++) {
                randNum = rand.nextInt();
                toTimeList.addFirst(randNum);
            }

            middleTime = System.nanoTime();

            // calculate overhead for looping and substantiating a random integer
            for (int j = 0; j < i; j++) {
                randNum = rand.nextInt();
            }

            endTime = System.nanoTime();

            totalTime = (double) ((middleTime - startTime) - (endTime - middleTime));
            System.out.println(i + "\t" + totalTime);
        }
    }
}
