package greddy;

/**
 * Created by wangshuyang on 2021-7-25.
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        char[] sChars = s.toCharArray();
        int index = -1;
        for (char c : sChars) {
            index = t.indexOf(c, index + 1);
            if (index == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/is-subsequence/solution/pan-duan-zi-xu-lie-by-leetcode-solution/
     * 动态规划解法时间复杂度是O(n)
     * 状态转移方程
     * f[i][j] = i            t[i]=j
     * ​f[i][j] = f[i+1][j]    t[i]!=j
     * 假定下标从 00 开始，那么 f[i][j]f[i][j] 中有 0 <= i <= m-1，对于边界状态 f[m-1][..]，我们置 f[m][..] 为 mm，
     * 让 f[m-1][..]f正常进行转移。这样如果 f[i][j]=m，则表示从位置i开始往后不存在字符j。
     * 这样，我们可以利用f数组，每次 O(1) 地跳转到下一个位置，直到位置变为 m 或 s 中的每一个字符都匹配成功。
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequenceDynamicProgramming(String s, String t) {
        int n = s.length(), m = t.length();

        // 1、首先对f[m][..]进行初始化
        int[][] f = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }
        // 2、对状态转移方程的所有值进行初始化
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a')
                    f[i][j] = i;
                else
                    f[i][j] = f[i + 1][j];
            }
        }
        // 3、遍历子数组，进行判断
        int add = 0;
        for (int i = 0; i < n; i++) {
            if (f[add][s.charAt(i) - 'a'] == m) {
                return false;
            }
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }
}
