package homework02;

/**
 * Application for testing the Matrix class.
 * 
 * @author Cody Cortello
 * @version 1/17/14
 */
public class MatrixAdditionAlgorigthms
{
    public static void main (String[] args)
    {
    	// Make some array sizes (the arrays will be square, so only one
    	//  dimension is specified
        int[] sizes = {1000,2000,3000,4000,5000,6000,7000}; // for bigger sizes I got a heap overflow error
        
        // print initial message
        System.out.println("Matrix addition algorithm tests\nName: Cody Cortello\nDate: 1/17/15\n\n");

        // run tests using addition method 1 (adding down rows)
        matrixAddition1(sizes, 20);

        // run tests using addition method 2 (adding down columns)
        matrixAddition2(sizes, 20);        
        
        System.out.println("Done!");
    }

    /* Matrix addition tester adding down rows */
    public static void matrixAddition1 (int[] sizes, int timesToLoop)
    {
    	// print initial message
    	System.out.println("Method 1 (add down rows)\nSize\tTime");
    	
    	// iterate through sizes array
    	for (int size=0; size<sizes.length; size++)
        {
    		// create two matrixes of the selected size
        	Matrix m1 = new Matrix(sizes[size],sizes[size]), m2= new Matrix(sizes[size],sizes[size]);
        	
        	// populate them with values (1)
        	for (int row=0; row<m1.getHeight(); row++)
        		for (int column=0; column<m1.getWidth(); column++)
        		{
        			long randomNumber1 = (long) Math.random()*100;
        			long randomNumber2 = (long) Math.random()*100;
        			
        			m1.setElement(row, column, randomNumber1);
        			m2.setElement(row, column, randomNumber2);
        		}
        	
        	/**
        	 * Note!! correct timer usage copied from TimingExperiment08.java in this
        	 * week's lab, author = pajensen
        	 */
        	
        	// establish timers to compute execution time for the addition
        	
        	long startTime, midpointTime, stopTime;
        	
        	// First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.
        	
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000){}

            // Now, run the test.

            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++)
                    m1.add(m2);
            
            // finalize timers, again taken from TimingExperiment08.java
            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            for (long i = 0; i < timesToLoop; i++){}
            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.
            
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
            
            System.out.println(sizes[size]+"\t"+averageTime);
        }
    }
    
    /* Matrix addition tester adding down columns */
    public static void matrixAddition2 (int[] sizes, int timesToLoop)
    {
    	// print initial message
    	System.out.println("\nMethod 2 (add down columns)\nSize\tTime");
    	
    	// iterate through sizes array
    	for (int size=0; size<sizes.length; size++)
        {
    		// create two matrixes of the selected size
        	Matrix m1 = new Matrix(sizes[size],sizes[size]), m2= new Matrix(sizes[size],sizes[size]);
        	
        	// populate them with values (1)
        	for (int row=0; row<m1.getHeight(); row++)
        		for (int column=0; column<m1.getWidth(); column++)
        		{
        			long randomNumber1 = (long) Math.random()*100;
        			long randomNumber2 = (long) Math.random()*100;
        			
        			m1.setElement(row, column, randomNumber1);
        			m2.setElement(row, column, randomNumber2);
        		}
        	
        	/**
        	 * Note!! correct timer usage copied from TimingExperiment08.java in this
        	 * week's lab, author = pajensen
        	 */
        	
        	// establish timers to compute execution time for the addition
        	
        	long startTime, midpointTime, stopTime;
        	
        	// First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.
        	
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000){}

            // Now, run the test.

            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++)
                    m1.add2(m2);
            
            // finalize timers, again taken from TimingExperiment08.java
            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            for (long i = 0; i < timesToLoop; i++){}
            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.
            
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;
            
            System.out.println(sizes[size]+"\t"+averageTime);
        }
    }

}
