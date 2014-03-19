package homework07;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * A tree composed of TreeNodes
 * Created by Cody on 3/17/14.
 */
public class Tree {
    private Node root;
    private Map<Integer, Integer> widths;

    public Tree(File inputFile) {
        try {
            // create scanner in the passed file
            Scanner s = new Scanner(inputFile);

            // substantiate the root of the tree
            s.next();
            root = new Node(null, s.next().substring(0, s.next().length() - 1));
//            System.out.println(root.data);

            // use recursion to populate the children of the root node
            while (s.hasNext()) {
                String currentString = s.next();
                System.out.println(currentString);
                if (currentString.startsWith("</")) {

                }
            }
            System.out.println("After StringTree while loop");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found!");
        }

    }

    /**
     * Represents a single node in
     */
    public class Node {
        // TODO: determine public or private nature of fields
        public String data;
        public Node parent;
        public ArrayList<Node> children;

        public Node(Node parent, String data) {
            this.parent = parent;
            this.data = data;
            children = new ArrayList<Node>();
        }
    }
}
