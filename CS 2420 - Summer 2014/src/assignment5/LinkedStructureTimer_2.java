package assignment5;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for timing analysis of the <tt>addFirst</tt>, <tt>get(i)</tt>, and <tt>remove</tt> methods from the
 * <tt>MyLinkedList</tt> class, the <tt>add</tt>, <tt>get(i)</tt>, and <tt>remove</tt> methods from a generic
 * <tt>ArrayList</tt>, and finally, timing analysis tests of the <tt>push</tt>, <tt>pop</tt>, and <tt>peek</tt> methods
 * from the <tt>MyStack</tt> class. Each method has a different way of timing that is appropriate for that particular
 * method to understand its complexity and growth rate.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 * @version 6/19/2014
 */
public class LinkedStructureTimer_2 {

    /**
     * Main method to call one of the timing test methods below. main only needs to call the methods below, one at a
     * time for timing analysis. Each method tests a different method from MyLinkedList corresponding to the required
     * methods from the analysis handout
     *
     * @param args Not used, there is no input from the console.
     */
    public static void main(String[] args) {
//        addFirstTimer();
//        addTimer();
//        linkedGetTimer();
//        arrayGetTimer();
//        linkedRemoveTimer();
//        arrayRemoveTimer();
//        pushTimer();
//        popTimer();
//        peekTimer();
    }

    /**
     * Tests the <tt>addFirst</tt> method of the <tt>MyLinkedList</tt> class to determine it's running time complexity.
     */
    static void addFirstTimer() {
        int timesToLoop = 2500;  // higher number causes more accurate average time, but takes longer
        int maxSize = 100000;   // determines right boundary of plot, or MAX inputs 'N'
        Random rand = new Random();  // used to create random lists

        //print info for the max input size and the number of times looping
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop);

        // print column headers for the output data
        System.out.println("\nsize" + "\ttime");

