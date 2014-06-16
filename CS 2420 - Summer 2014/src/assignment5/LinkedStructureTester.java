package assignment5;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Random;

public class LinkedStructureTester extends TestCase {
    // list for testing that contains objects of type String
    MyLinkedList<String> testListString = new MyLinkedList<String>();

    /**
     * Tests that the first element is <tt>null</tt>, then adds 2 elements checking after each that the element added is
     * in the first index. This test also tests the <tt>getFirst</tt> method at the same time.
     *
     * @throws Exception
     */
    public void testAddFirst() throws Exception {
        // first test that the head and first element is null
        assertTrue(testListString.head == null);
        // add an string object to the testList
        testListString.addFirst("addFirstTest001");
        assertTrue(testListString.head != null);
        // assert that the first element in the list is now "addFirstTest001"
        assertTrue(testListString.getFirst().equals("addFirstTest001"));
        // do the same as above to test it a second time
        testListString.addFirst("addFirstTest002");
        assertTrue(testListString.getFirst().equals("addFirstTest002"));
    }

    /**
     * Tests that the first element is <tt>null</tt>, then adds 2 elements checking after each that the element added
     * is in the last index. This test also tests the <tt>getLast</tt> method at the same time.
     * @throws Exception
     */
    public void testAddLast() throws Exception {
        assertTrue(testListString.head == null);
        testListString.addFirst("addFirstTest001");
        testListString.addFirst("addFirstTest002");
        // add an string object to the testList with addLast
        testListString.addLast("addLastTest001");
        // assert that the last element in the list is now "addFirstTest001"
        assertTrue(testListString.getLast().equals("addLastTest001"));
        // do the same as above to test it a second time
        testListString.addLast("addLastTest002");
        assertTrue(testListString.getLast().equals("addLastTest002"));
    }

    /**
     * This test first asserts with the new test that <tt>testListString</tt> is again null. It then adds 5 elements to
     * the list with already proven working methods. It then tests the <tt>add</tt> and <tt>get</tt> methods.
     * @throws Exception
     */
    public void testAdd() throws Exception {
        assertTrue(testListString.head == null);
        // add five string elements to add elements to middle indexes
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");

        // use same list as the two tests above so that elements are already present
        // add an element to index 2
        testListString.add(2, "addTest001");
        // assert that the element at index 2 in the list is now "addTest001"
        assertTrue(testListString.get(2).equals("addTest001"));
        // do the same as above to test it a second time
        testListString.add(2, "addTest002");
        assertTrue(testListString.get(2).equals("addTest002"));
        // test that the first element added "addTest001" is now at index 3
        assertTrue(testListString.get(3).equals("addTest001"));
    }

    /**
     * This method is already tested to work in <tt>testAddFirst</tt>
     * @throws Exception
     */
    public void testGetFirst() throws Exception {
    }

    /**
     * This method is already tested to work in <tt>testAddLast</tt>
     * @throws Exception
     */
    public void testGetLast() throws Exception {
    }

    /**
     * This method is already tested to work in <tt>testAdd</tt>
     * @throws Exception
     */
    public void testGet() throws Exception {
    }

    /**
     * Tests that the first item is removed from a list with <tt>removeFirst</tt> and also that the method returns the
     * correct item that it removed.
     * @throws Exception
     */
    public void testRemoveFirst() throws Exception {
        // fist assert list is null, then add three items to the list
        assertTrue(testListString.head == null);
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");

        // assert the first item is still "one"
        assertTrue(testListString.getFirst().equals("one"));
        // remove the first item testing that it also returns the first item that removed
        assertEquals("one", testListString.removeFirst());
        // then test that the new first item is equal to "two"
        assertTrue(testListString.getFirst().equals("two"));
    }

    /**
     * Tests that the last item is removed from a list with <tt>removeLast</tt> and also that the method returns the
     * correct item that it removed.
     * @throws Exception
     */
    public void testRemoveLast() throws Exception {
        // fist assert list is null, then add three items to the list
        assertTrue(testListString.head == null);
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");

        // assert the last item is "three"
        assertTrue(testListString.getLast().equals("three"));
        // remove the last item testing that it also returns the last item that removed
        assertEquals("three", testListString.removeLast());
        // then test that the new last item is equal to "two"
        assertTrue(testListString.getLast().equals("two"));
    }

    /**
     * First test the list is null again. Adds five elements. Removes elements at index 1, test the new elements
     * in their place.
     * @throws Exception
     */
    public void testRemove() throws Exception {
        assertTrue(testListString.head == null);
        // add five string elements to add elements to middle indexes
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");

        // assert that item at index 1 is "two" and size is 5
        assertTrue(testListString.get(1).equals("two") && testListString.size == 5);
        // remove item at index 1
        testListString.remove(1);
        // assert that item at index 1 is now "three" and size is 4
        assertTrue(testListString.get(1).equals("three") && testListString.size == 4);
        // assert that "one" is still there as the first element
        assertTrue(testListString.getFirst().equals("one"));
    }

