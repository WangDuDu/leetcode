package greddy;

/**
 * Created by wangshuyang on 2021-7-24.
 * 股票买卖问题，只允许买卖一次
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (0 == length) {
            return 0;
        }

        int min = prices[0];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }

        return max;
    }
}
