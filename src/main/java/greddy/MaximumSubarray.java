package greddy;

/**
 * Created by wangshuyang on 2021-7-25.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int preSum = nums[0];
        int maxsum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum = preSum > 0 ? preSum + nums[i] : nums[i];
            maxsum = Integer.max(preSum, maxsum);
        }
        return maxsum;
    }
}
