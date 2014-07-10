package assignment8;

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
    //TODO

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
    public final int size() {
        //TODO
        return 0;
    }

    /* advised helper methods - not necessary, but recommended */

    public void rehash() {

    }

    public double getLamda() {
        return 0.0;
    }

    public int getCollisions() {
        return 0;
    }
}
