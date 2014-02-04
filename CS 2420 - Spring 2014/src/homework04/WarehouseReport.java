package homework04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Prompts user to enter filename of warehouse data and computes and prints which products do not exist in any warehouse,
 * which products still exist in every warehouse, and the single busiest day for each warehouse.
 *
 * @version 2/2/14.
 * @author Cody Cortello
 */
public class WarehouseReport {

    public static void main(String[] args) {

        // prompt user for input
        System.out.print("Enter a filename to parse: ");
        Scanner userInput = new Scanner(System.in);

//        debug
//        File dataFile = new File("src/homework04/"+userInput.next());
        File dataFile = new File("src/homework04/data1.txt");

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileScanner.hasNextLine())
            processNextLine(fileScanner.nextLine());

        userInput.close();
        System.out.println("Done!");
    }

    private static void processNextLine(String s) {
        System.out.println(s);
    }
}
