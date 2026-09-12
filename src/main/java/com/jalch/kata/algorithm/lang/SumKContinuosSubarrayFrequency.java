package com.jalch.kata.algorithm.lang;

import java.util.Map;
import java.util.HashMap;

public class SumKContinuosSubarrayFrequency {

    // =================================================================
    // Given an array of integers nums and an integer k, determine the
    // total number of subarrays whose sum is exactly equal to k.
    // =================================================================

    public static int get(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        Map<Integer, Integer> prefixSumFreq = new HashMap<>();
        // We have seen a prefix sum of 0 once as a baseline.
        prefixSumFreq.put(0, 1);
        int result = 0;
        int currentSum = 0;
        for (int rigth = 0; rigth < nums.length; rigth++) {
            // P(i) => Prefix sum until i.
            // We are looking for P(rigth) - P(i) = k
            // P(rigth) = currentSum
            // P(i) = complement.
            // In prefixSumFreq we have stored the frequencies of
            // all the continuos prefix sums for each i < rigth;
            // => P(i) = P(rigth) - k
            // => prefixSumFreq.get(complement) == currentSum - k
            // => If there is a match, we increase the result by the
            // by the frequency.
            currentSum += nums[rigth];
            int complement = currentSum - k;
            result += prefixSumFreq.getOrDefault(complement, 0);
            // Add/Increase the current sum for this index by 1
            // for next iterations.
            prefixSumFreq.merge(currentSum, 1, Integer::sum);

        }
        return result;
    }

}
