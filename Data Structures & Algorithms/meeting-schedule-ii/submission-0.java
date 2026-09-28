class Solution {
    public int minMeetingRooms(List<Main.Interval> intervals) {
        if (intervals.size() == 0) {
            return 0;
        }

        intervals.sort((a, b) -> a.start - b.start);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(intervals.get(0).end);

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start >= pq.peek()) {
                pq.poll();
            }

            pq.offer(intervals.get(i).end);
        }

        return pq.size();
    }
}