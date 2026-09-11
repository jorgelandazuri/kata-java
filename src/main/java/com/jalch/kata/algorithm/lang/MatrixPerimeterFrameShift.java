package com.jalch.kata.algorithm.lang;

public class MatrixPerimeterFrameShift {

    // =============================================================================
    // Given an M × N matrix, shift only the elements located on the absolute
    // outermost border/perimeter clockwise by k steps, while leaving all interior
    // internal sub-grid cell values completely anchored and unchanged.
    // =============================================================================

    // Time complexity O(3*2*( M+(N-2) )) = O(M+N)
    // Space complexity O(M+N)
    public static void shift(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || k <= 0)
            return;
        int m = matrix.length;
        int n = matrix[0].length;
        int frameElementsCount = 2 * m + 2 * (n - 2);
        // Check if the shifting leaves the matrix the same.
        // Only shift the delta.
        if (k >= frameElementsCount)
            k = k % frameElementsCount;
        if (k == 0)
            return;

        int[] frameElementsArr = new int[frameElementsCount];
        int upperRowIndex = 0;
        int bottomRowIndex = m - 1;
        int leftColIndex = 0;
        int rightColIndex = n - 1;

        // Populate the temp array for the first time.
        int left = frameElementsCount;
        int j = 0;
        for (int i = 0; i < n && left > 0; i++) {
            frameElementsArr[j] = matrix[upperRowIndex][i];
            left--;
            j++;
        }
        for (int i = 1; i < m && left > 0; i++) {
            frameElementsArr[j] = matrix[i][rightColIndex];
            left--;
            j++;
        }
        for (int i = rightColIndex - 1; i >= 0 && left > 0; i--) {
            frameElementsArr[j] = matrix[bottomRowIndex][i];
            left--;
            j++;
        }
        for (int i = bottomRowIndex - 1; i >= 0 && left > 0; i--) {
            frameElementsArr[j] = matrix[i][leftColIndex];
            left--;
            j++;
        }
        // Rotate the array.
        reverse(frameElementsArr, 0, frameElementsCount - 1);
        reverse(frameElementsArr, 0, k - 1);
        reverse(frameElementsArr, k, frameElementsCount - 1);
        // Populate the matrix frame with the shifted array.
        j = 0;
        for (int i = 0; i < n; i++) {
            matrix[upperRowIndex][i] = frameElementsArr[j];
            left--;
            j++;
        }
        for (int i = 1; i < m; i++) {
            matrix[i][rightColIndex] = frameElementsArr[j];
            left--;
            j++;
        }
        for (int i = rightColIndex - 1; i >= 0; i--) {
            matrix[bottomRowIndex][i] = frameElementsArr[j];
            left--;
            j++;
        }
        for (int i = bottomRowIndex - 1; i > 0; i--) {
            matrix[i][leftColIndex] = frameElementsArr[j];
            left--;
            j++;
        }

    }

    private static void reverse(int[] nums, int start, int end) {
        if (start < 0 || end < 0 || start >= nums.length || end >= nums.length || start > end)
            return;
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
