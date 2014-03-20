package homework07;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A tree composed of Nodes
 * Created by Cody Cortello and Nick Houle on 3/17/14.
 */
public class Tree {
    private Node root;
    private Node currentNode;
    private int width, height;

    public Tree(File inputFile) {
        width = 0;
        height = 0;
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
            System.out.println("");
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
     * Returns the pixel width of the current tree in order to draw it using the Graphics class.
     * Note: this is done by passing the children of the root to a recursive function getNodeWidth and recursively
     * calculating the width of the entire tree.
     *
     * @param g the Graphics object used by the paint method
     * @return the pixel width of the tree as an integer
     */
    public int getTreeWidth(Graphics g) {
        return getNodeWidth(root, g);
    }

    private int getNodeWidth(Node inputNode, Graphics g) {
        for (Node n : inputNode.children) {
            System.out.println("gNW: current node = " + inputNode.data);
            int childrenWidth = getNodeWidth(n, g);
            System.out.println("childrenWidth = " + childrenWidth);
            int currentNodeWidth = g.getFontMetrics().stringWidth(inputNode.data) + 10;
            System.out.println("currentNodeWidth = " + currentNodeWidth);
            if (currentNodeWidth > childrenWidth)
                return currentNodeWidth;
            return childrenWidth;
        }
        if (inputNode.children.size() == 0) {
            int nodeWidth = g.getFontMetrics().stringWidth(inputNode.data) + 10;
            System.out.println("gNW:if: current node = " + inputNode.data + ", length = " + nodeWidth);
            return nodeWidth;
        }
        return 0;
    }

    public int getTreeHeight(Graphics g) {
        return 10000;
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
