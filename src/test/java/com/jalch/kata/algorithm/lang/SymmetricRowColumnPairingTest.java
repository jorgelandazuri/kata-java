package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SymmetricRowColumnPairingTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Edge Cases (Null, Empty, and 1x1 Matrix)
                Arguments.of(null, 0),
                Arguments.of(new int[][] {}, 0),
                Arguments.of(new int[][] { { 5 } }, 1), // Row 0 [5] equals Col 0 [5] -> 1 pair
                // 2. Fully Symmetric Matrices (Every Row i equals Column i)
                // Row 0 equals Col 0; Row 1 equals Col 1 -> 2 pairs
                Arguments.of(new int[][] {
                        { 1, 2 },
                        { 2, 1 }
                }, 2),
                // Completely symmetric across main diagonal -> 3 pairs
                Arguments.of(new int[][] {
                        { 2, 4, 6 },
                        { 4, 3, 5 },
                        { 6, 5, 1 }
                }, 3),
                // 3. Asymmetric Layouts with Valid Cross-Pairs
                // Row 0 [3,2,1] matches nothing.
                // Row 1 [1,7,6] matches nothing.
                // Row 2 [2,7,7] matches Column 1 [2,7,7] -> 1 pair (R2, C1)
                Arguments.of(new int[][] {
                        { 3, 2, 1 },
                        { 1, 7, 6 },
                        { 2, 7, 7 }
                }, 1),
                // 4. Multiple / Duplicate Row & Column Combinations
                // Row 0 matches Col 0, Col 1
                // Row 1 matches Col 0, Col 1
                // Total combinations = 2 rows * 2 cols -> 4 pairs
                Arguments.of(new int[][] {
                        { 1, 1 },
                        { 1, 1 }
                }, 4),
                // Row 0 [3,1,2,2] matches Col 0 [3,1,2,2] -> 1 pair
                // Row 1 [1,4,4,5] matches nothing
                // Row 2 [2,4,2,2] matches Col 2 [2,4,2,2] 
                // Row 3 [2,4,2,2] matches Col 2 [2,4,2,2]
                // Total expected = 1 + 2 + 2 = 5 pairs
                Arguments.of(new int[][] {
                        { 3, 1, 2, 2 },
                        { 1, 4, 4, 5 },
                        { 2, 4, 2, 2 },
                        { 2, 4, 2, 2 }
                }, 3),
                // 5. Complete Mismatch
                Arguments.of(new int[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
                }, 0)
        );
    }

    @ParameterizedTest(name = "Test {index}: Matrix expected pairs={1}")
    @MethodSource("testData")
    void testGetPairsCount(int[][] matrix, int expected) {
        Assertions.assertEquals(expected, SymmetricRowColumnPairing.getPairsCountBruteForce(matrix));
        Assertions.assertEquals(expected, SymmetricRowColumnPairing.getPairsCountHashMap(matrix));
    }
}
