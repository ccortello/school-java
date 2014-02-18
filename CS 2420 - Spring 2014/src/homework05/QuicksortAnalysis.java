package homework05;

/**
 * Implements and tests the 'Quicksort' algorithm
 *
 * @author Cody Cortello
 * @version 2/17/2014
 */
public class QuicksortAnalysis {

    int comparisons = 0;

    public static void main(String[] args) {
        new QuicksortAnalysis();
    }

    public QuicksortAnalysis() {

        // print the report header
        System.out.println("Cody Cortello\nCS 2420\n\nSize\tn");

        // sort arrays from size [0,20] inclusive
        for (int arraySize = 0; arraySize <= 20; arraySize++) {

            // store the total comparisons across all 20 trials
            int totalComparisons = 0;

            // run the sort on a new array of size 'arraySize' 20 times to find the average number of comparisons made
            // when sorting an array of that size
            for (int sortNumber = 1; sortNumber <= 20; sortNumber++) {
                // reset the number of comparisons for the current sort
                comparisons = 0;

                // create a random array of data and sort it
                double[] sortArray = createDoubleArray(arraySize);
                quicksort(sortArray);

                // add the number of comparisons to the totalComparison count for average comparison calculation
                totalComparisons += comparisons;
            }

            // compute the average number of comparisons
            int averageComparisons = totalComparisons / 20;

            // print the tab-delimited results of the sorting
            System.out.println(arraySize + "\t" + averageComparisons);
        }
    }

    public static double[] createDoubleArray(int n) {

        // initialize array
        double[] returnArray = new double[n];

        // fill array with doubles
        for (int i = 0; i < n; i++) {
            returnArray[i] = Math.random();
        }

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

        // handle trivial sorts
        if (end - start < 1)
            return;

        // find the middle value for the pivot
        int mid = partition(data, start, end);

        // use recursive calls to sort the array before the pivot and after the pivot
        qs(data, start, mid - 1);
        qs(data, mid + 1, end);
    }

    /**
     * Partitions an array for use in the quicksort algorithm
     */
    private int partition(double[] data, int start, int end) {

        // store the value of the pivot, assuming the pivot to be the last element in the array
        double pivot = data[end];

        // store the leftmost and rightmost points in the array
        int left = start;
        int right = end - 1;

        // start infinite loop, will be ended with the break statement
        while (true) {

            // increment 'left' and find the first value in the array larger than the pivot
            //  Note: advanceCount is used to increment the comparisons counter, and
            //  doesn't change the boolean it is passed
            while (advanceCount(data[left] < pivot))
                left++;

            while (right > left && advanceCount(data[right] >= pivot))
                right--;

            if (left >= right)
                break;

            // swap left and right elements
            double temp = data[left];
            data[left] = data[right];
            data[right] = temp;

            // move the positions of right and left toward the pivot
            left++;
            right--;
        }

        // swap left and end elements
        double temp = data[left];
        data[left] = data[end];
        data[end] = temp;

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
}
