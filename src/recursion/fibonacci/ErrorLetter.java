package recursion.fibonacci;

import leetcode.editor.cn.BestTimeToBuyAndSellStockIv;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ErrorLetter.java
 * @Description TODO
 * @createTime 2022年04月26日 22:45:00
 *
 * (1)确定动态规划数组：dp[i]
 *      表示有i对信封和新全部装错所有可能的情况
 * (2)确定状态转移方程：dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2])
 *      假设一共有i对信封和信，把第m封信，放在错误的信封n，那么n可以是除了m之外的任意一个信封，n的取值有i - 1种
 *      (1)假设第n封信正好放在信封m，这样相当于m和n互相换了信，n的位置已经确定，剩下的i - 2封信找错误的位置，
 *         剩下的i - 2对信封和信全部错位的情况是dp[i - 2]。m可能的位置有i - 1种，每一种都对应有dp[i - 2]种情况，
 *         所以dp[i] = (i - 1) * dp[i - 2]
 *      (2)假设第n封信没有放在信封m，n的位置不确定，此时剩下的i - 1封信找错误的位置
 *         剩下的i - 1对信封和信全部错位的情况是dp[i - 1]。m可能的位置有i - 1种，每一种都对应有dp[i - 1]种情况，
 *         所以dp[i] = (i - 1) * dp[i - 1]
 * (3)初始化：
 *      dp[0] = 0
 *      dp[1] = 0
 *      dp[2] = 1
 * (4)优化：
 *      根据状态转移方程，我们只需保留前两个值即可，当n = 0的之后直接return 0
 */
public class ErrorLetter {
    public static void main(String[] args) {
        ErrorLetter.Solution solution = new ErrorLetter().new Solution();
        System.out.println(solution.errorletter(10));
        System.out.println(solution.errorletter2(10));
    }

    class Solution {
        public int errorletter(int n) {
            int[] dp = new int[n];
            dp[0] = 0;
            dp[1] = 0;
            dp[2] = 1;
            for (int i = 3; i < n; i++) {
                dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
            }
            return dp[n - 1];
        }


        public int errorletter2(int n) {
            if (n == 0 || n == 1) {
                return 0;
            }

            if (n == 2) {
                return 1;
            }
            int pre1 = 0;
            int pre2 = 1;
            int result = 0;
            for (int i = 3; i < n; i++) {
                result = (i - 1) * (pre1 + pre2);
                pre1 = pre2;
                pre2 = result;
            }
            return result;
        }

    }
}
