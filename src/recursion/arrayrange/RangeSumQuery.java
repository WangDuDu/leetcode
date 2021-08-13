package recursion.arrayrange;

/**
 * Created by wangshuyang on 2021-8-13.
 */
public class RangeSumQuery {
    private int[] sum;
    public RangeSumQuery(int[] nums) {
        int length = nums.length;
        sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) {
            return sum[right];
        } else {
            return sum[right] - sum[left - 1];
        }
    }
}
