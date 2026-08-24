class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int oneStepBefore = 1; 
        int twoStepsBefore = 2;

        for (int i = 3; i <= n; i++) {
            int current = oneStepBefore + twoStepsBefore;

            oneStepBefore = twoStepsBefore;
            twoStepsBefore = current;
        }

        return twoStepsBefore;
    }
}