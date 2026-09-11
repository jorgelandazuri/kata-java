package com.jalch.kata.algorithm.lang;

public class DiagonalZigzagTraversalMatrix {

    // ===========================================================================
    // Given an M × N matrix, return an array containing all the
    // elements of the matrix ordered along a continuous diagonal
    // zigzag trajectory pathway.
    // ===========================================================================

    enum DIRECTION {
        UP, DOWN
    };

    // O(MxN) time and space complexity.
    // This solution works but is not very elegant
    public static int[] toArray(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new int[] {};
        if (matrix.length == 1)
            return matrix[0];
        int[] result = new int[matrix.length * matrix[0].length];
        int arrayLength = result.length;
        if (matrix[0].length == 1) {
            for (int i = 0; i < matrix.length; i++)
                result[i] = matrix[i][0];
            return result;
        }
        int r = 0;
        int c = 0;
        result[0] = matrix[r][c];
        c++;
        int assigned = 1;
        DIRECTION currentDirection = DIRECTION.DOWN;
        while (assigned < arrayLength) {
            result[assigned] = matrix[r][c];
            if (currentDirection == DIRECTION.DOWN) {
                r++;
                c--;
                if (c < 0) {
                    currentDirection = DIRECTION.UP;
                    c = 0;
                    if (r > matrix.length - 1) {
                        r = matrix.length - 1;
                        c++;
                    }
                } else if (r > matrix.length - 1) {
                    r = matrix.length - 1;
                    c += 2;
                }
            } else if (currentDirection == DIRECTION.UP) {
                r--;
                c++;
                if (r < 0) {
                    currentDirection = DIRECTION.DOWN;
                    r = 0;
                    if (c > matrix[0].length - 1) {
                        c = matrix[0].length - 1;
                        r++;
                    }
                } else if (c > matrix[0].length - 1) {
                    c = matrix[0].length - 1;
                    r += 2;
                }

            }
            assigned++;
        }
        return result;
    }

    public static int[] toArrayRefactored(int[][] matrix) {
        // An M × N matrix has exactly M + N - 1 total diagonal stripes.
        // The sum of the row and column indices (r + c) is identical
        // for every single cell on that stripe.
        // E.G.
        // { 1, 2, 3 }
        // { 4, 5, 6 }
        // { 7, 8, 9 }
        // Stripe 0 (r+c = 0): (0,0) -> Value 1
        // Stripe 1 (r+c = 1): (0,1), (1,0) -> Values 2, 4
        // Stripe 2 (r+c = 2): (0,2), (1,1), (2,0) -> Values 3, 5, 7
        // Stripe 3 (r+c = 3): (1,2), (2,1) -> Values 6, 8
        // Stripe 4 (r+c = 4): (2,2) -> Value 9
        // If the stripe index s is even, you read the stripe going Up.
        // If s is odd, you read the stripe going Down.

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return new int[] {};

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] result = new int[rows * cols];
        int currentIndex = 0;
        int stripes = rows + cols - 1;

        for (int s = 0; s < stripes; s++) {
            if (s % 2 == 0) {
                // GOING UP
                int r = Math.min(s, rows - 1); // 0 <= r < rows
                int c = s - r; // r+c = s => c = s-r to stay in the stripe.

                while (r >= 0 && c < cols) {
                    result[currentIndex] = matrix[r][c];
                    r--;
                    c++;
                    currentIndex++;
                }
            } else {
                // GOING DOWN
                int c = Math.min(s, cols - 1); // 0 <= r < rows
                int r = s - c; // r+c = s => r = s-c to stay in the stripe.

                while (c >= 0 && r < rows) {
                    result[currentIndex] = matrix[r][c];
                    r++;
                    c--;
                    currentIndex++;
                }
            }
        }
        return result;
    }

}
