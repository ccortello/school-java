package assignment6;

import java.util.ArrayList;

/**
 * Created by Cody on 6/25/2014.
 */
public class MyBSTtester {
    public static void main(String[] args) {
        new MyBSTtester();
    }

    MyBSTtester() {
        BinarySearchTree<String> testBST = new BinarySearchTree<String>("hello");
        ArrayList<String> testList = new ArrayList<String>();
        testList.add(null);
        testBST.addAll(testList);
    }
}
