package sort;

import java.util.PriorityQueue;

/**
 * Created by wangshuyang on 2021-3-30.
 *
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 *
 */
public class KthElement {
    public int findKthLargest(int[] nums, int k) {
        return quickPick(nums, k);
    }

    /**
     * 快速选择的方法找第K大的元素
     * @param nums
     * @param k
     * @return
     */
    private int quickPick(int[] nums, int k) {
        // 这里是寻找第K个最大的元素，这里转换成第K个 最大的元素在从小到大排序的数组中的下标
        int target = nums.length - k;
        int start = 0;
        int end = nums.length - 1;
        // strat和end的每次变化都是以1为单位的，所以这里的start<end等价于start!=end。当start=end的时候说明只有一个元素了，
        // 肯定就是它了不需要再进行partition操作了
        while (start < end) {
            int partitionResult = partition(nums, start, end);
            if (partitionResult == target) {
                return nums[partitionResult];
            } else if (partitionResult < target) {
                start = partitionResult + 1;
            } else {
                end = partitionResult - 1;
            }
        }
        return nums[end];
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = start;
        int left = start;
        int right = end + 1;
        while (true) {
            while (nums[++left] < nums[pivot] && left < end);
            while (nums[--right] > nums[pivot] && right > start);
            if (left < right) {
                swap(nums, left, right);
                continue;
            }
            swap(nums, start, right);
            return right;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    /**
     * 最小堆寻找第K大的元素
     * @param nums
     * @param k
     * @return
     */
    private int heapPick(int[] nums, int k) {
        int target = nums.length - k;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            priorityQueue.add(num);
            // 当队列中的元素大于k个的时候，队列中最小的元素被弹出，队列中始终存的最大k个元素
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        // 将队列中k个元素里面最小的那个取出来就是第k个最大的
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        int[] array = new int[]{3,2,1,5,6,4};
        new KthElement().findKthLargest(array, 2);
    }
}
