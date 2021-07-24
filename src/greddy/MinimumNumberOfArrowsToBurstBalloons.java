package greddy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by wangshuyang on 2021-7-24.
 */
public class MinimumNumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        // 1、首先对边界值进行处理
        if (0 == points.length) {
            return 0;
        }

        // 2、首先对区间按照区间结尾的大小，从小到大进行排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] < o2[1]) ? -1 : (o1[1] == o2[1] ? 0 : 1);
            }
        });

        int count = 1;
        int end = points[0][1];
        // 3、将有重叠的区间选出来，有重叠的区间算一支箭
        for (int i = 1; i < points.length; i++) {
            // 代表有重叠
            if (points[i][0] <= end) {
                continue;
            }
            count++;
            end = points[i][1];
        }

        return count;
    }
}
