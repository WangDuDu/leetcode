package leetcode.editor.cn;

//给你一个整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
// Related Topics 数组 动态规划 回溯 👍 1192 👎 0

/**
 * (1)确定动态规划数组dp[i][j]
 *      表示，遍历到第i个数，表达式的值为j的表达式数目
 * (2)确定状态转移方程
 *      当前遍历到的值应该是+：dp[i][j] = dp[i - 1][j - nums[i]]
 *      当前遍历到的值应该是-：dp[i][j] = dp[i - 1][j + nums[i]]
 * 当分析到这里的时候我们发现有问题。j的值可能为负数，这样我们就不能用上述推导方式了，和416题相似，我们需要将这个问题变为一个全部为正数的子集和等于某一个值
 * 我们将nums分为两个子集，加「+」号的正数为sum[P](positive)，加「-」号的为-sum[N](negative)，全部为正数的和为sum(nums)，那么有以下推论
 * 要达成的目标是
 * sum(P) - sum(N) = target
 * 两边同时加上sum(P) + sum(N)
 * sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
 * 其中sum(P) + sum(N) = sum[nums]
 * 2 * sum[P] = target + sum[nums]
 * sum[P] = (target + sum[nums]) / 2
 * -sum[nums]<=target<=sum[nums],所以(target + sum[nums]) / 2 >= 0
 *
 * 然后问题就变为了求一个全为正数的子集和为newTarget = (target + sum[nums]) / 2, newTarget >= 0
 * (1)确定动态规划数组dp[i][j]
 *      表示前i个值的子集和为j的情况
 * (2)确定状态转移方程
 *      当前的值加入子集
 *          j >= nums[i]: dp[i][j] = dp[i - 1][j - nums[i]]
 *          i <  nums[i]: dp[i][j] = 0
 *      当前的值不加入子集
 *          dp[i][j] = dp[i - 1][j]
 * (3)初始化：
 *      在初始化的时候一定要注意，在初始化dp[0][0]的时候，当nums的第一个值nums[0]=0的时候dp[0][0]=2，因为第一个值0可以加入子集，也可以不加入子集，有两种情况
 *      第一个值不为0的时候，只有不把第一个值放入子集一种情况
 *      dp[0][0] = nums[0] == 0 ? 2 : 1;
 *      第一个值是否等于j决定了，前1个值的和能否为j
 *      dp[0][j] = nums[0] == j ? 1 : 0;
 */

public class TargetSum {
    public static void main(String[] args) {

        Solution solution = new TargetSum().new Solution();
//        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
//        System.out.println(solution.findTargetSumWays(new int[]{1}, 1));
        System.out.println(solution.findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1}, 1));

    }

    class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            int length = nums.length;
            int sum = 0;
            for (int i = 0; i < length; i++) {
                sum += nums[i];
            }

            if (target > sum || -target > sum || (target + sum) % 2 == 1) {
                return 0;
            }
            int newTarget = (target + sum) / 2;
            int[][] dp = new int[length][newTarget + 1];

            dp[0][0] = nums[0] == 0 ? 2 : 1;
            for (int j = 1; j <= newTarget; j++) {
                dp[0][j] = nums[0] == j ? 1 : 0;
            }

            for (int i = 1; i < length; i++) {
                for (int j = 0; j <= newTarget; j++) {
                    dp[i][j] = (j >= nums[i] ? dp[i - 1][j - nums[i]] : 0) + dp[i - 1][j];
                }
            }
            return dp[length - 1][newTarget];
        }
    }

}