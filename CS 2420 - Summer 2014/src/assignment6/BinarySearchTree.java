package assignment6;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A BST class in which elements are Comparable (necessary for all BSTs) and without duplicates
 *
 * @author Cody Cortello
 * @author Casey Nordgran
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>, TreeTraversal<Type> {
    private BinaryNode root;
    private int size;

    // creates an empty BST
    public BinarySearchTree() {
        size = 0;
    }

    // create a BST with an initial Node
    public BinarySearchTree(Type element) {
        root = new BinaryNode(element);
        size = 1;
    }

    // create a BST from a Collection
    public BinarySearchTree(Collection<? extends Type> items) {
        size = 0;
        this.addAll(items);
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if the input item was actually
     * inserted); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    public boolean add(Type item) {
        // throw exception if passed parameter is null
        if (item == null)
            throw new NullPointerException("Tried adding null");

        // handle adding to null set - update root and increment size
        if (size == 0) {
            root = new BinaryNode(item);
            size++;
            return true;
        }

        // find the Node where the data should be added
        BinaryNode currentNode = root;
        while (!currentNode.isLeaf()) {
            // if the node is found don't change the BST and return false
            if (currentNode.data.equals(item))
                return false;
                // if the item should go to the left either change the current node to the left subtree or create a left node
            else if (item.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null)
                    currentNode = currentNode.getLeft();
                else break;
            } else {// if the item should go to the right either change the current node to the right subtree or create a right node
                if (currentNode.getRight() != null)
                    currentNode = currentNode.getRight();
                else break;
            }
        }
        // if the data is at the current node don't change anything and return false
        if (item.compareTo(currentNode.getData()) == 0)
            return false;
            // implement adding to left of currentNode
        else if (item.compareTo(currentNode.getData()) < 0) {
            currentNode.setLeft(new BinaryNode(item));
            size++;
            return true;
        }
        // implement adding to right of currentNode
        else {
            currentNode.setRight(new BinaryNode(item));
            size++;
            return true;
        }
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if any item in the input collection
     * was actually inserted); otherwise, returns false
     * @throws NullPointerException if any of the items is null
     */
    public boolean addAll(Collection<? extends Type> items) {
        // handle adding null
        if (items == null)
            throw new NullPointerException("Tried to add a null Collection with addAll");
        // store the current size in order to check for changes to the set when this method returns
        int size = this.size();
        // add each element
        for (Type element : items)
            add(element);
        // return a boolean iff the BST was changed (if the size was changed)
        return (this.size() != size);
    }

    /**
     * Removes all items from this set. The set will be empty after this method call.
     */
    public void clear() {
        // reset fields
        root = null;
        size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item; otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    public boolean contains(Type item) {
        // handle null parameter
        if (item == null)
            throw new NullPointerException("Tried contains with null item");
        // an empty set cannot contain any certain item - return false
        if (isEmpty())
            return false;

        // search the BST for a Node containing the passed data
        BinaryNode currentNode = root; // start from root
        while (!currentNode.isLeaf()) {
            Type data = currentNode.getData();
                /* the if and if else statements that follow simply compare item against the data in the current node
                   and either change the node to iterate further down the BST or return as appropriate */
            if (item.compareTo(data) == 0)
                return true;
            else if (item.compareTo(data) < 0) {
                if (currentNode.getLeft() == null)
                    return false;
                currentNode = currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null)
                    return false;
                currentNode = currentNode.getRight();
            }
        }
        // this statement is only reached when the while loop hits a leaf node, in which case only the currentNode's
        //  data has to be checked
        return (item.compareTo(currentNode.getData()) == 0);
    }

    /**
     * Determines if for each item in the specified collection, there is an item in this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item in this set that is equal to it;
     * otherwise, returns false
     * @throws NullPointerException if any of the items is null
     */
    public boolean containsAll(Collection<? extends Type> items) {
        // every BST contains an empty collection, so if one is passed return true
        if (items.size() == 0)
            return true;
        // an empty BST cannot contain any specific item - return false
        if (isEmpty())
            return false;

        // check if each element in the collection is found in the BST. If not return false
        for (Type element : items)
            if (!contains(element)) // return false if the element isn't found in the BST
                return false;
        // return true iff every element in the passed collection was found in the BST
        return true;
    }

    /**
     * Returns true if this set contains no items.
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if the input item was actually
     * removed); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    public boolean remove(Type item) {
        // handle null parameter
        if (item == null)
            throw new NullPointerException("Tried remove with null");
        // if the BST is empty (and the item isn't null) the item cannot exist in the BST, so return false
        if (isEmpty())
            return false;
        // handle removing root by removing root's successor and copying the removed node's data to root
        if (item.compareTo(root.getData()) == 0) {
            Type data = root.getSuccessor().getData();
            remove(data);
            root.setData(data);
            size--;
            return true;
        }
        // iterate through BST to find a Node with a child containing the specified data (a child is found instead of
        //  the actual node to remove so that the remove methods can have access to the parent of the node to be
        //  removed).
        BinaryNode currentNode = root;
        while (item.compareTo(currentNode.getLeft().getData()) != 0 && item.compareTo(currentNode.getRight().getData()) != 0) {
            // if the data should be left of the current node check the left child
            if (item.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() == null) // if no left child then the node doesn't exist and cannot be removed
                    return false;
                if (item.compareTo(currentNode.getLeft().getData()) == 0) // if the left child matches the data then remove the node
                    break;
                // if a left subtree exists and the root doesn't match the data then continue iterating down the BST
                currentNode = currentNode.getLeft();
            }
            // if the data should be right of the current node check the right child
            else {
                if (currentNode.getRight() == null) // if no right child then the node doesn't exist and cannot be removed
                    return false;
                if (item.compareTo(currentNode.getRight().getData()) == 0) // if the right child matches the data then store then remove the node
                    break;
                // if a right subtree exists and the root doesn't match the data then continue iterating down the BST
                currentNode = currentNode.getRight();
            }
        }
        // set the direction according to which node matches the data
        int direction = (item.compareTo(currentNode.getLeft().getData()) == 0) ? -1 : 1;
        // these statements only execute if the correct node was found, in which case the node should be removed
        //  according to the number of children, the size should decrement, and this method should return true.
        currentNode.remove(direction);
        size--;
        return true;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if any item in the input collection
     * was actually removed); otherwise, returns false
     * @throws NullPointerException if any of the items is null
     */
    public boolean removeAll(Collection<? extends Type> items) {
        // for empty collections simply return false, as nothing needs to happen and the BST will be unaffected
        if (items.size() == 0)
            return false;
        // store the size in order to check for changes to the BST when returning
        int size = size();
        // remove each element in the Collection from the BST, throwing an Exception if any element is null
        for (Type element : items)
            remove(element);
        // return true iff at least one element was removed (the size was changed during method execution)
        return (size != size());
    }

    /**
     * Returns the number of items in this set.
     */
    public int size() {
        return size;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted order (equivalent to an in-order
     * depth-first-traversal)
     */
    public ArrayList<Type> toArrayList() {
        return new ArrayList<Type>(inOrderDFT());
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws java.util.NoSuchElementException if the set is empty
     */
    public Type first() throws NoSuchElementException {
        // throw an exception for an empty set
        if (root == null)
            throw new NoSuchElementException("Tried first with an empty BST");
        // if root is smallest then return root's data
        if (root.getLeft() == null)
            return root.data;
            // otherwise find the largest node (rightmost node) and return its data
        else return root.getLeftmostNode().getData();
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws java.util.NoSuchElementException if the set is empty
     */
    public Type last() throws NoSuchElementException {
        // throw an exception for an empty set
        if (root == null)
            throw new NoSuchElementException("Tried last with an empty BST");
        // if root is biggest then return root's data
        if (root.getRight() == null)
            return root.data;
            // otherwise find the largest node (rightmost node) and return its data
        else return root.getRightmostNode().getData();
    }

    /**
     * Performs a pre-order depth-first-traversal of the tree
     *
     * @return the list containing the tree elements
     */
    public List<Type> inOrderDFT() {
        return null;
    }

    /**
     * Performs an in-order depth-first-traversal of the tree
     *
     * @return the list containing the tree elements
     */
    public List<Type> preOrderDFT() {
        return null;
    }

    /**
     * Performs a post-order depth-first-traversal of the tree
     *
     * @return the list containing the tree elements
     */
    public List<Type> postOrderDFT() {
        return null;
    }

    /**
     * Performs a level-order breath-first-traversal of the tree
     *
     * @return the list containing the tree elements
     */
    public List<Type> levelOrderBFT() {
        return null;
    }


    /* DOT methods taken from BST.java in the week 6 example package */
    // Driver for writing this tree to a dot file

    /**
     * Creates a file with .dot extension to contain information about the tree The format must be readable by the DOT
     * tool use by graphviz.
     *
     * @param filename - file containing the DOT formated data
     */
    public void writeDot(String filename) {
        try {
            // PrintWriter(FileWriter) will write output to a file
            PrintWriter output = new PrintWriter(new FileWriter(filename));

            // Set up the dot graph and properties
            output.println("digraph BST {");
            output.println("node [shape=record]");

            if (root != null)
                writeDotRecursive(root, output);
            // Close the graph
            output.println("}");
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Recursive method for writing the tree to a dot file
    private void writeDotRecursive(BinaryNode n, PrintWriter output) throws Exception {
        output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");

        if (n.left != null) {
            // write the left subtree
            writeDotRecursive(n.left, output);

            // then make a link between n and the left subtree
            output.println(n.data + ":L -> " + n.left.data + ":D");
        }
        if (n.right != null) {
            // write the left subtree
            writeDotRecursive(n.right, output);

            // then make a link between n and the right subtree
            output.println(n.data + ":R -> " + n.right.data + ":D");
        }
    }

    /**
     * Represents a general binary tree node. Each binary node contains data, a left child, and a right child, and a
     * parent.
     * <p/>
     * This would make a good node class for a BinarySearchTree implementation
     */
    private class BinaryNode {
        // Since the outer BST class declares <Type>, we can use it here without redeclaring it for BinaryNode
        private Type data;
        private BinaryNode left, right;

        /**
         * Construct a new node with known children
         */
        public BinaryNode(Type _data, BinaryNode _left, BinaryNode _right) {
            data = _data;
            left = _left;
            right = _right;
        }

        /**
         * Construct a new node with no children
         */
        public BinaryNode(Type _data) {
            this(_data, null, null);
        }

        /**
         * Getter method.
         *
         * @return the node data.
         */
        public Type getData() {
            return data;
        }

        /**
         * Setter method.
         *
         * @param _data - the node data to be set.
         */
        public void setData(Type _data) {
            data = _data;
        }

        /**
         * Getter method.
         *
         * @return the left child node.
         */
        public BinaryNode getLeft() {
            return left;
        }

        /**
         * Setter method.
         *
         * @param _left - the left child node to be set.
         */
        public void setLeft(BinaryNode _left) {
            left = _left;
        }

        /**
         * Getter method.
         *
         * @return the right child node.
         */
        public BinaryNode getRight() {
            return right;
        }

        /**
         * Setter method.
         *
         * @param _right - the right child node to be set.
         */
        public void setRight(BinaryNode _right) {
            right = _right;
        }

        /**
         * Number of children Use this to help figure out which BST deletion case to perform
         *
         * @return The number of children of this node
         */
        public int numChildren() {
            int numChildren = 0;

            if (getLeft() != null)
                numChildren++;
            if (getRight() != null)
                numChildren++;

            return numChildren;
        }

        /**
         * Returns true iff the BinaryNode is a leaf node (has no children)
         *
         * @return a boolean indicating if the node is a leaf or not
         */
        public boolean isLeaf() {
            return (numChildren() == 0);
        }

        /**
         * @return The leftmost node in the binary tree rooted at this node.
         */
        public BinaryNode getLeftmostNode() {
            // Base case, done for you
            if (left == null)
                return this; // returns "this" node

            // find leftmost Node
            BinaryNode returnNode = this.getLeft();
            while (returnNode.left != null)
                returnNode = returnNode.left;
            return returnNode;
        }

        /**
         * @return The rightmost node in the binary tree rooted at this node.
         */
        public BinaryNode getRightmostNode() {
            // Base case, done for you
            if (getRight() == null)
                return this; // returns "this" node

            // find rightmost Node
            BinaryNode returnNode = this.getRight();
            while (returnNode.getRight() != null)
                returnNode = returnNode.getRight();
            return returnNode;
        }

        /**
         * This method applies to binary search trees only (not general binary trees).
         *
         * @return The successor of this node.
         * @throws NoSuchElementException if the node has no successor (is a leaf node)
         *                                <p/>
         *                                The successor is a node which can replace this node in a case-3 BST deletion. It is either the smallest node
         *                                in the right subtree, or the largest node in the left subtree.
         */
        public BinaryNode getSuccessor() throws NoSuchElementException {
            // throw an exception if no successor exists (in case of a leaf node)
            if (isLeaf())
                throw new NoSuchElementException("Attempted .getSuccessor on a leaf node!");
            if (right != null)
                return right.getLeftmostNode();
            else
                return left.getRightmostNode();
        }

        /**
         * @return The height of the binary tree rooted at this node. The height of a tree is the length of the longest
         * path to a leaf node. Consider a tree with a single node to have a height of zero.
         * <p/>
         * The height of a tree with more than one node is the greater of its two subtrees' heights, plus 1
         */
        public int height() {
            // handle base case of recursion
            if (isLeaf())
                return 0;

            // find height of left subtree and right subtree, where a null subtree is of height 0)
            int leftHeight = (left == null) ? 0 : left.height();
            int rightHeight = (right == null) ? 0 : right.height();

            // return the correct height for this node, being the max height of the subtrees + 1
            return (Math.max(leftHeight, rightHeight) + 1);
        }

        /**
         * Removes the child of the this node according to the passed direction
         *
         * @param direction an int indicating which child to remove: -1 for the left, 1 for the right
         * @throws NoSuchElementException if the node doesn't have the indicated child, or the node is null
         */
        public void remove(int direction) {
            // throw Exceptions for every invalid removal case, with a message as appropriate
            if (direction != -1 && direction != 1)
                throw new NoSuchElementException("Tried BinaryNode.remove with the invalid direction " + direction + "!");
            if (direction == -1 && left == null)
                throw new NoSuchElementException("Tried BinaryNode.remove to the left with no left child!");
            if (direction == 1 && right == null)
                throw new NoSuchElementException("Tried BinaryNode.remove to the right with no right child!");

            // implement removal based on the direction and number of children
            if (direction == -1) { // removing the left child
                if (left.numChildren() == 0)
                    remove0(direction);
                else if (left.numChildren() == 1)
                    remove1(direction);
                else remove2(direction);
            } else { // removing the right child
                if (right.numChildren() == 0)
                    remove0(direction);
                else if (right.numChildren() == 1)
                    remove1(direction);
                else remove2(direction);
            }
        }

        /* Remove methods for each possible number of children. Note that input validation happens in the remove method above */

        /**
         * Removes a node with no children
         *
         * @param direction an int indicating which child to remove: -1 for the left, 1 for the right
         */
        private void remove0(int direction) {
            if (direction == -1) // removing the left child
                left = null;
            else // removing the right child
                right = null;
        }

        /**
         * Removes a node with one child
         *
         * @param direction an int indicating which child to remove: -1 for the left, 1 for the right
         */
        private void remove1(int direction) {
            if (direction == -1) { // removing the left child
                // find which subtree needs to replace the removed node and replace the node with the subtree
                if (left.left != null)
                    setLeft(left.left);
                else setLeft(left.right);
            } else { // removing the right child
                // find which subtree needs to replace the removed node and replace the node with the subtree
                if (right.left != null)
                    setLeft(right.left);
                else setLeft(right.right);
            }
        }

        /**
         * Removes a node with two children
         *
         * @param direction an int indicating which child to remove: -1 for the left, 1 for the right
         */
        private void remove2(int direction) {
            if (direction == -1) { // removing left node
                // if the right node has no left children then it is the successor - copy its data and remove it
                if (left.right.left == null) {
                    left.data = left.right.data;
                    left.remove(1);
                    return;
                }
                // otherwise find the parent of the successor then copy the successor's data and remove it
                BinaryNode parentNode = left.right;
                while (parentNode.left.left != null)
                    parentNode = parentNode.left;
                left.data = parentNode.left.data;
                parentNode.remove(-1);
            } else { // removing right node
                // if the right node has no left children then it is the successor - copy its data and remove it
                if (right.right.left == null) {
                    right.data = right.right.data;
                    right.remove(1);
                    return;
                }
                // otherwise find the parent of the successor then copy the successor's data and remove it
                BinaryNode parentNode = right.right;
                while (parentNode.left.left != null)
                    parentNode = parentNode.left;
                right.data = parentNode.left.data;
                parentNode.remove(-1);
            }
        }
    }
}
