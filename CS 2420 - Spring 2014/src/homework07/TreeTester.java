package homework07;

import java.io.File;

/**
 * Created by Cody on 3/11/14.
 */
public class TreeTester {
    public static void main(String[] args) {
        new TreeTester();
    }

    public TreeTester() {

//      debug which chooses the correct file automatically
        File treeFile = new File("C:/Users/Cody/workspace/CS 2420 - Spring 2014/src/homework07/testTree.tree");

        // create file choose GUI
//        JFileChooser chooser = new JFileChooser("C:/Users/Cody/workspace/CS 2420 - Spring 2014/bin/homework07");
//        FileNameExtensionFilter gifFilter = new FileNameExtensionFilter(
//                "Tree Lists", "tree");
//        chooser.setFileFilter(gifFilter);
//        File treeFile = chooser.getSelectedFile();
//
////        debug which prints the name of the file to the console
//        int returnVal = chooser.showOpenDialog(null);
//        if(returnVal == JFileChooser.APPROVE_OPTION) {
//            System.out.println("You chose to open this file: " +
//                    chooser.getSelectedFile().getName());
//        }

        Tree testTree = new Tree(treeFile);
        TreePanel treePanel = new TreePanel(testTree);
    }
}
