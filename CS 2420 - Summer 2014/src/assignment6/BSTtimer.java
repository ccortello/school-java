package assignment6;

import java.util.ArrayList;
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
//        problem3i();
        bstInsertRandomTime();
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

    void bstInsertRandomTime() {
        int timesToLoop = 100;  // higher number causes more accurate average time
        int maxSize = 100000;   // determines right boundary of plot
        Random rand = new Random(); // used to create random lists

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime\tavgTime");

        // testing loop
        for (int i = 0; i <= maxSize; i += 5000) {   // each of these loops accounts for a different input size 'N'

            // declare necessary variables and lists for testing
            ArrayList<Integer> intList = new ArrayList<Integer>(i);
            ArrayList<Integer> tempList = new ArrayList<Integer>(i);
            BinarySearchTree<Integer> randListBST = new BinarySearchTree<Integer>();
            long startTime, midTime, endTime;
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            // create list if Integers from 1 to 'i'
            for (int j = 1; j <= i; j++) {
                intList.add(j);
            }

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here.
            startTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                tempList = new ArrayList<Integer>(intList);
                for (int k = 0; k < i * 10; k++) {        //loops 10 times more to ensure good permuation
                    swapElements(tempList, rand.nextInt(i), rand.nextInt(i));
                }
                for (int k = 0; k < i; k++) {
                    randListBST.add(tempList.get(k));
                }
            }

            midTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                tempList = new ArrayList<Integer>(intList);
                for (int k = 0; k < i * 10; k++) {
                    swapElements(tempList, rand.nextInt(i), rand.nextInt(i));
                }
                for (int k = 0; k < i; k++) {
                    tempList.get(k);
                }
            }
            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results
        }
    }

    public static <T extends Comparable<? super T>> void swapElements(ArrayList<T> list, int left, int right) {
        // stored copy of left indexed element to temp
        T temp = list.get(left);
        // reassign left indexed element to a copy of the the right indexed element
        list.set(left, list.get(right));
        // replace right indexed element with previous value of left index stored in temp
        list.set(right, temp);
    }
}
