package lab01;

public class CoinFlipExperiment {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int[] counts = new int[201];

        int winnings = 0; // total money won or lost

        for (int i = 0; i < 10000; i++) {
            int amount = coinFlipExperiment();
//			System.out.println ("Win/loss amount: " + amount);

            winnings += amount;
            counts[amount + 100] = counts[amount + 100] + 1;
        }
//		System.out.println("Winnings = "+winnings);

        System.out.println("Likelihood of win/loss amount after 100 flips:");

        System.out.print("Amount");
        System.out.print("\t");  // A tab character
        System.out.print("Probability");
        System.out.println();

        // Loop through amounts

        double attempts = 10000.0;
        for (int i = 0; i <= 200; i++) {
            System.out.print(i - 100);
            System.out.print("\t");
            System.out.print(counts[i] / attempts);
            System.out.println();
        }
    }

    /**
     * Returns the amount of money you'd win or lose
     * by flipping an unbalanced coin 100 times.
     *
     * @return the amount of money won/lost
     */
    static public int coinFlipExperiment() {
        // Declare an int variable to keep track of winnings.
        int winnings;

        // Set winnings to 0.
        winnings = 0;
        for (int i = 0; i < 100; i++) {
            // Flip the coin.
            double flip = Math.random();

            // If heads, add $1 to winnings.
            if (flip < 0.505)
                winnings += 1;

                // If tails, subtract $1 from winnings.
            else
                winnings -= 1;
        }

        // Return winnings.
        return winnings;
    }
}
