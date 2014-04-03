package homework08;

/**
 * Created by Cody and Nick Houle on 3/31/14.
 */
public class SpecialtySetTrial {
    public static void main(String[] args) {
        SpecialtySet<Integer> specialtySet = new SpecialtySet<Integer>();
        int test = 0;
        for (int i = 1; i <= 10; i++) {
//            test = (int) (Math.random() * 100);
            test = i;
            specialtySet.add(test);
            System.out.println("Added Integer #" + i + ": " + test);
        }
        for (int i = 1; i <= 10; i++) {
//            test = (int) (Math.random() * 100);
            test = i;
            specialtySet.add(test);
            System.out.println("Added Integer #" + i + ": " + test);
        }
        System.out.println("Size = " + specialtySet.size());
        if (specialtySet.validate())
            System.out.println("Validated successfully!");
        else System.out.println("Validation failed");

        System.out.println("Size = " + specialtySet.size());
//        System.out.println("specialtySet.toString: ");
//        System.out.println(specialtySet);
//        System.out.println("Done testing!");
    }
}
