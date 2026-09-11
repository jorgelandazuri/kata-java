package com.jalch.kata.algorithm.lang;

public class SpiralMatrixInwardGeneration {

    // =============================================================================
    // Given a positive integer n, generate an n × n matrix populated with
    // elements from 1 to n² arranged in a clockwise spiral sequence pattern
    // from the outer layer inwards.
    // =============================================================================

    public static int[][] generate(int n) {
        if (n <= 0)
            return new int[][] {};
        int[][] result = new int[n][n];
        int e = 1;
        int rigthLimit = n - 1;
        int dowmLimit = n - 1;
        int leftLimit = 0;
        int topLimit = 0;
        int maxElements = n * n;
        while (e <= maxElements) {
            // Left to rigth along the outer top row.
            for (int i = leftLimit; i <= rigthLimit; i++) {
                result[topLimit][i] = e;
                e++;
            }
            topLimit++;
            // Top to down along the outer rigth column.
            for (int i = topLimit; i <= dowmLimit; i++) {
                result[i][rigthLimit] = e;
                e++;
            }
            rigthLimit--;
            // Rigth to left along the outer bottom row.
            for (int i = rigthLimit; i >= leftLimit; i--) {
                result[dowmLimit][i] = e;
                e++;
            }
            dowmLimit--;
            // Bottom to top along the outer left column.
            for (int i = dowmLimit; i >= topLimit; i--) {
                result[i][leftLimit] = e;
                e++;
            }
            leftLimit++;
        }

        return result;
    }

}
