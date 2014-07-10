package assignment8;

import java.math.BigInteger;
import java.util.Random;

/**
 * A good hashing algorithm for String objects which uses Java's Random
 * functionality to iterate through random ints
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class GoodHashFunctor implements HashFunctor {
    public int hash(String item) {
        BigInteger seed = BigInteger.valueOf(0);
        for (int i = 0; i < item.length(); i++)
            seed += BigInteger.valueOf(item.charAt(i));
        return new Random(Integer.parseInt(intString)).nextInt();
    }
}
