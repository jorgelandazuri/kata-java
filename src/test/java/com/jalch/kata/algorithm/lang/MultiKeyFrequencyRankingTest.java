package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MultiKeyFrequencyRankingTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Edge Cases (Null, Empty, and Single Element Arrays)
                Arguments.of(null, null),
                Arguments.of(new int[] {}, new int[] {}),
                Arguments.of(new int[] { 9 }, new int[] { 9 }),
                // 2. Clear Frequencies (No Tie-Breakers Needed)
                // Id 4 appears 3 times, Id 1 appears 1 time -> [4, 4, 4, 1]
                Arguments.of(new int[] { 1, 4, 4, 4 }, new int[] { 4, 4, 4, 1 }),
                // Id 7 appears 3 times, Id 2 appears 2 times, Id 9 appears 1 time -> [7, 7, 7,
                // 2, 2, 9]
                Arguments.of(new int[] { 9, 7, 2, 7, 2, 7 }, new int[] { 7, 7, 7, 2, 2, 9 }),
                // 3. Frequencies Match (Tie-Breaker Rule: Ascending ID numerical order)
                // Both 1 and 2 appear twice. Sorted by value ascending -> [1, 1, 2, 2]
                Arguments.of(new int[] { 2, 1, 2, 1 }, new int[] { 1, 1, 2, 2 }),
                // 30, 20, 10 all appear exactly once. Sorted by value ascending -> [10, 20, 30]
                Arguments.of(new int[] { 30, 20, 10 }, new int[] { 10, 20, 30 }),
                // 4. Complex Mixed Scenarios (Frequencies + Tie-Breakers + Duplicates)
                // Frequencies: 5 appears 3 times. 2 appears 2 times. 4 appears 2 times.
                // Grouping priorities:
                // - Highest frequency: 5s go first -> [5, 5, 5]
                // - Tied frequency (twice): 2 and 4. Sorted by value -> 2s go before 4s -> [2,
                // 2, 4, 4]
                // Total combined expected sequence -> [5, 5, 5, 2, 2, 4, 4]
                Arguments.of(new int[] { 4, 5, 2, 5, 4, 2, 5 }, new int[] { 5, 5, 5, 2, 2, 4, 4 }),
                // 5. Negative Integers and Extreme Bounds
                // Frequencies: -5 appears 2 times. -10 appears 2 times.
                // Both tied with 2 occurrences. Value sorting ascending: -10 is smaller than -5
                // -> [-10, -10, -5, -5]
                Arguments.of(new int[] { -5, -10, -5, -10 }, new int[] { -10, -10, -5, -5 }));
    }

    @ParameterizedTest(name = "Test {index}: Array={0} expected sorted output={1}")
    @MethodSource("testData")
    void testGroupAndSortByFrequency(int[] input, int[] expected) {
        Assertions.assertArrayEquals(expected, MultiKeyFrequencyRanking.groupAndSortByFrequency(input));
    }
}
