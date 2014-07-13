package assignment8;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * class to perform the timing analysis of assignment 8
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class HashTableTimer {
	static int MAX_TABLE_SIZE = 1000000;
	static int TIMES_TO_LOOP = 10;
	static int INTERVAL = 10000;
	static int ADDED_ITEMS = 5000;
	static boolean printAsRuns = true;
	static boolean printAtEnd = false;
	static boolean printToFile = false;
	static long seed = 82314917283974983L;

    public static void main(String[] args) {
        try {
//            BadHashFunctionTime();
//            FairHashFunctionTime();
//            GoodHashFunctionTime();
	        BadHashProbingTableTime();
        } catch (Exception e) {System.out.println(e.getMessage() + "\n" + e.getStackTrace());}
    }

	public static void BadHashFunctionTime() throws Exception {
		long startTime, midTime, endTime;
        int dataIndex = 0;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("BadHashFunctionTime" +
				".txt"), "utf-8"));

        int[] sizeData = new int[MAX_TABLE_SIZE / INTERVAL + 1];
        double[] totalTimeData = new double[MAX_TABLE_SIZE / INTERVAL + 1];

        HashFunctor hasher = new BadHashFunctor();

        // print header
		if (printAsRuns || printAtEnd)
			System.out.println("Timing trial for BadHashFunctor()\nHashes\tTotal time (s)\tAverage Time (ns)");
		if (printToFile)
			writer.write("Timing trial for BadHashFunctor()\nHashes	Total time	Average Time");

        // table sizes
		for (int i = 0; i <= MAX_TABLE_SIZE; i += INTERVAL) {
			if (i == 0) i = 1000;

            int combined = i * TIMES_TO_LOOP;
            String[] randomStrings = stringArray(combined);
            String temp;
            int index;

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            startTime = System.nanoTime();
            index = 0;
//            for (int j = 0; j < TIMES_TO_LOOP; j++) {
            for (int k = 0; k < combined; k++) {
                temp = randomStrings[index++];
                // this next statement is what is dependent on the hashtable size.
                int hashIndex = hasher.hash(temp) % i;
            }
//            }

            midTime = System.nanoTime();
            index = 0;
			for (int k = 0; k < combined; k++)
				temp = randomStrings[index++];

            endTime = System.nanoTime();

            // calculate the total time and the average time
			double totalTime = (double) (2 * midTime - startTime - endTime) / 1e9;
			double avgTime = totalTime * 1e9 / combined;

            // store the times to be printed after execution completes
			if (printAtEnd) {
				sizeData[dataIndex] = i;
				totalTimeData[dataIndex] = totalTime;
				totalTimeData[dataIndex++] = avgTime;
			}

            // write the data to the created file
			if (printToFile)
				writer.write(i + "\t" + totalTime + "\t" + avgTime);

			// print the results as the test runs
			if (printAsRuns)
				System.out.println(i + "\t" + totalTime + "\t" + avgTime);

            // return i to 0 for the first loop
            if (i == 1000) i = 0;
        }
		System.out.println("Finished BadHashFunction timing!\n");
		// after testing finishes, print stored data
		if (printAtEnd) {
			System.out.println("size\ttime");
			for (int i = 0; i <= MAX_TABLE_SIZE / INTERVAL; i++)
				System.out.println(sizeData[i] + "\t" + totalTimeData[i]);
		}
	}

	public static void FairHashFunctionTime() throws Exception {
		long startTime, midTime, endTime;
        int dataIndex = 0;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("FairHashFunctionTime" +
				".txt"), "utf-8"));

        int[] sizeData = new int[MAX_TABLE_SIZE / INTERVAL + 1];
        double[] totalTimeData = new double[MAX_TABLE_SIZE / INTERVAL + 1];

        HashFunctor hasher = new FairHashFunctor();

        // print header
		if (printAsRuns || printAtEnd)
			System.out.println("Timing trial for FairHashFunctor()\nHashes\tTotal time (s)\tAverage Time (ns)");
		if (printToFile)
			writer.write("Timing trial for BadHashFunctor()\nHashes	Total time	Average Time");

        // table sizes
		for (int i = 0; i <= MAX_TABLE_SIZE; i += INTERVAL) {
			if (i == 0) i = 1000;

            int combined = i * TIMES_TO_LOOP;
            String[] randomStrings = stringArray(combined);
            String temp;
            int index;

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            startTime = System.nanoTime();
            index = 0;
//            for (int j = 0; j < TIMES_TO_LOOP; j++) {
            for (int k = 0; k < combined; k++) {
                temp = randomStrings[index++];
                // this next statement is what is dependent on the hashtable size.
                int hashIndex = hasher.hash(temp) % i;
            }
//            }

            midTime = System.nanoTime();
            index = 0;
			for (int k = 0; k < combined; k++)
				temp = randomStrings[index++];

            endTime = System.nanoTime();

            // calculate the total time and the average time
			double totalTime = (double) (2 * midTime - startTime - endTime) / 1e9;
			double avgTime = totalTime * 1e9 / combined;

            // store the times to be printed after execution completes
			if (printAtEnd) {
				sizeData[dataIndex] = i;
				totalTimeData[dataIndex] = totalTime;
				totalTimeData[dataIndex++] = avgTime;
			}

            // write the data to the created file
			if (printToFile)
				writer.write(i + "\t" + totalTime + "\t" + avgTime);

			// print the results as the test runs
			if (printAsRuns)
				System.out.println(i + "\t" + totalTime + "\t" + avgTime);

            // return i to 0 for the first loop
            if (i == 1000) i = 0;
        }
		System.out.println("Finished FairHashFunction timing!\n");
		// after testing finishes, print stored data
		if (printAtEnd) {
			System.out.println("size\ttime");
			for (int i = 0; i <= MAX_TABLE_SIZE / INTERVAL; i++)
				System.out.println(sizeData[i] + "\t" + totalTimeData[i]);
		}
	}

	public static void GoodHashFunctionTime() throws Exception {
		long startTime, midTime, endTime;
        int dataIndex = 0;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("GoodHashFunctionTime" +
				".txt"), "utf-8"));

        int[] sizeData = new int[MAX_TABLE_SIZE / INTERVAL + 1];
        double[] totalTimeData = new double[MAX_TABLE_SIZE / INTERVAL + 1];

        HashFunctor hasher = new GoodHashFunctor();

        // print header
		if (printAsRuns || printAtEnd)
			System.out.println("Timing trial for GoodHashFunctor()\nHashes\tTotal time (s)\tAverage Time (ns)");
		if (printToFile)
			writer.write("Timing trial for BadHashFunctor()\nHashes	Total time	Average Time");

        // table sizes
		for (int i = 0; i <= MAX_TABLE_SIZE; i += INTERVAL) {
			if (i == 0) i = 1000;

            int combined = i * TIMES_TO_LOOP;
            String[] randomStrings = stringArray(combined);
            String temp;
            int index;

            // let a while loop run for a full second to get things spooled up.
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1e9) { //empty block
            }

            startTime = System.nanoTime();
            index = 0;
