package dynamicprogramming.zeroonebackpack;

//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
// Related Topics 数组 动态规划 👍 1279 👎 0

/**
 * 416
 *
 * 我们将给定集合所有元素的和记为sum，那么本题可以视为是否存在子集的和为sum/2的0-1背包问题，
 * 当sum是奇数的时候一定为false
 *
 * (1)确定动态规划数组dp[i][j]
 *      表示处理到第i个元素时元素和是否可以为j
 * (2)确定状态转移方程:dp[i][j] = (j >= nums[i] && dp[i - 1][j - nums[i]]) || dp[i - 1][j];
 *      如果当前遍历到的元素不进行处理那么，那么元素和是否能为j取决于，之前的元素和是否能为j，dp[i][j] = dp[i - 1][j]
 *      如果当前遍历到的元素进行处理的话，分为两种情况
 *          如果j>=当前处理到的元素值也就是j>=nums[i]，那么dp[i][j] = dp[i - 1][j - nums[i]]
 *          如果j<当前处理到的元素值，也就是j<nums[i]，那么dp[i][j] = false
 * (3)初始化：
 *      dp[0][0] 代表当前处理到第0个元素的和为0，只要不把当前元素放入子集即可，所以dp[0][0] = true
 *      dp[0][j] 当j = nums[0]的时候为true，其余值为false
 *
 */

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new PartitionEqualSubsetSum().new Solution();
    }

    class Solution {
        public boolean canPartition(int[] nums) {
            int length = nums.length;
            int sum = 0;
            for (int i = 0; i < length; i++) {
                sum = sum + nums[i];
            }

            if (sum % 2 == 1) {
                return false;
            }

            int half = sum / 2;

            boolean[][] dp = new boolean[length][half + 1];
            dp[0][0] = true;
            for (int j = 1; j < half; j++) {
                if (j == nums[0]) {
                    dp[0][j] = true;
                } else {
                    dp[0][j] = false;
                }
            }

            for (int i = 1; i < length; i++) {
                for (int j = 0; j <= half; j++) {
                    dp[i][j] = (j >= nums[i] && dp[i - 1][j - nums[i]]) || dp[i - 1][j];
                }
            }

            return dp[length - 1][half];
        }
    }

}