        // testing loops
        for (int i = 0; i <= maxSize; i += 5000) {  // each of these loops accounts for a different input size 'N'
            // allows i to equal 1000 then 5000 and then even 5000 increments after.
            if (i == 0) i = 1000;

            // declare necessary variables and lists for testing
            MyLinkedList<Integer> addFirstList = new MyLinkedList<Integer>();
            int[] randNum = new int[i * timesToLoop];
            long startTime, midTime, endTime;
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            // create array of random numbers for addFirstList to add from, before any timing starts.
            for (int j = 0; j < timesToLoop * i; j++) {
                randNum[j] = rand.nextInt();
            }

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing starts here.
            startTime = System.nanoTime();

            for (int j = 0; j < timesToLoop; j++) {  //timesToLoop helps take an average of i inputs
                for (int k = 0; k < i; k++) {               //number of inputs depends on i
                    addFirstList.addFirst(randNum[j * k]);
                }
                addFirstList = new MyLinkedList<Integer>();
            }

            midTime = System.nanoTime();      // middle time is set, to subtract startTime from.

            for (int j = 0; j < timesToLoop; j++) {     // same loops without addFirst to determine overhead to subtract
                for (int k = 0; k < i; k++) {
                }
                addFirstList = new MyLinkedList<Integer>();
            }

            endTime = System.nanoTime();

            // determine total amount of time to add 'on average' to add i amount of inputs
            double avgTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            System.out.println(i + "\t" + avgTime);     // print results to screen

            if (i == 1000) i = 0; // used to allows 1000 to 5000 then even increments of 5000
        }
    }

    /**
     * Test the <tt>add(int index, E element)</tt> method of an <tt>ArrayList</tt> to determine it's running time
     * complexity, as well as compare it's results that of the LinkedList.
     */
    static void addTimer() {
        int timesToLoop = 50;  // higher number causes more accurate average time
        int maxSize = 100000;   // determines right boundary of plot
        Random rand = new Random(); // used to create random lists

        //print info for the max input size and the number of times looping
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop);

        // print column headers for the output data. Input Size, total time, & average time or growth rate
        System.out.println("\nsize\ttime\tavg");

        // testing loop
        for (int i = 0; i <= maxSize; i += 5000) {   // each of these loops accounts for a different input size 'N'
            // allows i to equal 1000 then 5000 and then even 5000 increments after.
            if (i == 0) i = 1000;

            // declare necessary variables and lists for testing
            ArrayList<Integer> addList = new ArrayList<Integer>(i);
            int[] randNum = new int[i * timesToLoop];
            long startTime, midTime, endTime;
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            // create array of random numbers for addList to add from, before any timing starts.
            for (int j = 0; j < timesToLoop * i; j++) {
                randNum[j] = rand.nextInt();
            }

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here.
            startTime = System.nanoTime();

            for (int j = 0; j < timesToLoop; j++) {   //timesToLoop helps take an average of i inputs
                for (int k = 0; k < i; k++) {         //number of inputs depends on i
                    addList.add(0, randNum[j * k]);
                }
                addList = new ArrayList<Integer>(i);
            }

            midTime = System.nanoTime();        // middle time is set, to subtract startTime from.

            for (int j = 0; j < timesToLoop; j++) {  // same loops without addFirst to determine overhead to subtract
                for (int k = 0; k < i; k++) {
                }
                addList = new ArrayList<Integer>(i);
            }

            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results

            if (i == 1000) i = 0;
        }
    }

    /**
     * Tests the <tt>get(int index)</tt> method of the <tt>MyLinkedList</tt> class to determine it's running time
     * complexity.
     */
    static void linkedGetTimer() {
        int timesToLoop = 20;  // higher number causes more accurate average time, but takes much much longer
        int maxSize = 100000;   // determines right boundary of plot
        Random rand = new Random(); // used to create random lists

        //print info for the max input size and the number of times looping
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop);

        // print column headers for the output data
        System.out.println("\nsize\ttime\tavg");

        // testing loops
        for (int i = 0; i <= maxSize; i += 10000) {  // each of these loops accounts for a different input size 'N'

            // allows i to equal 1000 then 5000 and then even 5000 increments after.
            if (i == 0) i = 1000;
            // declare necessary variables and lists for testing
            MyLinkedList<Integer> linkedGetList = new MyLinkedList<Integer>();
            long startTime, midTime, endTime;
//            int[] indexArray = new int[i];
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            // add random numbers to linkedGetList until size equals i, this so the get method can be tested.
            for (int j = 0; j < i; j++) {
                linkedGetList.addFirst(rand.nextInt(i));
            }

            // this while loop runs for a full second to get things warmed up and running before timing starts
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here
            startTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                for (int k = 0; k < i; k++) {
                    linkedGetList.get(rand.nextInt(i));  //'get' will be called i times for timesToLoop times for a
                }                                           //good average time for i
            }

            midTime = System.nanoTime();   // midTime is set to aid in subtracting overhead
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {     // same loops run again without .get() to determine overhead
                for (int k = 0; k < i; k++) {
                    rand.nextInt(i);
                }
            }

            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results

            if (i == 1000) i = 0;  // used to allows 1000 to 5000 then even increments of 5000
        }
    }

    /**
     * Test the <tt>get</tt> method of an <tt>ArrayList</tt> to determine it's run time complexity and compare it to the
     * linked list. Expected get(i) runtime is 0(1) or constant time.
     */
    static void arrayGetTimer() {
        int timesToLoop = 10;  // higher number causes more accurate average time
        int maxSize = 10000000;   // right boundry is extremely high to show ArrayLists constant complexity with get(i)

        //print info for the max input size and the number of times looping
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop);

        // print column headers for the output data
        System.out.println("\nsize\ttime\tavg");

        // testing loops
        for (int i = 0; i <= maxSize; i += 500000) {  // each of these loops accounts for a different input size 'N'

            if (i == 0) i = 1000;             // allows i to equal 1000 then 5000 and then even 5000 increments after.
            int randNum = 0;
            // declare necessary variables and lists for testing
            ArrayList<Integer> arrayGetList = new ArrayList<Integer>(i);
            long startTime, midTime, endTime;

            // add random numbers to linkedGetList until size equals i, this so the get method can be tested.
            for (int j = 0; j < i; j++) {
                arrayGetList.add((int) (Math.random() * Integer.MAX_VALUE));
            }

            // this while loop runs for a full second to get things warmed up and running before timing starts
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { //empty block
            }

            // startTime and testing start here
            startTime = System.nanoTime();
            for (int j = 0; j < timesToLoop; j++) {
                for (int k = 0; k < i; k++) {
                    randNum = (int) (Math.random() * i);
                    arrayGetList.get(randNum);  //'get' will be called i times for timesToLoop times for a
                }                                           //good average time for i
            }

            midTime = System.nanoTime();   // midTime is set to aid in subtracting overhead
            for (int j = 0; j < timesToLoop; j++) {     // same loops run again without .get() to determine overhead
                for (int k = 0; k < i; k++) {
                    randNum = (int) (Math.random() * i);
                }
            }

            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results

            if (i == 1000) i = 0;  // used to allows 1000 to 5000 then even increments of 5000
        }
    }

    static void linkedRemoveTimer() {
        int timesToLoop = 10;  // higher number causes more accurate average time
        int maxSize = 100000;   // determines right boundary of plot
        Random rand = new Random(); // used to create random lists

        //print info for the max input size and the number of times looping
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop);

        // print column headers for the output data
        System.out.println("\nsize\ttime\tavg");

        // testing loops
        for (int i = 0; i <= maxSize; i += 5000) {  // each of these loops accounts for a different input size 'N'

            // allows i to equal 1000 then 5000 and then even 5000 increments after.
            if (i == 0) i = 1000;
            // declare necessary variables and lists for testing
            MyLinkedList<Integer> linkedRemoveList = new MyLinkedList<Integer>();
            MyLinkedList<Integer> tempList = new MyLinkedList<Integer>();
            long startTime, midTime, endTime;
            int[] indexArray = new int[i];
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            // add random numbers to linkedGetList until size equals i, this so the get method can be tested.
            for (int j = 0; j < i; j++) {
                tempList.addFirst(rand.nextInt());
            }

            // this array is to have pre-set random index for linkedGetList to use with get.
            for (int j = 0; j < i; j++) {
                indexArray[j] = rand.nextInt(i);
            }

            // this while loop runs for a full second to get things warmed up and running before timing starts
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here
            startTime = System.nanoTime();

            for (int j = 0; j < timesToLoop; j++) {
                for (int k = 0; k < i; k++) {
                    linkedRemoveList.get(indexArray[k]);  //'get' will be called i times for timesToLoop times for a
                }                                           //good average time for i
            }

            midTime = System.nanoTime();   // midTime is set to aid in subtracting overhead

            for (int j = 0; j < timesToLoop; j++) {     // same loops run again without .get() to determine overhead
                for (int k = 0; k < i; k++) {
                }
            }

            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results

            if (i == 1000) i = 0;  // used to allows 1000 to 5000 then even increments of 5000
        }
    }

    static void arrayRemoveTimer() {
        //TODO: arrayRemoveTimer
    }

    static void pushTimer() {
        //TODO: pushTimer
    }

    static void popTimer() {
        //TODO: popTimer
    }

    static void peekTimer() {
        //TODO: peekTimer
    }
}
