package homework05;

/**
 * Implements and tests the 'Quicksort' algorithm
 *
 * @author Cody Cortello
 * @version 2/17/2014
 */
public class QuicksortAnalysisPart5 {

    // the number of comparisons is a field simply to allow access outside of the for loops which modify it
    long comparisons = 0;

    // a variable for the cutoff point where the sort algorithm switches to insertion sort
    int cutoff = 0;

    public static void main(String[] args) {
        new QuicksortAnalysisPart5();
    }

    public QuicksortAnalysisPart5() {

        // print the report header
        System.out.println("Cody Cortello\nCS 2420\nHomework05\nPart 5\n\nCutoff\tTime\tComparisons");

        // loop through cutoff values and find the average time and number of comparisons for sorting an array of
        // 1 million doubles with that cutoff value, then print the results
        for (int i = 0; i < 51; i++) {

            // change the cutoff value
            cutoff = i;

    		/* timing code modified from Peter Jensen's TimingExperiment08.java from his CS 2420 class of Spring 2014 */

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.

            long timesToLoop = 20;

            startTime = System.nanoTime();

            for (long j = 0; j < timesToLoop; j++) {
                // create a random array of data and sort it
                double[] sortArray = createDoubleArray(1000000);
                quicksort(sortArray);
            }

            midpointTime = System.nanoTime();

            // Calculate the cost of looping and randomizing the arrays.

            for (long j = 0; j < timesToLoop; j++) {
                double[] timedArray = createDoubleArray(1000000);
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and sorting the array.
            // Average it over the number of runs.
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
            averageTime /= 1e9;

            // compute the average number of comparisons
            double averageComparisons = comparisons / timesToLoop;

            // reset the total comparison count
            comparisons = 0;

            // print out tab-delimited results
            System.out.println(cutoff + "\t" + averageTime + "\t" + averageComparisons);
        }
    }

    /**
     * A simple method which returns an array of size n with random doubles from [0,1)
     *
     * @param n the size of the array
     * @return an array of doubles randomized from [0,1)
     */
    public static double[] createDoubleArray(int n) {

        // initialize array
        double[] returnArray = new double[n];

        // fill array with doubles
        for (int i = 0; i < n; i++)
            returnArray[i] = Math.random() * 10;

        // return array
        return returnArray;
    }

    /**
     * A barebones implementation of quicksort which simply passes the call to another function 'qs'
     *
     * @param data an array of doubles to be sorted
     */
    public void quicksort(double[] data) {
        qs(data, 0, data.length - 1);
    }

    /**
     * Sorts an array of doubles from the start index to the end index using a recursive Quicksort algorithm
     *
     * @param data  An array of doubles to be sorted
     * @param start The index of the first element to be sorted
     * @param end   The index of the final element to be sorted
     */
    public void qs(double[] data, int start, int end) {

        // base case of the recursion
        if (end - start + 1 < 2)
            return;

            // implement insertion sort if the array has fewer than 10 elements
        else if (end - start + 1 < cutoff)
            insertionSortSub(data, start, end);

            // otherwise sort the array using Quicksort
        else {

            // find the middle value for the pivot
            int mid = partition(data, start, end);

            // use recursive calls to sort the array before the pivot and after the pivot
            qs(data, start, mid - 1);
            qs(data, mid + 1, end);
        }
    }

    /**
     * Finds the optimal quicksort pivot in the subarray of an input array
     *
     * @param data  an array of doubles to be partitioned
     * @param start the index of the first element of the subarray (0-indexed)
     * @param end   the index of the last element of the subarray (0-indexed)
     * @return the index of the quicksort pivot
     */
    private int partition(double[] data, int start, int end) {

        // store the value of the pivot, assuming the pivot to be the last element in the array
        double pivot = data[end];

        // initialize the 'left' and 'right' indices
        int left = start;
        int right = end - 1;

        // start infinite loop, will be ended with the break statement
        //  Note: advanceCount is used to increment the comparisons counter, and doesn't change the boolean it is passed
        while (true) {

            // increment 'left' until the value at 'left' is greater than or equal to the pivot
            while (advanceCount(data[left] < pivot))
                left++;

            // decrement 'right' until the value at 'right' is less than the value at the pivot or the right index
            //  crosses the left
            while (right > left && advanceCount(data[right] >= pivot))
                right--;

            // if the indices have crossed exit the while loop
            if (left >= right)
                break;

            // else, switch the positions of the indices
            double temp = data[left];
            data[left] = data[right];
            data[right] = temp;

            // and move them one element toward the center of the array
            left++;
            right--;
        }

        // swap left and end elements
        double temp = data[left];
        data[left] = data[end];
        data[end] = temp;

        // and return 'left', the optimal pivot position
        return left;
    }

    /**
     * A simple function which returns what it's passed, and increments the 'comparisons' field
     *
     * @param inputBool the boolean passed
     * @return the input boolean unchanged
     */
    private boolean advanceCount(boolean inputBool) {
        comparisons++;
        return inputBool;
    }

    /**
     * This method is like the 'Quicksort' method, wherein the passed array is made into a subarray and that subarray is
     * sorted using insertionSort
     *
     * @param data  an array of doubles to be sorted
     * @param start the index of the first value to be sorted
     * @param end   the index of the final value to be sorted
     */
    public void insertionSortSub(double[] data, int start, int end) {

        // initialize the subarray
        double[] subArray = new double[end - start + 1];
//        System.out.println("subArray size: "+subArray.length);

        // copy the elements from the start and end indexes of the input array 'data' to the created subarray
        for (int dataIndex = start, subIndex = 0; dataIndex <= end; dataIndex++, subIndex++) {
//            System.out.println("Start: "+start+", end: "+end+", subIndex: "+subIndex+", dataIndex: "+dataIndex);
            subArray[subIndex] = data[dataIndex];
        }

//        System.out.println("outside first insertion sort loop!");

        // sort the subarray using insertionSort
        insertionSort(subArray);

        // and copy the values from the newly sorted subarray back into 'data'
        for (int dataIndex = start, subIndex = 0; dataIndex <= end; dataIndex++, subIndex++) {
//            System.out.println("Start: "+start+", end: "+end+", subIndex: "+subIndex+", dataIndex: "+dataIndex);
            data[dataIndex] = subArray[subIndex];
        }
    }

    /**
     * Sorts the input array using insertion sort,
     * then returns a reference to the input array.
     * Note: this entire method was copied from Peter Jensen's SortExperiment.java for the CS 2420 Spring 2014 class
     *
     * @param d an array of doubles
     * @return the sorted array of doubles
     */
    public double[] insertionSort(double[] d) {
        for (int curr_pos = 1; curr_pos < d.length; curr_pos++) {
            double temp = d[curr_pos];
            int insertion_pos = curr_pos;
            while (insertion_pos > 0
                    && advanceCount(temp < d[insertion_pos - 1])) {
                d[insertion_pos] = d[insertion_pos - 1];
                insertion_pos--;
            }
            d[insertion_pos] = temp;
        }


        return d;
    }
}
