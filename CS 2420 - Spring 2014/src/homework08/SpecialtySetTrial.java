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
        for (int i = 1; i <= 10000; i++) {
            test = (int) (Math.random() * 10000);
//            test = i;
            specialtySet.add(test);
            addedNumbers.add(test);
            addedSet.add(test);
//            System.out.println("Added Integer #" + i + ": " + test);
        }
        System.out.println("Size = " + specialtySet.size() + ", size should be " + addedSet.size());
        if (specialtySet.validate())
            System.out.println("Validated successfully!");
        else System.out.println("Validation failed");
//        System.out.println("Added numbers, in order: "+addedNumbers);
        System.out.println("Added a total of " + addedNumbers.size() + " numbers");

        System.out.println("Done testing!");
    }
}