//            for (int j = 0; j < TIMES_TO_LOOP; j++) {
            for (int k = 0; k < combined; k++) {
                temp = randomStrings[index++];
                // this next statement is what is dependent on the hashtable size.
                int hashIndex = hasher.hash(temp) % i;
            }
//            }

            midTime = System.nanoTime();
            index = 0;
			for (int k = 0; k < combined; k++)
				temp = randomStrings[index++];

            endTime = System.nanoTime();

            // calculate the total time and the average time
			double totalTime = (double) (2 * midTime - startTime - endTime) / 1e9;
			double avgTime = totalTime * 1e9 / combined;

            // store the times to be printed after execution completes
			if (printAtEnd) {
				sizeData[dataIndex] = i;
				totalTimeData[dataIndex] = totalTime;
				totalTimeData[dataIndex++] = avgTime;
			}

            // write the data to the created file
			if (printToFile)
				writer.write(i + "\t" + totalTime + "\t" + avgTime);

			// print the results as the test runs
			if (printAsRuns)
				System.out.println(i + "\t" + totalTime + "\t" + avgTime);

            // return i to 0 for the first loop
//            if (i == 1000) i = 0;
        }
		System.out.println("Finished GoodHashFunction timing!\n");
		// after testing finishes, print stored data
		if (printAtEnd) {
			System.out.println("size\ttime");
			for (int i = 0; i <= MAX_TABLE_SIZE / INTERVAL; i++)
				System.out.println(sizeData[i] + "\t" + totalTimeData[i]);
		}
	}

	public static String[] stringArray(int length) {

        // method to create a string array of random strings made up of lowercase letters.

		String[] array = new String[length];
		Random rand = new Random(System.currentTimeMillis());

        // first loop to iterate indexes in array
		for (int i = 0; i < length; i++) {
			// sets random string length at index 'i' of the string array, length from 4 to 20 characters
            int stringLength = rand.nextInt(17) + 4;
            // for loop adds random characters to string at index 'i' until j = stringLength-1
            array[i] = "";
            for (int j = 0; j < stringLength; j++)
                array[i] += Character.toString((char) (rand.nextInt(26) + 97));

        }
        return array;
    }

