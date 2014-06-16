package assignment5;

import java.util.NoSuchElementException;

/**
 * Represents a generic doubly linked list.
 *
 * @param <E> - the type of elements contained in the linked list
 *
 * @author Paymon Saebi
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
        size = 0;
    }

    /**
     * @param element - The element to add at the beginning of the list.
     *                <p/>
     *                Inserts the specified element at the beginning of the list. O(1) for a doubly-linked list.
     */
    public void addFirst(E element) {
        // if first element added
        if (size == 0) {
            head = new Node(element);
            tail = head;
            size++;
        } else {
            head.prev = new Node(element);
            head.prev.next = head;
            head = head.prev;
            size++;
        }
    }

    /**
     * @param o - The element to add at the end of the list.
     *          <p/>
     *          Inserts the specified element at the end of the list. O(1) for a doubly-linked list.
     */
    public void addLast(E o) {
        // if this is first add, call addFirst
        if (size == 0) addFirst(o);

        // otherwise add new node, interchange references, assign new node as tail.
        tail.next = new Node(o);
        tail.next.prev = tail;
        tail = tail.next;
        size++;
    }

    /**
     * Inserts the specified element at the specified position in the list. Throws IndexOutOfBoundsException if index is
     * out of range. O(N) for a doubly-linked list.
     */
    public void add(int index, E element) throws IndexOutOfBoundsException {
        // check bounds and throw exception if necessary
        if (index == 0) {
            this.addFirst(element);
            return;
        }
        if (index == size - 1) {
            this.addLast(element);
            return;
        }
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node prevNode, nextNode = head;
        for (int i = 0; i < index; i++) {
            nextNode = nextNode.next;
        }
        prevNode = nextNode.prev;

        prevNode.next = new Node(element);
        nextNode.prev = prevNode.next;
        prevNode.next.prev = prevNode;
        nextNode.prev.next = nextNode;
        size++;
    }

    /**
     * Returns the first element in the list. Throws NoSuchElementException if the list is empty. O(1) for a
     * doubly-linked list.
     */
    public E getFirst() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException();
        return head.data;
    }

    /**
     * Returns the last element in the list. Throws NoSuchElementException if the list is empty. O(1) for a
     * doubly-linked list.
     */
    public E getLast() throws NoSuchElementException {
        if (size == 0)
            throw new NoSuchElementException();
        return tail.data;
    }

    /**
     * Returns the element at the specified position in the list. Throws IndexOutOfBoundsException if index is out of
     * range. O(N) for a doubly-linked list.
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if (size == 0 || index >= size || index < 0)
            throw new IndexOutOfBoundsException();

        if (index == 0)
            return this.getFirst();
        if (index == size - 1)
            return this.getLast();

        Node toReturn = head;
        for (int i = 0; i < index; i++)
            toReturn = toReturn.next;

        return toReturn.data;
    }

    /**
     * Removes and returns the first element from the list. Throws NoSuchElementException if the list is empty. O(1) for
     * a doubly-linked list.
     */
    public E removeFirst() throws NoSuchElementException {
        // variable to hold the removed element to be returned
        E item;
        // check that there is at least 1 element in the list
        if (size == 0)
            throw new NoSuchElementException();
        if (size == 1) {
            item = head.data;
            head = null;
            tail = null;
            size--;
            return item;
        }
        // store the data at head first, set head to second item, set first item to null
        item = head.data;
        head = head.next;
        head.prev = null;
        size--;
        // return the stored data item
        return item;
    }

    /**
     * Removes and returns the last element from the list. Throws NoSuchElementException if the list is empty. O(1) for
     * a doubly-linked list.
     */
    public E removeLast() throws NoSuchElementException {
        // variable for item to be removed and returned
        E item;
        // check that there is at least 1 element in the list
        if (size == 0)
            throw new NoSuchElementException();

        if (size == 1) {
            item = tail.data;
            head = null;
            tail = null;
            size--;
            return item;
        }

        // store the data at tail first, set tail to second to last item, set last item to null
        item = tail.data;
        tail = tail.prev;
        tail.next = null;
        size--;
        // return the stored data item
        return item;
    }

    /**
     * Removes and returns the element at the specified position in the list. Throws IndexOutOfBoundsException if index
     * is out of range. O(N) for a doubly-linked list.
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        // variable for item to be removed and returned
        E item;
        if (size == 0 || index >= size || index < 0)
            throw new IndexOutOfBoundsException();

        Node toRemove = head;
        // loop index amount to arrive at the desire node to remove
        for (int i = 0; i < index; i++)
            toRemove = toRemove.next;

        item = toRemove.data;
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        toRemove.next = null;
        toRemove.prev = null;
        size--;

        // returned stored item that was removed.
        return item;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present Returns true if the
     * element was found and removed, false otherwise O(N) for a doubly-linked list.
     */
    public boolean remove(E element) {
        if (size == 0)
            return false;
        // create starting node
        Node toRemove = head;
        while (! toRemove.data.equals(element) && toRemove != tail)
            toRemove = toRemove.next;

        if (toRemove == tail && ! toRemove.data.equals(element))
            return false;

        // at this point the element is found. Remove it, decrement size and return true.
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        toRemove.next = null;
        toRemove.prev = null;
        size--;
        return true;
    }

    /**
     * Returns true if this list contains the specified element or false if this list does not contain the element. O(N)
     * for a doubly-linked list.
     */
    public boolean contains(E element) {
        if (size == 0)
            return false;

        Node toFind = head;
        while (! toFind.data.equals(element) && toFind != tail)
            toFind = toFind.next;

        if (toFind == tail && ! toFind.data.equals(element))
            return false;
        return true;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list, or -1 if this list does not
     * contain the element. O(N) for a doubly-linked list.
     */
    public int indexOf(E element) {
        if (size == 0)
            return -1;

        int returnIndex = 0;
        Node toFind = head;
        while (! toFind.data.equals(element) && toFind != tail) {
            toFind = toFind.next;
            returnIndex++;
        }

        if (toFind == tail && ! toFind.data.equals(element))
            return - 1;
        return returnIndex;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not
     * contain the element. O(N) for a doubly-linked list.
     */
    public int lastIndexOf(E element) {
        if (size == 0)
            return -1;

        int returnIndex = size - 1;
        Node toFind = tail;
        while (! toFind.data.equals(element) && toFind != head) {
            toFind = toFind.prev;
            returnIndex--;
        }

        if (toFind == head && ! toFind.data.equals(element))
            return - 1;
        return returnIndex;
    }

    /**
     * Returns the number of elements in this list. O(1) for a doubly-linked list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this collection contains no elements. O(1) for a doubly-linked list.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all of the elements from this list. O(1) for a doubly-linked list.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * O(N) for a doubly-linked list.
     */
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++)
            result[i] = this.get(i);

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
