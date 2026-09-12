package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class RotateImage90DegreesTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Structural Edge Cases (Null, Empty, 1x1 Matrix)
                Arguments.of(null, null),
                Arguments.of(new int[][] {}, new int[][] {}),
                Arguments.of(new int[][] { { 5 } }, new int[][] { { 5 } }),
                // 2. Smallest Multi-Element Matrix (2x2 Matrix)
                Arguments.of(new int[][] {
                        { 1, 2 },
                        { 3, 4 }
                }, new int[][] {
                        { 3, 1 },
                        { 4, 2 }
                }),
                // 3. Standard Odd Matrix (3x3 Matrix - Layered Ring Navigation)
                // Outer ring elements rotate clockwise around the anchored center cell '5'
                Arguments.of(new int[][] {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
                }, new int[][] {
                        { 7, 4, 1 },
                        { 8, 5, 2 },
                        { 9, 6, 3 }
                }),
                // 4. Standard Even Matrix (4x4 Matrix - Core nested layer rotation)
                Arguments.of(new int[][] {
                        { 1,  2,  3,  4 },
                        { 5,  6,  7,  8 },
                        { 9,  10, 11, 12 },
                        { 13, 14, 15, 16 }
                }, new int[][] {
                        { 13, 9,  5,  1 },
                        { 14, 10, 6,  2 },
                        { 15, 11, 7,  3 },
                        { 16, 12, 8,  4 }
                })
        );
    }

    @ParameterizedTest(name = "Test {index}: Matrix {0} 90 degrees clockwise rotation is {1} [using temp array]")
    @MethodSource("testData")
    void testRotateWithTempArr(int[][] input, int[][] expected) {
        Assertions.assertArrayEquals(expected, RotateImage90Degrees.rotateWithTempArr(input));
    }

    @ParameterizedTest(name = "Test {index}: Matrix {0} 90 degrees clockwise rotation is {1} [by layers]")
    @MethodSource("testData")
    void testRotateByLayers(int[][] input, int[][] expected) {
        Assertions.assertArrayEquals(expected, RotateImage90Degrees.rotateByLayers(input));
    }
    
    @ParameterizedTest(name = "Test {index}: Matrix {0} 90 degrees clockwise rotation is {1} [by transposing and reversing]")
    @MethodSource("testData")
    void testRotateByTransposingAndReversing(int[][] input, int[][] expected) {
        Assertions.assertArrayEquals(expected, RotateImage90Degrees.rotateByTransposingAndRowReversal(input));
    }
}
