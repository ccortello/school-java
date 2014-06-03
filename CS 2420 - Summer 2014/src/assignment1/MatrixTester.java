package assignment1;

public class MatrixTester {
    public static void main(String[] args) {
        Matrix M1 = new Matrix(new int[][]
                {{1, 2, 3},
                        {2, 5, 6}});

        Matrix M2 = new Matrix(new int[][]
                {{4, 5},
                        {3, 2},
                        {1, 1}});

        // this is the known correct result of multiplying M1 by M2
        Matrix referenceMultiply = new Matrix(new int[][]
                {{13, 12},
                        {29, 26}});

		
		/* 
         * Note that none of the tests below will be correct until you have implemented all methods.
		 * This is just one example of a test, you must write more tests and cover all cases.
		 */

        // get the matrix computed by your times method
        Matrix computedMultiply = M1.times(M2);

        // exercises your toString method
        System.out.println("Computed result for M1 * M2:\n" + computedMultiply);

        // exercises your .equals method, and makes sure that your computed result is the same as the known correct result
        if (!referenceMultiply.equals(computedMultiply))
            System.out.println("Should be:\n" + referenceMultiply);

        else
            System.out.println("Multiplication and .equals validate!");

        Matrix M3 = new Matrix(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        Matrix M4 = new Matrix(new int[][]{{2000, 3000, 4000}, {5000, 6000, 7000}, {8000, 9000, 10000}});

        Matrix M5 = M3.plus(M4);
        System.out.println("\nTesting .plus: M5 =\n" + M5); /// should be 2001, 3002, 4003...

        Matrix M6 = M3.times(M4);
        System.out.println("Testing .times: M6 =\n" + M6); /// should be 36k, 42k, 48k, 81k, 96k, 111k, 126k, 150k, 174k

        Matrix M7 = new Matrix(new int[10][15]);

        System.out.println("Testing incorrect addition dimensions:");
        Matrix M8 = M6.plus(M7);

        System.out.println("\nTesting incorrect multiplication dimensions:");
        Matrix M9 = M6.times(M7);

        System.out.println("\nTesting complete!\nCody Cortello - u0781604");
    }
}