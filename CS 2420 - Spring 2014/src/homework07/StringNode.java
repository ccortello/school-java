package homework07;

import javax.swing.tree.TreeNode;
import java.util.Set;

/**
 * Represents one Node of a Tree with one root node and sorted children
 * Created by Cody on 3/17/14.
 */
public class StringNode {
    private String data;
    private TreeNode root;
    private TreeNode parent;
    private Set<TreeNode>[] children;

    public StringNode() {
    }
}
