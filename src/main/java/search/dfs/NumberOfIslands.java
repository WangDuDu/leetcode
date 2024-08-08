package search.dfs;

/**
 * 200. 岛屿数量
 * 矩阵中的连通分量数目
 */
public class NumberOfIslands {

    int[][] orientations = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public int numIslands(char[][] grid) {
        int xLength = grid.length;
        int yLength = grid[0].length;
        int res = 0;

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int x, int y) {
        grid[x][y] = '0';
        for (int i = 0; i < 4; i++) {
            int xPos = x + orientations[i][0];
            int yPos = y + orientations[i][1];
            if (xPos >= 0 && yPos >= 0 && xPos < grid.length && yPos < grid[0].length && grid[xPos][yPos] == '1') {
                dfs(grid, xPos, yPos);
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(new NumberOfIslands().numIslands(grid));
    }
}
