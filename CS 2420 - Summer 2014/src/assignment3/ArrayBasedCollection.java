package assignment3;

import java.util.*;

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
     * Initialize an ArrayBasedCollection<E> Object with a default capacity of 10.
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedCollection() {
        size = 0;
        // There is no clean way to convert between E and Object, the text book discusses this.
        // We can't instantiate an array of unknown type E, so we must create an Object array, and type-cast
        data = (E[]) new Object[10]; // Start with an initial capacity of 10
    }

    /**
     * Initialize an ArrayBasedCollection<E> Object with a specified capacity.
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedCollection(int capacity) {
        size = 0;
        // There is no clean way to convert between E and Object, the text book discusses this.
        // We can't instantiate an array of unknown type E, so we must create an Object array, and type-cast
        data = (E[]) new Object[capacity]; // Start with an initial capacity specified by the parameter
    }

    /**
     * Double the capacity of the current array (maintains the elements and their ordering)
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        // This is a helper function specific to ArrayCollection
        // Doubles the size of the data storage array, retaining its current contents.
        // You will need to use something similar to the code in the constructor above to create a new array.

        // if the size is zero increase the size to 1
        if (data.length == 0) {
            data = (E[]) new Object[1];
            return;
        }

        // for normal growth create a new array of twice the capacity
        E newData[] = (E[]) new Object[this.data.length * 2];

        // copy all elements
        for (int i = 0; i < size; i++)
            newData[i] = data[i];

        // swap the smaller array with the substantiated bigger one
        this.data = newData;
    }

    /**
     * Ensures that this collection contains the specified element.
     *
     * @param arg0  the element to be added
     * @return true iff the ArrayCollection changed after method execution
     */
    public boolean add(E arg0) {
        // check null case
        if (arg0 == null)
            return false;

        // check for a duplicate
        if (this.contains(arg0))
            return false;

        // grow if the array is full
        if (size == data.length)
            grow();

        // if the element isn't in the list add it to the end and increment the size
        data[size++] = arg0;
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this collection (optional operation).
     *
     * @param arg0  a Collection of elements to be added
     * @return true iff any elements have been added
     */
    public boolean addAll(Collection<? extends E> arg0) {
        int newSize = this.size; // save the initial size of this collection
        for (E element : arg0) // add each element in the passed Collection
            if (!this.contains(element)) // check for duplicates of this and arg0
                this.add(element);

        return (newSize != this.size); // if the size changed then this ArrayBasedCollection changed - return as appropriate
    }

    /**
     * Removes all of the elements from this collection (optional operation).
     */
    public void clear() {
        data = (E[]) new Object[10]; // Start with an initial capacity of 10
        size = 0;
    }

    /**
     * Returns true if this collection contains the specified element.
     *
     * @param arg0  the Object to be found
     * @return true iff arg0 is found in the Object
     */
    public boolean contains(Object arg0) {
        // handle null case
        if (arg0 == null)
            return false;

        // loop through this.data and check each position against the passed Object
        for (int i = 0; i < size; i++)
            if (data[i].equals(arg0))
                return true; // if they match return true
        return false; // if no matches were found return false
    }

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     */
    public boolean containsAll(Collection<?> arg0) {
        if (arg0.size() == 0)
            return false;

//        // initiate a
//        Iterator iter = arg0.iterator();
//
//        // loop through the Collection, and if any element in the passed Collection is not found return false
//        while (iter.hasNext())
//            if (!this.contains(iter.next()))
//                return false;

        for (Object element : arg0)
            if (!this.contains(element))
                return false;

        // iff every element was found return true
        return true;
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
        return new ArrayBasedIterator();
    }

    /**
     * Removes a single instance of the specified element from this collection, if it is present (optional operation).
     */
    public boolean remove(Object arg0) {
        // TODO: comment this bit
        int objIndex = -1;
        for (int i = 0; i < size; i++)
            if (data[i].equals(arg0)) {
                objIndex = i;
                break;
            }

        // if the object wasn't found return false
        if (objIndex == -1)
            return false;

        // cascade items backward to effectively remove the item
        for (int i = objIndex; i < size - 1; i++)
            data[i] = data[i + 1];

        // nullify the final item (which has an adjacent copy at this point) and return true
        data[(size--) - 1] = null;
        return true;
    }

    /**
     * Removes all of this collection's elements that are also contained in the specified collection (optional operation).
     */
    public boolean removeAll(Collection<?> arg0) {
        // TODO: comment this bit
        int initialSize = size;
        for (Object element : arg0)
            this.remove(element);
        return (size != initialSize);
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection (optional operation).
     */
    public boolean retainAll(Collection<?> arg0) {
        //check for null Collection
        if (arg0.size() == 0)
            return false;
        int initialSize = this.size; // used to check for changes (if objects were actually removed)
        for (E element : data) // loop through the array of E items in this object
            if (!arg0.contains(element)) // if the object shouldn't be retained remove it
                this.remove(element);
        return (this.size != initialSize); // return a boolean based on the size changing (if elements were removed)
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
        // create new array and copy the elements from this.data into the array
        Object[] returnArray = new Object[size];
        for (int i = 0; i < size; i++)
            returnArray[i] = data[i];
        return returnArray;
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

        // create an array of objects and use insertion sort to sort the array in place
        E[] sortedArray = (E[]) this.toArray();
        for (int i = 1; i < sortedArray.length; i++) {
            E val = sortedArray[i];
            int j;
            for (j = i - 1; j >= 0 && (cmp.compare(sortedArray[j], val) == 1); j--)
                sortedArray[j + 1] = sortedArray[j];
            sortedArray[j + 1] = val;
        }

        // change the sorted array to a sorted ArrayList and return it
        ArrayList<E> sortedList = new ArrayList<E>(sortedArray.length);
        Collections.addAll(Arrays.asList(sortedArray));
        return sortedList;
    }

    /**
     *
     */
    private class ArrayBasedIterator implements Iterator<E> {
        int index;
        boolean canRemove;
        E toRemove;

        public ArrayBasedIterator() {
            index = 0;
            canRemove = false;
        }

        /**
         * @return
         */
        public boolean hasNext() {
            return canRemove;
        }

        /**
         *
         * @return
         * @throws NoSuchElementException
         */
        public E next() throws NoSuchElementException {
            // if there is nothing left to iterate throw the exception
            if (index == size)
                throw new NoSuchElementException();

            // if there are elements left then 1) increment the Iterator, 2) change the gotNext boolean if the end of the list
            //  has been reached, 3) asserts canRemove, and 4) return the correct data
            index++;
            canRemove = (index != size);
            toRemove = data[index - 1];
            return toRemove;
        }

        /**
         *
         * @throws IllegalStateException
         */
        public void remove() throws IllegalStateException {
            if (!canRemove)
                throw new IllegalStateException();

            ArrayBasedCollection.this.remove(toRemove);
            toRemove = null;
            canRemove = false;
        }
    }
}
