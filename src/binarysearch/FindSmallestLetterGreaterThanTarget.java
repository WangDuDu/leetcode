package binarysearch;

/**
 *
 * Created by wangshuyang on 2021-7-26.
 */
public class FindSmallestLetterGreaterThanTarget {
    public static char nextGreatestLetter(char[] letters, char target) {
        if (letters.length == 1) {
            return letters[0];
        }

        int start = 0;
        int end = letters.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target >= letters[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        /**
         * 这里需要明确以下几点
         * 1、start > end
         * 2、如果start<letters.length 那么一定有letters[start] > target。二分法查找的最后其实就是在两个数（如a[1],a[2],a[3]）或三个数（如a[2],a[3]）中找答案
         * a[1],a[2],a[3]的情况
         * 如果最后位置start=end=1 && a[1]>target那么 end = (start + end)/2 - 1 = start - 1 = 0, 此时(a[start] = a[1]) > target
         * 如果最后位置start=end=1 && a[1]<target那么 start = (start + end)/2 + 1 = end + 1 = 2, 此时(a[start] = a[2]) > target，这是因为只有(a[2] = a[mid]) > target的时候，最后位置才会在start=end=1
         * 如果最后位置start=end=3 && a[3]>target那么 end = (start + end)/2 - 1 = start - 1 = 2, 此时(a[start] = a[3]) > target
         * 如果最后位置start=end=3 && a[3]<target那么 start = (start + end)/2 + 1 = end + 1 = 4, 此时(a[start] = a[3]) > target，这是因为最后圈定target一定在区间[a[1],a[3]]内，a[4]一定大于target
         *
         * 如a[2],a[3]的情况
         * 若最终位置start=end=2 && a[2]>target那么 end = (start + end)/2 - 1 = start - 1 = 1, 此时(a[start] = a[2]) > target, start>end循环结束
         * 若最终位置start=end=2 && a[2]<target那么 start = (start + end)/2 + 1 = start + 1 = 3, 此时start=end=3，start=end不存在这种最终位置，转移到下一步进行判断
         * 若最终位置start=end=3 && a[3]>target那么 end = (start + end)/2 - 1 = start - 1 = 2，此时(a[start] = a[2]) > target
         * 若最终位置start=end=3 && a[3]<target那么 start = (start + end)/2 + 1 = start + 1 = 4，此时(a[start] = a[4]) > target,这是因为最后圈定target一定在区间[a[2],a[3]]内，a[4]一定大于target
         * */
        return start < letters.length ? letters[start] : letters[0];
    }
}
