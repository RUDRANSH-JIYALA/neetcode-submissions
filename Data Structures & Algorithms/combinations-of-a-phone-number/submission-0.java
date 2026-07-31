class Solution {
    private String[] map = {
        "", "", "abc", "def", "ghi", "jkl",
        "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();

        if (digits.length() == 0)
            return ans;

        backtrack(digits, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backtrack(String digits, int index, StringBuilder curr, List<String> ans) {
        if (index == digits.length()) {
            ans.add(curr.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {
            curr.append(ch);
            backtrack(digits, index + 1, curr, ans);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}