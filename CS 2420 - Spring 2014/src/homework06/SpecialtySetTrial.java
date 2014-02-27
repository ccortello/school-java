package homework06;

/**
 * Created by Cody Cortello on 2/25/14.
 */
public class SpecialtySetTrial {
    public static void main(String[] args) {
        SpecialtySet<Integer> newSet = new SpecialtySet<Integer>();
        newSet.add(123);
        if (newSet.contains(123))
            System.out.println("correctly contains 123");
        System.out.println("Done!");
    }
}
