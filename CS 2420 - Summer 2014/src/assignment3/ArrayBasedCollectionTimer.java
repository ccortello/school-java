package assignment3;

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
        int timesToLoop = 10;

        // First, spin computing stuff until one second has gone by.
        // This allows this thread to stabilize.
        // (As seen in lab1 experiment 8)
        startTime = System.nanoTime();

        while (System.nanoTime() - startTime < 1000000000) {
        } // empty block

        // Run complete timing for different values of N
        for (int N = 1000; N <= 20000; N += 1000) {
            // Generate the random array before starting the timer

            //int[] nums = SortUtil.randomInts(N);
            //int[] nums = SortUtil.permuteInts(N);
            //int[] nums = SortUtil.ascendingInts(N);
            int[] nums = SortUtil.descendingInts(N);

            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++) {
                // The array must be cloned every time since it gets sorted
                // the first iteration of this loop, and the rest will not
                // act the way we exp[ect them to do.

                //SortUtil.selectionSort(nums);
                nums.
                //SortUtil.shellSort(nums);
            }

            midpointTime = System.nanoTime();

            // Run a loop with non-timed code to capture the cost of running
            // the loop and anything extra that maybe needed to setup the timing
            for (int i = 0; i < timesToLoop; i++) {
                temp = nums.clone();
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

            System.out.println(averageTime);
        }
    }


}
