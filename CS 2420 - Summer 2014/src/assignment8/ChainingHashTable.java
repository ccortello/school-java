package assignment8;

import java.util.LinkedList;

/**
 * A closed-addressed HashTable implementation which uses separate chaining to resolve collisions and doesn't allow for
 * duplicate items.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class ChainingHashTable extends HashTable {
    // underlying data structure for ChainingHashTable, uses seperate chaining to avoid collisions
    private LinkedList<String>[] storage;


    /**
     * Constructor for the ChainingHashTable class that uses separate chaining as the method to resolve collisions.
     *
     * @param capacity initial capacity of the linked list string array
     * @param functor  function object used to create a hashcode for the added strings to this table.
     */
    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor) {
        /*if specified capacity is not prime, than for ChainingHashTable also change capacity to next largest prime
        so that when determining hashcodes they will be more evenly distributed*/
        if (! isPrime(capacity))
            capacity = nextPrime(capacity);
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        hasher = functor;
        this.capacity = capacity;
        size = 0;
        collisions = 0;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     *
     * @return true if this set changed as a result of this method call (that is, if the input item was actually
     * inserted); otherwise, returns false
     */
    public boolean add(String item) {
        // determine string hashcode using HashFunctor specified in constructor
        int hashCode = hasher.hash(item);
        // use the item's hashcode and array size to determine the index or list to add item
        int index = Math.abs(hashCode % capacity);
        // check that String item is not already contained in the LinkedList at this index, if so return false.
        if (storage[index].contains(item))
            return false;
        // first check if the LinkedList is empty, if not increment collisions, if empty increment M (# of lists)
        if (! storage[index].isEmpty())
            collisions++;
        else
            // specified item is not contained in the LinkedList at this index, add specified item and return true.
            storage[index].add(item);
        // increase size
        size++;
        return true;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified item.
     *
     * @param item - the item sought in this set
     *
     * @return true if there is an item in this set that is equal to the input item; otherwise, returns false
     */
    public boolean contains(String item) {
        // determine string hashcode using HashFunctor specified in constructor
        int hashCode = hasher.hash(item);
        // use the item's hashcode and array size to determine the index or list to add item
        int index = Math.abs(hashCode % capacity);
        // check first that LinkedList is not empty at this index, if it is return false.
        if (storage[index].isEmpty() && item != "")
            return false;
        // check if the item is contained within the LinkedList at this index, if so return true, else return false.
        if (storage[index].contains(item))
            return true;
        // item not found, return false.
        return false;
    }

    /**
     * Clear all elements in this ChainingHashTable
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        // set everything to zero & create new empty array for the variable 'storage'
        size = 0;
        collisions = 0;
        storage = (LinkedList<String>[]) new LinkedList[capacity];
    }
}
