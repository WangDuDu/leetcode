package leetcode.editor.cn;

//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1] 
//输出：0 
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。 
//
// 示例 4： 
//
// 
//输入：prices = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁵ 
// 
// Related Topics 数组 动态规划 👍 1096 👎 0

/**
 * 在本题中股票只能交易两次，其实主要影响的是当天要买入股票的场景
 * <p>
 * 今天为止交易了0次：不管哪天的利润都是0
 * 今天为止交易了1次：(1)昨天为止交易了0次，那么今天进行了卖，前边的某一天进行了买，所以最大利润是max(prices[j] - preices[n] + n天为止交易0次的最大利润)(0<=n<j)，n代表在第n天进行了买入
 *                 (2)昨天为止交易了1次，那么今天没有进行交易，利润等于昨天的利润
 *                 (3)昨天为止交易了2次，这种情况不可能出现
 * 今天为止交易了2次：(1)昨天为止交易了0次，那么今天先进行了一次卖，然后又进行了一次买+卖，所以最大利润是max(prices[j] - preices[n] + n天为止交易0次的最大利润)(0<=n<j)，n代表在第n天进行了买入
 *                 (2)昨天为止交易了1次，那么今天进行了第二次卖，最大利润为所以最大利润是max(prices[j] - prices[n] + n天为止交易1次的最大利润)(0<=n<j)，n代表在第n天进行了买入
 *                 (3)昨天为止交易了2次，那么今天没有进行交易，利润等于昨天的利润
 * <p>
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
 * <p>
 * prices[6] - prices[0] + dp[0][0]
 * prices[6] - prices[1] + dp[0][1]
 * prices[6] - prices[2] + dp[0][2]
 * prices[6] - prices[3] + dp[0][3]
 * prices[6] - prices[4] + dp[0][4]
 * 在上边的例子中，重复计算的部分为
 * - prices[0] + dp[0][0]
 * - prices[1] + dp[0][1]
 * - prices[2] + dp[0][2]
 * - prices[3] + dp[0][3]
 * 而prices[6],prices[5]这些事固定不变的，
 * 基于此我们将上边重复的部分设为maxProfit存储起来
 * dp[i][j] = max(dp[i][j - 1], prices[j] + maxProfit)(0<=n<j)
 * maxProfit = max(-prices[n] + dp[i - 1][n], maxProfit)
 */

public class BestTimeToBuyAndSellStockIII {
    public static void main(String[] args) {

        Solution solution = new BestTimeToBuyAndSellStockIII().new Solution();
        System.out.println(solution.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(solution.maxProfit(new int[]{7, 6, 4, 3, 1}));
        System.out.println(solution.maxProfit(new int[]{1, 2, 3, 4, 5}));

        System.out.println(solution.maxProfit2(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(solution.maxProfit2(new int[]{7, 6, 4, 3, 1}));
        System.out.println(solution.maxProfit2(new int[]{1, 2, 3, 4, 5}));

        System.out.println(solution.maxProfit3(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(solution.maxProfit3(new int[]{7, 6, 4, 3, 1}));
        System.out.println(solution.maxProfit3(new int[]{1, 2, 3, 4, 5}));
    }

    class Solution {
        public int maxProfit(int[] prices) {
            int days = prices.length;
            if (days == 1) {
                return 0;
            }
            int[][] dp = new int[3][days];
            for (int j = 0; j < days; j++) {
                dp[0][j] = 0;
            }
            dp[1][0] = 0;
            dp[2][0] = 0;

            // 这种循环方式会超时
            for (int j = 1; j < days; j++) {
                int max1 = dp[1][j - 1];
                int max2 = dp[2][j - 1];
                for (int n = 0; n < j; n++) {
                    if ((prices[j] - prices[n] + dp[0][n]) > max1) {
                        max1 = prices[j] - prices[n] + dp[0][n];
                    }
                    if ((prices[j] - prices[n] + dp[1][n]) > max2) {
                        max2 = prices[j] - prices[n] + dp[1][n];
                    }
                }
                dp[1][j] = max1;
                dp[2][j] = max2;
            }
            return Integer.max(dp[1][days - 1], dp[2][days - 1]);
        }

        public int maxProfit2(int[] prices) {
            int days = prices.length;
            if (days == 1) {
                return 0;
            }
            int[][] dp = new int[3][days];
            for (int j = 0; j < days; j++) {
                dp[0][j] = 0;
            }
            dp[1][0] = 0;
            dp[2][0] = 0;

            for (int i = 1; i < 3; i++) {
                int maxProfit = -prices[0];
                for (int j = 1; j < days; j++) {
                    dp[i][j] = Integer.max(dp[i][j - 1], prices[j] + maxProfit);
                    maxProfit = Integer.max(-prices[j] + dp[i - 1][j], maxProfit);
                }
            }

            return Integer.max(dp[1][days - 1], dp[2][days - 1]);
        }

        public int maxProfit3(int[] prices) {
            int days = prices.length;
            if (days == 1) {
                return 0;
            }
            int[][] dp = new int[3][days];
            for (int j = 0; j < days; j++) {
                dp[0][j] = 0;
            }
            dp[1][0] = 0;
            dp[2][0] = 0;

            int maxProfit1 = -prices[0];
            int maxProfit2 = -prices[0];
            for (int j = 1; j < days; j++) {
                int max1 = dp[1][j - 1];
                int max2 = dp[2][j - 1];
                if ((prices[j] + maxProfit1) > max1) {
                    max1 = prices[j] + maxProfit1;
                }
                if ((prices[j] + maxProfit2) > max2) {
                    max2 = prices[j] + maxProfit2;
                }

                // 下一个循环会比本次循环多一个-prices[j] + dp[0][j - 1]，所以只需要将这个多的与当前最大值比较即可
                maxProfit1 = Integer.max(maxProfit1, -prices[j] + dp[0][j - 1]);
                maxProfit2 = Integer.max(maxProfit2, -prices[j] + dp[1][j - 1]);
                dp[1][j] = max1;
                dp[2][j] = max2;
            }
            return Integer.max(dp[1][days - 1], dp[2][days - 1]);
        }
    }

}