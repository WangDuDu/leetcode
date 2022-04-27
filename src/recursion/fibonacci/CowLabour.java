package recursion.fibonacci;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CowLabour.java
 * @Description TODO
 * @createTime 2022年04月27日 21:33:00
 *
 * 母牛生产问题
 *
 * 假设农场中成熟的母牛每年都会生 1 头小母牛，并且永远不会死。第一年有 1 只小母牛，从第二年开始，母牛开始生小母牛。每只小母牛 3 年之后成熟又可以生小母牛。
 * 给定整数 N，求 N 年后牛的数量。
 *
 * (1)确定动态规划数组：dp[i]表示第年的牛的数量
 * (2)确定动态转移方程：dp[i] = dp[i - 1] * 2 + dp[i - 1]
 *      每年的母牛数量是去年的母牛数量 + 今年新出生的母牛数量。今年新出生的母牛数量 = 三年前的母牛总数量
 * (3)初始化
 *      dp[0] = 0 第0年没有母牛
 *      dp[1] = 1 第1年有一头母牛
 *      dp[2] = 2 第2年，第一年的母牛生了一头母牛，总共有三头
 *      dp[3] = 3 第3年，第一年的母牛又生了一头母牛，总共有三头
 * (4)优化：本题的优化点还是在于只需要保留之前三年的数据即可
 */
public class CowLabour {

    public static void main(String[] args) {
        CowLabour.Solution solution = new CowLabour().new Solution();
        System.out.println(solution.cowLabour(5));
    }

    class Solution {
        private long cowLabour(int n) {
            if (n <= 3) {
                return n;
            }

            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 3;

            for (int i = 4; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 3];
            }

            return dp[n];
        }
    }

}
