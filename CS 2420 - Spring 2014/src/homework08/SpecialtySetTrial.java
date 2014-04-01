package homework08;

/**
 * Created by Cody and Nick Houle on 3/31/14.
 */
public class SpecialtySetTrial {
    public static void main(String[] args) {
        SpecialtySet<Integer> specialtySet = new SpecialtySet<Integer>();
        for (int i = 0; i < 1000; i++) {
            specialtySet.add((int) (Math.random() * 1000));
            System.out.println("Added Integer #" + (i + 1));
        }
        specialtySet.validate();
        System.out.println(specialtySet.size());
//        System.out.println("specialtySet.toString: ");
//        System.out.println(specialtySet);
//        System.out.println("Done testing!");
    }
}
