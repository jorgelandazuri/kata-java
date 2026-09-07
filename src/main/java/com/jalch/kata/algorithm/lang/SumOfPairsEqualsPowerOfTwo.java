package com.jalch.kata.algorithm.lang;

import java.util.HashMap;

public class SumOfPairsEqualsPowerOfTwo {

    // Given an array of integers numbers, find the number of pairs of indices (i,
    // j)
    // such that i <= j and numbers[i] + numbers[j] is equal to some power of 2.

    // O(n^2) time complexity (brute force) 
    // O(1) space complexity (same memory used as nums.length grows)
    public static int find(int[] nums) {

        int pairsFound = 0;
        for (int left = 0; left < nums.length; left++) {
            for (int right = left; right < nums.length; right++) {
                long currentSum = (long) nums[left] + nums[right];
                // E.G:
                // 8 => 1000 (all power of 2 have a leading 1 and the rest 0)
                // 7 => 0111 (hence subtracting 1 unit would inverse)
                // & bitwise AND
                // 1000 & 0111 = 0000 (So, for every power of 2, currentSum & (currentSum -1)
                // should be zero )
                // Also, we know that any value <=0 can't be a power of two, plus the mechanism
                // above would work
                // for those numbers.
                if (currentSum > 0 && (currentSum & (currentSum - 1)) == 0)
                    pairsFound++;
            }
        }
        return pairsFound;
    }

    // O(n) time complexity (linear time as N grows as the nums is checked only once per item) 
    // O(N) space complexity (in the worst-case scenario (where all numbers in the input array 
    // are unique), the HashMap will store up to nums.length distinct key-value pairs).
    public static int findOptmised(int[] nums) {
        // Precompute possible powers of 2 that could match the sum of 2 integers.
        long[] powersOfTwo = new long[32];
        for (int i = 0; i < powersOfTwo.length; i++)
            powersOfTwo[i] = 1L << i;

        int pairsFound = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            for (long power : powersOfTwo) {
                long target = power - n;
                if (target >= Integer.MIN_VALUE && target <= Integer.MAX_VALUE) {
                    pairsFound += freq.getOrDefault((int) target, 0);
                }
            }
            long selfSum = (long) n + n;
            if (selfSum > 0 && (selfSum & (selfSum - 1)) == 0)
                pairsFound++;
            // freq.put(n, freq.getOrDefault(n, 0) + 1);
            freq.merge(n, 1, Integer::sum);
        }

        return pairsFound;
    }

}
