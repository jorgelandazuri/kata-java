package com.jalch.kata.algorithm.lang;

import java.util.List;
import java.util.ArrayList;

public class MatrixSpiralOrderTraversal {

    // =================================================================
    // Given an M x N matrix, return an array containing the matrix
    // elements in spiral order, starting from the top-left cell.
    // =================================================================

    // Time complexity: O(MxN) -> Benchmark(M=N=800)=2.91 ms
    // Space complexity: O(1)
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0)
            return result;
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 1 && n == 1) {
            result.add(matrix[0][0]);
            return result;
        }
        int topIndex = 0;
        int leftIndex = 0;
        int rigthIndex = n - 1;
        int bottomIndex = m - 1;
        int expectedElements = m * n;

        while (result.size() < expectedElements) {
            if (result.size() == expectedElements - 1) {
                result.add(matrix[topIndex][leftIndex]);
                continue;
            }
            // Add top row except last
            for (int i = leftIndex; i < rigthIndex; i++) {
                result.add(matrix[topIndex][i]);
            }
            // Add right column except last
            for (int i = topIndex; i < bottomIndex; i++) {
                result.add(matrix[i][rigthIndex]);
            }
            // Add bottom row except first, check if the result already reached desired size
            // (for non-squared matrices)
            for (int i = rigthIndex; i > leftIndex && result.size() < expectedElements; i--) {
                result.add(matrix[bottomIndex][i]);
            }
            // Add left column except first, check if the result already reached desired
            // size (for non-squared matrices)
            for (int i = bottomIndex; i > topIndex && result.size() < expectedElements; i--) {
                result.add(matrix[i][leftIndex]);
            }
            // Update index for next inner grid.
            topIndex++;
            leftIndex++;
            rigthIndex--;
            bottomIndex--;
        }

        return result;
    }

    // Best
    // Time complexity: O(MxN) -> Benchmark(M=N=800)=1.84 ms
    // Space complexity: O(1)
    public static List<Integer> spiralOrderWithIntDirection(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new ArrayList<>();

        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0;
        int col = -1;
        // Direcion:
        // 1: when going left->right or top->bottom
        // -1: when going rigth->left or bottom->top
        int direction = 1;
        List<Integer> result = new ArrayList<>();

        while (rows > 0 && cols > 0) {
            for (int i = 0; i < cols; i++) {
                col += direction;
                result.add(matrix[row][col]);
            }
            rows--;

            for (int i = 0; i < rows; i++) {
                row += direction;
                result.add(matrix[row][col]);
            }
            cols--;

            direction *= -1;
        }

        return result;
    }

}
