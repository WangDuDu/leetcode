package dynamicprogramming.longestSubsequence;

//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。 
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//
// 
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 
// 
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 
//
// 示例 2： 
//
// 
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 
//
// 示例 3： 
//
// 
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text1.length, text2.length <= 1000 
// text1 和 text2 仅由小写英文字符组成。 
// 
// Related Topics 字符串 动态规划 👍 952 👎 0

/**
 * 1143
 *
 * (1)确定动态规划数组：dp[i][j]
 *      表示字符串1的0~i和字符串2的0~j的最长公共子序列
 * (2)确定状态转移方程：
 *      1、dp[i][j] = dp[i - 1][j - 1] + 1 (array1[i] = array2[j])
 *      2、dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) (array[i] != array[j])
 * (3)确定初试值
 *      dp[0][j] = array1[0] == array2[j] ? 1 : 0
 *      dp[i][0] = array1[i] == array2[0] ? 1 : 0
 */

public class LongestCommonSubsequence {
    public static void main(String[] args) {

        Solution solution = new LongestCommonSubsequence().new Solution();
        System.out.println(solution.longestCommonSubsequence("abcde", "ace"));
        System.out.println(solution.longestCommonSubsequence("abc", "abc"));
        System.out.println(solution.longestCommonSubsequence("abc", "def"));
    }

    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            char[] array1 = text1.toCharArray();
            char[] array2 = text2.toCharArray();
            int length1 = array1.length;
            int length2 = array2.length;

            int[][] dp = new int[length1][length2];

            dp[0][0] = array1[0] == array2[0] ? 1 : 0;
            for (int i = 1; i < length1; i++) {
                if (dp[i - 1][0] == 1) {
                    dp[i][0] = 1;
                } else {
                    dp[i][0] = array1[i] == array2[0] ? 1 : 0;
                }
            }

            for (int j = 1; j < length2; j++) {
                if (dp[0][j - 1] == 1) {
                    dp[0][j] = 1;
                } else {
                    dp[0][j] = array1[0] == array2[j] ? 1 : 0;
                }
            }

            for (int i = 1; i < length1; i++) {
                for (int j = 1; j < length2; j++) {
                    if (array1[i] == array2[j]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
                    }

                }
            }
            return dp[length1 - 1][length2 - 1];
        }
    }

}