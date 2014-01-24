/** A simple experiment on the workings of a rudimentary selection sort.
 * 28 arrays of length 1 to 1000 are created and filled with random doubles
 * in the range [0,1). The arrays are then sorted and resorted 50 times
 * each, and the average number of "swaps" made for each array size is
 * counted and printed.
 * 
 * @author Cody Cortello
 * @version January 8, 2014
 */

package homework01;

public class SelectionSortExperiment {
	public static void main(String[] args) {
		
		// Print header
		System.out.println("Cody Cortello\nAssignment #1\nNote: I used the average of 50 tests\nfor each of the following 28 array sizes\n\nn\tcount(n)");
		
		// Create array of sizes to test
		int[] sizes = {1,2,3,4,5,6,7,8,9,10,20,30,40,50,60,70,80,90,100,200,300,400,500,600,700,800,900,1000};
		
		// Loop through sizes array and print the selection sort for each size
		for(int size:sizes) {
			int totalSwaps=0; //record the total number of swaps for the 50 attempts
			for(int timesSorted=0;timesSorted<50;timesSorted++){
				double testArray[]=makeRandomArray(size);
				totalSwaps += selectionSortExperiment(testArray);
			}
			int averageSwaps=totalSwaps/50; // Calculate the average number of swaps
			System.out.print(size+"\t"+averageSwaps);
			
			int avgOverN=averageSwaps/size;
			System.out.println("\t"+avgOverN);
		}
	}
	/** Returns an array of doubles filled with random values in the
	  * range [0...1).
	  *
	  * The caller specifies a non-negative n to indicate the size of
	  *   the array.
	  *
	  * This function does not print anything to the console.
	  *
	  * @param  n  The desired size of an array.
	  * @return    An array of n doubles filled with random values
	  */
	static public double[] makeRandomArray (int n) {
		double[] randomArray = new double[n];
		for (int i=0;i<randomArray.length-1;i++)
			randomArray[i]=Math.random();
		return randomArray;
	}
	
	/** Returns a count of how many times selection sort finds a
	  * 'better' value during the sorting process.  The input array
	  * is sorted as a side effect.
	  *
	  * This function does not print anything to the console.
	  *
	  * @param  data  An array of double values
	  * @return       A count of how many times 'better' values were found
	  */
	static public int selectionSortExperiment (double[] data) {
		int size = data.length;		// Set size = size of array
		int swapCount=0;			// Record the number of times we swap values in the array
		
			for(int firstPosition=0;firstPosition<=size-2;firstPosition++) {	// Loop a 'first position' value from 0 to size-2 inclusive
				int bestPosition=firstPosition;	// Set a 'best position' value = first position
				
				for(int currentPosition=firstPosition+1;currentPosition<=size-1;currentPosition++)	// Loop a 'current position' value from first position+1 to size-1 inclusive
					if(data[currentPosition]<data[bestPosition]) {	// If array[current position] < array[best position] then
						bestPosition=currentPosition;	// Best position = current position
						swapCount++;	// Increment the swap counter
					}
				
				/* Swap values in the array at array[first position] and array[best position] */
				double tempDouble=data[firstPosition]; 	// Store the first position in a temporary variable
				data[firstPosition]=data[bestPosition];	// Store the best position value in the first position slot
				data[bestPosition]=tempDouble;			// Store the temp variable (the original first position value) to the best position slot, completing the swap
			}
		return swapCount;
	}
}