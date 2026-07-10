class Solution {
    public int dfs(TreeNode node, int mx) {
        if (node == null) return 0;

        int count = 0;
        if (node.val >= mx) count = 1;

        mx = Math.max(mx, node.val);

        count += dfs(node.left, mx);
        count += dfs(node.right, mx);

        return count;
    }

    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }
}