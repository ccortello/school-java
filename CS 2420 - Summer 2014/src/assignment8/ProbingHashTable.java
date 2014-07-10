package assignment8;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * An open-addressed HashTable implementation which uses quadratic probing to resolve collisions
 * and doesn't allow for duplicate items
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class ProbingHashTable extends HashTable {
    private String[] table;

    /**
     * Constructs a HashTable with a given capacity and using a particular hash
     * function.
     *
     * @param capacity the number of 'buckets' in the hash table
     * @param functor  the hash function the table uses to index objects
     */
    public ProbingHashTable(int capacity, HashFunctor functor) {
        // check that capacity is a prime number, if not, make it prime.
        if (! isPrime(capacity))
            capacity = nextPrime(capacity);
        // initialize the object fields appropriately
        this.collisions = 0;
        this.capacity = capacity;
        this.table = new String[capacity];
        hasher = functor;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     * if the input item was actually inserted); otherwise, returns false
     */
    public boolean add(String item) {
        // don't add the item if it already exists in the table
        if (this.contains(item))
            return false;

        // use quadratic probing to check for a used add location (either empty or deleted)
        int hash = hasher.hash(item);
        for (int i = 0; i < (capacity / 2) + 1; i++) {
            int currentIndex = hash + i ^ 2; // compute the next location to check
            if (table[currentIndex] == null) { // if the location can be used add the item and quit checking locations
                table[currentIndex] = item;
                size++;
                break;
            }
            collisions++; // if the item cannot be added at the location it's a collision
        }

        // resize the table if the load factor has become too large
        if (getLamda() > 0.5)
            rehash();
        return true;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input
     * item; otherwise, returns false
     */
    public boolean contains(String item) {
        // handle null case
        if (item == null)
            return true; // return true so the caller doesn't try to change the table

        // use quadratic probing to check for the item
        int hash = hasher.hash(item);
        for (int i = 0; i < (capacity / 2) + 1; i++) {
            int currentIndex = hash + i ^ 2;
            if (table[currentIndex] == null) // if the location is empty the item hasn't been added
                return false;
            else if (table[currentIndex].equals(item)) // if the item is found it's obviously in the table
                return true;
        }
        return false;
    }

    /**
     * Clear all elements in the ProbingHashTable
     */
    public void clear() {
        size = 0;
        collisions = 0;
        table = new String[capacity];
    }

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
}
