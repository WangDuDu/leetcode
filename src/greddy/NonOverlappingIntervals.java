package greddy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by wangshuyang on 2021-7-24.
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 1、首先对边界值进行处理
        if (0 == intervals.length) {
            return 0;
        }

        // 2、首先对区间按照区间结尾的大小，从小到大进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] - o2[1]) < 0 ? -1 : ((o1[1] == o2[1]) ? 0 : 1);
            }
        });
        int count = 0;
        int end = intervals[0][1];

        // 3、选择区间的时候尽量取区间结尾比较小的，这样留给后边区间选择的空间比较大
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                continue;
            }
            count++;
        }
        return count;
    }
}
