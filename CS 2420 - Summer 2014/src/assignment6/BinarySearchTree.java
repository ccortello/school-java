package assignment6;

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

    // creates an empty BST
    public BinarySearchTree() {
        root = null;
    }
    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if the input item was actually
     * inserted); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean add(Type item) {
        return false;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if any item in the input collection
     * was actually inserted); otherwise, returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean addAll(Collection<? extends Type> items) {
        return false;
    }

    /**
     * Removes all items from this set. The set will be empty after this method call.
     */
    @Override
    public void clear() {

    }

    /**
     * Determines if there is an item in this set that is equal to the specified item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item; otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean contains(Type item) {
        return false;
    }

    /**
     * Determines if for each item in the specified collection, there is an item in this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item in this set that is equal to it;
     * otherwise, returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean containsAll(Collection<? extends Type> items) {
        return false;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if the input item was actually
     * removed); otherwise, returns false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean remove(Type item) {
        return false;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if any item in the input collection
     * was actually removed); otherwise, returns false
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean removeAll(Collection<? extends Type> items) {
        return false;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted order (equivalent to an in-order
     * depth-first-traversal)
     */
    @Override
    public ArrayList<Type> toArrayList() {
        return null;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws java.util.NoSuchElementException if the set is empty
     */
    @Override
    public Type first() throws NoSuchElementException {
        if (root == null) // exception for empty set
            throw new NoSuchElementException("Empty BST, no first element!");
        else return root.getLeftmostNode().getData(); // return smallest (leftmost) Node's data
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws java.util.NoSuchElementException if the set is empty
     */
    @Override
    public Type last() throws NoSuchElementException {
        return null;
    }

    /**
     * Performs a pre-order depth-first-traversal of the tree
     *
     * @return the list containing the tree elements
     */
    @Override
    public List<Type> inOrderDFT() {
        return null;
    }

    /**
     * Performs an in-order depth-first-traversal of the tree
     *
     * @return the list containing the tree elements
     */
    @Override
    public List<Type> preOrderDFT() {
        return null;
    }

    /**
     * Performs a post-order depth-first-traversal of the tree
     *
     * @return the list containing the tree elements
     */
    @Override
    public List<Type> postOrderDFT() {
        return null;
    }

    /**
     * Performs a level-order breath-first-traversal of the tree
     *
     * @return the list containing the tree elements
     */
    @Override
    public List<Type> levelOrderBFT() {
        return null;
    }

    /**
     * Creates a file with .dot extension to contain information about the tree The format must be readable by the DOT
     * tool use by graphviz.
     *
     * @param filename - file containing the DOT formated data
     */
    @Override
    public void writeDot(String filename) {

    }

    /**
     * Represents a general binary tree node. Each binary node contains data, a left child, and a right child, and a
     * parent.
     * <p/>
     * This would make a good node class for a BinarySearchTree implementation
     */
    private class BinaryNode {
        // Since the outer BST class declares <Type>, we can use it here without redeclaring it for BinaryNode
        Type data;

        BinaryNode left;

        BinaryNode right;

        // Adding a parent reference breaks the definition of a tree,
        // but some people find it makes BST management easier.
        // If you choose to use them, remember to update parent pointers
        // when adding/removing nodes!
        BinaryNode parent;

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
         * Construct a new node with a known parent
         */
        public BinaryNode(Type _data, BinaryNode _parent) {
            this(_data, null, null);
            parent = _parent;
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
         * Getter method.
         *
         * @return the parent of this node.
         */
        public BinaryNode getParent() {
            return parent;
        }

        /**
         * Setter method.
         *
         * @param _parent - the parent node to be set.
         */
        public void setParent(BinaryNode _parent) {
            parent = _parent;
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
         * @return The leftmost node in the binary tree rooted at this node.
         */
        public BinaryNode getLeftmostNode() {
            // Base case, done for you
            if (getLeft() == null)
                return this; // returns "this" node

            // FILL IN - do not return null
            return null;
        }

        /**
         * @return The rightmost node in the binary tree rooted at this node.
         */
        public BinaryNode getRightmostNode() {
            // Base case, done for you
            if (getRight() == null)
                return this; // returns "this" node

            // FILL IN - do not return null
            return null;
        }

        /**
         * This method applies to binary search trees only (not general binary trees).
         *
         * @return The successor of this node.
         * <p/>
         * The successor is a node which can replace this node in a case-3 BST deletion. It is either the smallest node
         * in the right subtree, or the largest node in the left subtree.
         */
        public BinaryNode getSuccessor() {
            // FILL IN - do not return null
            return null;
        }

        /**
         * @return The height of the binary tree rooted at this node. The height of a tree is the length of the longest
         * path to a leaf node. Consider a tree with a single node to have a height of zero.
         * <p/>
         * The height of a tree with more than one node is the greater of its two subtrees' heights, plus 1
         */
        public int height() {
            // FILL IN - do not return -1
            return - 1;
        }
    }
}
