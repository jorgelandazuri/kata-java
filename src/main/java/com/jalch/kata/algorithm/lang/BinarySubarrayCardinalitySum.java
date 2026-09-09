package com.jalch.kata.algorithm.lang;

import java.util.HashMap;

public class BinarySubarrayCardinalitySum {

    // ===========================================================================
    // 1)
    // Given a binary array nums containing only 0s and 1s, and an integer target.
    // Find the total number of continuous subarrays whose elements sum up exactly
    // to target.
    // ===========================================================================

    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public static int countContinuousSubarrays(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        int length = nums.length;
        if (target > length)
            return 0;

        // Presum of subarray - > P(x) with i=end and s=start of the subarray.
        // SubarraySum = P(i) - P(s-1)
        // We are looking to count the unique subarrays where SubarraySum=target
        // Hence
        // target = P(i) - P(s-1)
        // P(s-1) = P(i) - target
        // When you are standing at any index (i), you already know your current
        // running total P(i) and your target.
        // By calculating P(i) - target, you get the exact
        // historical prefix sum needed to form a valid match.

        // key=historicalPrefixSum value=frequency
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);
        int counter = 0;
        int currentSum = 0;

        for (int n : nums) {
            currentSum += n;

            if (prefixSumMap.containsKey(currentSum - target))
                counter += prefixSumMap.get(currentSum - target);

            prefixSumMap.merge(currentSum, 1, Integer::sum);
        }
        return counter;
    }

    // =========================================================================
    // 2)
    // Find the longest subarray (containing only 1s and 0s) length
    // with an equal number of 0s and 1s
    // =========================================================================

    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public static int findLongestNeutralSumSubarrayLength(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        int length = nums.length;
        int maxLength = 0;
        int currentSum = 0;
        // key=currentSum, value= firstIndex where found.
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        // We treat 0s as -1 to balance the sum so target is ZERO
        prefixSum.put(0, -1);
        for (int i = 0; i < length; i++) {
            currentSum += (nums[i] == 0) ? -1 : 1;
            // Same concept as 1) problem solution, but target is now ZERO
            if (prefixSum.containsKey(currentSum)) {
                int previousIndex = prefixSum.get(currentSum);
                int currentLength = i - previousIndex;
                maxLength = Math.max(currentLength, maxLength);
            } else {
                // We only need to add it once (when the sum key doesn't exist)
                // to ensure is the longest subarray.
                prefixSum.put(currentSum, i);
            }

        }
        return maxLength;
    }

}
