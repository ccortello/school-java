package assignment7;

import java.util.HashMap;
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
//        BFStimer();
        DFStimer();
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

    void DFStimer() {
        int timesToLoop = 20;  // higher number causes more accurate average time, but takes much longer
        int maxSize = 20000;   // determines right boundary of plot

        //print info for the max input size and the number of times looping, as well as column headers for results
        System.out.println("MaxSize = " + maxSize + ", loops = " + timesToLoop + "\n\nsize\ttime");

        // testing loops,each of these loops accounts for a different input size 'N'
        for (int i = 2000; i <= maxSize; i += 2000) {
            String name1, name2;
            long startTime, midTime, endTime;
            Graph testGraph;
            HashMap<String,Vertex> map;
            int toAdd;

            // this while loop runs for a full second to get things warmed up and running before timing starts
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) {/*empty*/}

            // startTime and testing start here
            startTime = System.nanoTime();
            for (int j = 0; j < timesToLoop; j++) {
                GraphUtil.generateGraphInDotFile("testGraph2.dot", i, 2, false, true, false);
                for (int k = 0; k < 5; k++) {
                    testGraph = GraphUtil.buildGraphFromDotFile("testGraph2.dot");
                    System.nanoTime();
//                    System.nanoTime();
//                    map = testGraph.getVertices();
//                    name1 = "v"; name2 = "v";
//                    toAdd = 0;
//                    while (! map.containsKey(name1))
//                        name1 = "v" + toAdd++;
//                    toAdd = i;
//                    while (! map.containsKey(name2) || name1 == name2)
//                        name2 = "v" + toAdd--;
                    try {
                        GraphUtil.depthFirstSearch(testGraph, "v" + ((int) (Math.random() * 10)), "v" + (i - ((int) (Math.random() * 10))));
                    }
                    catch (Exception e) {
                        continue;
                    }
                }
            }

            midTime = System.nanoTime();   // midTime is set to aid in subtracting overhead
            for (int j = 0; j < timesToLoop; j++) {
                GraphUtil.generateGraphInDotFile("testGraph2.dot", i, 2, false, true, false);
                for (int k = 0; k < 5; k++) {
                    testGraph = GraphUtil.buildGraphFromDotFile("testGraph2.dot");
                    System.nanoTime();
//                    map = testGraph.getVertices();
//                    name1 = "v"; name2 = "v";
//                    toAdd = 0;
//                    while (! map.containsKey(name1))
//                        name1 = "v" + toAdd++;
//                    toAdd = i;
//                    while (! map.containsKey(name2) || name1 == name2)
//                        name2 = "v" + toAdd--;System.nanoTime();
                    try {
                    }
                    catch (Exception e) {
                        continue;
                    }
                }
            }
            endTime = System.nanoTime();

            // subtract the over head and determine average time for 'i' calls to get.
            double totalTime = ((midTime - startTime) - (endTime - midTime)) / timesToLoop;
            double avgTime = totalTime / 10;
            System.out.println(i + "\t" + avgTime);     // print results
        }
    }
}

