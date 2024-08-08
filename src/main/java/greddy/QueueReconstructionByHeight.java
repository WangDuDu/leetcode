package greddy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wangshuyang on 2021-7-24.
 */
public class QueueReconstructionByHeight {
    public static int[][] reconstructQueue(int[][] people) {
        if (0 == people.length) {
            return new int[0][0];
        }

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] > o2[0]) ? -1 : ((o1[0] == o2[0]) ? ((o1[1] < o2[1]) ? -1 : 1) : 1);
            }
        });

        List<int[]> queue = new ArrayList<>();
        for (int[] p : people) {
            queue.add(p[1], p);
        }

        return queue.toArray(new int[queue.size()][]);
    }

    public static void main(String[] args) {
        int people[][] = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println(reconstructQueue(people).length);
    }
}
