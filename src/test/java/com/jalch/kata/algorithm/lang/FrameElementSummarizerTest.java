package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class FrameElementSummarizerTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Edge Cases (Null, Empty, and Tiny Arrays)
                Arguments.of(null, 0),
                Arguments.of(new int[] {}, 0),
                // Idx 0 (value 7) is added twice -> 7 + 7 = 14
                Arguments.of(new int[] { 7 }, 14),
                // Idx 0 (value 5) added twice -> 5 + 5 = 10
                Arguments.of(new int[] { 5, 10 }, 10),
                // 2. Multiples of 3 Only (Indices 3, 6)
                // Idx 0 (1+1=2), Idx 3 (value 4) -> 2 + 4 = 6
                Arguments.of(new int[] { 1, 2, 3, 4 }, 6),
                // 3. Multiples of 5 Only (Index 5)
                // Idx 0 (1+1=2), Idx 3 (value 4), Idx 5 (value 6) -> 2 + 4 + 6 = 12
                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6 }, 12),
                // 4. Combined Multiples of 3 and 5 (Indices 3, 5, 6)
                // Idx 0 (1+1=2), Idx 3 (value 4), Idx 5 (value 6), Idx 6 (value 7) -> 2 + 4 + 6
                // + 7 = 19
                Arguments.of(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 19),
                // 5. The Multiples of 15 Trap (Index 15 - Divisible by BOTH 3 and 5)
                // Array size 16 (Indices 0 to 15). All elements are 1s.
                // Valid indices:
                // - Idx 0 (added twice) -> 2
                // - Idx 3, 6, 9, 12 (x1) -> 4
                // - Idx 5, 10 (x1) -> 2
                // - Idx 15 (added twice) -> 2
                // Total expected sum -> 2 + 4 + 2 + 2 = 10
                Arguments.of(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 10));
    }

    @ParameterizedTest(name = "Test {index}: Array={0} expected sum={1}")
    @MethodSource("testData")
    void testSum(int[] input, int expected) {
        Assertions.assertEquals(expected, FrameElementSummarizer.sum(input));
    }
}
