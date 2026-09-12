package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class MatrixSpiralOrderTraversalTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Structural Edge Cases (Null, Empty, and 1x1 Matrix)
                Arguments.of(null, List.of()),
                Arguments.of(new int[][] {}, List.of()),
                Arguments.of(new int[][] { { 5 } }, List.of(5)),
                // 2. Square Matrix (3x3 - Standard Spiral Wave Pattern)
                Arguments.of(new int[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
                }, List.of(1, 2, 3, 6, 9, 8, 7, 4, 5)),
                // 3. Asymmetric Rectangular Matrix (Wide Layout: 3 Rows x 4 Columns)
                Arguments.of(new int[][] {
                        { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 }
                }, List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7)),
                // 4. Asymmetric Rectangular Matrix (Tall Layout: 4 Rows x 3 Columns)
                Arguments.of(new int[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 },
                        { 10, 11, 12 }
                }, List.of(1, 2, 3, 6, 9, 12, 11, 10, 7, 4, 5, 8)),
                // 5. Flat Row and Column Vectors
                Arguments.of(new int[][] {
                        { 1, 2, 3, 4 }
                }, List.of(1, 2, 3, 4)),
                Arguments.of(new int[][] {
                        { 1 },
                        { 2 },
                        { 3 }
                }, List.of(1, 2, 3)));
    }

    @ParameterizedTest(name = "Test {index}: Matrix {0} spiral order list is {1}")
    @MethodSource("testData")
    void testSpiralOrder(int[][] input, List<Integer> expected) {
        Assertions.assertEquals(expected, MatrixSpiralOrderTraversal.spiralOrder(input));
        Assertions.assertEquals(expected, MatrixSpiralOrderTraversal.spiralOrderWithIntDirection(input));
    }
}
