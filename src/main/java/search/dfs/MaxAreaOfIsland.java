package search.dfs;

/**
 * Created by wangshuyang on 2021-8-5.
 */
public class MaxAreaOfIsland {
    private int m = 0;
    private int n = 0;
    int[][] directions = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        m = grid.length;
        n = grid[0].length;
        int maxAreaSize = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxAreaSize = Math.max(maxAreaSize, dfs(grid, i, j));
            }
        }
        return maxAreaSize;
    }

    public int dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
            return 0;
        }
        grid[x][y] = 0;
        int areaSize = 1;
        for (int[] direction : directions) {
            areaSize = areaSize + dfs(grid, x + direction[0], y + direction[1]);
        }
        return areaSize;
    }
}
