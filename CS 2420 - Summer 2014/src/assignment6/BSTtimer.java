package assignment6;

import java.util.Random;

/**
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class BSTtimer {
    public static void main(String[] args) {
        new BSTtimer();
    }

    BSTtimer() {
        problem3i();
    }

    void problem3i() {
        int timesToLoop = 20;  // higher number causes more accurate average time, but takes much longer
        int[] sizes = new int[]{100, 1000, 2000, 4000, 8000, 10000, 20000, 50000, 100000};
        long startTime, midTime, endTime;

        Random rand = new Random(); // used to create random lists
        long seed = 783789742385993847L;
        rand.setSeed(seed);

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("Sizes = " + sizes + ", loops = " + timesToLoop + "\n\nsize\ttime\tavgTime");

        // loop through each array size
        for (int size : sizes) {  // each of these loops accounts for a different input size 'N'

            // declare necessary variables and lists for testing
            BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>(size);
            int[] addArray = new int[size];
            for (int i : addArray)
                addArray[i] = i;

            startTime = System.nanoTime();
            // this while loop runs for a full second to get things warmed up and running before timing starts
            while (System.nanoTime() - startTime < 1e9) {
            }

            // start the tests
            startTime = System.nanoTime();
            for (int j = 0; j < timesToLoop; j++) {
                for (int addInt : addArray) // add each integer from the array
                    testBST.add(addInt);
                testBST = new BinarySearchTree<Integer>(size); // then reset the BST for the next loop
            }

            // find the testing overhead (operations separate from the one being tested)
            midTime = System.nanoTime();
            for (int j = 0; j < timesToLoop; j++) {
                testBST = new BinarySearchTree<Integer>(size);
            }

            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / size;
            System.out.println(size + "\t" + totalTime + "\t" + avgTime);     // print results
        }
    }
}
