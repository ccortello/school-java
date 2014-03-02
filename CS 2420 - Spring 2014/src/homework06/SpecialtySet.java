package homework06;

/**
 * Objects of this class represent a set of sortable values.  The set
 * has the following performace characteristics:
 * <p/>
 * - the set is kept in a sorted linked list
 * <p/>
 * - getting the size of the set - theta(1)
 * <p/>
 * - adding, removing, or searching for a random element - theta(n)
 * <p/>
 * - adding, removing, or searching for an element that
 * immediately follows the previously accessed element - theta(1)
 * <p/>
 * In other words, this set performs very well if additions or
 * removals occur with long sequential runs of ordered data values.
 * <p/>
 * Note:  This data structure is not threadsafe because instance
 * variables are used to keep track of visit state.  An iterator
 * would be a much better idea!
 *
 * @author your_name_here
 * @version current_date_here
 */
public class SpecialtySet<E extends Comparable<E>> {
    // Instance variables.  Students are allowed
    //   only these, do not add or change instance variables.

    private Node head, last, current;
    private int size;

    // Instance methods below.

    /**
     * Constructs an empty set.
     */
    public SpecialtySet() {
    }


    /**
     * Returns the number of elements in this SpecialtySet.
     *
     * @return a count of the elements in this set
     */
    public int size() {
        return size;
    }


    /**
     * A private helper function that locates the position
     * in the linked list where 'data' exists, or could be
     * inserted.  Upon completion, two instance variables are set:
     * <p/>
     * The 'last' variable points to the node prior to the
     * position that 'data' should occupy in the list.  'Last'
     * will be null if 'data' should occupy the first position
     * in the list.
     * <p/>
     * The 'current' variable ether points to the node containing
     * the data (if the data exists in the list), or the node
     * following the position that the data should occupy (if
     * the data does not exist in the list).  'Current' will
     * be null only if the data is not in the list and would
     * naturally appear at the end of the list.
     * <p/>
     * Finally, if the node at 'last' exists and last.data < data,
     * this function begins the search at 'current'.  Otherwise,
     * this function starts the search at the beginning of the
     * linked list.
     *
     * @param data
     */
    private void locatePosition(E data) {

        // if the list is of size 0 then return
        if (size == 0)
            return;

        // if at end of list and last element is too big, then restart the search
        if (current == null || (last != null && last.data.compareTo(data) > 0)) {
            last = null;
            current = head;
        }

        // while not at end and the current data is less than the data being looked for
        while (current != null && current.data.compareTo(data) < 0) {
            if (current.data.compareTo(data) == 0) // if the data at 'current' doesn't match 'data'
                return;

            // then continue walking through the list
            last = current;
                current = current.next;

                // return if at end of list ('current' and 'last' should be correctly set)
            if (current == null)
                return;
            }
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

        // set the Node variables correctly
        locatePosition(data);

        // return true iff the data at 'current' = the given data
        if (current != null && current.data.compareTo(data) == 0)
            return true;
        else
            return false;
    }

    /**
     * Adds the specified data to the set.  (If the data
     * is already in the set, the data is ignored.)
     *
     * @param data a data value to be added to the set
     */
    /* Implementation note:  If an element is actually added,
     *   'current' will refer to the node containing the added
     *   data after this function, and 'last' will refer
     *   to the previous node (as appropriate).
     */
    public void add(E data) {

        // handle null case
        if (this == null)
            return;

        // if the data already exists in the list don't add it (if condition will be skipped and nothing will happen
        if (!contains(data)) {

            // find the correct location for the data
            locatePosition(data);

            // initialize new Node (to be added) with the given data
            Node newNode = new Node(data);

            // handle adding to empty set
            if (size == 0) {

                // add the new node, increment size, and return
                head = newNode;
                current = newNode;
                size++;
                System.out.println("added, set = " + this.toString());
                return;
            }

            // if at beginning of list update head, last, and current and increment size
            if (last == null && current == head) {
                current = newNode;
                current.next = head;
                head = current;
                size++;
            }

            // if at end of list simply update 'last' node and increment size
            else if (last != null && current == null) {
                last.next = newNode;
                current = newNode;
                size++;
            }

            // otherwise, simply update Node tracker variables (middle of list) and increment size
            else if (!last.data.equals(data)) {
                last.next = newNode;
                last.next.next = current;
                current = last.next;
                size++;
            }
        }
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

        //
        if (contains(data)) {

            // handle removing first element
            if (last == null && current == head) {
                head = current.next;
                current = head;
                size--;
                return;
            }

            last.next = last.next.next;
            current = last.next;
            size--;
        }
    }


    /**
     * Prints the contents of the SpecialtySet in order,
     * with tabs as delimiters
     *
     * @return String of the contents of the SpecialtySet
     */
    public String toString() {
        // initialize return String and reset list trackers to the beginning of the list
        String returnString = "";
        current = head;
        last = null;

//        System.out.println("in toString, btw: Size = " + size);

        // loop through the list and add each element in the list to the return String
        while (current != null) {
            returnString += current.data.toString() + "\t";

            last = current;
            current = current.next;
        }
        returnString = returnString.trim();
        return returnString;

    }

    // An example of an inner class (a class within another object).

    /**
     * A private helper class for the SpecialtySet class.
     * Node objects are used to construct linked lists
     * in a SpecialtySet.
     * <p/>
     * Students are not allowed to change this class.
     *
     * @author Peter Jensen
     * @version 2/22/2014
     */
    private class Node {
        private final E data;   // The data element - cannot be changed after it is assigned
        private Node next;      // Initialized to null when this object is created

        /**
         * Builds this node to contain the specified data.  By default, this
         * node does not point to any other nodes (next is null), although it
         * is expected that 'next' may change.
         * <p/>
         * Also note, the data variable is final, the data reference cannot be
         * changed.  (This fact is largely irrelevant.)
         *
         * @param data the data to store in the node
         */
        Node(E data) {
            this.data = data;
        }
    }
}