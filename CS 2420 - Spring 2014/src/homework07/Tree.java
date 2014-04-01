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
    private int width, currentHeight, currentDepth, treeDepth;

    public Tree(File inputFile) {
        currentDepth = 1;
        width = 0;
        currentHeight = 0;
        try {
            // create scanner in the passed file
            Scanner s = new Scanner(inputFile);

            // substantiate the root of the tree
            s.next();
            String rootString = s.next();
            root = new Node(null, rootString.substring(0, rootString.length() - 1));
            currentNode = root;

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
        root.width = g.getFontMetrics().stringWidth(root.data);
        return getNodeWidth(root, g);
    }

    private int getNodeWidth(Node inputNode, Graphics g) {
        for (Node n : inputNode.children) {
            n.width = g.getFontMetrics().stringWidth(n.data) + 10;
//            System.out.println("node "+n.data+", width = "+n.width);
            getNodeWidth(n, g);
        }
        if (inputNode.children.size() == 0)
            this.width += g.getFontMetrics().stringWidth(inputNode.data) + 10;
        return this.width;
    }

    public int getTreeHeight(Graphics g) {
        root.height = g.getFontMetrics().getHeight();
        root.depth = 1;
        return getNodeHeight(root, g);
    }

    public int getNodeHeight(Node inputNode, Graphics g) {
        if (inputNode != null)
            inputNode.height = g.getFontMetrics().getHeight();
//        System.out.println("Node: "+inputNode.data+", height = "+inputNode.height);
        for (Node n : inputNode.children) {
            currentDepth++;
            inputNode.depth = currentDepth;
            if (currentDepth > treeDepth)
                treeDepth = currentDepth;
            getNodeHeight(n, g);
        }
        currentDepth--;

        return treeDepth * (g.getFontMetrics().getHeight() + 5);
    }

    public void drawTree(Graphics g, int treeWidth, int treeHeight) {
        drawNode(root, g, treeWidth, treeHeight);
    }

    private void drawNode(Node inputNode, Graphics g, int treeWidth, int treeHeight) {
        int x = 0;
//        System.out.println("node "+inputNode.data+": x="+x+", y="+y);
        for (Node n : inputNode.children) {
            drawNode(n, g, treeWidth, treeHeight);
            currentHeight += inputNode.height;
            g.drawRect(0, currentHeight, n.width, n.height);
            g.drawString(n.data, x + 5, currentHeight + inputNode.height - 2);
        }
//        g.drawRect(0, currentHeight, root.width, root.height);
//        g.drawString(root.data, x + 5, currentHeight+inputNode.height-2);
    }

    /**
     * Represents a single node in the tree, being a simple container class for three data fields
     */
    public class Node {

        // a Node is a con
        public String data;
        public Node parent;
        public ArrayList<Node> children;
        public int width, height, depth;

        // to construct a Node simply copy the passed data into the correct fields
        public Node(Node parent, String data) {
            this.parent = parent;
            this.data = data;
            children = new ArrayList<Node>();
        }
    }
}
