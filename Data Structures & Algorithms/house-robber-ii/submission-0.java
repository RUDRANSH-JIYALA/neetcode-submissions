class Solution {
    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int option1 = robHouse(nums, 0, nums.length - 2);
        int option2 = robHouse(nums, 1, nums.length - 1);

        return Math.max(option1, option2);
    }

    private int robHouse(int[] nums, int start, int end) {

        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {

            int current = Math.max(prev1, nums[i] + prev2);

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}