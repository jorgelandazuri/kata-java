package com.jalch.kata.algorithm.lang;

public class RemoveDuplicatedFromSortedArray {

    // You are given an integer array nums that is already sorted in non-decreasing
    // order. Your task is to remove
    // the duplicate values in-place so that each element in the array appears only
    // once, while preserving the
    // original relative order of the elements.

    // After removing the duplicates, let k be the number of unique elements
    // remaining in the array. The first k
    // positions of nums should contain these unique elements in sorted order. Any
    // values beyond index k - 1 are
    // irrelevant and can be ignored.

    // Your goal is to modify the array in-place and return the value k,
    // representing the count of unique elements.

    // Constraints:
    // 0 ≤ nums.length ≤ 3 * 10^4
    // -100 ≤ nums[i] ≤ 100
    // numbers is sorted in non-decreasing order.

    // O(n) time complexity -> Takes a linear amount of time as numbers.length
    // increases.
    // O(1) memory complexity -> Takes a constant amount of extra memory as
    // numbers.length increases.
    public static int remove(int[] numbers) {
        if (numbers.length == 0)
            return 0;

        int left = 0;
        for (int rigth = 1; rigth < numbers.length; rigth++) {
            if (numbers[left] != numbers[rigth]) {
                left++;
                numbers[left] = numbers[rigth];
            }
        }
        return left + 1;
    }

}
