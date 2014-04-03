package homework08;

/**
 * Objects of this class represent a set of sortable values.  The set
 * has the following performance characteristics:
 * <p/>
 * - the set is kept in a sorted binary tree
 * <p/>
 * - getting the size of the set - theta(1)
 * <p/>
 * - adding, removing, or searching for a random element - theta(log n)
 * <p/>
 * - adding, removing, or searching for an element that
 * immediately follows the previously accessed element - theta(log n)
 * <p/>
 * In other words, this set performs very well if additions or
 * removals occur with long sequential runs of ordered data values.
 * <p/>
 * Note:  This data structure is not threadsafe because instance
 * variables are used to keep track of visit state.  An iterator
 * would be a much better idea!
 *
 * @author Cody Cortello and Nick Houle
 * @version 3/31/14
 */
public class SpecialtySet<E extends Comparable<E>> {
    // Instance variables.  Students are allowed
    //   only these, do not add or change instance variables.

    private Node root;  // The root of the BST
    private int size;   // The number of data elements in the tree

    // Instance methods below.

    /**
     * Constructs an empty set.
     */
    public SpecialtySet() {
        this.root = null;
        this.size = 0;
    }


    /**
     * Returns the number of elements in this SpecialtySet.
     *
     * @return a count of the elements in this set
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns 'true' if the specified data is in the set,
     * false otherwise.
     *
     * @param data A data value to search for
     * @return true iff the data is in the set
     */
    /* Implementation note:  The postconditions for the
     * 'locatePosition' function are also guaranteed for
     * this function.
     */
    public boolean contains(E data) {
        if (this.root == null)
            return false;
        else
            return nodeContains(root, data);
    }

    /**
     * Adds the specified data to the set.  (If the data
     * is already in the set, the data is ignored.)
     *
     * @param data a data value to be added to the set
     */
    public void add(E data) {
        // adding to an empty tree
        if (this.root == null) {
            System.out.println("Adding " + data + ", size == " + this.size);
            this.root = new Node(data);
            this.size = 1;
            System.out.println("New tree root = " + root.data + ", size = " + this.size);
            return;
        }

        // if the data exists in the tree don't add it
        if (this.contains(data)) return;

        // use a recursive function to add the data to the set
        //  note: doing this.contains followed by this.add parses through the tree twice in succession,
        //  thereby doubling the cost, but maintains theta(log n)
        System.out.println("Adding " + data + ", size = " + this.size);
        if (data.compareTo(root.data) == -1)
            nodeAdd(this.root.left, data);
        else nodeAdd(this.root.right, data);
        this.size++;
    }

    /**
     * Guarantees that the specified data is not in the set.
     * (The data is removed if needed.)
     *
     * @param data a data value to be removed from the set
     */
    /* Implementation note:  If an element is actually removed, 
     *   'current' will refer to the node following the removed
     *   node after this function, and 'last' will refer
     *   to the previous node (as appropriate).
     */
    public void remove(E data) {
    }

    /**
     * A debugging function (not required) that
     * verifies the element count and element sortedness.
     * My test also printed out the contents of the set.
     *
     * Students may write debugging functions like this
     * one, but they may not write external tests or other
     * internal code that depends on the execution of any
     * internal test function.
     *
     * @return true iff the set passes an internal test
     */
    boolean validate() {
        if (this.root == null)
            return true;
        else if (height(this.root) == -1)
            return false;
        return true;
    }

    /**
     * Private helper to get the height of the tree
     */
    private int height(Node root) {
        if (root == null)
            return 0;

        int left_child = height(root.left);
        int right_child = height(root.right);

        if (left_child == -1 || right_child == -1)
            return -1;

        if (Math.abs(left_child - right_child) > 1) {
            return -1;
        }

        return Math.max(left_child, right_child) + 1;

    }

    /**
     * A toString override for outputting the contents of the tree
     */
    public String toString() {
        String outputString = "";
        if (this.root != null) {
            outputString += this.root.toString();
        }
        return outputString;
    }

    // An example of an inner class (a class within another object).

    /**
     * A private helper class for the SpecialtySet class.
     * Node objects are used to construct binary trees
     * in a SpecialtySet.
     * <p/>
     * Students are not allowed to change this class.
     *
     * @author Peter Jensen
     * @version 2/22/2014
     */
    private class Node {
        private E data;      // The data element - may be changed after it is assigned
        private Node parent;       // Parent - initialized to null
        private Node left, right;  // Children - initialized to null
        private int height;        // Height of this subtree - initialized to 0

        /**
         * Builds this node to contain the specified data.  By default, this
         * node does not point to any other nodes (next is null), although it
         * is expected that 'next' may change.
         *
         * @param data the data to store in the node
         */
        Node(E data) {
            this.data = data;
        }

        /**
         * @param data
         * @return
         */
        private boolean nodeContains(Node n, E data) {
            // handle null case and end of recursion
            if (n == null)
                return false;

            // check the current node first
            if (n.data.compareTo(data) == 0)
                return true;

                // use recursion to check the left and right nodes
            else if (data.compareTo(n.data) == -1) {
                if (n.left != null)
                    return nodeContains(n.left, data);
                else return false;
            } else {
                if (n.right != null)
                    return nodeContains(n.right, data);
                else return false;
            }

        }

        private void nodeAdd(Node n, E data) {
            // if the current node matches the data simply return
            if (n.data.compareTo(data) == 0)
                return;

            // otherwise use recursion to add to the left or right side as appropriate
            if (n.data.compareTo(data) == -1) {
                if (n.left != null)
                    nodeAdd(n.left, data);
                else {
                    Node newNode = new Node(data);
                    n.left = newNode;
                    updateHeight(newNode);
                }
            }
        }

        /**
         * Private helper to update the height of the affected
         * nodes in the binary tree
         */
        private void updateHeight(Node n) {
            if (n.parent.height == n.height) {
                n.parent.height++;
                updateHeight(n.parent);
            }
        }

        /**
         * A toString override which recursively creates an output string
         */
        public String toString() {
            String outputString = "";
            if (this == null)
                return outputString;
            else {
                outputString += this.left.toString();
                outputString += this.data.toString();
                outputString += this.right.toString();
                return outputString;
            }
        }
    }
}
