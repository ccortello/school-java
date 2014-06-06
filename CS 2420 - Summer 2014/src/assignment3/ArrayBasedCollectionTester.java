package assignment3;

/**
 * @author Paymon Saebi
 * @author Cody Cortello
 * @author Casey Nordgran
 *         <p/>
 *         Systematically arrayTest the ArrayBasedCollection class
 */
public class ArrayBasedCollectionTester {
    static ArrayBasedCollectionTest arrayTest = new ArrayBasedCollectionTest();
    static SearchUtilTest searchTest = new SearchUtilTest();

    /**
     * Set up arrayTest fields then call 'tester' method to execute tests
     */
    public static void main(String[] args) {
        System.out.println("If no exceptions are thrown then the JUnit tests all passed");
        try {
            arrayTest.setUp();
            searchTest.setUp();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        tester();
    }

    /**
     * Execute JUnit tests
     */
    public static void tester() {
        try {
            arrayTest.testAdd();
            arrayTest.testAddAll();
            arrayTest.testClear();
            arrayTest.testContains();
            arrayTest.testContainsAll();
            arrayTest.testGrow();
            arrayTest.testIsEmpty();
            arrayTest.testIterator();
            arrayTest.testRemove();
            arrayTest.testRemoveAll();
            arrayTest.testRetainAll();
            arrayTest.testSize();
            arrayTest.testToArray();
            arrayTest.testToSortedList();
            arrayTest.tearDown();

            searchTest.testBinarySearch();
            searchTest.tearDown();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
