package homework04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Prompts user to enter filename of warehouse data and computes and prints which products do not exist in any warehouse,
 * which products still exist in every warehouse, and the single busiest day for each warehouse.
 *
 * @version 2/2/14.
 * @authoer Cody Cortello
 */
public class WarehouseReport {

    public static void main(String[] args) {

        // prompt user for input
        System.out.print("Enter a filename to parse: ");
        Scanner userInput = new Scanner(System.in);

        try {
            Scanner fileScanner = new Scanner(new File(userInput.next()));
            while (fileScanner.hasNextLine()) {
                processNextLine(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }

    private static void processNextLine(String s) {
        System.out.println(s);
    }
}
