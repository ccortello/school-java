package assignment7;

import java.util.Random;

/**
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class GraphUtilTimer {
    public static void main(String[] args) {
        new GraphUtilTimer();
    }

    GraphUtilTimer() {
        BFStimer();
//        DFStimer();
//        dijkstrasTimer();
//        topologicalSortTimer();
    }

    void BFStimer() {
        int timesToLoop = 50;  // higher number causes more accurate average time
        int maxSize = 100000;   // determines right boundary of plot
        Random rand = new Random(); // used to create random lists

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime\tavg");

        // testing loop
        for (int i = 1000; i <= maxSize; i += 1000) {   // each of these loops accounts for a different input size 'N'

            BinarySearchTree<Integer> randListBST;
            long startTime, midTime, endTime;
            long seed = System.currentTimeMillis();
            rand.setSeed(seed);

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here.
            startTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                randListBST = new BinarySearchTree<Integer>();
                for (int k = 0; k < i; k++) {
                    randListBST.add(k);
                }
            }

            midTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                randListBST = new BinarySearchTree<Integer>();
                for (int k = 0; k < i; k++) {
                }
            }
            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / i;
            System.out.println(i + "\t" + totalTime + "\t" + avgTime);     // print results
        }
    }
}

