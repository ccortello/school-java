package examples.week2examples;

/**
 *
 */
public class SortDemo {
    public static void main(String[] args) {
        // Which one of these is best, average, worst case?
        // Does it matter for selection or insertion sort?

        //int[] nums = new int[]{1,2,3,4,5};
        //int[] nums = new int[]{5,4,3,2,1};
        //int[] nums = new int[]{4,1,5,3,2};
        //int[] nums = SortUtil.randomInts(5);
        int[] nums = SortUtil.permuteInts(10);
        //int[] nums = SortUtil.ascendingInts(10);
        //int[] nums = SortUtil.descendingInts(10);


        System.out.println("before sorting");
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + " ");
        System.out.println("\n");

        //SortUtil.selectionSort(nums);
        SortUtil.insertionSort(nums);
        //SortUtil.shellSort(nums);

        if (!SortUtil.isSorted(nums))
            System.out.println("error");

        System.out.println("after sorting");
        for (int i = 0; i < nums.length; i++)
            System.out.print(nums[i] + " ");

        System.out.println("\n");
        timer();
    }

    public static void timer() {
        int[] temp;
        long startTime, midpointTime, stopTime;

        // Setup for the timing experiment.
        int timesToLoop = 1000;

        // First, spin computing stuff until one second has gone by.
        // This allows this thread to stabilize.
        // (As seen in lab1 experiment 8)
        startTime = System.nanoTime();

        while (System.nanoTime() - startTime < 1000000000) {
        } // empty block

        // Run complete timing for different values of N
        for (int N = 200; N <= 4000; N += 200) {
            // Generate the random Matrices before starting the timer
            // Which one of these is best, average, worst case?
            // Does it matter for selection or insertion sort?
            //int[] nums = SortUtil.randomInts(N);
            //int[] nums = SortUtil.permuteInts(N);
            //int[] nums = SortUtil.ascendingInts(N);
            int[] nums = SortUtil.descendingInts(N);

            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++) {
                // The array must be cloned every time since it gets sorted
                // the first iteration of this loop, and the rest will not
                // act the way we exp[ect them to do.
                temp = nums.clone();

                //SortUtil.selectionSort(nums);
                SortUtil.insertionSort(temp);
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
