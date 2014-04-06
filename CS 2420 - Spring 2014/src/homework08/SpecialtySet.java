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
            this.root = new Node(data);
            this.size = 1;
            return;
        }

        // if the data exists in the tree don't add it
        if (this.contains(data)) return;

        // use a recursive function to add the data to the set
        //  note: doing this.contains followed by this.add parses through the tree twice in succession,
        //  thereby doubling the cost, but maintains theta(log n)
//        System.out.println("Adding " + data + ", size = " + this.size);
        if (data.compareTo(this.root.data) == -1) {
//            System.out.println("Adding " + data + " to root.left");
            root.nodeAdd(this.root, data);
        } else {
//            System.out.println("Adding " + data + " to root.right");
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
        // explicit removal of last node
        if (root.data.compareTo(data) == 0 && size == 1) {
            this.root = null;
            size--;
        }

        // if the data is in the tree remove it
        if (this.contains(data)) {
            root.nodeRemove(0, root, data);
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

    /**
     * A toString override for outputting the contents of the tree
     */
    public String toString() {
        String outputString = "";
        if (this.root != null)
            outputString += this.root.toString();
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
         * A recursive function which returns true iff the input node either matches the input data or has a node
         * directly under it which matches the data
         * @param data the data to be found
         * @return boolean true if the node either matches the data or a subnode under it matches the data
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
            // check null case
            if (n == null) return;

            // if the current node matches the data simply return
            if (n.data.compareTo(data) == 0) return;

            // otherwise use recursion to add to the left or right side as appropriate
            if (data.compareTo(n.data) == -1) {
//                System.out.println("nodeAdd: added data " + data + " is less than data " + n.data);
                if (n.left != null) nodeAdd(n.left, data);
                else { // if the left branch is null add a new node in that position and update
                    //  the height and parent variables
                    Node newNode = new Node(data);
                    n.left = newNode;
                    newNode.parent = n;
                    updateHeight(n);
                    balance(newNode);
                }
            } else { // if the value is greater test the right side of the node
//                System.out.println("nodeAdd: added data " + data + " is greater than data " + n.data);
                if (n.right != null) nodeAdd(n.right, data);
                else { // if the right branch is null add a new node in that position and update
                    //  the height and parent variables
                    Node newNode = new Node(data);
                    n.right = newNode;
                    newNode.parent = n;
                    updateHeight(n);
                    balance(newNode);
                }
            }
        }

        /**
         * Checks the height of the children of the passed node to see if balancing is necessary and
         * if balancing _is_ necessary uses one of four rotate methods to correctly balance the node
         *
         * @param n the node at the root of the balancing
         */
        private void balance(Node n) {
            // check to see if adding the node unbalanced the tree anywhere along its height and balances if necessary
            if (n == null || n.parent == null || n.parent.parent == null) return;

            Node grandparent = n.parent.parent;

            // check the height conditions
            int leftHeight = grandparent.left == null ? -1 : grandparent.left.height;
            int rightHeight = grandparent.right == null ? -1 : grandparent.right.height;
//            System.out.println("leftHeight="+leftHeight+", rightHeight="+rightHeight);

            // if the heights show imbalance then balance the node
            if (Math.abs(leftHeight - rightHeight) == 2) {

//                System.out.print("balance: n=" + n.data + ", ");
//                if(n.parent!=null) System.out.print("parent="+n.parent.data+", ");
//                if (n.parent!=null&&n.parent.parent!=null) System.out.println("grandparent="+n.parent.parent.data);

                // check the location of the nodes under grandparent and balance the tree accordingly
                if (n.data.compareTo(grandparent.data) == -1) {
                    if (n.data.compareTo(n.parent.data) == -1)
                        rotateLeft(n);
                    else rotateLeftRight(n);
                } else {
                    if (n.data.compareTo(n.parent.data) == 1) rotateRight(n);
                    else rotateRightLeft(n);
                }
            } else {
                // move up in the tree
                balance(n.parent);
            }
        }


        /* The next four methods alter the locations of the nodes connected to n to assure the AVL tree condition */

        private void rotateLeft(Node n) {
            // store references to the parent and grandparent nodes
            Node parent = n.parent;
            Node grandparent = n.parent.parent;

            // swap the top two data fields (to maintain a correct root node upon position swapping)
            E temp = grandparent.data;
            grandparent.data = parent.data;
            parent.data = temp;

            // rotate into correct positions
            grandparent.left = n;
            parent.left = parent.right;
            parent.right = grandparent.right;
            grandparent.right = parent;

            // update the parent fields
            parent.parent = grandparent;
            n.parent = grandparent;

            balanceHeightUpdate(n);

            // update the height of the parent node
            int parentRight = parent.right == null ? -1 : parent.right.height;
            int parentLeft = parent.left == null ? -1 : parent.left.height;

            // update the height of the grandparent node
            int grandLeft = grandparent.left == null ? -1 : grandparent.left.height;
            int grandRight = grandparent.right == null ? -1 : grandparent.right.height;
            grandparent.height = Math.max(grandLeft, grandRight);
        }

        private void rotateRight(Node n) {
//            if (n==null || n.parent==null||n.parent.parent==null) return;
//            System.out.print("rotateRight, n="+n+", ");
//            System.out.print("parent=" + (n.parent == null ? null : n.parent.data) + ", ");
//            System.out.println("grandparent=" + (n.parent==null||n.parent.parent == null || n.parent.data == null ? null : n.parent.parent.data));
            // store references to the parent and grandparent nodes
            Node parent = n.parent;
            Node grandparent = n.parent.parent;

            // swap the top two data fields (to maintain a correct root node upon position swapping)
            E temp = grandparent.data;
            grandparent.data = parent.data;
            parent.data = temp;

            // rotate into correct positions
            grandparent.right = n;
            parent.right = parent.left;
            parent.left = grandparent.left;
            grandparent.left = parent;

            // update the parent fields
            parent.parent = grandparent;
            n.parent = grandparent;

            balanceHeightUpdate(n);

            // update the height of the parent node
            int parentRight = parent.right == null ? -1 : parent.right.height;
            int parentLeft = parent.left == null ? -1 : parent.left.height;
            parent.height = Math.max(parentRight, parentLeft) + 1;
//            System.out.println("parent "+parent.data+" height="+parent.height+", rightHeight="+parentRight+", leftHeight="+parentLeft);

            // update the height of the grandparent node
            int grandLeft = grandparent.left == null ? -1 : grandparent.left.height;
            int grandRight = grandparent.right == null ? -1 : grandparent.right.height;
            grandparent.height = Math.max(grandLeft, grandRight) + 1;
//            System.out.println("grandparent "+grandparent.data+" height="+grandparent.height+", rightHeight="+grandRight+" leftHeight="+grandLeft);


//            System.out.print("rotateRight completed, n=");
//            System.out.println(n);
        }

        private void rotateLeftRight(Node n) {
//            System.out.println("rotateLeftRight");
            // store references to the parent and grandparent nodes
            Node parent = n.parent;
            Node grandparent = n.parent.parent;

            // swap n and grandparent data (to maintain a correct root node upon position swapping)
            E temp = grandparent.data;
            grandparent.data = n.data;
            n.data = temp;

            // rotate into correct positions
            parent.right = n.left;
            n.left = n.right;
            n.right = grandparent.right;
            grandparent.right = n;

            // update the parent fields
            parent.parent = grandparent;
            n.parent = grandparent;

            balanceHeightUpdate(n);

            // update the height of the parent node
            int parentRight = parent.right == null ? -1 : parent.right.height;
            int parentLeft = parent.left == null ? -1 : parent.left.height;
            parent.height = Math.max(parentRight, parentLeft) + 1;

            // update the height of the n node
            int nLeft = n.left == null ? -1 : n.left.height;
            int nRight = n.right == null ? -1 : n.right.height;
            n.height = Math.max(nLeft, nRight) + 1;

            // update the height of the grandparent node
            int grandLeft = grandparent.left == null ? -1 : grandparent.left.height;
            int grandRight = grandparent.right == null ? -1 : grandparent.right.height;
            grandparent.height = Math.max(grandLeft, grandRight) + 1;
        }

        private void rotateRightLeft(Node n) {
            // store references to the parent and grandparent nodes
            Node parent = n.parent;
            Node grandparent = n.parent.parent;

            // swap n and grandparent data (to maintain a correct root node upon position swapping)
            E temp = grandparent.data;
            grandparent.data = n.data;
            n.data = temp;

            // rotate into correct positions
            parent.left = n.right;
            n.right = n.left;
            n.left = grandparent.left;
            grandparent.left = n;

            // update the parent fields
            parent.parent = grandparent;
            n.parent = grandparent;

            balanceHeightUpdate(n);

            // update the height of the n node
            int nLeft = n.left == null ? -1 : n.left.height;
            int nRight = n.right == null ? -1 : n.right.height;
            n.height = Math.max(nLeft, nRight) + 1;

            // update the height of the parent node
            int parentRight = parent.right == null ? -1 : parent.right.height;
            int parentLeft = parent.left == null ? -1 : parent.left.height;
            parent.height = Math.max(parentRight, parentLeft) + 1;

            // update the height of the grandparent node
            int grandLeft = grandparent.left == null ? -1 : grandparent.left.height;
            int grandRight = grandparent.right == null ? -1 : grandparent.right.height;
            grandparent.height = Math.max(grandLeft, grandRight) + 1;
        }

        /**
         * Recursively updates the heights of the nodes in a tree after a balance has ocurred
         *
         * @param n the node whose height is checked
         */
        private void balanceHeightUpdate(Node n) {
            if (n.parent != null && n.parent.height - n.height == 2) {
                n.parent.height--;
                balanceHeightUpdate(n.parent);
            }
        }

        /**
         * Private helper to update the height of the affected nodes in the binary tree
         */
        private void updateHeight(Node n) {
            if (n == null) return; // handle null case (base case of recursion)
            int leftHeight = n.left == null ? -1 : n.left.height;
            int rightHeight = n.right == null ? -1 : n.right.height;
            int nodeHeight = Math.max(leftHeight, rightHeight) + 1; // find what the node's height _should_ be
            if (n.height == nodeHeight) return; // if the node's height is correct end the recursion
            n.height = nodeHeight;
            updateHeight(n.parent);
        }

        /**
         * A recursive method to find the longest path from the passed node to the bottom of the tree
         * @param n the node whose height is needed
         * @return
         */
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
                String thisParent = this.parent == null ? null : this.parent.data.toString();
                String thisLeft = this.left == null ? null : this.left.data.toString();
                String thisRight = this.right == null ? null : this.right.data.toString();

                if (this.left != null) outputString += this.left.toString();
                outputString += this.data.toString() + "\t" + this.height + "\t" + thisParent + "\t" + thisLeft + "\t" + thisRight+"\n";
                if (this.right != null) outputString += this.right.toString();
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
            // if the node isn't in the tree then simply return (null case)
            if (n == null) return;

            // check the data against the data in the current node
            int compare = data.compareTo(n.data);

            // if the data is in the left branch remove it from the left branch
            if (compare == -1) n.nodeRemove(-1, n.left, data);

            // if the data is in the right branch remove it from the right branch
            else if (compare == 1) n.nodeRemove(1, n.right, data);

            else { // if the data matches the data at the current node (Node n) then n is to be removed
                // establish references for the nodes connected to the node to be removed
                Node removeParent = n.parent;
                Node removeLeft = n.left;
                Node removeRight = n.right;

                // if the node has no children then simply delete it and update the parent's height
                if (removeLeft == null && removeRight == null) {
                    if (direction == -1) removeParent.left = null;
                    else if (direction == 1) removeParent.right = null;
                    // note: root case with no children is handled at beginning of SpecialtySet remove()
                    updateHeight(removeParent);
                }

                // if the node only has one child then delete that child and update the parent accordingly

                // only has right children
                else if (removeLeft == null) {
                    if (direction == -1) removeParent.left = removeRight;
                    else if (direction == 1) removeParent.right = removeRight;
                    else {
                        // find the node with the smallest data which is greater than the node being deleted, referred to
                        //  here as the 'leftmostRight' node as per its location in a tree diagram
                        Node leftmostRight = n.right;
                        while (leftmostRight.left != null) leftmostRight = leftmostRight.left;

                        // copy the data from the leftmostRight node to the node to be deleted and remove the reference to
                        //  the leftmost right node. This serves the functionality of replacing the current node with the
                        //  leftmost right node. Afterwards, update the removed node's parent, as per all removals
                        n.data = leftmostRight.data; // copy the data into the node to be deleted
                        Node leftmostRightParent = leftmostRight.parent; // store a reference to the leftmostRight's parent,
                        //  so we can reference it after the deletion
                        if (n.right == leftmostRight) leftmostRightParent.right = leftmostRightParent.right.right;
                        else leftmostRightParent.left = leftmostRightParent.left.right;
                        updateHeight(leftmostRightParent);
                    }
                    updateHeight(removeRight);

                    // only has left children
                } else if (removeRight == null) {
                    if (direction == -1) removeParent.left = removeLeft;
                    else if (direction == 1) removeParent.right = removeLeft;
                    else {
                        // find the node with the greatest data which is smaller than the node being deleted, referred to
                        //  here as the 'rightmostLeft' node as per its location in a tree diagram
                        Node rightmostLeft = n.left;
                        while (rightmostLeft.right != null) rightmostLeft = rightmostLeft.right;

                        // copy the data from the leftmostRight node to the node to be deleted and remove the reference to
                        //  the leftmost right node. This serves the functionality of replacing the current node with the
                        //  leftmost right node. Afterwards, update the removed node's parent, as per all removals
                        n.data = rightmostLeft.data; // copy the data into the node to be deleted
                        Node leftmostRightParent = rightmostLeft.parent; // store a reference to the leftmostRight's parent,
                        //  so we can reference it after the deletion
                        if (n.left == rightmostLeft) leftmostRightParent.left = leftmostRightParent.left.left;
                        else leftmostRightParent.right = leftmostRightParent.right.left;
                        updateHeight(leftmostRightParent);
                    }
                    updateHeight(removeLeft);

                    // if the node has two children
                } else {

                    // find the node with the smallest data which is greater than the node being deleted, referred to
                    //  here as the 'leftmostRight' node as per its location in a tree diagram
                    Node leftmostRight = n.right;
                    while (leftmostRight.left != null) leftmostRight = leftmostRight.left;

                    // copy the data from the leftmostRight node to the node to be deleted and remove the reference to
                    //  the leftmost right node. This serves the functionality of replacing the current node with the
                    //  leftmost right node. Afterwards, update the removed node's parent, as per all removals
                    n.data = leftmostRight.data; // copy the data into the node to be deleted
                    Node leftmostRightParent = leftmostRight.parent; // store a reference to the leftmostRight's parent,
                    //  so we can reference it after the deletion
                    if (n.right == leftmostRight) leftmostRightParent.right = leftmostRightParent.right.right;
                    else leftmostRightParent.left = leftmostRightParent.left.right;
                    updateHeight(leftmostRightParent);
                }
            }
        }
    }
}
