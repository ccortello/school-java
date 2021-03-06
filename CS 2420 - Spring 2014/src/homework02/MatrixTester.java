/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with more code to
 * sufficiently test your Matrix class. We will be using our own MatrixTester for grading.
 */
package homework02;

/**
 * Application for testing the Matrix class.
 * 
 * @author Cody Cortello - u0781604
 * @version 1/17/14
 */
public class MatrixTester
{
    public static void main (String[] args)
    {
        System.out.println ("Cody Cortello");
        System.out.println ("Assignment #2");        
        
        // Note - when you create a new int[][], you can supply initial values, see
        //   below for the syntax.
        
        // These statements exercise the second Matrix constructor.
        
        Matrix M1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });

        Matrix M2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });

        // This is the known correct result of multiplying M1 by M2.
        
        Matrix referenceMultiply = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });

        /*
         * Note that none of the tests below will be correct until you have implemented all methods. This is just one
         * example of a test, you must write more tests and cover all cases.
         */

        // Get the matrix computed by your times method.
        // This statement exercises your multiply method.

        Matrix computedMultiply = M1.multiply(M2);
        
        // This test exercises your toString method.
        System.out.print ("\nTest #1 - Computed result for M1 * M2:\n" + computedMultiply);

        // This statement exercises your .equals method, and makes sure that your 
        // computed result is the same as the known correct result.
        
        if (!referenceMultiply.equals(computedMultiply))
            System.out.println("  Should be:\n" + referenceMultiply);
        else
            System.out.println ("  Correct");

        // My tests begin here
        //  Initialize two matrixes and use the toString to print them.
        System.out.println("\n\n------------------------\nMy Tests\n------------------------\n\n"
        		+ "Test 2 - construction from a 2D array,\nmultiplication, and addition\n");
        Matrix m1 = new Matrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 } });
        Matrix m2 = new Matrix(new int[][] { { 7, 10, 13, 16 }, { 8, 11, 14, 17 }, { 9, 12, 15, 18 } });

        System.out.println("m1:\n"+m1);
        System.out.println("m2:\n"+m2);

        // Multiplication test
        System.out.println("m1 * m2:\n"+m1.multiply(m2));
        
        Matrix m4 = new Matrix(new int[][] { { 5, 2, 7 }, { 9, 12, -6 } });
        System.out.println("m4:\n"+m4);
        
        // Addition test
        System.out.println("m1 + m4:\n"+m1.add(m4));
        
        // Test 3 header
        System.out.println("------------------------\nTest 3 - normal construction, setElement,"
        		+ " and getElement\n------------------------\n");
        
        // Normal construction test
        Matrix m5 = new Matrix(2,2);
        System.out.println("m5:\n"+m5);
        
        // setElement test
        System.out.println("set 1,1 to 165 (remember, still 0-based indexing)\n");
        m5.setElement(1, 1, 165);
        
        System.out.println("m5:\n"+m5);
        
        // getElement test
        System.out.println("Element 1,1 = "+m5.getElement(1, 1));
        
        // Test 4 header
        System.out.println("\n------------------------\nTest 4 - getHeight and "
        		+ "getWidth\n------------------------\n");

        System.out.println("m2:\n"+m2);
        
        // getHeight test
        System.out.println("Height of m2 = "+m2.getHeight());
        System.out.println("Width of m2 = "+m2.getWidth());
        

        // Test 5 header
        System.out.println("\n------------------------\nTest 5 - failure tests\n------------------------\n");
        
        // equals failure
        System.out.println("Is m1 equal to \"hello world\"?");
        String helloWorld = "hello world";
        System.out.println(m1.equals(helloWorld));

        System.out.println("\nIs m1 equal to m2?");
        System.out.println(m1.equals(m2));
        
        System.out.println("\nIs m1 equal to m1+m1?");
        System.out.println(m1.equals(m1.add(m1)));
        
        System.out.println("\nIs m1 equal to m1?");
        System.out.println(m1.equals(m1));
        
        System.out.println("\nWhat is m2 multiplied by m1?");
        System.out.println(m2.multiply(m1));
    }
}
