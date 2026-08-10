class Solution {
    int rows, cols;
    int[][] heights;
    boolean[][] pacific;
    boolean[][] atlantic;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        rows = heights.length;
        cols = heights[0].length;

        pacific = new boolean[rows][cols];
        atlantic = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            dfs(r, 0, pacific);
            dfs(r, cols - 1, atlantic);
        }

        for (int c = 0; c < cols; c++) {
            dfs(0, c, pacific);
            dfs(rows - 1, c, atlantic);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    void dfs(int r, int c, boolean[][] ocean) {
        if (ocean[r][c]) return;

        ocean[r][c] = true;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) {
                continue;
            }

            if (heights[nr][nc] < heights[r][c]) {
                continue;
            }

            dfs(nr, nc, ocean);
        }
    }
}