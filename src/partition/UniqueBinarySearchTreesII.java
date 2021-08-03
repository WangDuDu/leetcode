package partition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangshuyang on 2021-8-3.
 */
public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<TreeNode>();
        }
        return generateSubTrees(1, n);
    }

    private List<TreeNode> generateSubTrees(int start, int end) {
        List<TreeNode> res = new LinkedList<>();
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTreeNode = generateSubTrees(start, i - 1);
            List<TreeNode> rightTreeNode = generateSubTrees(i + 1, end);
            for (TreeNode left : leftTreeNode) {
                for (TreeNode right : rightTreeNode) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = left;
                    treeNode.right = right;
                    res.add(treeNode);
                }
            }
        }
        return res;
    }
}
