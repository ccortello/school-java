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
        int[] sizes = new int[]{100, 1000, 2000, 4000, 8000};
        Random rand = new Random(); // used to create random lists
        MyStack<Integer> peekTimingList;

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime\tavgTime");

        // testing loops
        for (int size : sizes) {  // each of these loops accounts for a different input size 'N'

            if (i == 0) i = 1000;// allows i to equal 1000 then 5000 and then even 5000 increments after.
            // declare necessary variables and lists for testing
            peekTimingList = new MyStack<Integer>();
            long startTime, midTime, endTime;
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            for (int j = 0; j < i; j++) {
                peekTimingList.push(rand.nextInt());
            }

            // this while loop runs for a full second to get things warmed up and running before timing starts
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) {
            }

            // startTime and testing start here
            startTime = System.nanoTime();
            for (int j = 0; j < timesToLoop; j++) {
                for (int k = 0; k < i; k++) {
                    peekTimingList.peek();
                }
            }
            midTime = System.nanoTime();   // midTime is set to aid in subtracting overhead
            for (int j = 0; j < timesToLoop; j++) {
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
}
