package binarysearch;

/**
 * Created by wangshuyang on 2021-8-1.
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findFirst(nums, target + 1) - 1;
        if (first <= last && last < nums.length && nums[first] == target && nums[last] == target) {
            return new int[]{first, last};
        } else {
            return new int[]{-1, -1};
        }
    }

    public int findFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
