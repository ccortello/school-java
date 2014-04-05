package homework08;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Cody and Nick Houle on 3/31/14.
 */
public class SpecialtySetTrial {
    public static void main(String[] args) {
        SpecialtySet<Integer> specialtySet = new SpecialtySet<Integer>();
        int test = 0;
        ArrayList<Integer> addedNumbers = new ArrayList<Integer>();
        HashSet<Integer> addedSet = new HashSet<Integer>();
        int[] numbersToAdd = {7, 9, 4, 10, 5, 8, 2, 3, 1, 11, -1, -2};
        int[] numbersToRemove = {9};
        for (int i = 0; i < numbersToAdd.length; i++) {
//            test = (int) (Math.random() * 10000);
//            test = i;
            test = numbersToAdd[i];
            specialtySet.add(test);
            addedNumbers.add(test);
            addedSet.add(test);
//            System.out.println("Added Integer #" + i + ": " + test);
        }
        for (int i = 0; i < numbersToRemove.length; i++) {
//            test = (int) (Math.random() * 10000);
//            test = i;
            System.out.println("Removing Integer #" + (i + 1) + ": " + test);
            test = numbersToRemove[i];
            specialtySet.remove(test);
        }
        System.out.println("Size = " + specialtySet.size() + ", size should be " + addedSet.size());
        if (specialtySet.validate())
            System.out.println("Validated successfully!");
        else System.out.println("Validation failed");
//        System.out.println("Added numbers, in order: "+addedNumbers);
        System.out.println("Added a total of " + addedNumbers.size() + " numbers");
        System.out.println("\nPrinting Specialty Set...");
        System.out.println(specialtySet);

        System.out.println("Done testing!");
    }
}
