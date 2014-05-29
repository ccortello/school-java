package examples.week2examples;

import assignment1.Matrix;

import java.util.Random;

public class MatrixTimer {

    /**
     * @param args
     */
    public static void main(String[] args) {

        long startTime, midpointTime, stopTime;

        // First, spin computing stuff until one second has gone by.
        // This allows this thread to stabilize.
        // (As seen in lab1 experiment 8)

        startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1000000000) { // empty block
        }

        // Now, run the test.

        int timesToLoop = 1000;
        int numTests = 2;

        // Print column headers
        System.out.println("N: plus");


        // Run complete test for different values of N
        for (int N = 10; N <= 100; N += 10) {

            // Generate the random Matrices before starting the timer
            Matrix M1 = randomSquareMatrix(N);
            Matrix M2 = randomSquareMatrix(N);


            // ----- Lab 1 timing code -----

            startTime = System.nanoTime();

            Matrix result;
            for (long i = 0; i < timesToLoop; i++)
                result = M1.plus(M2);

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.

            for (int i = 0; i < timesToLoop; i++) { // empty block
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.

            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
                    / timesToLoop;


            System.out.println(N + ": " + averageTime);


        } // closes for(int N = 10; N <= 100; N+=10)


    }

    private static Matrix randomSquareMatrix(int N) {
        Random r = new Random();
        int random2DArray[][] = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                random2DArray[i][j] = r.nextInt();
        return new Matrix(random2DArray);
    }

}
