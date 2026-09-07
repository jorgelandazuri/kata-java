package com.jalch.kata.algorithm.lang;

import java.util.Arrays;
import java.util.function.Function;

public class NeighborOperationArrayMutation {

    // You are given an array of integers a. For each element, replace it with the
    // sum or multiplication of itself, its left neighbor, and its right neighbor.
    // If a neighbor doesn't exist (at the boundaries), consider its value to be
    // 0 (sum) or 1 (multiplication).

    public enum NeighborIntOperation {
        
        ADD(ids -> Arrays.stream(ids).sum(), 0),
        MULTIPLY(ids -> Arrays.stream(ids).reduce(1, (a, b) -> a * b), 1);

        private final Function<int[], Integer> operation;
        private final int defaultValue;

        NeighborIntOperation(Function<int[], Integer> operation, int defaultValue) {
            this.operation = operation;
            this.defaultValue = defaultValue;
        }

        public int apply(int... numbers) {
            return this.operation.apply(numbers);
        }

        public int getDefaultValue() {
            return this.defaultValue;
        }
    }

    // O(n) time complexity, O(n) space complexity
    public static int[] mutateUsingExtraArray(int[] nums, NeighborIntOperation op) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int[] result = new int[nums.length];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int current = nums[i];
            int left = (i > 0) ? nums[i - 1] : op.getDefaultValue();
            int right = (i != n - 1) ? nums[i + 1] : op.getDefaultValue();
            result[i] = op.apply(left, current, right);
        }
        return result;
    }

    // O(n) time complexity, O(1) space complexity
    public static int[] mutateInPlace(int[] nums, NeighborIntOperation op) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int n = nums.length;
        // Keep track of what the left element USED to be before we overwrote it
        int cachedLeft = op.getDefaultValue(); 

        for (int i = 0; i < n; i++) {
            int current = nums[i];
            int right = (i != n - 1) ? nums[i + 1] : op.getDefaultValue();
            // 1. Calculate the new value using the cached left value
            int newValue = op.apply(cachedLeft, current, right);
            // 2. The current element now becomes the 'left' neighbor for the next iteration
            cachedLeft = current;
            // 3. Overwrite the array safely in-place
            nums[i] = newValue;
        }
        
        return nums;
    }

}
