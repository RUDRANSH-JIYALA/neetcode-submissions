class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(int[] nums, int target, int index, List<Integer> curr, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        if (index == nums.length || target < 0) {
            return;
        }

        curr.add(nums[index]);
        backtrack(nums, target - nums[index], index, curr, ans);
        curr.remove(curr.size() - 1);

        backtrack(nums, target, index + 1, curr, ans);
    }
}