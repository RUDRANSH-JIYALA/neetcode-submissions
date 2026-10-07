class CountSquares {

    HashMap<Integer, HashMap<Integer, Integer>> map;

    public CountSquares() {
        map = new HashMap<>();
    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];

        map.putIfAbsent(y, new HashMap<>());

        HashMap<Integer, Integer> row = map.get(y);

        row.put(x, row.getOrDefault(x, 0) + 1);
    }

    public int count(int[] point) {
        int x = point[0];
        int y = point[1];

        if (!map.containsKey(y)) {
            return 0;
        }

        int ans = 0;

        HashMap<Integer, Integer> row = map.get(y);

        for (int x2 : row.keySet()) {

            if (x2 == x) {
                continue;
            }

            int side = Math.abs(x2 - x);

            int horizontalCount = row.get(x2);

            int count1 = getCount(x, y + side);
            int count2 = getCount(x2, y + side);

            ans += horizontalCount * count1 * count2;

            count1 = getCount(x, y - side);
            count2 = getCount(x2, y - side);

            ans += horizontalCount * count1 * count2;
        }

        return ans;
    }

    private int getCount(int x, int y) {
        if (!map.containsKey(y)) {
            return 0;
        }

        return map.get(y).getOrDefault(x, 0);
    }
}