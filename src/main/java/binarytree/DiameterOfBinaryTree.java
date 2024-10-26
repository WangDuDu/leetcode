package binarytree;

import com.sun.source.tree.Tree;

public class DiameterOfBinaryTree {
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDeep(root);
        return res;
    }

    private int maxDeep(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDeep = maxDeep(node.left);
        int rightDeep = maxDeep(node.right);

        res = Math.max(res, leftDeep + rightDeep);
        return Math.max(leftDeep, rightDeep) + 1;
    }
}
