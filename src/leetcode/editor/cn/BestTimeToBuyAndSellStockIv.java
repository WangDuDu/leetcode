package leetcode.editor.cn;

//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。 
//
// 示例 2： 
//
// 
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
//。 
//
// 
//
// 提示： 
//
// 
// 0 <= k <= 100 
// 0 <= prices.length <= 1000 
// 0 <= prices[i] <= 1000 
// 
// Related Topics 数组 动态规划 👍 711 👎 0

/**
 * 本题是买卖股票III的进阶版，从最多交易两次变成了最多交易k次，但是本质上换汤不换药，在k=2的情况下和买卖股票III是一模一样的，为了方便理解我们假设k=3进行分析
 *
 * 1、今天为止交易了0次，不论哪天的利润都是0
 * 2、今天为止交易了1次：(1)昨天为止交易了0次，那么今天肯定完成了股票的卖出，假设是在第n天买入股票的，今天持有的最大利润为max(prices[j] - prices[n] + dp[1 - 1][n])(0<=n<j)
 *                    (2)昨天为止交易了1次，那么今天没有进行任何交易，今天持有的利润等于昨天交易1次的利润
 *                    (3)昨天为止交易了2次，这种情况不会出现
 *                    (4)昨天为止交易了3次，这种情况不会出现
 * 3、今天为止交易了2次：(1)昨天为止交易了0次，那么今天进行了一次卖，然后又进行了第2次的买+卖，这种情况其实和2(1)的情况是一样的，可以合并入2(1)
 *                      假设是在第n天买入股票的，今天持有的最大利润为max(prices[j] - prices[n] + dp[1 - 1][n])(0<=n<j)
 *                    (2)昨天为止交易了1次，那么今天进行了第2次卖的操作，假设是在第n天第二次买入股票的，今天持有的最大利润为max(prices[j] - prices[n] + dp[2 - 1][n])(0<=n<j)
 *                    (3)昨天为止交易了2次，那么今天没有进行任何交易，今天持有的利润等于昨天交易2次的利润
 *                    (4)昨天为止交易了3次，这种情况不会出现
 * 4、今天为止交易了3次：(1)昨天为止交易了0次，那么今天进行了一次卖，然后又进行了第2次和第3次的买+卖，这种情况其实和2(1)、3(1)的情况是一样的，可以合并入2(1)
 *                      假设是在第n天买入股票的，今天持有的最大利润为max(prices[j] - prices[n] + dp[1 - 1][n])(0<=n<j)
 *                    (2)昨天为止交易了1次，那么今天进行了第2次卖的操作，并进行了第3次的买+卖，这种情况和3(2)是一样的，可以并入3(2)，
 *                      假设是在第n天第二次买入股票的，今天持有的最大利润为max(prices[j] - prices[n] + dp[2 - 1][n])(0<=n<j)
 *                    (3)昨天为止交易了2次，那么今天进行了第3次卖的操作，假设在第n天进行了第3次买入，今天持有的最大利润是max(prices[j] - prices[n] + dp[3 - 1][n])(0<=n<j)，
 *                    (4)昨天为止交易了3次，那么今天没有进行任何交易，今天持有的利润等于昨天交易3次的利润
 * 经过上边的分析，我们可以看出「今天为止交易了3次」包含了前边1、2、3的最优解，所以最终的最优解肯定是dp[3][j](j=天数)
 * 综合上述分析状态转移方程得出：dp[i][j] = max(dp[i][j - 1], max(prices[j] - prices[n] + dp[i - 1][n]))(0<=n<j)
 *
 * (1)确定动态规划数组：dp[i][j]表示持有的利润
 * i = 0 当天为止进行了0次交易 i = 1表示当天为止进行了1次交易 i = 2表示当天为止进行了2次交易
 * j 表示第j天
 * (2)确定状态转移方程：
 * 今天为止交易了0次：dp[0][j] = 0 在交易0次的情况下，不管哪天的利润都是0
 * 今天为止交易了1次：dp[1][j] = max(dp[1][j - 1], max(prices[j] - prices[n] + dp[0][n]))(0<=n<j)
 * 今天为止交易了2次：dp[2][j] = max(dp[2][j - 1], max(prices[j] - prices[n] + dp[1][n]))(0<=n<j)
 * <p>
 * 综合下来：dp[i][j] = max(dp[i][j - 1], max(prices[j] - prices[n] + dp[i - 1][n]))(0<=n<j)
 * (3)初始化：
 * dp[0][0] ~ dp[0][prices] = 0 在交易0次的情况下，不管哪天的利润都是0
 * dp[1][0] = 0 在第一天交易一次的情况下，只能是在第一天先买后卖，所以最大利润是0
 * dp[2][0] = 0 在第一天交易两次的情况下，只能是在第一天先买后卖，再买再卖所以最大利润是0
 * (4)优化：如果没有做优化的话，上边的答案是超时的
 * 我们可以分析一下，时间耗时其实主要耗费在双循环的，所以要想办法减去内部循环，将内部循环放在外部循环中来做
 * 在内部循环中prices[j] - prices[n] + dp[i - 1][n]很多重复计算的部分
 * prices[5] - prices[0] + dp[0][0]
 * prices[5] - prices[1] + dp[0][1]
 * prices[5] - prices[2] + dp[0][2]
 * prices[5] - prices[3] + dp[0][3]
 * prices[5] - prices[4] + dp[0][4]
 * <p>
 * prices[6] - prices[0] + dp[0][0]
 * prices[6] - prices[1] + dp[0][1]
 * prices[6] - prices[2] + dp[0][2]
 * prices[6] - prices[3] + dp[0][3]
 * prices[6] - prices[4] + dp[0][4]
 * prices[6] - prices[5] + dp[0][5]
 * 在上边的例子中，重复计算的部分为
 * - prices[0] + dp[0][0]
 * - prices[1] + dp[0][1]
 * - prices[2] + dp[0][2]
 * - prices[3] + dp[0][3]
 * - prices[4] + dp[0][4]
 * 而prices[6],prices[5]这些事固定不变的，
 * 基于此我们将上边重复的部分设为maxProfit存储起来
 * dp[i][j] = max(dp[i][j - 1], prices[j] + maxProfit)(0<=n<j)
 * maxProfit = max(-prices[n] + dp[i - 1][n], maxProfit)
 */

public class BestTimeToBuyAndSellStockIv {
    public static void main(String[] args) {

        Solution solution = new BestTimeToBuyAndSellStockIv().new Solution();
        System.out.println(solution.maxProfit(2, new int[]{2,4,1}));
        System.out.println(solution.maxProfit(2, new int[]{3,2,6,5,0,3}));
    }

    class Solution {
        public int maxProfit(int k, int[] prices) {
            int days = prices.length;
            if (days == 1 || days == 0) {
                return 0;
            }

            int[][] dp = new int[k + 1][days];

            for (int i = 0; i < days; i++) {
                dp[0][i] = 0;
            }

            for (int i = 1; i <= k; i++) {
                dp[i][0] = 0;
            }


            for (int i = 1; i < k + 1; i++) {
                int maxProfit = -prices[0];
                for (int j = 1; j < days; j++) {
                    dp[i][j] = Integer.max(dp[i][j - 1], prices[j] + maxProfit);
                    maxProfit = Integer.max(maxProfit, -prices[j] + dp[i - 1][j]);
                }
            }
            return dp[k][days - 1];
        }
    }

}