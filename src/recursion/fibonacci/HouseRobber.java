package recursion.fibonacci;

/**
 * Created by wangshuyang on 2021-8-9.
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
}
