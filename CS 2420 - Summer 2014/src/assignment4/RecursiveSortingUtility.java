package assignment4;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 *         <p/>
 *         This sorting utility class provides static methods for recursive sorting
 *         <p/>
 *         Merge sort methods for threshold setting, driving, recursing, and merging
 *         Quicksort methods for driving, recursing, and finding different pivots
 *         Input generators for creating ascending, descending, and permuted lists
 */
public class RecursiveSortingUtility {
    private static int mergesortThreshold = 0;
    private static int seed = 802349812;

    /**
     * Helper method for setting the switching threshold for merge sort
     *
     * @param desiredThreshold - merge sort switching threshold
     */
    public static void setMergeortThreshold(int desiredThreshold) {
        mergesortThreshold = desiredThreshold;
    }

    /**
     * Iterative insertion sort method
     *
     * @param list  - input ArrayList of objects, must have a Comparable implementation
     * @param start - start index of the subarray of objects
     * @param end   - end index of the subarray of objects
     */
    private static <T extends Comparable<? super T>> void insertionSortIterative(ArrayList<T> list, int start, int end) {
        // i index is the first element on the unsorted side of list
        // the list section is the portion of list from indexes start to end inclusive.
        for (int i = start + 1; i <= end; i++) {
            // store value of item to insert
            T val = list.get(i);
            int j;
            // copy elements to the right until element is found that is < val
            for (j = i - 1; j >= start && list.get(j).compareTo(val) > 0; j--)
                list.set(j + 1, list.get(j));
            // insert val at one index above the first element that is less than val
            list.set(j + 1, val);
        }
    }

    /**
     * Recursive merge sort driver method
     *
     * @param list - input ArrayList of objects, must have a Comparable implementation
     */
    public static <T extends Comparable<? super T>> void mergeSortDriver(ArrayList<T> list) {
        mergeSortRecursive(list, new ArrayList<T>(list.size()), 0, list.size() - 1);
    }

    /**
     * Recursive merge sort algorithm method
     *
     * @param list  - input ArrayList of T objects that must have a Comparable implementation
     * @param temp  - temporary ArrayList of T objects to help with merging
     * @param start - start index of the subarray of objects
     * @param end   - end index of the subarray of objects
     */
    private static <T extends Comparable<? super T>> void mergeSortRecursive(ArrayList<T> list, ArrayList<T> temp, int start, int end) {

        // handle base case when list portion reaches size of mergsortThreshold.
        if (end - start <= mergesortThreshold && mergesortThreshold > 1) {
            insertionSortIterative(list, start, end);
            return;
        }

        // check this condition if mergesortThreshold is set to 1 or 0
        if (start >= end)
            return;

        int mid = (start + end) / 2;
        mergeSortRecursive(list, new ArrayList<T>(mid - start + 1), start, mid);  //left half
        mergeSortRecursive(list, new ArrayList<T>(end - mid), mid + 1, end);  //right half

        mergeSortedPortions(list, temp, start, mid, end);  //merge halves
    }

    /**
     * Recursive merge sort helper method
     *
     * @param list   - input ArrayList of T objects that must have a Comparable implementation
     * @param temp   - temporary ArrayList in  which the result will be placed
     * @param start  - start index of the subarray of objects
     * @param middle - middle index of the subarray of objects
     * @param end    - end index of the subarray of objects
     */
    private static <T extends Comparable<? super T>> void mergeSortedPortions(ArrayList<T> list, ArrayList<T> temp, int start, int middle, int end) {
        // set new variables to start and end of each half
        int leftBegin = start;
        int leftEnd = middle;
        int rightBegin = middle + 1;
        int rightEnd = end;

        while (leftBegin <= leftEnd && rightBegin <= rightEnd) {
            if (list.get(leftBegin).compareTo(list.get(rightBegin)) < 0) {
                temp.add(list.get(leftBegin++));
            } else if (list.get(leftBegin).compareTo(list.get(rightBegin)) > 0)
                temp.add(list.get(rightBegin++));
            else {
                temp.add(list.get(leftBegin++));
                temp.add(list.get(rightBegin++));
            }
        }
        for (int i = leftBegin; i <= leftEnd; i++)
            temp.add(list.get(i));

        for (int i = rightBegin; i <= rightEnd; i++)
            temp.add(list.get(i));

        for (int i = start, j = 0; i <= end && j <= (end - start); i++, j++)
            list.set(i, temp.get(j));
    }

    /**
     * Recursive quicksort driver method
     *
     * @param list - input ArrayList of T objects that must have a Comparable implementation
     */
    public static <T extends Comparable<? super T>> void quickSortDriver(ArrayList<T> list) {
        quickSortRecursive(list, 0, list.size() - 1);
    }

