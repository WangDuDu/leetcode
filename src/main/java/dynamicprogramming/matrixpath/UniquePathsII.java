package dynamicprogramming.matrixpath;

//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 
//
// 示例 1： 
//
// 
//输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
//输出：2
//解释：3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
//
// 示例 2： 
//
// 
//输入：obstacleGrid = [[0,1],[0,0]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// m == obstacleGrid.length 
// n == obstacleGrid[i].length 
// 1 <= m, n <= 100 
// obstacleGrid[i][j] 为 0 或 1 
// 
// Related Topics 数组 动态规划 矩阵 👍 776 👎 0

/**
 *
 * 这个题有几点需要注意
 * 1、数组和网格位置的对应关系应该是下面这种，而不是想成坐标系那种左下角是(0,0)
 *  m = 4, n = 4的网格和数组表示
 *   |  0  |  1  |  2  |  3  |
 * --------------------------
 * 0 |     |     |     |     |
 * --------------------------
 * 1 |     |     |     |     |
 * --------------------------
 * 2 |     |     |     |     |
 * --------------------------
 * 3 |     |     |     |     |
 *
 * 2、这道题首先判断当前位置有没有障碍，有的话直接置位0跳出循环，否则入口和出口有障碍的情况会出现错误
 *
 * (1)确定动态规划数组dp[m][n]一个二维数组，代表走到某个网格的时候的最优解
 * (2)确定状态转移方程：
 *      dp[x][y] = dp[x - 1][y] + dp[x][y - 1] (0 < x < m && 0 < y < n)
 *      dp[x][y] = dp[x - 1][y] (y = 0)
 *      dp[x][y] = dp[x][y - 1] (x = 0)
 *      dp[x][y] = 0 (obstacleGrid[x][y] = 1)
 *      dp[x][y] = 1 (x = 0 && y = 0)
 * (3)初始化：初始化需要将有障碍的网格的最优解初始化为0， 然后将起点的值初始化为1
 *      dp[x][y] = 0 (obstacleGrid[x][y] = 1)
 *      dp[x][y] = 1 (x = 0 && y = 0)
 */

public class UniquePathsII {
    public static void main(String[] args) {
        Solution solution = new UniquePathsII().new Solution();
        int[][] test1 = new int[][]{{0,0,0},{0,1,0},{0,0,0}};
        int[][] test2 = new int[][]{{0,1},{0,0}};
        System.out.println(solution.uniquePathsWithObstacles(test1));
    }

    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (obstacleGrid[i][j] == 1) {
                        dp[i][j] = 0;
                        continue;
                    }
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                        continue;
                    }
                    if (i == 0) {
                        dp[i][j] = dp[i][j - 1];
                        continue;
                    }
                    if (j == 0) {
                        dp[i][j] = dp[i - 1][j];
                        continue;
                    }
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m - 1][n - 1];
        }
    }

}