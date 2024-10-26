package binarytree;

import java.util.ArrayList;
import java.util.List;

public class PreorderTraversal {
    List<Integer> res = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        traverse(root);
        return res;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }
        res.add(node.val);

        if (node.left != null) {
            traverse(node.left);
        }
        if (node.right != null) {
            traverse(node.right);
        }
    }
}
