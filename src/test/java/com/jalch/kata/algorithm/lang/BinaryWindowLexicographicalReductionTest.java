package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BinaryWindowLexicographicalReductionTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Structural Edge Cases (Null, Empty, and 1x1 Matrix)
                Arguments.of(null, null),
                Arguments.of(new boolean[][] {}, new int[][] {}),
                Arguments.of(new boolean[][] { { true } }, new int[][] { { 0 } }),
                // 2. Completely Clear vs Completely Full Fields
                Arguments.of(new boolean[][] {
                        { false, false },
                        { false, false }
                }, new int[][] {
                        { 0, 0 },
                        { 0, 0 }
                }),
                Arguments.of(new boolean[][] {
                        { true, true, true },
                        { true, true, true },
                        { true, true, true }
                }, new int[][] {
                        { 3, 5, 3 },
                        { 5, 8, 5 },
                        { 3, 5, 3 }
                }),
                // 3. Asymmetric Grids (Varying Row/Column Dimensions)
                Arguments.of(new boolean[][] {
                        { true, false, true, false },
                        { false, true, false, false }
                }, new int[][] {
                        { 1, 3, 1, 1 },
                        { 2, 2, 2, 1 }
                }),
                // 4. Standard Sparse Distribution Patterns
                Arguments.of(new boolean[][] {
                        { true, false, false },
                        { false, true, false },
                        { false, false, false }
                }, new int[][] {
                        { 1, 2, 1 },
                        { 2, 1, 1 },
                        { 1, 1, 1 }
                }));
    }

    @ParameterizedTest(name = "Test {index}: For mine matrix {0}, the expected adjacent mines matrix is {1} ")
    @MethodSource("testData")
    void testGetMatrixDimension(boolean[][] input, int[][] expected) {
        Assertions.assertArrayEquals(expected, BinaryWindowLexicographicalReduction.getMatrixDimension(input));
    }
}
