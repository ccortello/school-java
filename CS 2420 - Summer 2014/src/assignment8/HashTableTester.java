package assignment8;

import junit.framework.TestCase;

/**
 * Tester class to test various methods
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class HashTableTester extends TestCase {

    public void testGoodHashFunctor() {
        HashFunctor trial = new GoodHashFunctor();
        trial.hash("test");
    }
}
