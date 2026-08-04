class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    TrieNode root = new TrieNode();

    private void insert(String word) {
        TrieNode node = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
        }

        node.word = word;
    }

    public List<String> findWords(char[][] board, String[] words) {

        for (String word : words)
            insert(word);

        List<String> ans = new ArrayList<>();

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j, root, ans);
            }
        }

        return ans;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node, List<String> ans) {

        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length)
            return;

        char ch = board[r][c];

        if (ch == '#')
            return;

        TrieNode next = node.children[ch - 'a'];

        if (next == null)
            return;

        if (next.word != null) {
            ans.add(next.word);
            next.word = null;
        }

        board[r][c] = '#';

        dfs(board, r + 1, c, next, ans);
        dfs(board, r - 1, c, next, ans);
        dfs(board, r, c + 1, next, ans);
        dfs(board, r, c - 1, next, ans);

        board[r][c] = ch;
    }
}