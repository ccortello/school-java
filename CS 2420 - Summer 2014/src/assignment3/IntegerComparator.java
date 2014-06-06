package assignment3;

import java.util.Comparator;

/**
 * @author Paymon Saebi
 * @author
 * @author Wrapper class for an Integer comparator, implementing the compare
 *         Uses the Integer class's comparable compareTo to do the comparison
 */
public class IntegerComparator implements Comparator<Integer> {
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
