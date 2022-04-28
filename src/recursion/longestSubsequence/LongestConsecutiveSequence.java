package recursion.longestSubsequence;

import java.util.Arrays;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return 1;
        }
        int[] dp = new int[length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < length; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                dp[i] = dp[i - 1] + 1;
            } else if (nums[i] == nums[i - 1]){
                dp[i] = dp[i - 1];
            } else {
                dp[i] = 1;
            }
        }
        for (int i = 0; i < length; i++) {
            res = Math.max(dp[i], res);
        }
        return res;
    }

}
