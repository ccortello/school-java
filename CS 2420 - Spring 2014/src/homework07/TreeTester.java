package homework07;

import javax.swing.tree.TreeNode;
import java.io.File;
import java.util.Scanner;

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

//        debug which prints the name of the file to the console
//        int returnVal = chooser.showOpenDialog(null);
//        if(returnVal == JFileChooser.APPROVE_OPTION) {
//            System.out.println("You chose to open this file: " +
//                    chooser.getSelectedFile().getName());
//        }

        try {
            Scanner treeScanner = new Scanner(treeFile);
            TreeNode treeRoot = new TreeNode(null);
            treeRoot.createTree(treeScanner);
        }
    }
}
catch(FileNotFoundException e){e.printStackTrace();}

        System.out.println("End of TreeTester constructor (Done!)");
    }
}
