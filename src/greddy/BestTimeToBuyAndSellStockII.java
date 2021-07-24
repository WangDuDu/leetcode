package greddy;

/**
 * Created by wangshuyang on 2021-7-24.
 */
public class BestTimeToBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (0 == length || 1 == length) {
            return 0;
        }
        int max = 0;
        for (int i = 1; i < length; i++) {
            max += Integer.max(0, prices[i] - prices[i - 1]);
        }
        return max;
    }
}
