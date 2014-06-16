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
        Node insertNodePrev = findNode(index);
        Node insertNodeNext = insertNodePrev.next;
        Node insertNode = new Node(element);
        insertNodePrev.next = insertNode;
        insertNode.prev = insertNodePrev;
        insertNodeNext.prev = insertNode;
        insertNode.next = insertNodeNext;
        size++;
    }

    /**
     * Returns a pointer to the Node at a specified index. Assumes index is in the correct range. O(N) for a
     * doubly-linked list.
     */
    private Node findNode(int index) {
        // handle finding head and tail Nodes
        if (index == 0)
            return head;
        else if (index == size - 1)
            return tail;

        // otherwise find the Node according to which end it is closer to
        Node currentNode;

        // if the Node is closer to the beginning then iterate from the head
        if (size / 2 > index) {
            currentNode = head;
            int insertIndex = 0;
            while (insertIndex != index) {
                currentNode = currentNode.next;
                insertIndex++;
            }
        }

        // if the Node is closer to the end then iterate from the tail
        else {
            currentNode = tail;
            int insertIndex = size - 1;
            while (insertIndex != index) {
                currentNode = currentNode.prev;
                insertIndex--;
            }
        }

        // return whichever Node was found
        return currentNode;
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
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();

        // handle removing from a list with one Node
        if (size == 1) {
            E data = head.data;
            clear();
            return data;
        }

        // find the node to return and return it
        else
            return findNode(index).data;
    }

    /**
     * Removes and returns the first element from the list. Throws NoSuchElementException if the list is empty. O(1) for
     * a doubly-linked list.
     */
    public E removeFirst() throws NoSuchElementException {
        // check for a first element
        if (size == 0)
            throw new NoSuchElementException();

        // handle removing from a list with one Node
        if (size == 1) {
            E data = head.data;
            clear();
            return data;
        }

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

        // handle removing from a list with one Node
        else if (size == 1) {
            E data = head.data;
            clear();
            return data;
        }

        // otherwise mark the tail, update Node fields, decrement size, and return the correct data
        Node returnNode = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        return returnNode.data;
    }

    /**
     * Removes and returns the element at the specified position in the list. Throws IndexOutOfBoundsException if index
     * is out of range. O(N) for a doubly-linked list.
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        // handle null list
        if (size == 0)
            return null;

        // check for a correct index
        if (index < 0 || index > size - 1)
            throw new IndexOutOfBoundsException();
        // handle removing head or tail
        if (index == 0) {
            E data = head.data;
            removeFirst();
            return data;
        } else if (index == size - 1) {
            E data = tail.data;
            removeLast();
            return data;
        }

        // find the node, remove the pointers to it, and return its data
        Node removeNode = findNode(index);
        removeNode.next.prev = removeNode.prev;
        removeNode.prev.next = removeNode.next;
        return removeNode.data;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present. Returns true if the
     * element was found and removed, false otherwise. O(N) for a doubly-linked list.
     */
    public boolean remove(E element) {
        // handle null case
        if (head == null)
            return false;

        // find the node with the passed data
        Node currentNode = head;
        while (currentNode != null && !currentNode.data.equals(element))
            currentNode = currentNode.next;

        // if the entire list was iterated through return false
        if (currentNode == null)
            return false;

        // otherwise, remove the Node
        if (currentNode.equals(head))
            removeFirst();
        else if (currentNode.equals(tail))
            removeLast();
        else {
            currentNode.next.prev = currentNode.prev;
            currentNode.prev.next = currentNode.next;
        }
        return true;
    }

    /**
     * Returns true if this list contains the specified element or false if this list does not contain the element. O(N)
     * for a doubly-linked list.
     */
    public boolean contains(E element) {
        // handle null case
        if (head == null)
            return false;

        // find the node with the passed data
        Node currentNode = head;
        while (currentNode != null && !currentNode.data.equals(element))
            currentNode = currentNode.next;

        // if the entire list was iterated through return false
        return (currentNode != null);
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list, or -1 if this list does not
     * contain the element. O(N) for a doubly-linked list.
     */
    public int indexOf(E element) {
        // handle null case
        if (head == null)
            return -1;

        // find the node with the passed data
        Node currentNode = head;
        int index = 0;
        while (currentNode != null && !currentNode.data.equals(element)) {
            currentNode = currentNode.next;
            index++;
        }

        // if the entire list was iterated through return -1
        return (currentNode == null) ? -1 : index;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not
     * contain the element. O(N) for a doubly-linked list.
     */
    public int lastIndexOf(E element) {
        // handle null case
        if (head == null)
            return -1;

        // find the last node with the passed data
        Node currentNode = tail;
        int index = size - 1;
        while (currentNode != null && !currentNode.data.equals(element)) {
            currentNode = currentNode.prev;
            index--;
        }

        // if the entire list was iterated through return -1
        return (currentNode == null) ? -1 : index;
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
        return (size == 0);
    }

    /**
     * Removes all of the elements from this list. O(1) for a doubly-linked list.
     */
    public void clear() {
        tail = null;
        head = null;
        size = 0;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * O(N) for a doubly-linked list.
     */
    public Object[] toArray() {
        // initialize return array and Node pointer for iteration
        Object[] result = new Object[size];
        Node currentNode = head;

        // add each Node in the list to the return array
        for (int i = 0; i < size - 1; i++) {
            result[i] = currentNode;
            currentNode = currentNode.prev;
        }

        // return the array
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
