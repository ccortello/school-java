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
     * Returns an ArrayList containing all of the items in this set, in sorted order.
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
        return null;
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
}
