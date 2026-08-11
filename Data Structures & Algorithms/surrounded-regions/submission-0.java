class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        Queue<int[]> q = new LinkedList<>();

        for (int r = 0; r < m; r++) {
            add(board, r, 0, q);
            add(board, r, n - 1, q);
        }

        for (int c = 0; c < n; c++) {
            add(board, 0, c, q);
            add(board, m - 1, c, q);
        }

        int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0];
            int c = cell[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n
                        && board[nr][nc] == 'O') {
                    board[nr][nc] = 'S';
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == 'S') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private void add(char[][] board, int r, int c, Queue<int[]> q) {
        if (board[r][c] == 'O') {
            board[r][c] = 'S';
            q.offer(new int[]{r, c});
        }
    }
}