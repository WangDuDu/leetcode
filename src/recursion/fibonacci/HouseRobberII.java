package recursion.fibonacci;

/**
 * Created by wangshuyang on 2021-8-9.
 * 213
 * 本题与HouseRobberI不同的是房子是环形的，也就是说第一个房子和最后一个房子不能同时偷，所以只有三种情况
 * （1）偷第一所不偷最后一所
 * （2）偷最后一所不偷第一所
 * （3）最后一所和最后一所都不偷
 * 三种情况中最后一种情况相当于去掉了两座房子是不划算的，所以只考虑前两种情况
 *
 * 1、确定动态规划的数组 dp[] dp[n]代表代表到了第n家的时候最优解
 * 2、确定动态转移方程 dp[n] = max(dp[n-1], dp[n-2] + dp[n])
 * 3、初始化 dp[0] = nums[0], dp[1] = nums[1]
 * 4、优化：主要是对存储空间的优化
 *    由于每一个最优解只与它前面两个最优解相关，所以只需要两个变量来存储最优解即可
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(rob2(nums, 0, n - 2), rob2(nums, 1, n - 1));
    }

    public int rob(int[] nums, int start, int end) {
        int pre1 = nums[start];
        int pre2 = 0;
        int max = pre1;
        for (int i = start + 1; i <= end; i++) {
            max = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = max;
        }
        return max;
    }

    /**
     * 未优化存储空间
     * @param nums
     * @param start
     * @param end
     * @return [1,3,1,3,100]
     */
    public int rob2(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Integer.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Integer.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,1,3,100};
        System.out.println(new HouseRobberII().rob(nums));
    }
}
