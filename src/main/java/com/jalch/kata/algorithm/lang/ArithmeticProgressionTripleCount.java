package com.jalch.kata.algorithm.lang;

import java.util.HashMap;

public class ArithmeticProgressionTripleCount {

    // Given an array of integers arr sorted in ascending order, and an integer
    // diff. A triplet (i, j, k) is valid if i < j < k and arr[j] - arr[i] == diff
    // as well as arr[k] - arr[j] == diff. Return the unique count of valid
    // arithmetic triplets in optimal O(N) execution time.

    public static int getCount(int[] nums, int diff) {
        if (nums == null || nums.length < 3)
            return 0;
        int count = 0;
        // Assume array is sorted in asceding order, so no check for this.
        HashMap<Integer, Integer> availableNums = new HashMap<>();
        for (int n : nums)
            availableNums.merge(n, 1, Integer::sum);
        for (int n : availableNums.keySet()) {
            // Given
            // arr[j] - arr[i] = diff
            // arr[k] - arr[j] = diff
            // 1)
            // arr[j] = arr[i] + diff -> arr[j] = n + diff
            // 2)
            // arr[k] = arr[j] + diff (from 1)
            // arr[k] = arr[i] + diff + diff -> arr[k] = n + 2*diff
            if (availableNums.containsKey(n + diff) && availableNums.containsKey(n + (2 * diff))) {
                // Total combinations = count(i) * count(j) * count(k)
                count += availableNums.get(n) * availableNums.get(n + diff) * availableNums.get(n + (2 * diff));
            }
        }
        return count;

    }

}
