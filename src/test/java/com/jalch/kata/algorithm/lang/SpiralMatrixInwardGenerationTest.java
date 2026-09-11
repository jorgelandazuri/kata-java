package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SpiralMatrixInwardGenerationTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Structural Edge Cases (Invalid input sizes)
                Arguments.of(0, new int[][] {}),
                Arguments.of(-5, new int[][] {}),
                // 2. Minimum Valid Grid Size (1x1 Matrix - Crucial Odd Boundary)
                Arguments.of(1, new int[][] {
                        { 1 }
                }),
                // 3. Small Even Matrix Size (2x2 Matrix)
                Arguments.of(2, new int[][] {
                        { 1, 2 },
                        { 4, 3 }
                }),
                // 4. Standard Odd Matrix Size (3x3 Matrix - Verifies first inward core layer)
                Arguments.of(3, new int[][] {
                        { 1, 2, 3 },
                        { 8, 9, 4 },
                        { 7, 6, 5 }
                }),
                // 5. Larger Even Matrix Size (4x4 Matrix)
                Arguments.of(4, new int[][] {
                        { 1, 2, 3, 4 },
                        { 12, 13, 14, 5 },
                        { 11, 16, 15, 6 },
                        { 10, 9, 8, 7 }
                }),
                // 6. Advanced Odd Matrix Size (5x5 Matrix - Stresses multi-layer collapsing
                // logic)
                Arguments.of(5, new int[][] {
                        { 1, 2, 3, 4, 5 },
                        { 16, 17, 18, 19, 6 },
                        { 15, 24, 25, 20, 7 },
                        { 14, 23, 22, 21, 8 },
                        { 13, 12, 11, 10, 9 }
                }),
                // 7. High-Tier Odd Matrix Size (7x7 Matrix - Deep nested sequential core spiral
                // check)
                Arguments.of(7, new int[][] {
                        { 1, 2, 3, 4, 5, 6, 7 },
                        { 24, 25, 26, 27, 28, 29, 8 },
                        { 23, 40, 41, 42, 43, 30, 9 },
                        { 22, 39, 48, 49, 44, 31, 10 },
                        { 21, 38, 47, 46, 45, 32, 11 },
                        { 20, 37, 36, 35, 34, 33, 12 },
                        { 19, 18, 17, 16, 15, 14, 13 }
                }));
    }

    @ParameterizedTest(name = "Test {index}: Generating spiral matrix for n={0}")
    @MethodSource("testData")
    void testGenerate(int n, int[][] expected) {
        Assertions.assertArrayEquals(expected, SpiralMatrixInwardGeneration.generate(n));
    }
}
