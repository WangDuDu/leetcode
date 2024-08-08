package search.bfs;

import base.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wangshuyang on 2021-8-3.
 */
public class ShortestPathInBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        // 创建一个队列来存储准备遍历节点，遍历完过的会出队
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(0, 0));

        // 二维数组表示八个方向，例如{1,1}表示当前的横坐标x和纵坐标y都+1，就到达了右上的位置
        int[][] directions = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

        int pathLength = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 代表现在遍历到第几层，这一层可能没有任何可达节点的

            pathLength++;
            // 每次循环把遍历上一层时加入的节点遍历完，外层循环代表遍历的层数
            while (size-- > 0) {
                // 首先拿到遍历到的当前节点的坐标(cx,cy)
                Pair<Integer, Integer> cur = queue.poll();
                Integer cx = cur.getLeft();
                Integer cy = cur.getRight();
                // 节点如果不可达的话直接遍历栈中的下一个节点
                if (grid[cx][cy] == 1) {
                    continue;
                }
                if (cx == grid.length - 1 && cy == grid[0].length - 1) {
                    return pathLength;
                }
                grid[cx][cy] = 1;
                for (int[] direction : directions) {
                    Integer nx = cx + direction[0];
                    Integer ny = cy + direction[1];
                    if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length) {
                        continue;
                    }
                    Pair<Integer, Integer> pair = new Pair<>(nx, ny);
                    queue.add(pair);
                }
            }
        }
        return -1;
    }
}
