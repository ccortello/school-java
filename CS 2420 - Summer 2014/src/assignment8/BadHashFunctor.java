package assignment8;

/**
 * A bad hashing algorithm for String objects
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class BadHashFunctor implements HashFunctor {
    public int hash(String item) {
        return item.length();
    }
}