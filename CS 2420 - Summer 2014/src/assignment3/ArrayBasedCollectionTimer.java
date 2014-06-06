package assignment3;

import java.util.HashSet;

/**
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 *         <p/>
 *         ArrayBasedCollection timing experiments.
 */
public class ArrayBasedCollectionTimer {
    /**
     *
     */
    public static void main(String[] args) {
        // TODO add any preparations needed for the timing experiment

        timer();
    }

    /**
     * This code is refactored from Paymon's SortDemo.java code provided on the class website
     */
    public static void timer() {
        //TODO: Write code to time your toSortedList, contains, and SearchUtil.binarySearch methods so you can plot the results.
        int[] temp;
        long startTime, midpointTime, stopTime;

        // Setup for the timing experiment.
        int timesToLoop = 100;
        System.out.println("N\tTime");

        // First, spin computing stuff until one second has gone by.
        // This allows this thread to stabilize.
        // (As seen in lab1 experiment 8)
        startTime = System.nanoTime();

        while (System.nanoTime() - startTime < 1000000000) {
        } // empty block

        // Run complete timing for different values of N
        for (int N = 1000; N <= 20000; N += 1000) {

            // Generate the random array before starting the timer
            //  note: instead of permuting ascending numbers a random array is created and ensured no duplications by
            //  checking each add element against a HashSet (which the ints are also added to)
            ArrayBasedCollection<Integer> nums = new ArrayBasedCollection<Integer>(N);
            HashSet<Integer> testSet = new HashSet<Integer>();
            int[] testArray = new int[N];
            int index = 0;
            while (nums.size() < N) {
                int intToAdd = (int) (Math.random() * 100000);
                nums.add(intToAdd);
                testSet.add(intToAdd);
                if (index < N)
                    testArray[index++] = intToAdd;
            }

            startTime = System.nanoTime();

            for (int i = 0; i < timesToLoop; i++) {
//                nums.toSortedList(new IntegerComparator());
                nums.contains(testArray[i]);
            }

            midpointTime = System.nanoTime();

            // Run a loop with non-timed code to capture the cost of running
            // the loop and anything extra that maybe needed to setup the timing
            for (int i = 0; i < timesToLoop; i++) {
                // empty block
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

            System.out.println(nums.size() + "\t" + averageTime);
        }
    }
}
