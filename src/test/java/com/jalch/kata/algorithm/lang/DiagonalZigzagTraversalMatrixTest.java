package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class DiagonalZigzagTraversalMatrixTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Structural Edge Cases (Null, Empty, and 1x1 Matrix)
                Arguments.of(null, new int[] {}),
                Arguments.of(new int[][] {}, new int[] {}),
                Arguments.of(new int[][] { { 5 } }, new int[] { 5 }),
                // 2. Standard Square Matrix (3x3 - Matching the conceptual walkthrough)
                // Diagonal stripes alternate direction: Up (1) -> Down (2,4) -> Up (7,5,3) ->
                // Down (6,8) -> Up (9)
                Arguments.of(new int[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
                }, new int[] { 1, 2, 4, 7, 5, 3, 6, 8, 9 }),
                // 3. Asymmetric Rectangular Matrix (More Columns than Rows: 2x3)
                // Stripe 1 (Up): [1] -> 1
                // Stripe 2 (Down): [2, 4] -> 2, 4
                // Stripe 3 (Up): [5, 3] -> 5, 3
                // Stripe 4 (Down): [6] -> 6
                Arguments.of(new int[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 }
                }, new int[] { 1, 2, 4, 5, 3, 6 }),
                // 4. Asymmetric Rectangular Matrix (More Rows than Columns: 3x2)
                // Stripe 1 (Up): [1] -> 1
                // Stripe 2 (Down): [2, 3] -> 2, 3
                // Stripe 3 (Up): [5, 4] -> 5, 4
                // Stripe 4 (Down): [6] -> 6
                Arguments.of(new int[][] {
                        { 1, 2 },
                        { 3, 4 },
                        { 5, 6 }
                }, new int[] { 1, 2, 3, 5, 4, 6 }),
                // 5. Flat Matrices (Single Row and Single Column boundaries)
                Arguments.of(new int[][] {
                        { 1, 2, 3, 4 }
                }, new int[] { 1, 2, 3, 4 }),
                Arguments.of(new int[][] {
                        { 1 },
                        { 2 },
                        { 3 }
                }, new int[] { 1, 2, 3 }));
    }

    @ParameterizedTest(name = "Test {index}: For matrix {0} flat zigzag array is {1}")
    @MethodSource("testData")
    void testFindTraversal(int[][] input, int[] expected) {
        Assertions.assertArrayEquals(expected, DiagonalZigzagTraversalMatrix.toArray(input));
        Assertions.assertArrayEquals(expected, DiagonalZigzagTraversalMatrix.toArrayRefactored(input));
    }
}
