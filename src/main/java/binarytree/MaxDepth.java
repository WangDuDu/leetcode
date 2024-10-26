package binarytree;



public class MaxDepth {
    int maxDeep = 0;
    int cDeep = 0;

    public int maxDepth(TreeNode root) {
        traverse(root);
        return maxDeep;
    }

    private void traverse(TreeNode node) {
        if (node == null) {
            return;
        }

        cDeep++;
        maxDeep = maxDeep > cDeep ? maxDeep : cDeep;
        if (node.left != null) {
            traverse(node.left);
        }
        if (node.right != null) {
            traverse(node.right);
        }
    }
}
