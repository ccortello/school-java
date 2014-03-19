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
    private Node currentNode;
    public int currentDepth;
    private Map<Integer, Integer> maximumWidths;

    public Tree(File inputFile) {
        currentDepth = 0;
        try {
            // create scanner in the passed file
            Scanner s = new Scanner(inputFile);

            // substantiate the root of the tree
            s.next();
            String rootString = s.next();
            root = new Node(null, rootString.substring(0, rootString.length() - 1));
            currentNode = root;

//            System.out.println("root data = "+root.data);

            // scans the input file and substantiates the tree
            while (s.hasNext()) {
                String currentString = s.next();
//                System.out.println(currentString + " | ");
                if (currentString.startsWith("<") && !currentString.substring(1, 2).equals("/")) { // if the string is an opening tag
//                    System.out.println("if: "+currentString);
                    String newNodeString = s.next();
                    Node addNode = new Node(currentNode, newNodeString.substring(0, newNodeString.length() - 1));
//                    System.out.println("New Node data = "+addNode.data);
                    currentNode.children.add(addNode);
                    currentNode = addNode;
                } else // otherwise the string is a closing tag - change the current node to its parent
                    currentNode = currentNode.parent;
            }
            printTree(root);
            System.out.println("After StringTree while loop");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found!");
        }
    }

    private void printTree(Node inputNode) {
        for (Node n : inputNode.children)
            printTree(n);
        System.out.println(inputNode.data);
    }

    /**
     * Represents a single node in
     */
    public class Node {
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
