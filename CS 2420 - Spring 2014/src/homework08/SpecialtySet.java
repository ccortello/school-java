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
        else return root.nodeContains(root, data);
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
        if (this.contains(data)) {
            System.out.println("Data " + data + " already exists in tree");
            return;
        }

        // use a recursive function to add the data to the set
        //  note: doing this.contains followed by this.add parses through the tree twice in succession,
        //  thereby doubling the cost, but maintains theta(log n)
        System.out.println("Adding " + data + ", size = " + this.size);
        if (data.compareTo(this.root.data) == -1) {
            System.out.println("Adding " + data + " to root.left");
            root.nodeAdd(this.root, data);
        } else {
            System.out.println("Adding " + data + " to root.right");
            root.nodeAdd(this.root, data);
        }
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
//        System.out.println("\nremove method called\n");
        // if the data is in the tree remove it
        if (!this.contains(data)) {
//            System.out.println("Data "+data+" not contained in tree");
            return;
        } else {
//            System.out.println("Data "+data+" contained in tree, removing...");
            root.nodeRemove(0, root, data); // TODO: should this need a direction passed, or no? Implement a join()?
            this.size--;
        }
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
        if (root == null)
            return true;

        // recursively check the left and right branches of the tree, returning false if either validation fails
        if (!root.nodeValidate(root.left)) return false;
        if (!root.nodeValidate(root.right)) return false;
        return true; // return true if both branches validate correctly
    }

//    /**
//     * Private helper to get the height of the tree
//     */
//    private int getHeight(Node root) {
//        if (root == null)
//            return 0;
//
//        int left_child = getHeight(root.left);
//        int right_child = getHeight(root.right);
//
//        if (left_child == -1 || right_child == -1) return -1;
//        if (Math.abs(left_child - right_child) > 1) return -1;
//
//        return Math.max(left_child, right_child) + 1;
//
//    }

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
            if (n == null) {
                System.out.println("nodeAdd, n = null");
                return;
            }
            if (n != null && n.data != null)
                System.out.println("nodeAdd called, n = " + n.data);
            else System.out.println("nodeAdd called, n = null");

            // check null case
                if (n == null) {
                    System.out.println("nodeAdd: n == null");
                    return;
                }

            // if the current node matches the data simply return
            if (n.data.compareTo(data) == 0) {
                System.out.println("nodeAdd: added data is greater than root");
                return;
            }

            // otherwise use recursion to add to the left or right side as appropriate
            if (data.compareTo(n.data) == -1) {
                System.out.println("nodeAdd: added data " + data + " is less than data " + n.data);
                if (n.left != null)
                    nodeAdd(n.left, data);
                else { // if the left branch is null add a new node in that position and update
                    //  the height and parent variables
                    Node newNode = new Node(data);
                    n.left = newNode;
                    newNode.parent = n;
                    updateHeight(newNode);
                }
            } else { // if the value is greater test the right side of the node
                System.out.println("nodeAdd: added data " + data + " is greater than data " + n.data);
                if (n.right != null)
                    nodeAdd(n.right, data);
                else { // if the right branch is null add a new node in that position and update
                    //  the height and parent variables
                    Node newNode = new Node(data);
                    n.right = newNode;
                    newNode.parent = n;
                    updateHeight(newNode);
                }
            }
        }

        /**
         * Private helper to update the height of the affected
         * nodes in the binary tree
         */
        private void updateHeight(Node n) {
            if (n == null) return; // handle null case (base case of recursion)
            int nodeHeight = maxHeight(n); // find what the node's height _should_ be
            if (n.height == nodeHeight) return; // if the node's height is correct end the recursion
            else {
                n.height = nodeHeight;
                updateHeight(n.parent);
            }
        }

        private int maxHeight(Node n) {
            if (n.left == null && n.right == null) return 0;

            int leftHeight = 0;
            int rightHeight = 0;

            if (n.left != null) leftHeight = maxHeight(n.left);
            if (n.right != null) rightHeight = maxHeight(n.right);

            return (Math.max(leftHeight, rightHeight) + 1);
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

        public boolean nodeValidate(Node n) {
            // handle null case and base case of recursion
            if (n == null) return true;

            // recursively check the left and right nodes.
            // first, compare the left and right nodes against the parent (the input node, n)
            if (n.left != null && n.left.data.compareTo(n.data) != -1) return false;
            if (n.right != null && n.right.data.compareTo(n.data) != 1) return false;

            // if the parent to children comparison works for this node then recursively do the same for
            // both of the children.
            if (nodeValidate(n.left) && nodeValidate(n.right)) return true;
            else return false;
        }

        public void nodeRemove(int direction, Node n, E data) {
            int compare = data.compareTo(n.data);
            System.out.println("nodeRemove: n.data = " + n.data + ", data=" + data + ", compare=" + compare);
            // if the data is in the left branch remove it from the left branch
            if (compare == -1) {
                n.nodeRemove(-1, n.left, data);
                return;
            }
            // if the data is in the right branch remove it from the right branch
            else if (compare == 1) {
                n.nodeRemove(1, n.right, data);
                return;
            } else { // if the data matches the data at the current node (Node n) then n is to be removed
                // establish references for the nodes connected to the node to be removed
                Node removeParent = n.parent;
                Node removeLeft = n.left;
                Node removeRight = n.right;
                System.out.println("nodeRemove: node found, n=" + n.data + ", parent=" + removeParent.data + ", left=" + removeLeft.data + ", right=" + removeRight);

                // if the node has no children then simply delete it and update the parent's height
                if (removeLeft == null && removeRight == null) {
                    System.out.println("Removing a node with no children!");
                    if (direction == -1) removeParent.left = null;
                    else if (direction == 1) removeParent.right = null;
                    else {
                    } // TODO: implement root case with no children
                    updateHeight(removeParent);
                }

                // if the node only has one child then delete that child and update the parent accordingly
                else if (removeLeft == null && removeRight != null) { // only has right children
                    System.out.println("Removing a node with only right children");
                    if (direction == -1) removeParent.left = removeRight;
                    else if (direction == 1) removeParent.right = removeRight;
                    // TODO: implement root case remove with one child
                    updateHeight(n);
                } else if (removeRight == null && removeLeft != null) {
                    n = removeLeft; //TODO: change this to null the node instead of the reference to it
                    // TODO: implement root case remove with one child
                    updateHeight(n);
                } else { // if the node has two children
                    // TODO: implement root case remove with two children. All below needs revision
                    Node leftmostRight = n.right;
                    while (leftmostRight.left != null) leftmostRight = leftmostRight.left;
                    n.data = leftmostRight.data;
                    Node leftmostRightParent = leftmostRight.parent;
                    leftmostRightParent.left = null;
                    updateHeight(leftmostRightParent);
                }
            }
        }
    }
}
