package homework06;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Cody Cortello on 2/25/14.
 */
public class SpecialtySetTrial {
    public static void main(String[] args) {
        SpecialtySet<Integer> newSet = new SpecialtySet<Integer>();
        newSet.add(123);
        System.out.println("first add completed!");
        if (newSet.contains(123))
            System.out.println("set correctly contains first add :123");

        String intsAdded = "";
        Set<Integer> compareSet = new TreeSet<Integer>();

        compareSet.add(123);
        newSet.add(122);
        if (newSet.contains(122))
            System.out.println("set correctly contains 122");
        newSet.add(121);
        if (newSet.contains(121))
            System.out.println("set correctly contains 121");
        newSet.add(124);
        System.out.println("added 124!");
        if (newSet.contains(124))
            System.out.println("set correctly contains 124");
        System.out.println("after third add");
        newSet.add(120);
        System.out.println("after fourth add");

        System.out.println("\n\nentering for loop");
//        for (int i=0; i<20; i++) {
//        	int randomInt = (int) (Math.random()*1000);
////        	System.out.println("before adding to the sets");
//        	newSet.add(randomInt);
////        	System.out.println("after adding to SpecialtySet");
//        	compareSet.add(randomInt);
//        	System.out.println("after adding to the compareSet");
//        	intsAdded+=randomInt+"\t";
//        }
//        System.out.println("after for loop");
//        newSet.add(10000);
//        intsAdded.trim();
//        System.out.println("intsAdded:\t"+intsAdded);
//        System.out.println("Set should be:\t"+compareSet.toString());
//        System.out.println("newSet elements:\t"+newSet.toString());
//        System.out.println("Done!");
        System.out.println("\n\nStarting validation");
        if (newSet.validate())
            System.out.println("Sorted correctly!");
    }
}
