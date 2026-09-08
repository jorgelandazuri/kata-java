package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ArithmeticProgressionTripleCountTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Edge Cases (Null, Empty, and Structurally Impossible Arrays)
                Arguments.of(null, 3, 0),
                Arguments.of(new int[] {}, 3, 0),
                Arguments.of(new int[] { 1 }, 3, 0),
                Arguments.of(new int[] { 1, 4 }, 3, 0),
                Arguments.of(new int[] { 1, 4, 10 }, 3, 0), // Spacing is right, but not uniform (4-1=3, 10-4=6)
                // 2. Standard Progressions (Unique Elements)
                Arguments.of(new int[] { 1, 4, 7 }, 3, 1), // Exact single triplet: (1, 4, 7)
                Arguments.of(new int[] { 1, 2, 4, 5, 7 }, 3, 1), // Embedded triplet with noise elements: (1, 4, 7)
                Arguments.of(new int[] { 1, 4, 7, 10 }, 3, 2), // Chained triplets: (1, 4, 7) and (4, 7, 10)
                // 3. Negative Integers & Cross-Zero Boundaries
                Arguments.of(new int[] { -10, -7, -4 }, 3, 1), // All negative numbers: (-10, -7, -4)
                Arguments.of(new int[] { -4, -1, 2, 5 }, 3, 2), // Crossing zero: (-4, -1, 2) and (-1, 2, 5)
                // 4. Repeated Numbers / Duplicates Handling
                // Two 4s: Combinations = count(1) * count(4) * count(7) -> 1 * 2 * 1 = 2
                // triplets
                Arguments.of(new int[] { 1, 4, 4, 7 }, 3, 2),
                // Large combination chain:
                // For 1, 4, 7 -> count(1)*count(4)*count(7) = 3 * 2 * 1 = 6
                // For 4, 7, 10 -> count(4)*count(7)*count(10) = 2 * 1 * 4 = 8
                // Total expected = 6 + 8 = 14
                Arguments.of(new int[] { 1, 1, 1, 4, 4, 7, 10, 10, 10, 10 }, 3, 14),
                // 5. Complete Failures & Plateaus
                Arguments.of(new int[] { 4, 4, 4, 4 }, 3, 0), // Multiple values but diff is 0, not 3
                Arguments.of(new int[] { 1, 5, 9 }, 3, 0)  // Uniform spacing but diff is 4, not 3
        );
    }

    @ParameterizedTest(name = "Test {index}: Array={0}, Diff={1} expected triplets={2}")
    @MethodSource("testData")
    void testGetCount(int[] input, int diff, int expected) {
        Assertions.assertEquals(expected, ArithmeticProgressionTripleCount.getCount(input, diff));
    }
}
