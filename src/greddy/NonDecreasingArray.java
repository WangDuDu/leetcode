package greddy;

/**
 * Created by wangshuyang on 2021-7-25.
 */
public class NonDecreasingArray {
    public boolean checkPossibility(int[] nums) {
        if (0 == nums.length) {
            return false;
        }
        if (1 == nums.length) {
            return true;
        }

        int count = 0;
        for (int i = 0; i < nums.length - 1 && count < 2; i++) {
            if (nums[i] <= nums[i + 1]) {
                continue;
            }
            count++;
            int temp = nums[i];
            nums[i] = nums[i + 1];
            if (i > 0 && nums[i] < nums[i - 1]) {
                nums[i] = temp;
                nums[i + 1] = temp;
            }
        }
        return count < 2;
    }
}
