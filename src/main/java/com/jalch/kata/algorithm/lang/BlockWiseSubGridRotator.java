package com.jalch.kata.algorithm.lang;

public class BlockWiseSubGridRotator {

    // =============================================================================
    // Given an N × N matrix representing an image grid where N is always a multiple
    // of 2. Divide the matrix into non-overlapping 2 × 2 localized sub-grids.
    // Rotate the elements within each individual 2 × 2 block clockwise by 90
    // degrees in-place, without moving the blocks themselves.
    // =============================================================================

    // O(N^2) time complexity
    // O(1) space complexity (in-place rotation)
    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length < 2)
            return;
        for (int i = 0; i < matrix.length; i += 2) {
            for (int j = 0; j < matrix.length; j += 2) {
                rotateGrid(matrix, i, j);
            }
        }
    }

    private static void rotateGrid(int[][] matrix, int startRow, int strartCol) {
        int temp = matrix[startRow][strartCol];
        matrix[startRow][strartCol] = matrix[startRow + 1][strartCol];
        matrix[startRow + 1][strartCol] = matrix[startRow + 1][strartCol + 1];
        matrix[startRow + 1][strartCol + 1] = matrix[startRow][strartCol + 1];
        matrix[startRow][strartCol + 1] = temp;
    }

}
