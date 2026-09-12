package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class SumKContinuosSubarrayFrequencyTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Structural Edge Cases (Null, Empty)
                Arguments.of(null, 5, 0),
                Arguments.of(new int[] {}, 5, 0),
                // 2. Simple Direct Matches & Basics
                Arguments.of(new int[] { 1, 1, 1 }, 2, 2), // Subarrays: [1, 1] (idx 0-1) and [1, 1] (idx 1-2)
                Arguments.of(new int[] { 1, 2, 3 }, 3, 2), // Subarrays: [1, 2] (idx 0-1) and [3] (idx 2)
                // 3. Negative Integers (Allows sums to fluctuate up and down)
                // Target k = 0.
                // Subarrays: [1, -1] (idx 0-1), [-1, 1] (idx 1-2), and the whole array [1, -1,
                // 1, -1] (idx 0-3)...
                Arguments.of(new int[] { 1, -1, 1, -1 }, 0, 4),
                // 4. Zero Combinations Trap
                // Target k = 3. Zeros add alternative structural start/end points without
                // changing the sum.
                // Subarrays:, [0, 3], [3, 0], [0, 3, 0] -> 4 valid combinations
                Arguments.of(new int[] { 0, 3, 0 }, 3, 4),
                // 5. Large Mixed Sequences
                Arguments.of(new int[] { 3, 4, 7, 2, -3, 1, 4, 2 }, 7, 4) // , [7], [7, 2, -3, 1], [1, 4, 2]
        );
    }

    @ParameterizedTest(name = "Test {index}: Array={0}, k={1} expected count={2}")
    @MethodSource("testData")
    void testGet(int[] input, int k, int expected) {
        Assertions.assertEquals(expected, SumKContinuosSubarrayFrequency.get(input, k));
    }
}
