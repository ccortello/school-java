package homework05;

/**
 * Implements and tests the 'Quicksort' algorithm
 *
 * @author Cody Cortello
 * @version 2/17/2014
 */
public class QuicksortAnalysisPart2 {

    // the number of comparisons is a field simply to allow access outside of the for loops which modify it
    long comparisons = 0;

    public static void main(String[] args) {
        new QuicksortAnalysisPart2();
    }

    public QuicksortAnalysisPart2() {

        // print the report header
        System.out.println("Cody Cortello\nCS 2420\nHomework 5\nPart 2\nSize\tComparisons");

        try { // for stack overflow
            // sort arrays from size [0,20] inclusive
            for (int arraySize = 0; arraySize <= 20; arraySize++) {

                // store the total comparisons across all 50 trials
                long totalComparisons = 0;

                // run the sort on a new array of size 'arraySize' 50 times to find the average number of comparisons made
                // when sorting an array of that size
                for (int sortNumber = 1; sortNumber <= 50; sortNumber++) {

                    // reset the number of comparisons for the current sort
                    comparisons = 0;

                    // create a random array of data and sort it
                    double[] sortArray = createIntArray(arraySize);
                    quicksort(sortArray);

                    // add the number of comparisons to the totalComparison count for average comparison calculation
                    totalComparisons += comparisons;
                }

                // compute the average number of comparisons
                double averageComparisons = totalComparisons / 50.0;

                // print tab-delimited results of the sorting
                System.out.println(arraySize + "\t" + averageComparisons);
            }

        	/* This next section is copy-pasted code from above with the index of the outermost for loop slightly altered */

            // sort arrays of size 2^n, where n goes from 0 to 16 inclusive. That is, sort arrays of size {1, 2, 4, 8, 16, 32, 64, ... 65536}.
            for (int arraySize = 1; arraySize <= 65536; arraySize *= 2) {

                // store the total comparisons across all 20 trials
                long totalComparisons = 0;

                // run the sort on a new array of size 'arraySize' 50 times to find the average number of comparisons made
                // when sorting an array of that size
                for (int sortNumber = 1; sortNumber <= 50; sortNumber++) {

                    // reset the number of comparisons for the current sort
                    comparisons = 0;

                    // create a random array of data and sort it
                    double[] sortArray = createIntArray(arraySize);
                    quicksort(sortArray);

                    // add the number of comparisons to the totalComparison count for average comparison calculation
                    totalComparisons += comparisons;
                }

                // compute the average number of comparisons
                double averageComparisons = totalComparisons / 50.0;

                // print tab-delimited results of the sorting
                System.out.println(arraySize + "\t" + averageComparisons);
            }
        } catch (StackOverflowError e) {
            System.out.println("\n\n-----------------\nStack overflow!\n-----------------");
        }
    }

    /**
     * A simple method which returns an array of size n with random doubles from [0,1)
     *
     * @param n the size of the array
     * @return an array of doubles randomized from [0,1)
     */
    public static double[] createIntArray(int n) {

        // initialize array
        double[] returnArray = new double[n];

        // fill array with ints
        for (int i = 0; i < n; i++)
            returnArray[i] = (int) (Math.random() * 10);

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

        // find the middle value for the pivot
        int mid = partition(data, start, end);

        // use recursive calls to sort the array before the pivot and after the pivot
        qs(data, start, mid - 1);
        qs(data, mid + 1, end);
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
     * A simple function which returns the boolean it's passed, and increments the 'comparisons' field
     *
     * @param inputBool the boolean passed
     * @return the input boolean unchanged
     */
    private boolean advanceCount(boolean inputBool) {
        comparisons++;
        return inputBool;
    }
}
