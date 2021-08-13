package recursion.arrayrange;

/**
 * Created by wangshuyang on 2021-8-13.
 */
public class ArithmeticSlices {
    public int numberOfArithmeticSlices(int[] nums) {
        int length = nums.length;
        if (length < 3) {
            return 0;
        }
        int[] dp = new int[length];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < length; i++) {
            if ((nums[i] - nums[i - 1]) == (nums[i - 1] - nums[i - 2])) {
                // 在以上一个数结尾的所有等差递增字数列加上nums[i]还是等差递增子数列
                // 在以上一个数结尾的所有等差递增字数列数量的基础上再加一个nums[i - 2], nums[i - 1], nums[i]的等差递增字数列
                dp[i] = dp[i - 1] + 1;
            }
        }
        int total = 0;
        for (int count : dp) {
            total = total + count;
        }
        return total;
    }
}
