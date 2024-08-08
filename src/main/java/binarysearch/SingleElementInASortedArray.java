package binarysearch;

/**
 * Created by wangshuyang on 2021-8-1.
 */
public class SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid % 2 == 1) {
                mid--;
            }
            /**
             * nums[mid + 1]不会数组越界的原因
             * mid是由(start + end) / 2计算而来的，结合end的初始值是nums.length - 1，所以只有在start=end=nums.length - 1的时候
             * mid才会等于nums.length - 1，但是在循环的条件时start < end所以mid最大只能是nums.length - 2，所以数组不会越界
             */
            if (nums[mid] == nums[mid + 1]) {
                start = mid + 2;
            } else {
                end = mid;
            }
        }
        return nums[start];
    }
}
