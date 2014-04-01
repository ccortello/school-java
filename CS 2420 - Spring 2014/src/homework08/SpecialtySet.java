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
        return root.nodeContains(data);
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
            this.root = new Node(data);
            this.size = 1;
            return;
        }

        // if the data exists in the tree don't add it
        if (this.contains(data))
            return;

        // use a recursive function to add the data to the set
        //  note: doing this.contains followed by this.add parses through the tree twice in succession,
        //  thereby doubling the cost, but maintains theta(log n)
        this.add(data);
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
     * <p/>
     * Students may write debugging functions like this
     * one, but they may not write external tests or other
     * internal code that depends on the execution of any
     * internal test function.
     *
     * @return true iff the set passes an internal test
     */
    boolean validate() {
        return false;  // Stub
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
        private boolean nodeContains(E data) {
            // handle null case and end of recursion
            if (this == null)
                return false;

            // check the current node first
            if (this.data.compareTo(data) == 0)
                return true;

                // use recursion to check the left and right nodes
            else if (data.compareTo(this.data) == -1)
                return this.left.nodeContains(data);
            else
                return this.right.nodeContains(data);
        }

        private void nodeAdd(E data) {
            // if the current node matches the data simply return
            if (this.data.compareTo(data) == 0)
                return;

            // otherwise use recursion to add to the left or right side as appropriate
            if (this.data.compareTo(data) == -1) {
                if (this.left != null)
                    this.left.nodeAdd(data);
                else {
                    Node newNode = new Node(data);
                    this.left = newNode;
                    updateHeight(newNode);
                }
            }
        }
    }
}
