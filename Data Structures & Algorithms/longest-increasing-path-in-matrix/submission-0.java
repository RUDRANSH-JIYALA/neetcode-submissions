class Solution {
    int[][] matrix;
    int[][] dp;
    int rows, cols;

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        rows = matrix.length;
        cols = matrix[0].length;
        dp = new int[rows][cols];

        int ans = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                ans = Math.max(ans, dfs(r, c));
            }
        }

        return ans;
    }

    private int dfs(int r, int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        int best = 1;

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nr < rows &&
                nc >= 0 && nc < cols &&
                matrix[nr][nc] > matrix[r][c]) {

                best = Math.max(best, 1 + dfs(nr, nc));
            }
        }

        dp[r][c] = best;
        return best;
    }
}