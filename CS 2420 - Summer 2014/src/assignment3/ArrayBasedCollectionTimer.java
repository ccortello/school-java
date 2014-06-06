package assignment3;

import java.util.Random;

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
        int timesToLoop = 20;
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
            ArrayBasedCollection<Integer> nums = permuteInts(N);

            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++)
                nums.toSortedList(new IntegerComparator());

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

//            System.out.println(N + "\t" + averageTime);
        }
    }

    private static ArrayBasedCollection<Integer> permuteInts(ArrayBasedCollection<Integer> nums) {

    }

    public ArrayBasedCollection<Integer> permuteInts(int size) {
        int randomArray[] = new int[size];
        for (int i = 0; i < size; i++)
            randomArray[i] = i;
        for (int i = 0; i < size; i++)
            swap(randomArray, i, new Random(System.currentTimeMillis()).nextInt(size));
        ArrayBasedCollection<Integer> retval = new ArrayBasedCollection<Integer>();
        for (int i = 0; i < size; i++) {
            retval.add(randomArray[i]);
        }
        return retval;
    }

    // Swaps two items in the given array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
