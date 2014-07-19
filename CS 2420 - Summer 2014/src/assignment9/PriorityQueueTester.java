package assignment9;

import junit.framework.TestCase;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Class to test various methods of the priority queues
 */
public class PriorityQueueTester extends TestCase {
	long seed = 3892477348952745972L;
	int operations = 1000000;

    /**
     * Tests the methods of the PriorityQueueHEAP class
     */
    public void testPriorityQueueHEAP() {
        // create a PQ of type Integer to test
        PriorityQueueHEAP<Integer> testPQ = new PriorityQueueHEAP<Integer>();
        // before adding, assert the size is zero
        assertEquals(0, testPQ.size());
        // add Integers to th PQ, and test min value
        testPQ.add(1);
        assertEquals((Integer) 1, testPQ.findMin());

    }

	public void testBulkHEAP() {
		// create a new test PriorityQueue and comparison Objects and test random operations upon them
		PriorityQueueHEAP<Integer> pq = new PriorityQueueHEAP<Integer>();
		PriorityQueue<Integer> jpq = new PriorityQueue<Integer>();

		Random rand = new Random(seed);
		int nextRandomInt = rand.nextInt();

		// for the first 50% of the operations just add things to the PQs to populate them
		for (int i = 0; i < 0.50 * operations; i++) {
			pq.add(nextRandomInt);
			jpq.add(nextRandomInt);
		}

		for (int i = 0; i < 0.50 * operations; i++) {
			nextRandomInt = rand.nextInt();

			// first type of random operation is add()
			if (nextRandomInt % 3 == 0) {
				System.out.println("Adding!");
				pq.add(nextRandomInt);
				jpq.add(nextRandomInt);
			}
			// second type of random operation is findMin()
			else if (nextRandomInt % 3 == 1) {
				System.out.println("Asserting findMin!");
				assertEquals(pq.findMin(), jpq.peek());
			}
			// third type of random operation is delMin()
			else {
				System.out.println("Asserting delMin()!");
				assertEquals(pq.deleteMin(), jpq.poll());
			}
		}
	}

	public void testBulkBST() {
		// create a new test PriorityQueue and comparison Objects and test random operations upon them
		PriorityQueueBST<Integer> pq = new PriorityQueueBST<Integer>();
		TreeMap<Integer> jpq = new PriorityQueue<Integer>();

		Random rand = new Random(seed);
		int nextRandomInt = rand.nextInt();

		// for the first 50% of the operations just add things to the PQs to populate them
		for (int i = 0; i < 0.50 * operations; i++) {
			pq.add(nextRandomInt);
			jpq.add(nextRandomInt);
		}

		for (int i = 0; i < 0.50 * operations; i++) {
			nextRandomInt = rand.nextInt();

			// first type of random operation is add()
			if (nextRandomInt % 3 == 0) {
				System.out.println("Adding!");
				pq.add(nextRandomInt);
				jpq.add(nextRandomInt);
			}
			// second type of random operation is findMin()
			else if (nextRandomInt % 3 == 1) {
				System.out.println("Asserting findMin!");
				assertEquals(pq.findMin(), jpq.peek());
			}
			// third type of random operation is delMin()
			else {
				System.out.println("Asserting delMin()!");
				assertEquals(pq.deleteMin(), jpq.poll());
			}
		}
	}
}
