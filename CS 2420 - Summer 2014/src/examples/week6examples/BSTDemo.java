package examples.week6examples;

public class BSTDemo {
    public static void main(String[] args) {
        BST bst = new BST();

        bst.insert("dog");
        bst.insert("pelican");
        bst.insert("zebra");
        bst.insert("cat");
        bst.insert("alpaca");
        bst.insert("cow");
        bst.insert("chupacabra");
        bst.insert("dragon");

        // Try inserting in alphabetical order instead
        // What happens to the shape (and thus performance) of the tree?
        /*
		bst.insert("alpaca");
		bst.insert("cat");
		bst.insert("chupacabra");
		bst.insert("cow");
		bst.insert("dog");
		bst.insert("dragon");
		bst.insert("pelican");
		bst.insert("zebra");
		*/

        // Will generate a file "BST.dot" in your project (not package) directory
        // On a CADE linux machine run the command:
        // dot -Tgif BST.dot -o BST.gif
        // Then open up BST.gif to examine the tree
        bst.writeDot("BST.dot");

        System.out.println(bst.search("cow"));
        System.out.println();
        System.out.println(bst.search("elephant"));
        System.out.println();
    }
}

