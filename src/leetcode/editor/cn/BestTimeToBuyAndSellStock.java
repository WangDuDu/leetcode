package leetcode.editor.cn;

//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁴ 
// 
// Related Topics 数组 动态规划 👍 2299 👎 0

/**
 * 在某一天的状态有两种，持有股票或者没有持有股票
 * 当持有股票的时候，有两种情况，(1)前一天就持有，今天没有卖出，那么和昨天持有的利润是一样的
 *                          (2)前一天没有持有，今天买入，由于只能买卖一次，那么今天就是第一次买入，之前的利润技术0，买入之后的利润就是0-当前价格
 * 当没有持有股票的时候，也有两种情况，(1)前一天没有持有，和昨天没有持有股票的利润是一样的
 *                               (2)前一天持有，那么今天就是卖出了，那么利润就是昨天持有的利润+今天卖出的价格(就是今天的股票价格)
 * 最后一天持有股票和没有持有股票的利润最大值就是最终的最大值
 *
 * 这种解法是怎么保证只有一次买卖的？
 * 如果在卖出后，再一次进行了买入操作，也就是持有
 *
 *
 *
 *
 * (1)确定动态规划数组：dp[i][j]表示持有的利润
 *      i = 0 表示不持股 i = 1 表示持股
 *      j 表示第j天
 * (2)确定状态转移方程：
 *      今天不持股的情况：dp[0][j] = max(dp[0][j - 1], dp[1][j - 1] + price[j])
 *      今天持股的情况：dp[1][j] = max(-price[j], dp[1][j - 1])
 * (3)初始化：
 *      dp[0][0] = 0
 *      dp[1][0] = -price[0]
 * (4)优化：
 *      存储空间上只需要保留前一天的数据即可
 */


public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {

        Solution solution = new BestTimeToBuyAndSellStock().new Solution();
        System.out.println(solution.maxProfit2(new int[]{7,1,5,3,6,4}));
        System.out.println(solution.maxProfit2(new int[]{7,6,4,3,1}));
    }

    class Solution {
        public int maxProfit(int[] prices) {
            if (prices.length == 0) {
                return 0;
            }
            int[][] dp = new int[2][prices.length];
            dp[0][0] = 0;
            dp[1][0] = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                dp[0][j] = Integer.max(dp[0][j - 1], dp[1][j - 1] + prices[j]);
                dp[1][j] = Integer.max(-prices[j], dp[1][j - 1]);
            }
            return Integer.max(dp[0][prices.length - 1], dp[1][prices.length - 1]);
        }

        public int maxProfit2(int[] prices) {
            if (prices.length == 0) {
                return 0;
            }
            int tomorrow0 = 0;
            int tomorrow1 = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                int today0 = Integer.max(tomorrow0, tomorrow1 + prices[j]);
                int today1 = Integer.max(-prices[j], tomorrow1);
                tomorrow0 = today0;
                tomorrow1 = today1;
            }
            return Integer.max(tomorrow0, tomorrow1);
        }
    }

}