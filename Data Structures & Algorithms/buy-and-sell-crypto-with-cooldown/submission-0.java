class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int hold = -prices[0];
        int sold = 0;
        int rest = 0;

        for (int i = 1; i < prices.length; i++) {
            int previousHold = hold;
            int previousSold = sold;
            int previousRest = rest;

            hold = Math.max(previousHold, previousRest - prices[i]);

            sold = previousHold + prices[i];

            rest = Math.max(previousRest, previousSold);
        }

        return Math.max(sold, rest);
    }
}