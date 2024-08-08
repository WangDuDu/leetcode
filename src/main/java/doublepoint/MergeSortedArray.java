package doublepoint;

/**
 * Created by wangshuyang on 2021-3-16.
 *
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 示例 2：
 *
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[i] <= 109
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class MergeSortedArray {
    /**
     * 解法1
     *
     * 两个指针从前往后遍历，当第二个数组的元素被归并的时候，数组一的元素的位置都要往后移动一位
     * 特别需要注意的是要将数组一被归并的元素的个数记录下来，作为数组1归并完毕跳出循环的依据
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (0 == n) {
            return;
        }
        if (0 == m) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        // num代表nums1已经归并的元素个数
        int i = 0, j = 0, num = 0;
        while (num <= m && j < n) {
            if (nums1[i] >= nums2[j]) {
                for (int k = nums1.length -1; k > i; k--) {
                    nums1[k] = nums1[k - 1];
                }
                nums1[i] = nums2[j];
                j++;
            } else {
                num++;
            }
            i++;
        }
        int a = nums1.length - 1;
        if (j < n) {
            for (int t = nums2.length - 1; t >= j ; t--) {
                nums1[a--] = nums2[t];
            }
        }
    }

    /**
     * 解法2
     *
     * 两个指针从后往前遍历，跳出循环的判断简单，也不用移动元素
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int k = m + n - 1;
        while (index1 >=0 && index2 >= 0) {
            if (nums1[index1] >= nums2[index2]) {
                nums1[k--] = nums1[index1--];
            } else {
                nums1[k--] = nums2[index2--];
            }
        }
        while (index2 >= 0) {
            nums1[k--] = nums2[index2--];
        }
    }


    public static void main(String[] args) {
        MergeSortedArray mergeSortedArray = new MergeSortedArray();
        int[] num1 = new int[]{4, 0, 0, 0, 0, 0};
        int[] num2 = new int[]{1, 2, 3, 5, 6};
        mergeSortedArray.merge2(num1, 1, num2, 5);
        for (int i = 0 ; i < num1.length; i++) {
            System.out.print(num1[i]);
        }
    }
}
