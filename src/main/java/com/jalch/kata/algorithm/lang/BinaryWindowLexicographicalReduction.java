package com.jalch.kata.algorithm.lang;

public class BinaryWindowLexicographicalReduction {

    // ===========================================================================
    // Given a binary string s and an integer k, find the maximum number of '1's
    // you can isolate within any continuous sliding window tracking segment of
    // size k after applying up to maxSwaps changes.
    // ===========================================================================

    // Time complexity O(N)
    // Space complexity O(1)
    public static int findMax(String str, int k, int maxSwaps) {
        if (str == null || str.isBlank())
            return 0;
        int length = str.length();
        if (length < k)
            return 0;

        int totalNumberOfOnes = 0;
        for (int i = 0; i < length; i++)
            if (str.charAt(i) == '1')
                totalNumberOfOnes++;

        int currentOnesSize = 0;
        int maxSizeWithMaxSwaps = 0;
        int left = 0;
        for (int rigth = 0; rigth < length; rigth++) {
            if (str.charAt(rigth) == '1')
                currentOnesSize++;
            if (rigth - left + 1 == k) {
                int currentZerosSize = k - currentOnesSize;
                int currentOuterOnes = totalNumberOfOnes - currentOnesSize;
                int swapPossible = Math.min(currentZerosSize, Math.min(maxSwaps, currentOuterOnes));
                int currentMax = currentOnesSize + swapPossible;
                maxSizeWithMaxSwaps = Math.max(maxSizeWithMaxSwaps, currentMax);

                if (str.charAt(left) == '1') {
                    currentOnesSize--;
                }
                left++;
            }
        }
        return maxSizeWithMaxSwaps;
    }
}
