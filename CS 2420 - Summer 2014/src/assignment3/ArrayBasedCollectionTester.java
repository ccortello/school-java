package assignment3;

/**
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 *         <p/>
 *         Systematically test the ArrayBasedCollection class
 */
public class ArrayBasedCollectionTester {
    static ArrayBasedCollectionTest test;

    /**
     * Set up test fields then call 'tester' method to execute tests
     */
    public static void main(String[] args) {
        try {
            test.setUp();
        } catch (Exception e) {
        }

        tester();
    }

    /**
     * Execute JUnit tests
     */
    public static void tester() {
        try {
            test.testAdd();
            test.testAddAll();
            test.testClear();
            test.testContains();
            test.testContainsAll();
            test.testGrow();
            test.testIsEmpty();
            test.testIterator();
            test.testRemove();
            test.testRemoveAll();
            test.testRetainAll();
            test.testSize();
            test.testToArray();
            test.testToSortedList();
            test.tearDown();
        } catch (Exception e) {
        }
    }
}