//     TODO: conduct an experiment assess the quality and efficiency of each of your three hash functions.
//    * A recommendation for this experiment is to create two plots: one that shows the
//    growth of the number of collisions incurred by each hash function for a variety of
//    hash table sizes, and one that shows the actual running time required by each hash
//    function for a variety of hash table sizes. You may use either type of table for
//    this experiment.

	public static void BadHashProbingTableTime() throws Exception {
		long startTime, midTime, endTime;
		int dataIndex = 0;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("BadHashFunctionTime.txt"), "utf-8"));

		int[] sizeData = new int[MAX_TABLE_SIZE / INTERVAL + 1];
		double[] totalTimeData = new double[MAX_TABLE_SIZE / INTERVAL + 1];

		HashFunctor hasher = new BadHashFunctor();

		// print header
		if (printAsRuns || printAtEnd)
			System.out.println("Timing trial for BadHash with ProbingTable\nInitial capacity\tTotal time (s)\tAverage " +
					"Time (ns)\tCollisions");
		if (printToFile)
			writer.write("Timing trial for BadHash with ProbingTable\nInitial capacity\tTotal time (s)\tAverage Time " +
					"(ns)\tCollisions");

		// table sizes
		for (int i = 100000; i <= MAX_TABLE_SIZE; i += INTERVAL) {
//			if (i == 0) i = 1000;

			ProbingHashTable table;
			int combined = i * TIMES_TO_LOOP;
			String[] randomStrings = stringArray(ADDED_ITEMS);
			int index;
			long totalCollisions = 0;

			// let a while loop run for a full second to get things spooled up.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1e9) { //empty block
			}

			startTime = System.nanoTime();
			for (int k = 0; k < TIMES_TO_LOOP; k++) {
				table = new ProbingHashTable(i, hasher);
				for (int j = 0; j < randomStrings.length; j++)
					table.add(randomStrings[j]);
				totalCollisions += table.getCollisions();
			}

			midTime = System.nanoTime();
			for (int k = 0; k < TIMES_TO_LOOP; k++) {
				table = new ProbingHashTable(i, hasher);
				for (int j = 0; j < ADDED_ITEMS; j++) {
				}
				totalCollisions += 0;
			}

			endTime = System.nanoTime();

			// calculate the total time and the average time
			double totalTime = (double) (2 * midTime - startTime - endTime) / 1e9;
			double avgTime = totalTime * 1e9 / combined;
//			long collisions = table.getCollisions();
			long collisions = totalCollisions / TIMES_TO_LOOP;

			// store the times to be printed after execution completes
			if (printAtEnd) {
				sizeData[dataIndex] = i;
				totalTimeData[dataIndex] = totalTime;
				totalTimeData[dataIndex++] = avgTime;
			}

			// write the data to the created file
			if (printToFile)
				writer.write(i + "\t" + totalTime + "\t" + avgTime + "\t" + collisions);

			// print the results as the test runs
			if (printAsRuns)
				System.out.println(i + "\t" + totalTime + "\t" + avgTime + "\t" + collisions);

			// return i to 0 for the first loop
//			if (i == 1000) i = 0;
		}
		System.out.println("Finished BadHashFunction timing!\n");
		// after testing finishes, print stored data
		if (printAtEnd) {
			System.out.println("size\ttime");
			for (int i = 0; i <= MAX_TABLE_SIZE / INTERVAL; i++)
				System.out.println(sizeData[i] + "\t" + totalTimeData[i]);
		}
	}

//     TODO: conduct an experiment assess the quality and efficiency of each of your two hash tables.
//     * A recommendation for this experiment is to create two plots: one that shows the number of
//     collisions incurred by each hash table using the hash function in GoodHashFunctor, and one
//     that shows the actual running time required by each hash table using the hash function in
//     GoodHashFunctor.
}
