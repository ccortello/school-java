package homework07;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * A tree composed of Nodes
 * Created by Cody on 3/17/14.
 */
public class Tree {
    private Node root;
    private Node currentNode;
    private Map<Integer, Integer> maximumWidths;

    public Tree(File inputFile) {
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
                // store the current string in the scanner for analysis
                String currentString = s.next();

                // if the string is an opening tag then create a new Node
                if (currentString.startsWith("<") && !currentString.substring(1, 2).equals("/")) {
                    String newNodeString = s.next();
                    Node addNode = new Node(currentNode, newNodeString.substring(0, newNodeString.length() - 1));

                    // update the current node's children and the current node variable to correctly place the new node
                    //  in the tree
                    currentNode.children.add(addNode);
                    currentNode = addNode;

                }

                // otherwise the tag is a closing tag, so simply go up a level in the tree
                else
                    currentNode = currentNode.parent;
            }
//          debug
            printTree(root);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found!");
        }
    }

    /**
     * A recursive DFS debugging function which prints a representation of the tree with the input node treated as root
     *
     * @param inputNode the top node of the tree to be printed
     */
    private void printTree(Node inputNode) {
        for (Node n : inputNode.children)
            printTree(n);
        System.out.print(inputNode.data+" ");
    }

    /**
     * Represents a single node in the tree, being a simple container class for three data fields
     */
    public class Node {

        // a Node is a con
        public String data;
        public Node parent;
        public ArrayList<Node> children;

        // to construct a Node simply copy the passed data into the correct fields
        public Node(Node parent, String data) {
            this.parent = parent;
            this.data = data;
            children = new ArrayList<Node>();
        }
    }
}
