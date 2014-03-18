package homework02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatrixTest {

    private Matrix matrix1, M1, M2;

    @Before
    public void setUp() throws Exception {
        matrix1 = new Matrix(new int[][]{{13, 12}, {29, 26}});
        M1 = new Matrix(new int[][]{{1, 2, 3}, {2, 5, 6}});
        M2 = new Matrix(new int[][]{{4, 5}, {3, 2}, {1, 1}});
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link lab04.MyHelperFunctions#findSmallestDiff(int[])}.
     */
    @Test
    public void testMatrix() {
        // Call the method.

        Matrix result;
        result = M1.multiply(M2);

        // Test the result.

        assertEquals(result, matrix1);

        // If the assert fails, the test fails.
        // If we get this far, the test passes.
    }

}
