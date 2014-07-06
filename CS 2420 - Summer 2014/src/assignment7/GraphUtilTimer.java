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
        int timesToLoop = 5000;  // higher number causes more accurate average time
        int[] sizes = new int[]{5, 10, 20, 50, 100, 200, 500};
        Random rand = new Random(656794684984L); // used to create random lists

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + sizes[sizes.length - 1] + ", loops = " + timesToLoop + "\n\nsize\ttime\tavg");

        // testing loop
        for (int size : sizes) {   // each of these loops accounts for a different input size 'N'

//            BinarySearchTree<Integer> randListBST;
            long startTime, midTime, endTime;
            Graph testGraph;

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here.
            startTime = System.nanoTime();
            for (int j = 0; j < timesToLoop; j++) {
                testGraph = GraphUtil.buildGraphFromDotFile(size + ",2udacuw.dot");
                GraphUtil.breadthFirstSearch(testGraph, "v0", "v" + (size - 1));
            }

            midTime = System.nanoTime();
            for (int j = 0; j < timesToLoop; j++) {
                testGraph = GraphUtil.buildGraphFromDotFile(size + ",2udacuw.dot");
            }
            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / size;
            System.out.println(size + "\t" + totalTime + "\t" + avgTime);     // print results
        }
    }
}

