package dynamicprogramming.fibonacci;

/**
 * 198.打家劫舍
 * Created by wangshuyang on 2021-8-9.
 * <p>
 * 1、确定动态规划的数组 dp[] dp[n]代表代表到了第n家的时候最优解
 * 2、确定动态转移方程 dp[n] = max(dp[n-1], dp[n-2] + dp[n])
 * 3、初始化 dp[0] = nums[0], dp[1] = nums[1]
 * 4、优化：主要是对存储空间的优化
 * 由于每一个最优解只与它前面两个最优解相关，所以只需要两个变量来存储最优解即可
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int pre1 = nums[0];
        int pre2 = 0;
        int max = pre1;
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = max;
        }
        return pre1;
    }

    /**
     * 未进行优化
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        if (nums.length < 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Integer.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Integer.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
