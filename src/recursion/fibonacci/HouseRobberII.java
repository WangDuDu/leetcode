package recursion.fibonacci;

/**
 * Created by wangshuyang on 2021-8-9.
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
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
}
