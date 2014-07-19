package assignment9;

import junit.framework.TestCase;

/**
 * Class to test various methods of the priority queues
 */
public class PriorityQueueTester extends TestCase {

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

}
