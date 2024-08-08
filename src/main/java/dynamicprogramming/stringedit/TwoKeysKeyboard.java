package dynamicprogramming.stringedit;

//最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作： 
//
// 
// Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。 
// Paste（粘贴）：粘贴 上一次 复制的字符。 
// 
//
// 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。 
//
// 
//
// 示例 1： 
//
// 
//输入：3
//输出：3
//解释：
//最初, 只有一个字符 'A'。
//第 1 步, 使用 Copy All 操作。
//第 2 步, 使用 Paste 操作来获得 'AA'。
//第 3 步, 使用 Paste 操作来获得 'AAA'。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1000 
// 
// Related Topics 数学 动态规划 👍 460 👎 0

/**
 * 650. 只有两个键的键盘
 *
 * (1)确定动态规划数组：
 * dp[i]表示打印出i个A的最少次数
 * (2)确定状态转移方程
 * dp[i] = Integer.min(dp[j] + i / j) (i % j == 0)
 * 要得带i个A，我们首先要得到j个A（其中i个A被平分成 i / j 份，每份有j个A。i/j必须是正数，也就是说j必须可以整除i）
 * 然后复制j个A，再粘贴 i / j - 1 次，粘贴+复制一共 i / j 次。同时得到j个i的最少次数为dp[j]
 * 最后我们要遍历，遍历所有可能的j求出 dp[j] + i / j，
 * 由此我们可以得出状态转移方程
 * dp[i] = Integer.min(dp[j] + i / j) (i % j == 0)
 * (3) 初始化
 * dp[1] = 0
 * (4) 优化
 * 正常情况下j的取值为 [1, i)，实际上当取值到j的时候，j对应的另外一个i的因数是 i / j，此时min(j, i/j) <= 根号i，
 * 所以j的取值可以变为 [1, 根号i]，然后同时计算j = j 和 j = i / j即可
 */
public class TwoKeysKeyboard {
    public static void main(String[] args) {

        Solution solution = new TwoKeysKeyboard().new Solution();
        System.out.println(solution.minSteps(9));
        System.out.println(solution.minSteps(1));
    }

    class Solution {
        public int minSteps(int n) {
            int[] dp =new int[n + 1];

            for (int i = 2; i <= n; i++) {
                dp[i] = i;
                for (int j = 1; j * j <= i; j++) {
                    if (i % j == 0) {
                        dp[i] = Integer.min(dp[i], dp[j] + i / j);
                        dp[i] = Integer.min(dp[i], dp[i / j] + j);
                    }
                }
                // 下边这种循环方式，当i % j 不为0的时候会终止循环，后边的遍历不到了
//                for (int j = 1; j * j <= i && i % j == 0; j++) {
//                    dp[i] = Integer.min(dp[i], dp[j] + i / j);
//                    dp[i] = Integer.min(dp[i], dp[i / j] + j);
//                }
            }
            return dp[n];
        }
    }

}