package leetcode.editor.cn;

//给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。 
//
// 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。 
//
// 返回 你能获得的 最大 利润 。 
//
// 
//
// 示例 1： 
//
// 
//输入：prices = [7,1,5,3,6,4]
//输出：7
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
//     总利润为 4 + 3 = 7 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
//     总利润为 4 。 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 3 * 10⁴ 
// 0 <= prices[i] <= 10⁴ 
// 
// Related Topics 贪心 数组 动态规划 👍 1671 👎 0

/**
 * 今天不持股：(1)昨天持股的情况下，那么今天卖出了股票，今天的累积利润就是昨天的持有的利润+今天的股票价格
 *           (2)昨天没有持股的情况下，今天要么没有操作，要么就是今天买入又卖出，两种情况下今天持有的利润=昨天持有的利润
 * 今天持股：(1)昨天持股的情况下，由于规定任何时候只能持有一股，那么今天持有的利润 = 昨天持有的利润
 *         (2)昨天没有持股的情况下，今天持股就是今天购买了股票，和只允许一次买卖不同，本题之前可能买卖了股票，所以是有利润的，所有今天持有的利润等于昨天持有的利润-今天的股价
 *
 *
 * (1)确定动态规划数组：dp[i][j]表示持有的利润
 *      i = 0 表示不持股 i = 1 表示持股
 *      j 表示第j天
 * (2)确定状态转移方程：
 *      今天不持股的情况：dp[0][j] = max(dp[0][j - 1], dp[1][j - 1] + prices[j])
 *      今天持股的情况：dp[1][j] = max(dp[0][j - 1] - prices[j], dp[1][j - 1])
 * (3)初始化：
 *      dp[0][0] = 0
 *      dp[1][0] = -price[0]
 * (4)优化：
 *      存储空间上只需要保留前一天的数据即可
 */


public class BestTimeToBuyAndSellStockII {
    public static void main(String[] args) {

        Solution solution = new BestTimeToBuyAndSellStockII().new Solution();
        System.out.println(solution.maxProfit2(new int[]{7,1,5,3,6,4}));
        System.out.println(solution.maxProfit2(new int[]{7,6,4,3,1}));
        System.out.println(solution.maxProfit2(new int[]{1,2,3,4,5}));
    }

    class Solution {
        public int maxProfit(int[] prices) {
            int days = prices.length;
            if (days == 1) {
                return 0;
            }
            int[][] dp = new int[2][days];
            dp[0][0] = 0;
            dp[1][0] = -prices[0];
            for (int j = 1; j < days; j++) {
                dp[0][j] = Integer.max(dp[0][j - 1], dp[1][j - 1] + prices[j]);
                dp[1][j] = Integer.max(dp[0][j - 1] - prices[j], dp[1][j - 1]);
            }
            return Integer.max(dp[0][days - 1], dp[1][days - 1]);
        }

        public int maxProfit2(int[] prices) {
            int days = prices.length;
            if (days == 1) {
                return 0;
            }
            int tomorrow0 = 0;
            int tomorrow1 = -prices[0];
            for (int j = 1; j < days; j++) {
                int today0 = Integer.max(tomorrow0, tomorrow1 + prices[j]);
                int today1 = Integer.max(tomorrow0 - prices[j], tomorrow1);
                tomorrow0 = today0;
                tomorrow1 = today1;
            }

            return Integer.max(tomorrow0, tomorrow1);
        }
    }

}