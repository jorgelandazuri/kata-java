package com.jalch.kata.algorithm.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class DistinctWindowKSubstringTrackerTest {

    static Stream<Arguments> testData() {
        return Stream.of(
                // 1. Edge Cases (Null, Empty, Invalid K, or String too short)
                Arguments.of(null, 3, 0),
                Arguments.of("", 3, 0),
                Arguments.of("abc", 0, 0),
                Arguments.of("abc", -1, 0),
                Arguments.of("ab", 3, 0), // String length < k
                // 2. Exact Matches & Basic Unique Tracking
                Arguments.of("abc", 3, 1), // Entire string is unique -> "abc"
                Arguments.of("abcdef", 3, 4), // Substrings: "abc", "bcd", "cde", "def"
                Arguments.of("aaaa", 1, 1), // k=1, duplicate blocks compress to single unique substring "a"
                // 3. Duplicate Traps inside the Sliding Window
                Arguments.of("abac", 3, 1), // "aba" has duplicates. Only "bac" is valid -> 1
                Arguments.of("abcabc", 3, 3), // Substrings: "abc", "bca", "cab". Then repeats are ignored -> 3
                // 4. Overlapping vs De-duplicated Global Tracking
                // String has 7 contiguous windows of length 4, all internally distinct:
                // "abcd", "bcde", "cdef", "defa", "efab", "fabc", and a duplicate "abcd".
                // The global Set deduplicates the second "abcd", leaving exactly 6 unique
                // substrings.
                Arguments.of("abcdefabcd", 4, 6),
                // 5. Large Mixed Stream
                // Valid 4-character distinct substrings:
                // "abcd" (idx 0), "bcde" (idx 1), "cdef" (idx 2) -> 3
                Arguments.of("abcdefg", 4, 4));
    }

    @ParameterizedTest(name = "Test {index}: String=''{0}'', k={1} expected count={2}")
    @MethodSource("testData")
    void testFind(String input, int k, int expected) {
        Assertions.assertEquals(expected, DistinctWindowKSubstringTracker.find(input, k));
    }
}
