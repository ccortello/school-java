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

        // find a prime number to use for the capacity
        int correctCapacity = nextPrime(capacity);

        // initialize the object fields appropriately
        this.capacity = correctCapacity;
        this.table = new String[correctCapacity];
        this.valid = new boolean[correctCapacity];
        this.functor = functor;
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

        // use quadratic probing to check for a valid add location (either empty or deleted)
        int hash = functor.hash(item);
        for (int i = 0; i < capacity; i++) {
            int currentIndex = hash + i ^ 2; // compute the next location to check
            if (valid[currentIndex]) { // if the location can be used add the item
                table[currentIndex] = item;
                valid[currentIndex] = false;
                size++;
            }
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
        // use quadratic probing to check for a valid add location (either empty or deleted)
        int hash = functor.hash(item);
        for (int i = 0; i < capacity; i++) {
            int currentIndex = hash + i ^ 2;
            if (table[currentIndex].equals(item) || valid[currentIndex]) // if the item is found or could be added
                return true;
        }
        return false;
    }

    /**
     * Returns the first prime integer greater than or equal to the passed integer
     */
    private int nextPrime(int number) {
        // copy the number so we don't affect the parameter
        int n = number;

        // make n odd if it's not
        if (n % 2 == 0)
            n++;

        // increase n until a prime number is found
        while (!isPrime(n))
            n += 2;

        return n;
    }

    /**
     * Returns true iff the passed int is prime
     */
    private boolean isPrime(int number) {
        // test each odd integer smaller than sqrt(number) to see if it's a factor
        for (int test = 3; test < Math.sqrt(number) + 1; test += 2)
            if (number % test == 0)
                return false;

        // if no factor was found the number is prime
        return true;
    }
}
