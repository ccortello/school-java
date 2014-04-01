package homework07;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by Cody Cortello and Nick Houle on 3/11/14.
 */
public class TreeTester {
    public static void main(String[] args) {
        new TreeTester();
    }

    public TreeTester() {

//      choose the correct file automatically
//        File treeFile = new File("C:/Users/Cody/workspace/CS 2420 - Spring 2014/src/homework07/testTree.tree");

        // create file choose GUI
        JFileChooser chooser = new JFileChooser("C:/Users/Cody/workspace/CS 2420 - Spring 2014/bin/homework07");
        FileNameExtensionFilter gifFilter = new FileNameExtensionFilter(
                "Tree Lists", "tree");
        chooser.setFileFilter(gifFilter);

        // if a file is selected create a tree and panel from the file
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File treeFile = chooser.getSelectedFile();
            Tree testTree = new Tree(treeFile);
            TreePanel treePanel = new TreePanel(testTree);
        }

    }
}
