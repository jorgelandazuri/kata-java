package com.jalch.kata.algorithm.lang;

public class MaxProfitToTradeAStock {

    // Given an array, prices, where prices[i] represent the price of a stock on the
    // i-th day,
    // maximize profit by selecting a single day to buy the stock and a different
    // day in the
    // future to sell it.
    // Return the maximum profit that can be achieved from this transaction. If no
    // profit can
    // be made, return 0.

    // Constraints
    // We can’t sell before buying a stock, that is, the array index at which stock
    // is bought
    // will always be less than the index at which the stock is sold.
    // 1 <= prices.length < 10^3
    // 0 <= prices[i] < 10^5

    // O(n^2) solution
    public static int getWithOnSquared(int[] prices) {
        int maxProfit = 0;

        for (int buyIndex = 0; buyIndex < prices.length - 1; buyIndex++) {
            for (int sellIndex = buyIndex + 1; sellIndex < prices.length; sellIndex++) {
                int currentProfit = prices[sellIndex] - prices[buyIndex];
                maxProfit = Math.max(currentProfit, maxProfit);
            }
        }

        return maxProfit;
    }

    // O(n) soliution
    public static int getWithOn(int[] prices) {

        int maxProfit = 0;
        int buyIndex = 0;
        int sellIndex = 1;
        while (sellIndex < prices.length) {
            if (prices[buyIndex] < prices[sellIndex]) {
                int currentProfit = prices[sellIndex] - prices[buyIndex];
                maxProfit = Math.max(currentProfit, maxProfit);
            } else {
                buyIndex = sellIndex;
            }
            sellIndex++;
        }
        return maxProfit;
    }

}
