class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        int index = 0;

        for (int right = 0; right < nums.length; right++) {

            while (!deque.isEmpty() &&
                   nums[deque.peekLast()] < nums[right]) {
                deque.pollLast();
            }

            deque.offerLast(right);

            if (deque.peekFirst() < right - k + 1) {
                deque.pollFirst();
            }

            if (right >= k - 1) {
                result[index++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }
}