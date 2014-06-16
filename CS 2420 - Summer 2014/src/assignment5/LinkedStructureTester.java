package assignment5;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Random;

public class LinkedStructureTester extends TestCase {
    // list for testing that contains objects of type String
    MyLinkedList<String> testListString = new MyLinkedList<String>();

    public void testAddFirst() throws Exception {
        // add an string object to the testList
        testListString.addFirst("addFirstTest001");
        // assert that the first element in the list is now "addFirstTest001"
        assertTrue(testListString.getFirst().equals("addFirstTest001"));
        // do the same as above to test it a second time
        testListString.addFirst("addFirstTest002");
        assertTrue(testListString.getFirst().equals("addFirstTest002"));
    }

    public void testAddLast() throws Exception {
        // add an string object to the testList
        testListString.addLast("addLastTest001");
        // assert that the last element in the list is now "addFirstTest001"
        assertTrue(testListString.getLast().equals("addLastTest001"));
        // do the same as above to test it a second time
        testListString.addLast("addLastTest002");
        assertTrue(testListString.getLast().equals("addLastTest002"));

    }

    public void testAdd() throws Exception {
        // use same list as the two tests above so that elements are already present
        // add an element to index 2, the very middle of the already present elements
        testListString.add(2, "addTest001");
        // assert that the element at index 2 in the list is now "addTest001"
        assertTrue(testListString.get(2).equals("addTest001"));
        // do the same as above to test it a second time
        testListString.add(2, "addTest002");
        assertTrue(testListString.get(2).equals("addTest002"));
        // test that the first element added "addTest001" is now at index 3
        assertTrue(testListString.get(3).equals("addTest001"));
    }

    public void testGetFirst() throws Exception {

    }

    public void testGetLast() throws Exception {

    }

    public void testGet() throws Exception {

    }

    public void testRemoveFirst() throws Exception {

    }

    public void testRemoveLast() throws Exception {

    }

    public void testRemove() throws Exception {

    }

    public void testRemove1() throws Exception {

    }

    public void testContains() throws Exception {

    }

    public void testIndexOf() throws Exception {

    }

    public void testLastIndexOf() throws Exception {

    }

    public void testSize() throws Exception {

    }

    public void testIsEmpty() throws Exception {

    }

    public void testClear() throws Exception {

    }

    public void testToArray() throws Exception {

    }

    public void testBulkTest() throws Exception {
        int initialIntegers = 100000, moves = 100000;
        Random rand = new Random(26491324791L);
        MyStack<Integer> stack = new MyStack<Integer>();
        ArrayList<Integer> addedInts = new ArrayList<Integer>();
        for (int i = 0; i < initialIntegers; i++) {
            Integer intToAdd = rand.nextInt();
            stack.push(intToAdd);
            addedInts.add(intToAdd);
        }
        for (int i = 0; i < moves; i++) {
            int next = rand.nextInt();
            if (next % 2 == 0) {
                Integer pushInt = rand.nextInt();
                stack.push(pushInt);
                addedInts.add(pushInt);
            } else if (next % 2 == 1) {
                stack.pop();
                addedInts.remove(addedInts.size() - 1);
            }
        }
//        System.out.println("Integers added = \n" + addedInts.size());
//        System.out.println("\nCurrent stack size:\n" + stack.size());
        assertEquals(addedInts.size(), stack.size());
    }
}