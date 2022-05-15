package dynamicprogramming.zeroonebackpack;

//给你一个二进制字符串数组 strs 和两个整数 m 和 n 。 
//
// 
// 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。 
//
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
//输出：4
//解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
//其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 
//n 的值 3 。
// 
//
// 示例 2： 
//
// 
//输入：strs = ["10", "0", "1"], m = 1, n = 1
//输出：2
//解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 600 
// 1 <= strs[i].length <= 100 
// strs[i] 仅由 '0' 和 '1' 组成 
// 1 <= m, n <= 100 
// 
// Related Topics 数组 字符串 动态规划 👍 714 👎 0

/**
 * 474
 *
 * 1、在正常情况下我们会考虑吧用三维数组dp[i][j][k]，k表示遍历到第k个String那么状态转移方程就是
 * dp[i][j][k] = Integer.max(dp[i][j][k - 1], dp[i - zeros][j - ones][k - 1] + 1);
 * 我们可以看到，当前的最优解只与遍历到上一个元素(k - 1)的最优解有关，所以我们没有必要将之前的所有解存储下来，只需要存储上一个元素的最优解就好了，
 * 所以k这个维度就不需要了，三维数组就编程了二维数组。
 * 2、我们在dp[i][j]的时候由于i - zeros和i - ones的值是可能小于0的，所以我们可以i从m开始，j从n开始处理，只处理zeros<=i<=m,ones<=j<=n的情况
 * 当j<zeros，j<ones的时候当前解不能放入子集中，dp[i][j]还是等于上次个字符串的dp[i][j]，所以可以不进行处理
 * 3、最开始的dp[i][j]全部默认为0，如果是三维数组的话，可以认为初始化dp[i][j][-1] = 0(0<=i<=m, <=0j<=n)，这里降维处理后dp[i][j] = 0(0<=i<=m, <=0j<=n)
 * 这也是降维的原因，使得初始化变得简单，不然的话，我们还需要初始化dp[i][j][1]的值，很头疼
 *
 *
 * (1)确定动态规划数组dp[i][j]表示子集做多有i个0和j个1的最优解
 * (2)确定状态转移方程：
 *      遍历strs，当前遍历到的字符串为str，str中0的个数为zeros，1的个数为ones
 *      该字符串放入子集：dp[i][j] = dp[i - zeros][j - ones] + 1
 *      该字符串不放入子集：dp[i][j] = dp[i][j]
 * (3)初始化：dp[0][0] = 0
 */

public class OnesAndZeroes {
    public static void main(String[] args) {

        Solution solution = new OnesAndZeroes().new Solution();
        System.out.println(solution.findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
        System.out.println(solution.findMaxForm(new String[]{"10", "0", "1"}, 1, 1));
    }

    class Solution {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][] dp = new int[m + 1][n + 1];

            dp[0][0] = 0;
            for (String str : strs) {
                int zeros = 0, ones = 0;
                for (char c : str.toCharArray()) {
                    if (c == '0') {
                        zeros++;
                    } else if (c == '1') {
                        ones++;
                    }
                }
                for (int i = m; i >= zeros; i--) {
                    for (int j = n; j >= ones; j--) {
                        dp[i][j] = Integer.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                    }
                }
            }
            return dp[m][n];
        }
    }

}