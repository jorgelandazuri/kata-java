package com.jalch.kata.algorithm.lang;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MaxAverageForSubarrayWithFixedLengthTest {

    private static int[][] inputData = {
            { 1 },
            { 5, 5 },
            { 10, 5, 2, -1, 6, 3, -2, -4, 4, 1, -3, -6, -1, -2, -5, -7 },
            { 7, 3, 1, -2, 6, 2, -1, -3, 4, 1, -2, -5, 2, 0, -4, -6 },
            { 12, 9, 5, 2, 8, 6, 4, 1, 7, 5, 3, 0, 4, 2, 0, -3 },
            { -10, -11, -12, -13, -20, -21, -22, -23, -30, -31, -32, -33, -40, -41, -42, -43 },
            { 5, 3, -2, -3, 4, 2, -3, -4, 3, 1, -4, -5, 2, 0, -5, -6 }
    };

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(inputData[0], 1, 1.0),
                Arguments.of(inputData[1], 2, 5.0),
                Arguments.of(inputData[2], 4, 4.0),
                Arguments.of(inputData[3], 5, 3.0),
                Arguments.of(inputData[4], 4, 7.0),
                Arguments.of(inputData[5], 3, -11),
                Arguments.of(inputData[6], 6, 1.5));
    }

    @ParameterizedTest(name = "Given the array {0} and k={1}, the maximum average is {2}")
    @MethodSource("testData")
    public void maxKSubarrayAverage(int[] inputOne, int inputTwo, double expected) {
        Assertions.assertEquals(expected, MaxAverageForSubarrayWithFixedLength.findMaxAverage(inputOne, inputTwo));
        Assertions.assertEquals(expected,
                MaxAverageForSubarrayWithFixedLength.findMaxAverageEducative(inputOne, inputTwo));
    }
}
