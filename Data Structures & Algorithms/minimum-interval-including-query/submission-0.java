class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int[][] sortedQueries = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] result = new int[queries.length];
        int i = 0;

        for (int[] q : sortedQueries) {
            int x = q[0];

            while (i < intervals.length && intervals[i][0] <= x) {
                int left = intervals[i][0];
                int right = intervals[i][1];
                int length = right - left + 1;

                pq.offer(new int[]{length, right});
                i++;
            }

            while (!pq.isEmpty() && pq.peek()[1] < x) {
                pq.poll();
            }

            result[q[1]] = pq.isEmpty() ? -1 : pq.peek()[0];
        }

        return result;
    }
}