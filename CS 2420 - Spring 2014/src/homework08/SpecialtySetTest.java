package homework08;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A few tests for the SpecialtySet class.  The last test demonstrates a novel
 * way to count comparisons.  Students should add more tests (especially more
 * simple tests) to validate their SpecialtySet.
 *
 * @author Peter Jensen
 * @version 2/22/2014
 */
public class SpecialtySetTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test the size of a newly constructed set.
     */
    @Test
    public void test01() {
        SpecialtySet<Double> s = new SpecialtySet<Double>();
        assertEquals("An newly constructed set should have a 0 size: ", 0, s.size());
    }

    /**
     * A simple add/contains test.
     */
    @Test
    public void test02() {
        SpecialtySet<String> s = new SpecialtySet<String>();
        s.add("Hello");

        // Bug in test, not your code, so fix the test first.
        assertEquals("The set should contain 'Hello': ", true, s.contains("Hello"));
    }

    /**
     * A long running add/remove/contains test that uses the
     * helper class below.  This test is VERY incomplete.
     */
    @Test
    public void test03() {
        // Our set.

        SpecialtySet<TrackedInteger> s = new SpecialtySet<TrackedInteger>();

        // A known good set to validate against.

        Set<Integer> v = new TreeSet<Integer>();

        // A random number generator seeded to give back the same sequence each time.

        Random r = new Random(8675309);

        // A debugging variable.

        int totalActions = 0;

        // Repeatedly 'visit' sequences of numbers.

        for (int repeat = 0; repeat < 100; repeat++) {
            // Pick a base, length, step

            int base = r.nextInt(1000);
            int length = r.nextInt(50) + 50;
            int step = r.nextInt(3) + 1;

            // Do an action the appropriate number of times.

            for (int i = 0; i < length; i++) {
                // Make the next integer in the sequence.

                TrackedInteger ti = new TrackedInteger(base + i * step);

                // Pick an action.

                int action = r.nextInt(3);

                // Do the action.

                totalActions++;

                if (action == 0) {
                    s.add(ti);    // Change our set
                    v.add(ti.i);  // Also change the known good set
                } else if (action == 1) {
                    s.remove(ti); // Change our set
                    v.remove(ti.i);  // Also change the known good set
                } else if (action == 2) {
                    // The 'contains' method should report identically for both sets.

                    assertEquals(s.contains(ti), v.contains(ti.i));
                }
            }
        }

        // If the specialty set is coded properly, a relatively small number of
        //   comparisons are done.

        assertTrue("Maximum comparison count test: ", TrackedInteger.comparisonCount <= 200000);

        // Uncomment if needed.

//        System.out.println (totalActions);
//        System.out.println (TrackedInteger.comparisonCount);
//        assertEquals(s.validate(), true);
    }

    /**
     * A helper class with a static variable for tracking all comparisons
     * made with any of this type of object.
     *
     * @author Peter Jensen
     * @version 2/22/2014
     */
    private static class TrackedInteger implements Comparable<TrackedInteger> {
        static long comparisonCount = 0;

        Integer i;

        TrackedInteger(int i) {
            this.i = i;
        }

        @Override
        public int compareTo(TrackedInteger o) {
            comparisonCount++;
            return i.compareTo(o.i);
        }

        @Override
        public boolean equals(Object o) {
            return (o instanceof TrackedInteger) ? ((TrackedInteger) o).compareTo(this) == 0 : false;
        }

        @Override
        public String toString() {
            return "" + i;
        }

    }


    /**
     * Test to see if a newly added element is in the Set
     */
    @Test
    public void test04() {
        SpecialtySet<Integer> newSet = new SpecialtySet<Integer>();
        newSet.add(123);
        System.out.println("\nfirst add completed!");
        assertTrue("set correctly contains first add 123", newSet.contains(123));
    }


    /**
     * Tests to see if the Set can handle doubles trying to be
     * added to it.
     */
    @Test
    public void test05() {
        // initialize the comparison sets
        Set<Double> compareSet = new TreeSet<Double>();
        SpecialtySet<Double> newSet = new SpecialtySet<Double>();

        // add 1000 random doubles to each set
        for (double d = 1; d < 1000; d++) {
            double randomDouble = Math.random() * 1000000000;
            newSet.add(randomDouble);
            compareSet.add(randomDouble);
        }

        for (Double d : compareSet) // make sure that the SpecialtySet contains each element in the automatically sorted TreeSet
            assertTrue(newSet.contains(d));
    }

    /**
     * Tests to see if the Set is sorted when given random integers
     */
    @Test
    public void test06() {
        String intsAdded = "";
        Set<Integer> compareSet = new TreeSet<Integer>();
        SpecialtySet<Integer> newSet = new SpecialtySet<Integer>();

        for (int i = 1; i < 2000; i++) {
            int randomInt = (int) (Math.random() * 1000000);
            newSet.add(randomInt);
            compareSet.add(randomInt);
            intsAdded += randomInt + "\t";
        }

        intsAdded.trim();
        System.out.println("\n This test checks if the list is sorted");
        System.out.println("\nintsAdded:\n" + intsAdded + "\n");
        System.out.println("Set should be:\t" + compareSet.toString());
        System.out.println("newSet elements:\n" + newSet.toString());
        assertTrue(newSet.validate());
    }

    /**
     * Tests if the set removes values correctly
     */
    @Test
    public void test07() {
        SpecialtySet<Integer> newSet = new SpecialtySet<Integer>();

        for (int i = 1; i < 20; i++)
            newSet.add(i);

        for (int i = 1; i < 21; i += 2)
            newSet.remove(i); // since this includes 1 and 19 the special remove cases are handled
    }


    /**
     * Tests if the set increments and decrements size correctly
     */
    @Test
    public void test08() {
        SpecialtySet<Integer> newSet = new SpecialtySet<Integer>();

        for (int i = 0; i < 100; i++) // set up list with integers 0 - 99
            newSet.add(i);

        for (int i = 0; i < 100; i += 2) // remove even numbers
            newSet.remove(i);

        assertEquals(newSet.size(), 50);
    }


}