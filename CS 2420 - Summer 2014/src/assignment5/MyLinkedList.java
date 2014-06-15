package assignment5;

import java.util.NoSuchElementException;

/**
 * Represents a generic doubly linked list.
 *
 * @param <E> - the type of elements contained in the linked list
 *
 * @author Paymon Saebi
 * @author
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
        //TODO
    }

    /**
     * @param element - The element to add at the beginning of the list.
     *                <p/>
     *                Inserts the specified element at the beginning of the list. O(1) for a doubly-linked list.
     */
    public void addFirst(E element) {
        //TODO
    }

    /**
     * @param element - The element to add at the end of the list.
     *                <p/>
     *                Inserts the specified element at the end of the list. O(1) for a doubly-linked list.
     */
    public void addLast(E o) {
        //TODO
    }

    /**
     * Inserts the specified element at the specified position in the list. Throws IndexOutOfBoundsException if index is
     * out of range. O(N) for a doubly-linked list.
     */
    public void add(int index, E element) throws IndexOutOfBoundsException {
        //TODO
    }

    /**
     * Returns the first element in the list. Throws NoSuchElementException if the list is empty. O(1) for a
     * doubly-linked list.
     */
    public E getFirst() throws NoSuchElementException {
        E item = null;
        //TODO
        return item;
    }

    /**
     * Returns the last element in the list. Throws NoSuchElementException if the list is empty. O(1) for a
     * doubly-linked list.
     */
    public E getLast() throws NoSuchElementException {
        E item = null;
        //TODO
        return item;
    }

    /**
     * Returns the element at the specified position in the list. Throws IndexOutOfBoundsException if index is out of
     * range. O(N) for a doubly-linked list.
     */
    public E get(int index) throws IndexOutOfBoundsException {
        E item = null;
        //TODO
        return item;
    }

    /**
     * Removes and returns the first element from the list. Throws NoSuchElementException if the list is empty. O(1) for
     * a doubly-linked list.
     */
    public E removeFirst() throws NoSuchElementException {
        E item = null;
        //TODO
        return item;
    }

    /**
     * Removes and returns the last element from the list. Throws NoSuchElementException if the list is empty. O(1) for
     * a doubly-linked list.
     */
    public E removeLast() throws NoSuchElementException {
        E item = null;
        //TODO
        return item;
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
            //TODO
        }
    }
}
