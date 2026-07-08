class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        
        if (root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();   // number of nodes at current level
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();   // remove front node
                level.add(node.val);

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            ans.add(level);
        }

        return ans;
    }
}