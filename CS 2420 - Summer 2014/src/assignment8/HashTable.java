package assignment8;

import java.util.Collection;
import java.util.LinkedList;

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
    public int size;
    public int capacity;
    private String[] table;

    /**
     *
     */
    public final boolean addAll(Collection<? extends String> items) {
        //TODO
        return false;
    }

    /**
     *
     */
    public final boolean containsAll(Collection<? extends String> items) {
        //TODO
        return false;
    }

    /**
     *
     */
    public final boolean isEmpty() {
        //TODO
        return false;
    }

    /**
     *
     */
    public final int size() {return size;}

    /* advised helper methods - not necessary, but recommended */

    /**
     * Double the size and add each element again
     */
    public void rehash() {
        // copy all existing elements in the table to a new list
        LinkedList<String> tableCopy = new LinkedList<String>();
        for (String s : table)
            if (s != null)
                tableCopy.add(s);

        // increase the capacity of the table, clear all elements, then rehash them back in
        capacity *= 2;
        clear();
        addAll(tableCopy);
    }

    public double getLamda() {
        return 0.0;
    }

    public int getCollisions() {
        return 0;
    }

    /**
     * Clear all elements in the table
     */
    public void clear() {
        size = 0;
        table = new String[capacity];
    }
}
