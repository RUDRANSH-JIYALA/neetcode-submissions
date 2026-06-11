class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;

        int[][] cars = new int[n][2];

        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        Arrays.sort(cars, (a, b) -> b[0] - a[0]);

        Stack<Double> stack = new Stack<>();

        for (int[] car : cars) {
            double time = (double)(target - car[0]) / car[1];

            stack.push(time);

            if (stack.size() >= 2 &&
                stack.peek() <= stack.get(stack.size() - 2)) {
                stack.pop();
            }
        }

        return stack.size();
    }
}