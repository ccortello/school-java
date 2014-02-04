package homework04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Prompts user to enter filename of warehouse data and computes and prints which products do not exist in any warehouse,
 * which products still exist in every warehouse, and the single busiest day for each warehouse.
 *
 * @version 2/2/14.
 * @author Cody Cortello
 */
public class WarehouseReport {

    // initialize lists, sets, and maps to hold relevant information from the file
    public Map<String, Integer> upcs;
    public ArrayList<Inventory> warehouses;

    public static void main(String[] args) {
        new WarehouseReport();
    }

    public WarehouseReport() {

        upcs = null;
        warehouses = null;

        // prompt user for input
        System.out.print("Enter a filename to parse: ");
        Scanner userInput = new Scanner(System.in);

        // initialize file from specified input
//        debug, for speed
        System.out.println("");
//        File dataFile = new File("src/homework04/"+userInput.next());
        File dataFile = new File("src/homework04/data1.txt");

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileScanner.hasNextLine())
            processNextLine(fileScanner.nextLine().trim());

        userInput.close();
        System.out.println("Done!");
    }

    /*
     * Checks the contents of the given String to determine how to process the data
     */
    private void processNextLine(String s) {

        Scanner stringScanner = new Scanner(s);
        String firstWord = stringScanner.next();

//    	debug
        System.out.println(firstWord);

        // check if final line
        if (firstWord.equals("End"))
            return;

        if (firstWord.equals("FoodItem")) {

            // scan the information from the String
            stringScanner.next();
            stringScanner.next();
            stringScanner.next();
            Integer upc = stringScanner.nextInt();

            stringScanner.next();
            stringScanner.next();
            int shelfLife = stringScanner.nextInt();

            stringScanner.next();
            String foodName = "";
            while (stringScanner.hasNext())
                foodName += stringScanner.next() + " ";
            foodName = foodName.trim();

//    		debug
//    		System.out.println("upc: "+upc+", food name: "+foodName+", shelf life: "+shelfLife);
        }


    }
}
