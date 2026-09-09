package com.jalch.kata.algorithm.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymmetricRowColumnPairing {

    // =============================================================================
    // Given an N × N integer matrix grid, return the number of pairs (Ri, Cj)
    // such that row Ri and column Cj are equal. A row and column are considered
    // equal if they contain the same elements in the same precise sequential order.
    // =============================================================================

    // Brute force
    // Time complexity O(N^3) -> Benchmark on N=500 => ~610.00 ms execution time.
    // Space complexity O(1)
    public static int getPairsCountBruteForce(int[][] matrix) {
        // Assume matric is N X N.
        if (matrix == null || matrix.length == 0)
            return 0;
        int length = matrix.length;
        int pairsCount = 0;
        for (int r = 0; r < length; r++) {
            for (int c = 0; c < length; c++) {
                boolean rowAndColEquals = true;
                for (int i = 0; i < length; i++) {
                    if (matrix[r][i] != matrix[i][c]) {
                        rowAndColEquals = false;
                        break;
                    }
                }
                if (rowAndColEquals)
                    pairsCount++;
            }

        }
        return pairsCount;
    }

    // Time complexity solution: O(2(NxN)) = 2xO(N^2) = O(N^2)
    // (HashMap lookup is O(1))
    // Benchmark on N=500 => ~42.00 ms execution time. (+14x faster)
    // Space complexity: O(N^2) (Lists)
    public static int getPairsCountHashMap(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int length = matrix.length;
        int pairsCount = 0;
        HashMap<List<Integer>, Integer> rowsFreq = new HashMap<>();
        for (int r = 0; r < length; r++) {
            List<Integer> currentRow = new ArrayList<>();
            for (int c = 0; c < length; c++) {
                currentRow.add(matrix[r][c]);
            }
            rowsFreq.merge(currentRow, 1, Integer::sum);
        }
        for (int c = 0; c < length; c++) {
            List<Integer> currentColumn = new ArrayList<>();
            for (int r = 0; r < length; r++) {
                currentColumn.add(matrix[r][c]);
            }
            pairsCount += rowsFreq.getOrDefault(currentColumn, 0);
        }
        return pairsCount;
    }

}
