package recursion.integerbreak;

public class IntegerBreak {
    public int integerBreak(int n) {
        // dp[i]表示分割整数i的最大值
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                // Math.max(dp[i - j] * j, j * (i - j))是看i-j本身大还是把i-j拆开后乘起来大(i-j>3的情况下应该都是拆开乘起来大)
                // 外层的Math.max是看按j，i-j来拆大，还是本身的dp[i]大
                dp[i] = Math.max(dp[i] ,Math.max(dp[i - j] * j, j * (i - j)));
            }
        }
        return dp[n];
    }
}
