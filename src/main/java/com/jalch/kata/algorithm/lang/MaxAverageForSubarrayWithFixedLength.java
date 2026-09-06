package com.jalch.kata.algorithm.lang;

public class MaxAverageForSubarrayWithFixedLength {

    // Given an array of integers nums, and an integer k, return the
    // maximum average of a contiguous subarray of length k.
    // Constraints:
    // 1 <= k <= nums.length <= 10^5
    // -10^4 nums[i] <= 10^4

    // Sliding window -> O(n) time complexity.
    public static double findMaxAverage(int[] nums, int k) {

        int left = 0;
        long maxWindowSum = Long.MIN_VALUE;
        long currentWindowSum = 0;
        for (int right = 0; right < nums.length; right++) {
            currentWindowSum += nums[right];
            if (right - left + 1 == k) {
                maxWindowSum = Math.max(maxWindowSum, currentWindowSum);
                currentWindowSum -= nums[left];
                left++;
            }
        }
        ;
        return (double) maxWindowSum / k;
    }

    // Educative.io solution.
    public static double findMaxAverageEducative(int[] nums, int k) {
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }

        int maxSum = currentSum;

        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, currentSum);
        }

        return (double) maxSum / k;
    }

}
