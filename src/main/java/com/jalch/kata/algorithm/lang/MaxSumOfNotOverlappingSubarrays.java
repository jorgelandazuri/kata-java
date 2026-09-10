package com.jalch.kata.algorithm.lang;

public class MaxSumOfNotOverlappingSubarrays {

    // =======================================================================
    // Given an integer array nums and two integers firstLen and secondLen,
    // return the maximum sum of elements in two non-overlapping contiguous
    // subarrays of lengths firstLen and secondLen.
    // =======================================================================

    // Time complexity: O(2N) = O(N)
    // Space complexity: O(1) -> constant auxiliary space as N increases.
    public static int findMaxSum(int[] nums, int firstLen, int secondLen) {
        if (nums == null || nums.length == 0)
            return 0;
        if (firstLen + secondLen > nums.length)
            return 0;
        // Scenatio A: firstLen on the left and secondLen on the rigth.
        // Scenatio B: firstLen on the rigth and secondLen on the left.
        // Get the maximum of both max sum options.
        return Math.max(
                calculateMaxSum(nums, firstLen, secondLen),
                calculateMaxSum(nums, secondLen, firstLen));
    }

    private static int calculateMaxSum(int[] nums, int firstLen, int secondLen) {

        int length = nums.length;
        int currentFirstSum = 0;
        int currentSecondSum = 0;
        // Get sum for each at the starting position, one next to each other.
        for (int i = 0; i < firstLen; i++) {
            currentFirstSum += nums[i];
        }
        for (int j = firstLen; j < firstLen + secondLen; j++) {
            currentSecondSum += nums[j];
        }

        int maxSum = currentFirstSum + currentSecondSum;
        int maxFirstSum = currentFirstSum;
        int firstLeft = 0;
        int firstRigth = firstLen - 1;
        int secondLeft = firstLen;
        int secondRigth = firstLen + secondLen - 1;

        // Allow secondRigth to go through 1 index more for one last iteration.
        while (secondRigth + 1 < length) {
            firstRigth++;
            currentFirstSum += nums[firstRigth];
            currentFirstSum -= nums[firstLeft];
            firstLeft++;
            // Find the maximum left side subarray sum.
            maxFirstSum = Math.max(maxFirstSum, currentFirstSum);
            // Move the rigth window one the left and get the current sum for it.
            secondRigth++;
            currentSecondSum += nums[secondRigth];
            currentSecondSum -= nums[secondLeft];
            secondLeft++;
            // Replace if the current max sum is less that the current candidate.
            maxSum = Math.max(maxSum, maxFirstSum + currentSecondSum);
        }

        return maxSum;
    }

}
