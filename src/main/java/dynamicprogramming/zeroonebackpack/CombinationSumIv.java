package dynamicprogramming.zeroonebackpack;

//给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。 
//
// 题目数据保证答案符合 32 位整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3], target = 4
//输出：7
//解释：
//所有可能的组合为：
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//请注意，顺序不同的序列被视作不同的组合。
// 
//
// 示例 2： 
//
// 
//输入：nums = [9], target = 3
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 1000 
// nums 中的所有元素 互不相同 
// 1 <= target <= 1000 
// 
//
// 
//
// 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？ 
// Related Topics 数组 动态规划 👍 638 👎 0

/**
 * 本题是数字可以重复使用，并且放入背包有顺序要求(同样的元素，不同的顺序是不同的解)，所以是有顺序的完全背包问题，需要注意两点
 * 一个是0~target的遍历要从0开始，另一个是对物品(这里是nums数组)的遍历要放到内层
 * 每个dp[i]会用每个num都试一遍，然后求dp[i+1]。这样(1,2,1)和(1,1,2)这种顺序
 * 例如nums = [3,2,1], target = 4
 * dp[1] = 1
 *      num = 3 dp[1] = dp[1] = 0
 *      num = 2 dp[1] = dp[1] = 0
 *      num = 1 dp[1] = dp[0] = 1 (1)
 * dp[2] = 2
 *      num = 3 dp[2] = dp[2] = 0
 *      num = 2 dp[2] = dp[2] + dp[0] = 1 (2)
 *      num = 1 dp[2] = dp[2] + dp[1] = 2 (2) (1, 1)
 * dp[3] = 4
 *      num = 3 dp[3] = dp[3] + dp[0] = 1 (3)
 *      num = 2 dp[3] = dp[3] + dp[1] = 2 (3) (1, 2)
 *      num = 1 dp[3] = dp[3] + dp[2] = 4 (3) (1, 2) (2, 1) (1, 1, 1)
 * dp[4] = 7
 *      num = 3 dp[4] = dp[4] + dp[1] = 1 (1, 3)
 *      num = 2 dp[4] = dp[4] + dp[2] = 3 (1, 3) (2, 2) (1, 1, 2)
 *      num = 1 dp[4] = dp[4] + dp[3] = 7 (1, 3) (2, 2) (1, 1, 2) (3, 1) (1, 2, 1) (2, 1, 1) (1, 1, 1, 1)
 * 每次用num求dp[i]的时候，都会把上一个num求得的dp[i]加到当前的值上，这样不同的情况就都兼顾到了
 *
 * (1)确定动态规划数组：dp[i]表示和为i的组合数量
 * (2)确定状态转移方程：dp[i] = dp[i - num] + dp[i]
 * (3)初始化：dp[0] = 1
 */
public class CombinationSumIv {
    public static void main(String[] args) {

        Solution solution = new CombinationSumIv().new Solution();
        System.out.println(solution.combinationSum4(new int[]{1,2,3}, 4));
        System.out.println(solution.combinationSum4(new int[]{9}, 3));
    }

    class Solution {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];

            dp[0] = 1;

            for (int i = 1; i <= target; i++) {
                for (int num : nums) {
                    if (i >= num) {
                        dp[i] = dp[i - num] + dp[i];
                    }
                }
            }
            return dp[target];
        }
    }

}