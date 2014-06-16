package assignment5;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Random;

public class LinkedStructureTester extends TestCase {

    public void testAddFirst() throws Exception {

    }

    public void testAddLast() throws Exception {

    }

    public void testAdd() throws Exception {
        MyLinkedList<Integer> testList = new MyLinkedList<Integer>();
        testList.addFirst(1);
        testList.addLast(2);
        testList.addLast(3);
        testList.addLast(4);
        testList.addLast(5);

        testList.add(2, 222);
        for (int i = 0; i < testList.size(); i++)
            System.out.println(testList.get(i));
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
                int randomIndex = rand.nextInt() / Integer.MAX_VALUE * addedInts.size();
                Integer popInt = addedInts.get(randomIndex);
                stack.pop();
                addedInts.remove(popInt);
            }
        }
//        System.out.println("Integers added = \n" + addedInts.size());
//        System.out.println("\nCurrent stack size:\n" + stack.size());
        assertEquals(addedInts.size(), stack.size());
    }
}