package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BinarySubarrayCardinalitySumTest {

    // =========================================================================
    // DATA AND TESTS FOR FUNCTION 1: countContinuousSubarrays
    // =========================================================================

    static Stream<Arguments> testDataForCount() {
        return Stream.of(
                // 1. Edge Cases (Null, Empty, and Targets Out of Reach)
                Arguments.of(null, 2, 0),
                Arguments.of(new int[] {}, 1, 0),
                Arguments.of(new int[] { 1, 0, 1 }, 5, 0),

                // 2. Simple Progressions (Standard Non-Zero Cases)
                Arguments.of(new int[] { 1, 1, 1 }, 2, 2),
                Arguments.of(new int[] { 1, 0, 1, 1, 0 }, 2, 5),

                // 3. The Zero Traps (Leading, Trailing, and Intermediate Zeros)
                Arguments.of(new int[] { 0, 1, 0, 1, 0 }, 2, 4),

                // 4. Target is Zero (Tricky window expansion)
                Arguments.of(new int[] { 0, 0, 1 }, 0, 3),
                Arguments.of(new int[] { 0, 0, 0 }, 0, 6),

                // 5. Large Complex Sequence
                Arguments.of(new int[] { 1, 0, 1, 0, 1 }, 2, 4));
    }

    @ParameterizedTest(name = "Function 1 - Test {index}: Array={0}, Target={1} expected count={2}")
    @MethodSource("testDataForCount")
    void testCountContinuousSubarrays(int[] input, int target, int expected) {
        Assertions.assertEquals(expected, BinarySubarrayCardinalitySum.countContinuousSubarrays(input, target));
    }

    // =========================================================================
    // DATA AND TESTS FOR FUNCTION 2: countLongestNeutralSumSubarray
    // =========================================================================

    static Stream<Arguments> testDataForLongest() {
        return Stream.of(
                // 1. Edge Cases (Null, Empty, or too small to form a pair)
                Arguments.of(null, 0),
                Arguments.of(new int[] {}, 0),
                Arguments.of(new int[] { 1 }, 0),
                Arguments.of(new int[] { 0 }, 0),
                // 2. Simple Balanced vs Unbalanced Elements
                Arguments.of(new int[] { 0, 1 }, 2), // Entire array is a match: [0, 1] -> Length 2
                Arguments.of(new int[] { 1, 1, 1 }, 0), // No zeros exist -> Length 0
                Arguments.of(new int[] { 0, 0, 0 }, 0), // No ones exist -> Length 0
                // 3. Nested or Multiphasic Windows
                Arguments.of(new int[] { 0, 1, 0 }, 2), // Left group [0, 1] or right group [1, 0] -> Max length 2
                Arguments.of(new int[] { 0, 1, 1, 0 }, 4), // Perfect symmetry around center -> Length 4
                Arguments.of(new int[] { 0, 0, 1, 1 }, 4), // Shifted step boundary -> Length 4
                // 4. The Wide Margin Trap (Ensuring your "else" firstIndex storage strategy
                // works)
                // Whole array length is 6. Prefix sums timeline:
                // idx: 0 1 2 3 4 5
                // val: [ 0, 1, 0, 0, 1, 1 ]
                // sum: -1, 0, -1, -2, -1, 0
                // Match at sum 0 (idx 5) vs base case (-1) -> 5 - (-1) = 6
                Arguments.of(new int[] { 0, 1, 0, 0, 1, 1 }, 6),
                // 5. Trailing Noise
                // Match is at the beginning. The remaining [1, 1] is dead noise.
                Arguments.of(new int[] { 0, 1, 0, 1, 1, 1 }, 4));
    }

    @ParameterizedTest(name = "Function 2 - Test {index}: Array={0} expected longest length={1}")
    @MethodSource("testDataForLongest")
    void testCountLongestNeutralSumSubarray(int[] input, int expected) {
        Assertions.assertEquals(expected, BinarySubarrayCardinalitySum.findLongestNeutralSumSubarrayLength(input));
    }
}