/**
 * Simulates a direct-mapped, n-way associative, or fully associative cache
 * 
 * @author Cody Cortello
 * @date 12/7/13
 */
public class CS3810HW9 {
	public static void main(String[] args) {
		/*
		 * offset = addr % blocksize
		 * row = (addr / blocksize) % #rows
		 * tag = addr / blocksize
		 */
		
		// establish type of cache with integer variables: direct = 1 way, associative = 2+ ways
		int ways = 1;
		int blocksize = 8; // in bytes
		int rows = 8;
		
		// import addresses to be accessed
		int [] addresses = {16, 20, 24, 28, 32, 36, 60, 64, 56, 60, 64, 68, 72, 76, 92, 96, 100, 104, 108, 112, 136, 140};
		
		// create 2D arrays for the valid bit, tag, and LRU
		boolean [][] valid = new boolean [rows][ways];
		int [][] tags = new int [rows][ways];
		int [][] LRU = new int [rows][ways];
		
		// initialize variables for LRU and efficiency calculations
		int time = 0;
		int cycles = 0;
		int hits = 0;
		int misses = 0;
		
		// run cache first time
doh:	for (int addr : addresses) {
			
			// increment time
			time++;
			
			// calculate row and tag for that address
			int row = (addr / blocksize) % rows;
			int tag = addr / blocksize;
			
			System.out.print("Accessing " +addr+ "(tag " +tag+ "): ");
			
			// iterate through each way and check the valid and tag entries for matches
			for (int way = 0; way < ways; way++) {
				if (valid[row][way] && tag==tags[row][way]) {
					
					// Hit
					hits++;
					cycles++;
					LRU[row][way]=time;
					
					System.out.println("hit from row " +row);
					continue doh;
				}
			}
			
			// if address not found in cache, then find LRU line
			int oldestWay=0;
			for (int way = 1; way < ways; way++) {
				if (LRU[row][way] < LRU[row][oldestWay]) {
					oldestWay=way;
				}
			}
			
			// store data
			valid[row][oldestWay]=true;
			LRU[row][oldestWay]=time;
			tags[row][oldestWay]=tag;
			
			// increment counters
			misses++;
			cycles += 18+blocksize;
			
			// display miss message
			System.out.println("miss - cached to row "+row);
		}
		System.out.println("Hits: "+hits);
		System.out.println("Misses: "+misses);
		System.out.println("Cycles: "+cycles);
		
		// initialize variables and run cache second time
		cycles=0;
		time=0;
		misses=0;
		hits=0;
		// run cache first time
doh2:	for (int addr : addresses) {
			
			// increment time
			time++;
			
			// calculate row and tag for that address
			int row = (addr / blocksize) % rows;
			int tag = addr / blocksize;
			
			System.out.print("Accessing " +addr+ "(tag " +tag+ "): ");
			
			// iterate through each way and check the valid and tag entries for matches
			for (int way = 0; way < ways; way++) {
				if (valid[row][way] && tag==tags[row][way]) {
					
					// Hit
					hits++;
					cycles++;
					LRU[row][way]=time;
					
					System.out.println("hit from row " +row);
					continue doh2;
				}
			}
			
			// if address not found in cache, then find LRU line
			int oldestWay=0;
			for (int way = 1; way < ways; way++) {
				if (LRU[row][way] < LRU[row][oldestWay]) {
					oldestWay=way;
				}
			}
			
			// store data
			valid[row][oldestWay]=true;
			LRU[row][oldestWay]=time;
			tags[row][oldestWay]=tag;
			
			// increment counters
			misses++;
			cycles += 18+blocksize;
			
			// display miss message
			System.out.println("miss - cached to row "+row);
		}
		System.out.println("Hits: "+hits);
		System.out.println("Misses: "+misses);
		System.out.println("Cycles: "+cycles);
		System.out.println("CPI: "+cycles/22.0);
		
		// initialize variables and run cache second time
		cycles=0;
		time=0;
		misses=0;
		hits=0;
		}
	}
