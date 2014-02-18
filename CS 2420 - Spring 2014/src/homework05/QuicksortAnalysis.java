package homework05;

/**
 * Implements and tests the 'Quicksort' algorithm
 *
 * @author Cody Cortello
 * @version 2/17/2014
 */
public class QuicksortAnalysis {
    public static void main(String[] args) {
        double[] randomArray = createDoubleArray(20);

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
        qs(data, 0, data.length);
    }

    /**
     * Sorts an array of doubles from the start index to the end index using a recursive Quicksort algorithm
     *
     * @param data  An array of doubles to be sorted
     * @param start The index of the first element to be sorted
     * @param end   The index of the final element to be sorted
     */
    public void qs(double[] data, int start, int end) {

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

            // find the correct value for left
            while (data[left] < pivot)
                left++;

            while (right > left && data[right] >= pivot)
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
}
