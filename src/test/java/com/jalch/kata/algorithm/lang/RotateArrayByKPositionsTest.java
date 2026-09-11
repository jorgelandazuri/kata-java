package com.jalch.kata.algorithm.lang;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

public class RotateArrayByKPositionsTest {

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(null, 2, null),
                Arguments.of(new int[] {}, 2, new int[] {}),
                Arguments.of(new int[] { 2, 3 }, 2, new int[] { 2, 3 }),
                Arguments.of(new int[] { 1, 2, 3, 4, 5 }, 2, new int[] { 4, 5, 1, 2, 3 }),
                Arguments.of(new int[] { -35, -29, -7, 8, 6 }, 3, new int[] { -7, 8, 6, -35, -29 }),
                Arguments.of(new int[] { 1 }, 5, new int[] { 1 }),
                Arguments.of(new int[] { 10, 20, 30, 40, 50 }, 7, new int[] { 40, 50, 10, 20, 30 }),
                Arguments.of(new int[] { 0, 0, 0, 0 }, 10, new int[] { 0, 0, 0, 0 }),
                Arguments.of(new int[] { 1, 2 }, 1, new int[] { 2, 1 }),
                Arguments.of(new int[] { 2, 4, 6, 8, 10 }, 0, new int[] { 2, 4, 6, 8, 10 }));
    };

    @ParameterizedTest(name = "For array {0} and k {1}, the expected rotated array should be {2}")
    @MethodSource("testData")
    public void rotateArray(int[] input, int k, int[] expected) {
        Assertions.assertArrayEquals(expected, RotateArrayByKPositions.rotateWithExtraSpace(input, k));
        Assertions.assertArrayEquals(expected, RotateArrayByKPositions.rotateInPlace(input, k));
    }
}
