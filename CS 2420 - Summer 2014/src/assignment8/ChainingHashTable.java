package assignment8;

/**
 * A closed-addressed HashTable implementation which uses separate chaining to resolve collisions
 * and doesn't allow for duplicate items.
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class ChainingHashTable extends HashTable {
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
     * Determines if there is an item in this set that is equal to the specified item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input
     * item; otherwise, returns false
     */
    public boolean contains(String item) {
        return false;
    }
}
