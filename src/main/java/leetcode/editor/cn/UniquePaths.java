package leetcode.editor.cn;

/**
 * @author wangshuyang
 *
 * (1)确定动态规划数组dp[m][n]一个二维数组，代表走到某个网格的时候的最优解
 * (2)确定状态转移方程：
 *      dp[x][y] = dp[x - 1][y] + dp[x][y + 1] (0 < x < m && 0 <= y < n - 1)
 *      dp[x][y] = 1 (x = 0 || y = n - 1)
 * (3)初始化: dp[x][y] = 1 (x = 0 || y = n - 1)
 */

public class UniquePaths {
    public static void main(String[] args) {
        Solution solution = new UniquePaths().new Solution();
        System.out.println(solution.uniquePaths(3, 7));
        System.out.println(solution.uniquePaths(3, 3));
        System.out.println(solution.uniquePaths(3, 2));
    }

    class Solution {
        public int uniquePaths(int m, int n) {
            if (m == 1 || n == 1) {
                return 1;
            }

            int[][] dp = new int[m][n];
            for (int i = 0; i < n; i++) {
                dp[0][i] =1;
            }

            for (int i = 0; i < m; i++) {
                dp[i][n - 1] = 1;
            }

            for (int i = 1; i < m ; i++) {
                for (int j = n - 2; j >= 0; j--) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j + 1];
                }
            }
            return dp[m - 1][0];
        }
    }

}