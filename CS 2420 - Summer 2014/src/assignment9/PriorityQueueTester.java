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
     * Tests the methods of the PriorityQueueHEAP class, the three methods tested are add,
     * findMin & deleteMin
     */
    public void testPriorityQueueHEAP() {
        // create a PQ of type Integer to test
        PriorityQueueHEAP<Integer> testPQ = new PriorityQueueHEAP<Integer>();
        // before adding, assert the size is zero
        assertEquals(0, testPQ.size());
        // add Integers to th PQ, and test min value
        testPQ.add(1);
        assertEquals((Integer) 1, testPQ.findMin());
        testPQ.add(20);
        assertEquals((Integer) 1, testPQ.findMin());
        testPQ.add(-5);
        assertEquals((Integer) (-5), testPQ.findMin());
        // test delete min
        testPQ.deleteMin();
        assertEquals((Integer) 1, testPQ.findMin());
        testPQ.deleteMin();
        assertEquals((Integer) 20, testPQ.findMin());
        // assert next delete min leaves the PQ to size 0
        testPQ.deleteMin();
        assertEquals(0, testPQ.size());
        // re-add integers to test other methods
        testPQ.add(1);
        testPQ.add(20);
        testPQ.add(-5);
        testPQ.add(-1);
        testPQ.add(12);
        assertEquals(5, testPQ.size());
        // create Object[] to test toArray method
        Object[] arrayTest = testPQ.toArray();
//        assertEquals("", arrayTest.toString());
        System.out.println(arrayTest.toString());
    }

    /**
     * Tests the methods of the PriorityQueueBST class, the three methods tested are add,
     * findMin & deleteMin
     */
    public void testPriorityQueueBST() {
        // create a PQ of type Integer to test
        PriorityQueueBST<Integer> testPQ = new PriorityQueueBST<Integer>();
        // before adding, assert the size is zero
        assertEquals(0, testPQ.size());
        // add Integers to th PQ, and test min value
        testPQ.add(1);
        assertEquals((Integer) 1, testPQ.findMin());
        testPQ.add(20);
        assertEquals((Integer) 1, testPQ.findMin());
        testPQ.add(-5);
        assertEquals((Integer) (-5), testPQ.findMin());
        // test delete min
        testPQ.deleteMin();
        assertEquals((Integer) 1, testPQ.findMin());
        testPQ.deleteMin();
        assertEquals((Integer) 20, testPQ.findMin());
        // assert next delete min leaves the PQ to size 0
        testPQ.deleteMin();
        assertEquals(0, testPQ.size());
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
		PriorityQueue<Integer> jpq = new PriorityQueue<Integer>();

		Random rand = new Random(seed);
		int nextRandomInt = rand.nextInt();

		// for the first 50% of the operations just add things to the PQs to populate them
		for (int i = 0; i < 0.50 * operations; i++) {
			if (jpq.contains(nextRandomInt)) // don't repeat items to maintain PriorityHeap set properties
				continue;
			pq.add(nextRandomInt);
			jpq.add(nextRandomInt);
		}

		for (int i = 0; i < 0.50 * operations; i++) {
			nextRandomInt = rand.nextInt();

			// first type of random operation is add()
			if (nextRandomInt % 3 == 0) {
				if (jpq.size() != 0 && jpq.contains(nextRandomInt)) // don't repeat items to maintain PriorityHeap set
					// properties
					continue;
				System.out.println("Adding, size = " + pq.size());
				pq.add(nextRandomInt);
				jpq.add(nextRandomInt);
			}
			// second type of random operation is findMin()
			else if (nextRandomInt % 3 == 1) {
				System.out.println("Asserting findMin, size = " + pq.size());
				assertEquals(pq.findMin(), jpq.peek());
			}
			// third type of random operation is delMin()
			else {
				System.out.println("Asserting delMin(), size = " + pq.size());
				assertEquals(pq.deleteMin(), jpq.poll());
			}
		}
	}
}
