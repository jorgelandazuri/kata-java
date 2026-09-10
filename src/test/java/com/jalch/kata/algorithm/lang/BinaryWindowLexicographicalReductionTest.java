package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BinaryWindowLexicographicalReductionTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Structural Edge Cases (Null, Empty, Blank, and K out of reach)
                Arguments.of(null, 3, 2, 0),
                Arguments.of("", 3, 2, 0),
                Arguments.of("   ", 3, 2, 0),
                Arguments.of("101", 5, 1, 0), // Window size k (5) is larger than string length (3)
                // 2. High Density / No-Swap Requirements
                Arguments.of("11111", 3, 0, 3), // k=3, no swaps needed -> window "111" has 3 ones
                Arguments.of("00000", 3, 2, 0), // FIX: Capped at 0 because zero total ones exist globally
                // 3. Exact Window Alignment (With vs Without Swaps)
                // String: "10110", k=4, maxSwaps=1
                // Window indices 0-3: "1011" -> 1 zero inside, 0 ones outside to swap ->
                // returns 3
                Arguments.of("10110", 4, 1, 3),
                // 4. Overlapping Sliding Window Selections
                // String: "010101", k=4, maxSwaps=1
                // Window "1010" (idx 1-4) -> 2 zeros inside, 1 one outside ("1") -> swaps 1
                // zero -> returns 3
                Arguments.of("010101", 4, 1, 3),
                Arguments.of("010101", 4, 2, 3), // FIX: Capped at 3 because only three 1s exist in the entire string
                // 5. Abundant Swaps vs Real External Material Caps
                Arguments.of("00100", 3, 10, 1), // FIX: Capped at 1 because only one 1 exists globally to utilize
                // 6. Advanced Edge Cases (Exact String Length, Zero Swaps allowed, Global
                // Shortages)
                Arguments.of("10101", 5, 1, 3), // String length exactly equals k. Outer ones is 0 -> no swaps possible,
                                                // returns 3
                Arguments.of("01110", 3, 0, 3), // maxSwaps is 0 but an existing perfect block of 1s of size k exists ->
                                                // returns 3
                Arguments.of("10000", 3, 2, 1) // High maxSwaps token allowance but only one total 1 exists globally ->
                                               // returns 1
        );
    }

    @ParameterizedTest(name = "Test {index}: String=''{0}'', k={1}, maxSwaps={2} expected max substring k length with ones={3}")
    @MethodSource("testData")
    void testFindMax(String str, int k, int maxSwaps, int expected) {
        Assertions.assertEquals(expected, BinaryWindowLexicographicalReduction.findMax(str, k, maxSwaps));
    }
}
