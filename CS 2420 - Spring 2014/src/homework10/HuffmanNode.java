package homework10;

import java.util.ArrayList;

/**
 * Objects of this class represent one node in a
 * Huffman tree.  A Huffman tree is a binary two-tree, so
 * each node has a left and right child (or no children).  In addition,
 * a HuffmanNode object contains a totalFrequency count
 * that is the sum of the frequencies of the left and right
 * sub-node (if any).  Lastly, a HuffmanNode object keeps a list
 * of all the tokens stored in its subtree.
 *
 * @author Peter Jensen - CS 2420
 * @version Spring 2014
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    // The sum of all the frequencies in all the leaf nodes at or below
    //   this node.
    private int totalFrequency;

    // A list of all the tokens contained at or below this node.  This list is kept in sorted order.
    private ArrayList<HuffmanToken> tokens;

    // The left and right children of this node.
    private HuffmanNode left, right;

    /**
     * This constructor creates a leaf node in the Huffman tree.
     * A leaf node in a Huffman tree is mapped to a single token.
     * Use this token to initialize the frequency in this node.
     *
     * @param token The token to store in this leaf node
     */
    public HuffmanNode(HuffmanToken token) {
        this.totalFrequency = token.getFrequency();
        this.tokens = new ArrayList<HuffmanToken>();
    }

    /**
     * This constructor creates an interior node in the Huffman tree.
     * It takes two non-null nodes as parameters, and it initializes
     * its fields by combining the frequency and tokens of the child nodes.
     * Finally, the internal list of tokens is sorted.
     * <p/>
     * This constructor has a major side effect.  To simplify building
     * of Huffman codes, this constructor will prepend Huffman code
     * bits to all of the tokens' codes stored below this point in the
     * Huffman tree.  All of the tokens in this node's left child subtree should
     * have a zero (false) bit prepended to their code, and all of the
     * tokens in this node's right child subtree should have a one (true) bit
     * prepended to their code.  Fortunately, each node contains a list
     * of the tokens in its subtree, so this step can be done without a
     * traversal.
     * <p/>
     * The advantage of this side effect is that when a Huffman tree has
     * been completely built, the Huffman codes for each token will also have
     * been built.
     *
     * @param left  The subtree to become the left child of this node
     * @param right The subtree to become the right child of this node
     */
    public HuffmanNode(HuffmanNode left, HuffmanNode right) {
        // sum the frequencies of the left and right nodes
        this.totalFrequency = left.totalFrequency + right.totalFrequency;

        // update the codes for the left and right nodes' tokens
        for (HuffmanToken leftToken : left.tokens)
            leftToken.prependBitToCode(false);
        for (HuffmanToken rightToken : right.tokens)
            rightToken.prependBitToCode(true);

        // add the tokens of the left and right nodes in sorted order
        // TODO: should this be in compareTo sorted order, or simply 'left then right' order as below?
        this.tokens.addAll(left.tokens);
        this.tokens.addAll(right.tokens);
    }

    /**
     * Returns the left subtree of this node, or null if this
     * is a leaf node.
     *
     * @return The left subtree of this node
     */
    public HuffmanNode getLeftSubtree() {
        if (this.left == null) return null;
        else return this.left;
    }

    /**
     * Returns the right subtree of this node, or null if this
     * is a leaf node.
     *
     * @return The right subtree of this node
     */
    public HuffmanNode getRightSubtree() {
        if (this.right == null) return null;
        else return this.right;
    }

    /**
     * Returns true if this node is a leaf node, false otherwise.
     *
     * @return True if this node is a leaf node
     */
    public boolean isLeafNode() {
        return (this.right == null && this.left == null);
    }

    /**
     * If this is a leaf node, this method returns the token
     * that was used to create this leaf node.  Null is returned
     * if this is not a leaf node.
     *
     * @return The token stored in this leaf node
     */
    public HuffmanToken getToken() {
        if (this.isLeafNode()) return this.tokens.get(0);
        else return null;
    }

    /**
     * This method assumes that a HuffmanNode is stored in Object o.
     * (Note that a typecast is required in the method below.)
     * True is returned if this node is identical to the node in o,
     * and false otherwise.  (Hint -- just use your compareTo function
     * below.)
     *
     * @param o An object that should be a HuffmanNode
     * @return True if the this node equals the given node
     */
    public boolean equals(Object o) {
        return (((HuffmanNode) o).compareTo(this) == 0);
    }

    /**
     * This method determines which of two nodes is less than the other.
     * This is used to determine priority of nodes when they are
     * inserted into a priority queue.
     * <p/>
     * Huffman nodes are compared by their frequency first, and in the
     * case of equal frequencies, by the byte value of the first token in
     * each node's list second.  If their frequencies and tokens are equal,
     * the HuffmanNode objects are assumed equal.  (An 'equal' result
     * should NEVER happen unless a node is compared to itself.)
     * <p/>
     * This method returns:
     * -1 if 'this' HuffmanNode object is less than 'node'
     * 0 if 'this' HuffmanNode object is equal to 'node'
     * 1 if 'this' HuffmanNode object is greater than to 'node'
     *
     * @param node An object that should be a HuffmanNode
     * @return -1, 0, or 1, depending on the relative order of the nodes
     */
    public int compareTo(HuffmanNode node) {

        // first, compare frequencies
        if (this.totalFrequency < node.totalFrequency) return -1;
        else if (this.totalFrequency > node.totalFrequency) return 1;
        else {
            // second, compare the byte value of the tokens
            if (this.tokens.get(0).getValue() < node.tokens.get(0).getValue()) return -1;
            else if (this.tokens.get(0).getValue() > node.tokens.get(0).getValue()) return 1;

                // this should only be reached if the frequencies and the tokens are the same
            else return 0;
        }
    }
}
