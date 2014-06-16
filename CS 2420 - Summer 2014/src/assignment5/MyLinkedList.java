package assignment5;

import java.util.NoSuchElementException;

/**
 * Represents a generic doubly linked list.
 *
 * @param <E> - the type of elements contained in the linked list
 *
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 * @author Description:
 */
public class MyLinkedList<E> implements List<E> {
    //Instance variables
    int size;
    Node head;
    Node tail;

    /**
     * Constructor.  Creates a blank linked list.
     */
    public MyLinkedList() {
        int size = 0;
    }

    /**
     * @param element - The element to add at the beginning of the list.
     *                <p/>
     *                Inserts the specified element at the beginning of the list. O(1) for a doubly-linked list.
     */
    public void addFirst(E element) {
        // if adding first Node
        if (size == 0) {
            head = new Node(element);
            tail = head;
            size++;
        }
        // if adding second Node
        else if (size == 1) {
            head.prev = new Node(element);
            head.prev.next = head;
            head = head.prev;
            tail = head.next;
            size++;
        }
        // adding to a list with more than one Node
        else {
            head.prev = new Node(element);
            head.prev.next = head;
            head = head.prev;
            size++;
        }
    }

    /**
     * @param o - The element to add at the end of the list.
     *                <p/>
     *                Inserts the specified element at the end of the list. O(1) for a doubly-linked list.
     */
    public void addLast(E o) {
        // if adding first Node
        if (size == 0) {
            head = new Node(o);
            tail = head;
            size++;
        }
        // if adding the second Node
        else if (size == 1) {
            head.next = new Node(o);
            head.next.prev = head;
            tail = head.next;
            size++;
        }
        // if adding to a list with more than one Node
        else {
            tail.next = new Node(o);
            tail.next.prev = tail;
            tail = tail.next;
            size++;
        }
    }

    /**
     * Inserts the specified element at the specified position in the list. Throws IndexOutOfBoundsException if index is
     * out of range. O(N) for a doubly-linked list.
     */
    public void add(int index, E element) throws IndexOutOfBoundsException {
        // check for a correct index
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        // call the correct method if the element should be added first or last
        if (index == 0) {
            addFirst(element);
            return;
        } else if (index == size) {
            addLast(element);
            return;
        }

        // if the element needs to be inserted somewhere in the middle find the Nodes around the insertion point and
        //  update the fields of all three Nodes.
        Node insertNodePrev = head, insertNode, insertNodeNext = null;

        // if the Node is closer to the beginning
        int insertIndex = 0;
        while (insertIndex != index) {
            insertNodePrev = insertNodePrev.next;
            insertIndex++;
        }
        insertNodeNext = insertNodePrev.next;
        insertNode = new Node(element);
        insertNodePrev.next = insertNode;
        insertNode.prev = insertNodePrev;
        insertNodeNext.prev = insertNode;
        insertNode.next = insertNodeNext;
        size++;
    }

    /**
     * Returns the first element in the list. Throws NoSuchElementException if the list is empty. O(1) for a
     * doubly-linked list.
     */
    public E getFirst() throws NoSuchElementException {
        // check for an empty list
        if (head == null)
            throw new NoSuchElementException();
        else // if there is an item in the list, return it
            return head.data;
    }

    /**
     * Returns the last element in the list. Throws NoSuchElementException if the list is empty. O(1) for a
     * doubly-linked list.
     */
    public E getLast() throws NoSuchElementException {
        // check for an empty list
        if (tail == null)
            throw new NoSuchElementException();
        else // if there is an item in the list, return it
            return tail.data;
    }

    /**
     * Returns the element at the specified position in the list. Throws IndexOutOfBoundsException if index is out of
     * range. O(N) for a doubly-linked list.
     */
    public E get(int index) throws IndexOutOfBoundsException {
        // check for a correct index
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
            // find the node to return and return it
        else {
            Node returnNode = head;
            int addIndex = 0;
            while (addIndex != index) {
                returnNode = returnNode.next;
                addIndex++;
            }
            return returnNode.data;
        }
    }

    /**
     * Removes and returns the first element from the list. Throws NoSuchElementException if the list is empty. O(1) for
     * a doubly-linked list.
     */
    public E removeFirst() throws NoSuchElementException {
        // check for a first element
        if (size == 0)
            throw new NoSuchElementException();
        // otherwise mark the head, update Node fields, decrement size, and return the correct data
        Node returnNode = head;
        head = head.next;
        head.prev = null;
        size--;
        return returnNode.data;
    }

    /**
     * Removes and returns the last element from the list. Throws NoSuchElementException if the list is empty. O(1) for
     * a doubly-linked list.
     */
    public E removeLast() throws NoSuchElementException {
        // check for a first element
        if (size == 0)
            throw new NoSuchElementException();
        // otherwise mark the head, update Node fields, decrement size, and return the correct data
        Node returnNode = tail;
        tail = tail.prev;
        tail.prev = null;
        size--;
        return returnNode.data;
    }

    /**
     * Removes and returns the element at the specified position in the list. Throws IndexOutOfBoundsException if index
     * is out of range. O(N) for a doubly-linked list.
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        E item = null;
        //TODO
        return item;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present Returns true if the
     * element was found and removed, false otherwise O(N) for a doubly-linked list.
     */
    public boolean remove(E element) {
        // TODO
        return false;
    }

    /**
     * Returns true if this list contains the specified element or false if this list does not contain the element. O(N)
     * for a doubly-linked list.
     */
    public boolean contains(E element) {
        // TODO
        return false;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list, or -1 if this list does not
     * contain the element. O(N) for a doubly-linked list.
     */
    public int indexOf(E element) {
        //TODO
        return 0;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not
     * contain the element. O(N) for a doubly-linked list.
     */
    public int lastIndexOf(E element) {
        //TODO
        return 0;
    }

    /**
     * Returns the number of elements in this list. O(1) for a doubly-linked list.
     */
    public int size() {
        //TODO
        return 0;
    }

    /**
     * Returns true if this collection contains no elements. O(1) for a doubly-linked list.
     */
    public boolean isEmpty() {
        //TODO
        return false;
    }

    /**
     * Removes all of the elements from this list. O(1) for a doubly-linked list.
     */
    public void clear() {
        //TODO
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * O(N) for a doubly-linked list.
     */
    public Object[] toArray() {
        Object[] result = null;
        //TODO
        return result;
    }

    private class Node {
        E data;
        Node next;
        Node prev;

        public Node(E element) {
            data = element;
        }
    }
}
