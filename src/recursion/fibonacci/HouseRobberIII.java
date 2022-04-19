package recursion.fibonacci;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName HouseRobberIII.java
 * @Description TODO
 * @createTime 2022年04月19日 22:10:00
 * 337
 * 本题中房屋从线性结构编程了树形结构，从树的数据结构我们可以看出每一个节点的最优解其实都依赖它左右子树的最优解，所以需要先求出左右子树的最优解
 * 所以我们要采用二叉树的后续遍历，即先处理左子树，再处理右子树，最后再处理当前节点
 * (1)确定动态规划数组：这里的动态规划数组仅有两个元素，dp[0]代表本节点不处理的最优解， dp[1]代表本节点处理的最优解
 * (2)确定动态转移方程：
 *    动态转移方程分为两部分，本节点不处理的场景和本节点处理的场景
 *    本节点不处理(dp[0])：本节点不处理，那么它的左右子节点可以选择处理也可以选择不处理，本节点不处理的情况下的最优解为左节点的最优解+右节点的最优解
 *                       左节点的最优解为左节点本身不处理和左节点本身处理的值中比较大的那个Integer.max(dpLeft[0], dpLeft[1])
 *                       右节点的最优解为右节点本身不处理和右节点本身处理的值中比较大的那个Integer.max(dpRight[0], dpRight[1])
 *    本节点做处理(dp[1])：本节点处理，那么它的左右子节点都不可以进行处理，本节点处理的情况下的最优解为左节点不处理的最优解+右节点的不处理最优解+本节点的值 dpLeft[0] + dpRight[0] + node.val
 * (3)初始化：要初始化的就是为null的节点，也就是子树为null的时候直接返回dp[0] = 0, dp[1] = 0
 *
 */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        int[] res = handle(root);
        return Integer.max(res[0], res[1]);
    }

    public int[] handle(TreeNode node) {
        int[] dp = new int[]{0, 0};
        if (node == null) {
            return dp;
        }
        int[] dpLeft = handle(node.left);
        int[] dpRight = handle(node.right);
        dp[0] = Integer.max(dpLeft[0], dpLeft[1]) + Integer.max(dpRight[0], dpRight[1]);
        dp[1] = dpLeft[0] + dpRight[0] + node.val;
        return dp;
    }
}
