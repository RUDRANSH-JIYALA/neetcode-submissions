class Twitter {

    private int time;
    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, List<int[]>> tweets;

    public Twitter() {
        time = 0;
        followMap = new HashMap<>();
        tweets = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, k -> new ArrayList<>())
              .add(new int[]{time++, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {
        followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int user : followMap.get(userId)) {
            List<int[]> list = tweets.get(user);
            if (list == null || list.isEmpty()) continue;
            int idx = list.size() - 1;
            int[] tweet = list.get(idx);
            pq.offer(new int[]{tweet[0], tweet[1], user, idx});
        }

        List<Integer> res = new ArrayList<>();

        while (!pq.isEmpty() && res.size() < 10) {
            int[] cur = pq.poll();
            res.add(cur[1]);

            int user = cur[2];
            int idx = cur[3] - 1;

            if (idx >= 0) {
                int[] tweet = tweets.get(user).get(idx);
                pq.offer(new int[]{tweet[0], tweet[1], user, idx});
            }
        }

        return res;
    }

    public void follow(int followerId, int followeeId) {
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> set = followMap.get(followerId);
        if (set != null) {
            set.remove(followeeId);
        }
    }
}