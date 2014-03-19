package homework07;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

/**
 * A tree composed of TreeNodes
 * Created by Cody on 3/17/14.
 */
public class StringTree {
    private StringNode root;
    private Map<Integer, Integer> widths;

    public StringTree(File inputFile) {
        try {
            Scanner s = new Scanner(inputFile);
            while (s.hasNext())
                System.out.println(s.next());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found!");
        }

    }
}