    /**
     * Recursive quicksort algorithm method
     *
     * @param list  - input ArrayList of T objects that must have a Comparable implementation
     * @param start - start index of the subarray of objects
     * @param end   - end index of the subarray of objects
     */
    private static <T extends Comparable<? super T>> void quickSortRecursive(ArrayList<T> list, int start, int end) {

        // handle base case and trivial sorts
        if (end - start + 1 < 2)
            return;

        // find an index for the pivot
        int mid = goodPivotStrategy(list, start, end);
//        int mid = betterPivotStrategy(list, start, end);
//        int mid = bestPivotStrategy(list, start, end);


        // store the value of the pivot in order to make comparisons
        T pivot = list.get(mid);

        // swap pivot to end
        swapElements(list, mid, end);

        // initialize the 'left' and 'right' indices
        int left = start;
        int right = end - 1;

        // start infinite loop, will be ended with the break statement
        while (true) {

            // increment left until an element which needs to be swapped is found
            while (list.get(left).compareTo(pivot) < 0)
                left++;

            // decrement right until an element which needs to be swapped is found
            while (right > left && list.get(right).compareTo(pivot) >= 0)
                right--;

            // stop moving pointers when 'left' index equals or passes 'right'
            if (left >= right)
                break;

            // swap left and right elements
            swapElements(list, left, right);

            // move the positions of right and left toward the pivot and try swapping again
            left++;
            right--;
        }

        // swap left and end elements
        swapElements(list, end, left);

        // use recursive calls to sort the array before the pivot and after the pivot
        quickSortRecursive(list, start, left - 1);
        quickSortRecursive(list, left + 1, end);
    }

    /**
     * Recursive quicksort helper method
     *
     * @param list  - input ArrayList of T objects that must have a Comparable implementation
     * @param start - start index of the subarray  of objects
     * @param end   - end index of the subarray  of objects
     * @return index of chosen pivot
     */
    public static <T extends Comparable<? super T>> int goodPivotStrategy(ArrayList<T> list, int start, int end) {
        int pivotIndex = (start + end) / 2;
        return pivotIndex;
    }

    /**
     * Recursive quicksort helper method
     *
     * @param list  - input ArrayList of T objects that must have a Comparable implementation
     * @param start - start index of the subarray  of objects
     * @param end   - end index of the subarray  of objects
     * @return index of chosen pivot
     */
    public static <T extends Comparable<? super T>> int betterPivotStrategy(ArrayList<T> list, int start, int end) {
        int range = (end - start) + 1;
        Random rand = new Random(seed);
        int pivotIndex = rand.nextInt() * range + start;

        return pivotIndex;
    }

    /**
     * Recursive quicksort helper method
     *
     * @param list  - input ArrayList of T objects that must have a Comparable implementation
     * @param start - start index of the subarray  of objects
     * @param end   - end index of the subarray  of objects
     * @return index of chosen pivot
     */
    public static <T extends Comparable<? super T>> int bestPivotStrategy(ArrayList<T> list, int start, int end) {
        int range = (end - start) + 1;
        Random rand = new Random(seed);
        int a = (rand.nextInt() * range) + start;
        int b = (rand.nextInt() * range) + start;
        int c = (rand.nextInt() * range) + start;

        if (list.get(a).compareTo(list.get(b)) >= 0) {
            if (list.get(b).compareTo(list.get(c)) >= 0)
                return b;
            if (list.get(a).compareTo(list.get(c)) >= 0)
                return c;
            else
                return a;
        }
        if (list.get(a).compareTo(list.get(b)) <= 0) {
            if (list.get(b).compareTo(list.get(c)) <= 0)
                return b;
            if (list.get(a).compareTo(list.get(c)) <= 0)
                return c;
        }
        return a;
    }

    /**
     * Best case input generation helper method
     *
     * @param size size of the returned ArrayList
     * @return an ArrayList of integers in sorted, ascending order.
     */
    public static ArrayList<Integer> generateBestCase(int size) {
        ArrayList<Integer> temp = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++)
            temp.add((int) (Math.random() * 10 + i * 10));

        return temp;
    }

    /**
     * Average case input generation helper method
     *
     * @param size the size of the returned ArrayList
     * @return An ArrayList of random integers from 0-size in permuted order
     */
    public static ArrayList<Integer> generateAverageCase(int size) {
        ArrayList<Integer> temp = new ArrayList<Integer>(size);
        Random rand = new Random(seed);
        for (int i = 0; i < size; i++)
            temp.add(rand.nextInt() * Integer.MAX_VALUE);

        return temp;
    }

    /**
     * Worst case nput generation helper method
     *
     * @param size the size of the returned ArrayList
     * @return An ArrayList of integers in descending order
     */
    public static ArrayList<Integer> generateWorstCase(int size) {
        ArrayList<Integer> temp = new ArrayList<Integer>(size);
        Random rand = new Random(seed);
        for (int i = size; i > 0; i--) {
            temp.add(i * 10 - (rand.nextInt() * 10));
        }
        return temp;
    }

    /**
     * ArrayList elements swapping Helper method
     *
     * @param list  - input ArrayList of objects, must have a Comparable implementation
     * @param left  - index of the left element
     * @param right - index of the right element
     */
    private static <T extends Comparable<? super T>> void swapElements(ArrayList<T> list, int left, int right) {
        // stored copy of left indexed element to temp
        T temp = list.get(left);
        // reassign left indexed element to a copy of the the right indexed element
        list.set(left, list.get(right));
        // replace right indexed element with previous value of left index stored in temp
        list.set(right, temp);
    }
}
