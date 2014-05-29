package examples;

import java.util.Random;

/**
 *
 */
public class SortUtil {
    private static Random rand = new Random(System.currentTimeMillis());

    public static void selectionSort(int[] nums) {
        int minIndex;

        int numSteps = 0;

        // For every item in the unsorted array
        for (int i = 0; i < nums.length; i++) {
            minIndex = i;
            // Find the smallest item remaining
            for (int j = i + 1; j < nums.length; j++) {
                numSteps++;

                if (nums[j] < nums[minIndex])
                    minIndex = j;
            }
            // Swap smallest item with the first of the unsorted items
            swap(nums, minIndex, i);
        }

        System.out.println("took " + numSteps + " steps");
    }

    public static void insertionSort(int[] nums) {
        int numSteps = 0;

        for (int i = 1; i < nums.length; i++) {
            int val = nums[i];
            int j;

            for (j = i - 1; j >= 0 && nums[j] > val; j--) {
                nums[j + 1] = nums[j];
                numSteps++;
            }

            nums[j + 1] = val;
        }

        System.out.println("took " + numSteps + " steps");
    }

    public static void shellSort(int[] nums) {
        int numSteps = 0;

        for (int gap = nums.length / 2; gap > 0; gap /= 2)
            for (int i = gap; i < nums.length; i++) {
                int val = nums[i];
                int j;

                for (j = i - gap; j >= 0 && nums[j] > val; j -= gap) {
                    nums[j + gap] = nums[j];
                    numSteps++;
                }

                nums[j + gap] = val;
            }

        System.out.println("took " + numSteps + " steps");
    }

    // Generate an array of random integers [0..size - 1]
    public static int[] randomInts(int size) {
        int retval[] = new int[size];
        for (int i = 0; i < size; i++)
            retval[i] = rand.nextInt(size);
        return retval;
    }

    public static int[] permuteInts(int size) {
        int retval[] = ascendingInts(size);
        for (int i = 0; i < size; i++)
            swap(retval, i, rand.nextInt(size));
        return retval;
    }

    // Generate an array of ascending integers
    public static int[] ascendingInts(int size) {
        int retval[] = new int[size];
        for (int i = 0; i < size; i++)
            retval[i] = i;
        return retval;
    }

    // Generate an array of descending integers
    public static int[] descendingInts(int size) {
        int retval[] = new int[size];
        for (int i = 0; i < size; i++)
            retval[i] = size - i - 1;
        return retval;
    }

    // Swaps two items in the given array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void printArray(int[] nums) {
        for (int k = 0; k < nums.length; k++)
            System.out.print(nums[k] + " ");
        System.out.println();
    }

    public static int[] copyArray(int[] nums) {
        int retval[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            retval[i] = nums[i];
        return retval;
    }

    // Determines if an array is sorted
    public static boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++)
            if (nums[i] > nums[i + 1])
                return false;
        return true;
    }

} // Closes class declaration


