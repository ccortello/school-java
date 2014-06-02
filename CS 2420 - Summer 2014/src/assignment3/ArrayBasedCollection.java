package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/**
 * @param <E> - generic type placeholder
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 *         <p/>
 *         This class Implements the Collection interface using an array as storage. The array must grow as needed.
 *         All methods should be implemented as defined by the Java API, unless otherwise specified in the assignment.
 *         <p/>
 *         It is your job to fill in the missing implementations and to header comment for this class. Every method should have
 *         comments (Javadoc-style prefered, but not necessary). The comments should at least indicate what the method
 *         does, what the arguments mean, and what the returned value is. It should specify any special cases that the method
 *         handles. Any code that is not self-explanatory should be in-line commented.
 */
public class ArrayBasedCollection<E> implements Collection<E> {
    E data[]; // Storage for the items in the collection
    int size; // Keep track of how many items we hold

    /**
     *
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedCollection() {
        size = 0;
        // There is no clean way to convert between E and Object, the text book discusses this.
        // We can't instantiate an array of unknown type E, so we must create an Object array, and type-cast
        data = (E[]) new Object[10]; // Start with an initial capacity of 10
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedCollection(int capacity) {
        size = 0;
        // There is no clean way to convert between E and Object, the text book discusses this.
        // We can't instantiate an array of unknown type E, so we must create an Object array, and type-cast
        data = (E[]) new Object[capacity]; // Start with an initial capacity specified by the parameter
    }

    /**
     *
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        // This is a helper function specific to ArrayCollection
        // Doubles the size of the data storage array, retaining its current contents.
        // You will need to use something similar to the code in the constructor above to create a new array.

        E newData[] = (E[]) new Object[this.data.length * 2];

        for (int i = 0; i < size; i++)
            newData[i] = data[i];

        this.data = newData;
    }

    /**
     * Ensures that this collection contains the specified element (optional operation).
     */
    public boolean add(E arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Adds all of the elements in the specified collection to this collection (optional operation).
     */
    public boolean addAll(Collection<? extends E> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Removes all of the elements from this collection (optional operation).
     */
    public void clear() {
        // TODO Auto-generated method stub
    }

    /**
     * Returns true if this collection contains the specified element.
     */
    public boolean contains(Object arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     */
    public boolean containsAll(Collection<?> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Returns true if this collection contains no elements.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns an iterator over the elements in this collection.
     */
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Removes a single instance of the specified element from this collection, if it is present (optional operation).
     */
    public boolean remove(Object arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Removes all of this collection's elements that are also contained in the specified collection (optional operation).
     */
    public boolean removeAll(Collection<?> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection (optional operation).
     */
    public boolean retainAll(Collection<?> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Returns the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    /**
     * Returns an array containing all of the elements in this collection.
     */
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
     */
    public <T> T[] toArray(T[] arg0) {
        //Don't implement this method (unless you want to).
        // It must be here to complete the Collection interface.
        // We will not test this method.

        return null;
    }

    /**
     *
     */
    public ArrayList<E> toSortedList(Comparator<? super E> cmp) {
        // Sorting method specific to ArrayCollection - not part of the Collection interface
        // Must implement an insertion sort (see lecture 6 for code ideas).

        // TODO Auto-generated method stub
        return null;
    }

    /**
     *
     */
    private class ArrayBasedIterator implements Iterator<E> {
        int index;
        boolean gotNext;

        public ArrayBasedIterator() {
            // TODO Auto-generated method stub
        }

        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        public E next() {
            // TODO Auto-generated method stub
            return null;
        }

        public void remove() {
            // TODO Auto-generated method stub
        }
    }
}
