package com.jalch.kata.algorithm.lang;

public class MinesweeperClueGenerator {

    // =======================================================================
    // Given an M × N boolean matrix representing a minefield grid where true
    // indicates a mine and false represents an empty space, return an integer
    // matrix of the same dimensions where each cell contains the count of
    // active mines in its 8 surrounding neighbor cells.
    // =======================================================================

    // O(MxN) time and space complexity.
    public static int[][] getMatrixDimension(boolean[][] matrix) {
        if (matrix == null)
            return null;
        if (matrix.length == 0)
            return new int[][] {};
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                result[r][c] = calculateNeighborSum(matrix, r, c);
            }
        }
        return result;
    }

    // DIRECTIONS can change if we define a bigger matrix for neighbors.
    private static int[][] DIRECTIONS = {
        { -1, -1 }, { -1, 0 }, { -1, 1 },
        { 0, -1 }, { 0, 1 },
        { 1, -1 }, { 1, 0 }, { 1, 1 }
    };

    private static int calculateNeighborSum(boolean[][] matrix, int r, int c) {
        int sum = 0;
        for (int[] dir : DIRECTIONS) {
            sum += getMineInt(matrix, r + dir[0], c + dir[1]);
        }
        return sum;
    }

    // Harcoded for 3x3 Neighbor
    // private static int calculateNeighborSum(boolean[][] matrix, int r, int c) {
    // return getMineInt(matrix, r - 1, c - 1) + getMineInt(matrix, r - 1, c) +
    // getMineInt(matrix, r - 1, c + 1) +
    // getMineInt(matrix, r, c - 1) + getMineInt(matrix, r, c + 1) +
    // getMineInt(matrix, r + 1, c - 1) + getMineInt(matrix, r + 1, c) +
    // getMineInt(matrix, r + 1, c + 1);
    // }

    private static int getMineInt(boolean[][] matrix, int r, int c) {
        if (r < 0 || c < 0 || r >= matrix.length || c >= matrix[0].length)
            return 0;
        else
            return matrix[r][c] ? 1 : 0;
    }

}
