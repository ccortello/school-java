package homework07;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by Cody on 3/11/14.
 */
public class TreeTester {
    public static void main(String[] args) {
        new TreeTester();
    }

    public TreeTester() {
        JFileChooser chooser = new JFileChooser("C:/Users/Cody/workspace/CS 2420 - Spring 2014/bin/homework07");
        FileNameExtensionFilter gifFilter = new FileNameExtensionFilter(
                "Tree Lists", "tree");
        chooser.setFileFilter(gifFilter);

//        debug which prints the name of the file to the console
//        int returnVal = chooser.showOpenDialog(null);
//        if(returnVal == JFileChooser.APPROVE_OPTION) {
//            System.out.println("You chose to open this file: " +
//                    chooser.getSelectedFile().getName());
//        }
        System.out.println("End of TreeTester constructor (Done!)");
    }
}
