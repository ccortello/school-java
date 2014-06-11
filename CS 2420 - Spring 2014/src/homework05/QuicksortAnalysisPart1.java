package homework05;

/**
 * Implements and tests the 'Quicksort' algorithm
 *
 * @author Cody Cortello
 * @version 2/17/2014
 */
public class QuicksortAnalysisPart1 {

    // the number of comparisons is a field simply to allow access outside of the for loops which modify it
    int comparisons = 0;

    public static void main(String[] args) {
        new QuicksortAnalysisPart1();
    }

    public QuicksortAnalysisPart1() {

        // print the report header
        System.out.println("Cody Cortello\nCS 2420\n\nSize\tComparisons");

        // sort arrays from size [3,20] inclusive
        for (int arraySize = 0; arraySize <= 20; arraySize++) {

            // store the total comparisons across all 20 trials
            int totalComparisons = 0;

            // run the sort on a new array of size 'arraySize' 20 times to find the average number of comparisons made
            // when sorting an array of that size
            for (int sortNumber = 1; sortNumber <= 50; sortNumber++) {

                // reset the number of comparisons for the current sort
                comparisons = 0;

                // create a random array of data and sort it
                double[] sortArray = createDoubleArray(arraySize);
                quicksort(sortArray);

                // add the number of comparisons to the totalComparison count for average comparison calculation
                totalComparisons += comparisons;
            }

            // compute the average number of comparisons
            double averageComparisons = totalComparisons / 50.0;

            // print tab-delimited results of the sorting
            System.out.println(arraySize + "\t" + averageComparisons);
        }

        /* This next section is literally copy-pasted code from above with the index of the outermost for loop slightly altered */

        // sort arrays of size 2^n, where n goes from 0 to 16 inclusive. That is, sort arrays of size {1, 2, 4, 8, 16,
        // 32, 64, ... 65536}.
        for (int arraySize = 1; arraySize <= 65536; arraySize *= 2) {

            // store the total comparisons across all 20 trials
            int totalComparisons = 0;

            // run the sort on a new array of size 'arraySize' 20 times to find the average number of comparisons made
            // when sorting an array of that size
            for (int sortNumber = 1; sortNumber <= 50; sortNumber++) {

                // reset the number of comparisons for the current sort
                comparisons = 0;

                // create a random array of data and sort it
                double[] sortArray = createDoubleArray(arraySize);
                quicksort(sortArray);

                // add the number of comparisons to the totalComparison count for average comparison calculation
                totalComparisons += comparisons;
            }

            // compute the average number of comparisons
            double averageComparisons = totalComparisons / 50.0;

            // print tab-delimited results of the sorting
            System.out.println(arraySize + "\t" + averageComparisons);
        }

        double[] millionArray = createDoubleArray(1000000);
        long startTime = System.nanoTime();
        quicksort(millionArray);
        long endTime = System.nanoTime();
        double millionTotalTime = (endTime - startTime) / 1000000000.0;
        System.out.println("\nMil Time: " + millionTotalTime);
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
        for (int i = 0; i < n; i++) {
            returnArray[i] = Math.random() * 10;
        }

        // return array
        return returnArray;
    }

    /**
     * A simple method which returns an array of size n with random ints from [0,10)
     *
     * @param n the size of the array
     * @return an array of ints randomized from [0,10)
     */
    public static double[] createIntArray(int n) {

        // initialize array
        double[] returnArray = new double[n];

        // fill array with doubles
        for (int i = 0; i < n; i++) {
            returnArray[i] = (int) Math.random() * 10;
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

        // handle base case and trivial sorts
        if (end - start + 1 < 2)
            return;

//        // implement insertion sort if the array has fewer than 10 elements
//        else if (end - start + 1 < 10) {
//            insertionSortSub(data, start, end);
//        }

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
     * Partitions an array for use in the quicksort algorithm
     */
    private int partition(double[] data, int start, int end) {

        // store the value of the pivot, assuming the pivot to be the last element in the array
        double pivot = data[end];

        // initialize the 'left' and 'right' indices
        int left = start;
        int right = end - 1;

        // start infinite loop, will be ended with the break statement
        while (true) {

            // increment 'left' to find the first value in the array larger than the pivot

            //  Note: advanceCount is used to increment the comparisons counter, and doesn't change the boolean it is passed
            while (advanceCount(data[left] < pivot))
                left++;

            //
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
