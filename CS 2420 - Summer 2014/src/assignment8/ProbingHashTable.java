package assignment8;

/**
 * A closed-addressed HashTable implementation which uses quadratic probing
 */
public class ProbingHashTable extends HashTable {
    /**
     * Constructs a HashTable with a given capacity and using a particular hash
     * function.
     *
     * @param capacity the number of 'buckets' in the hash table
     * @param functor  the hash function the table uses to index objects
     */
    public ProbingHashTable(int capacity, HashFunctor functor) {

    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     * if the input item was actually inserted); otherwise, returns false
     */
    public boolean add(String item) {
        return false;
    }

    /**
     * Removes all items from this set. The set will be empty after this method call.
     */
    public void clear() {

    }

    /**
     * Determines if there is an item in this set that is equal to the specified item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input
     * item; otherwise, returns false
     */
    public boolean contains(String item) {
        return false;
    }

    /* advised helper methods - not necessary, but recommended */

    private int nextPrime(int number) {
        return 0;
    }

    private boolean isPrime(int number) {
        return false;
    }
}
