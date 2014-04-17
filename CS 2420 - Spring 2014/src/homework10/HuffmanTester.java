package homework10;

import java.util.ArrayList;

/**
 * Created by Cody on 4/16/14.
 */
public class HuffmanTester {
    public static void main(String[] args) {
        ArrayList<Integer> testListOne = new ArrayList<Integer>();
        testListOne.add(10);
        testListOne.add(20);
        testListOne.add(30);
        ArrayList<Integer> testListTwo = new ArrayList<Integer>();
        testListTwo.add(5);
        testListTwo.add(15);
        testListTwo.add(25);
        testListTwo.add(35);
        System.out.println(testListOne.size() + "\t" + testListOne);
        System.out.println(testListTwo.size() + "\t" + testListTwo);
        ArrayList<Integer> testListThree = new ArrayList<Integer>(testListOne.size() + testListTwo.size());
        int i = 0, j = 0;
        while (i < testListOne.size() && j < testListTwo.size()) {
            System.out.println(testListOne.get(i) + "\t" + testListTwo.get(j));
            if (testListOne.get(i) < testListTwo.get(j)) {
                testListThree.add(testListOne.get(i));
                i++;
            } else {
                testListThree.add(testListTwo.get(j));
                j++;
            }
        }
//        if (k < testListOne.size())
        System.out.println(testListThree);
    }
}
