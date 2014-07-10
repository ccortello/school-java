package assignment8;

import java.math.BigInteger;

/**
 * A good hashing algorithm for String objects which uses Java's Random
 * functionality to iterate through random ints
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class GoodHashFunctor implements HashFunctor {
    public int hash(String item) {
        // string to hold all the integer values of each char in the specified string item
        String numVal = "";
        for (int i = 0; i < item.length(); i++) {
            numVal += item.codePointAt(i);
        }
        // convert the long string integer into a BigInteger
        BigInteger intVal = new BigInteger(numVal);
        // return the BigInteger converted to an integer
        return intVal.intValue();
    }
}
