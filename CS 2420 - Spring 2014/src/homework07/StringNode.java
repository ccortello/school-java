package homework07;

import java.util.TreeSet;

/**
 * Represents one Node of a Tree with one root node and sorted children
 * Created by Cody on 3/17/14.
 */
public class StringNode {
    public String data;
    private StringNode root;
    private StringNode parent;
    private TreeSet<StringNode> children;

    public StringNode() {
        children = new TreeSet<StringNode>();
    }

    /**
     * A simple override of the compareTo method which compares the Strings in the 'data' field of two StringNodes
     *
     * @param compareNode a StringNode to compare 'this' against
     * @return -1 if this < compareNode, 0 if this == compareNode, 1 if this > compareNode
     * Note: return paramaters are the same as the Comparable compareTo method
     */
    public int compareTo(StringNode compareNode) {
        return this.data.compareTo(compareNode.data);
    }
}
