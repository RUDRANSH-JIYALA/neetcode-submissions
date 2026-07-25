class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(candidates, target, 0, path, result);
        return result;
    }

    private void backtrack(int[] candidates, int remaining, int start,
                            List<Integer> path, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] > remaining) {
                break;
            }

            path.add(candidates[i]);
            backtrack(candidates, remaining - candidates[i], i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}