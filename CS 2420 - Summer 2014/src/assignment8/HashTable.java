package assignment8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * An abstract class facilitating the implementation of a concrete hash table.
 *
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public abstract class HashTable implements Set<String> {
    /**
     * FILL IN MEMBER VARIABLES!
     *
     * Any member variables that you would maintain in both your QuadProbeHashTable and
     * your ChainingHashTable (such as, say, a size variable) probably belong here in
     * the parent class. Should the variable(s) be public, private, or protected?
     */
    protected int size, capacity, collisions;
    protected String[] table;
    protected HashFunctor functor;

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise, returns false
     */
    public final boolean addAll(Collection<? extends String> items) {
        int initialSize = size;
        for (String s : items)
            if (!this.contains(s))
                this.add(s);
        return (initialSize != size);
    }

    /**
     * Determines if for each item in the specified collection, there is an item
     * in this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an
     * item in this set that is equal to it; otherwise, returns false
     */
    public final boolean containsAll(Collection<? extends String> items) {
        for (String s : items)
            if (!this.contains(s))
                return false;
        return true;
    }

    /**
     * @return true if this set contains no items.
     */
    public final boolean isEmpty() {
        return (size == 0);
    }

    /**
     * @return the number of items in this set.
     */
    public final int size() {return size;}

    /* advised helper methods - not necessary, but recommended */

    /**
     * Double the size and add each element again
     */
    public void rehash() {
        // copy all existing elements (which shouldn't be used) in the table to a new list
        ArrayList<String> tableCopy = new ArrayList<String>(Arrays.asList(table));

        // increase the capacity of the table, clear all elements, then rehash them back in
        capacity *= 2;
        clear();
        addAll(tableCopy);
    }

    /**
     * @return the load factor lambda of the current table
     */
    public double getLamda() {
        return ((double) capacity) / size;
    }

    /**
     * @return the number of collisions which happened in creating the current table
     */
    public int getCollisions() {
        return collisions;
    }

    /**
     * Clear all elements in the table
     */
    public void clear() {
        size = 0;
        collisions = 0;
        table = new String[capacity];
    }
}
