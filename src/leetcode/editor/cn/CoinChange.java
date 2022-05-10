package leetcode.editor.cn;

//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。 
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 2³¹ - 1 
// 0 <= amount <= 10⁴ 
// 
// Related Topics 广度优先搜索 数组 动态规划 👍 1926 👎 0

/**
 * 322
 *
 * 本题的硬币可以重复使用，属于完全背包问题，完全背包问题是要从前往后遍历的，这样算dp[i]，可能用到的dp[0] ~ dp[i - 1]已经都用过背包硬币值进行处理了
 * 如果不是完全背包问题，那么要从后往前遍历，这样算dp[i]的时候，可能用到的dp[0] ~ dp[i - 1]没有用过当前背包值进行处理了，不会存在重复使用的问题
 * (1) 确定动态规划数组dp[i]表示和为i的最优解
 * (2) 确定动态转移方程 dp[i] = Integer.min(dp[i - coin] + 1, dp[i])
 *      当前的值放入子集，dp[i] = dp[i - coin] + 1
 *      当前的值不放入子集，dp[i] = dp[i]
 * (3) 初始化dp[0] = 0
 *
 */

public class CoinChange {
    public static void main(String[] args) {

        Solution solution = new CoinChange().new Solution();
        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(solution.coinChange(new int[]{2}, 3));
        System.out.println(solution.coinChange(new int[]{1}, 0));
    }

    class Solution {
        public int coinChange(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }

            int[] dp = new int[amount + 1];

            for (int coin : coins) {
                for (int i = coin; i <= amount; i++) {
                    // 当i==coin的时候，最少的情况就是放入当前遍历到的硬币，数量最少为1
                    if (i == coin) {
                        dp[i] = 1;
                    // 当dp[i - coin] == 0的时候，代表前边的硬币不能使和正好等于i - coin，那么当前的硬币就不存在放入子集的情况，那么dp[i] = dp[i]，不再和dp[i - coin] + 1比较大小
                    // 当dp[i - coin] != 0的时候，如果前边硬币不能使和为i，也就是dp[i] == 0，那么当前硬币是肯定要放入子集的，所以dp[i] = dp[i - coin] + 1，不再和dp[i]比较
                    // 当dp[i - coin] != 0的时候，如果前边硬币能使和为i，也就是dp[i] != 0, 那么当前硬币可以放入子集也可以不放入，dp[i] = Integer.min(dp[i - coin] + 1, dp[i])
                    } else if (dp[i] == 0 && dp[i - coin] != 0) {
                        dp[i] = dp[i - coin] + 1;
                    } else if (dp[i - coin] != 0){
                        dp[i] = Integer.min(dp[i - coin] + 1, dp[i]);
                    }
                }
            }
            return dp[amount] == 0 ? -1 : dp[amount];
        }
    }

}