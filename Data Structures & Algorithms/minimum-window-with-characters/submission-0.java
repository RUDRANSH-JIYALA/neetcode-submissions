class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        HashMap<Character, Integer> countT = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (char c : t.toCharArray()) {
            countT.put(c, countT.getOrDefault(c, 0) + 1);
        }

        int have = 0;
        int need = countT.size();

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            window.put(c, window.getOrDefault(c, 0) + 1);

            if (countT.containsKey(c) &&
                window.get(c).intValue() == countT.get(c).intValue()) {
                have++;
            }

            while (have == need) {
                if ((right - left + 1) < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);

                if (countT.containsKey(leftChar) &&
                    window.get(leftChar) < countT.get(leftChar)) {
                    have--;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(start, start + minLen);
    }
}