    /**
     * <tt>remove</tt> is overloaded and this tests the second remove function when it is passed and object to be removed
     * instead of an index pointing to an object. Test null, then adds five elements. Tests that "four" is present, then
     * removes "four" and tests that it is no longer there. This also test the <tt>contains</tt> method as it uses it to
     * assert that "four" is present or not
     * @throws Exception
     */
    public void testRemove1() throws Exception {
        assertTrue(testListString.head == null);
        // add five string elements to add elements to middle indexes
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");

        // assert the list contains "four" and size is 5
        assertTrue(testListString.contains("four") && testListString.size == 5);
        // remove "four"
        testListString.remove("four");
        // assert the list no longer contains "four" and the size is now 4
        assertTrue(! testListString.contains("four") && testListString.size == 4);
    }

    /**
     * This is already tested above in <tt>testRemove1</tt>
     * @throws Exception
     */
    public void testContains() throws Exception {
    }

    /**
     * creates list of five elements, tests that passing "four" to <tt>indexOf</tt> returns the correct index of 3, the
     * then tests for index of "six" to show that it returns -1 meaning it is not in the list
     * @throws Exception
     */
    public void testIndexOf() throws Exception {
        assertTrue(testListString.head == null);
        // add eight string elements
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");
        testListString.addLast("seven");
        testListString.addLast("four");
        testListString.addLast("twelve");

        // assert that "four" is at index 3
        assertEquals(3, testListString.indexOf("four"));
        // asserts that searching for index of "six" returns -1 meaning its not in the list
        assertEquals(- 1, testListString.indexOf("six"));
    }

    public void testLastIndexOf() throws Exception {
        assertTrue(testListString.head == null);
        // add eight string elements to add elements to middle indexes
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");
        testListString.addLast("seven");
        testListString.addLast("four");
        testListString.addLast("twelve");

        // assert that the last occurrence of "four" is at index 6
        assertEquals(6, testListString.lastIndexOf("four"));
        // asserts that searching for index of the last occurrence of "eleven" returns -1 meaning its not in the list
        assertEquals(- 1, testListString.lastIndexOf("eleven"));
    }

    /**
     * test the size of the list after various adds
     * @throws Exception
     */
    public void testSize() throws Exception {
        assertTrue(testListString.head == null);
        assertEquals(0, testListString.size());
        // add eight string elements
        testListString.addFirst("one");
        testListString.addLast("two");
        assertEquals(2, testListString.size());
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");
        assertEquals(5, testListString.size());
        testListString.addLast("seven");
        testListString.addLast("four");
        testListString.addLast("twelve");
        assertEquals(8, testListString.size());
    }

    /**
     * tests multiple times that the list is empty or not after adding and removing elements
     * @throws Exception
     */
    public void testIsEmpty() throws Exception {
//        System.out.println(testListString.size());
        assertTrue(testListString.isEmpty());
        // add eight string elements
        testListString.addFirst("one");
        // then tests that its not empty
        assertFalse(testListString.isEmpty());
        // remove element
        assertEquals("one", testListString.removeFirst());
        // test that its empty again
        assertTrue(testListString.isEmpty());
    }

    /**
     * adds multiple elements testing the size, then clears them and asserts size == 0 and isEmpty is true
     * @throws Exception
     */
    public void testClear() throws Exception {
        assertTrue(testListString.head == null);
        assertEquals(0, testListString.size());
        // add eight string elements, asserting the growing size
        testListString.addFirst("one");
        testListString.addLast("two");
        assertEquals(2, testListString.size());
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");
        assertEquals(5, testListString.size());

        // now clear and show size == 0, and both head and tail are null
        testListString.clear();
        assertTrue(testListString.head == null);
        assertTrue(testListString.tail == null);
        assertEquals(0, testListString.size());
    }

    /**
     * creates a linkedlist of five elements, shows the returned Object array from toArray is all the same elements in
     * the same order
     * @throws Exception
     */
    public void testToArray() throws Exception {
        assertTrue(testListString.head == null);
        // add five string elements to add elements to middle indexes
        testListString.addFirst("one");
        testListString.addLast("two");
        testListString.addLast("three");
        testListString.addLast("four");
        testListString.addLast("five");

        Object[] testArray = testListString.toArray();
        assertEquals("one", testArray[0]);
        assertEquals("two", testArray[1]);
        assertEquals("three", testArray[2]);
        assertEquals("four", testArray[3]);
        assertEquals("five", testArray[4]);
        assertEquals(5, testArray.length);

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