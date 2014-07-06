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
        int maxSize = 200;   // determines right boundary of plot
        Random rand = new Random(656794684984L); // used to create random lists

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime\tavg");

        // testing loop
        for (int i = 3; i <= maxSize; i += 10) {   // each of these loops accounts for a different input size 'N'

//            BinarySearchTree<Integer> randListBST;
            long startTime, midTime, endTime;
            long seed = 6565456165L;
            rand.setSeed(seed);

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            // startTime and testing start here.
            startTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                GraphUtil.generateGraphInDotFile("testGraph.dot", i, 3, false, true, false);
                Graph testGraph = GraphUtil.buildGraphFromDotFile("testGraph.dot");
                for (int k = 0; k < 20; k++) {
                    GraphUtil.breadthFirstSearch(testGraph, "v0", "v" + (i - 1));
                }
            }

            midTime = System.nanoTime();
            rand.setSeed(seed);
            for (int j = 0; j < timesToLoop; j++) {
                GraphUtil.generateGraphInDotFile("testGraph.dot", i, 3, false, true, false);
                Graph testGraph = GraphUtil.buildGraphFromDotFile("testGraph.dot");
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

